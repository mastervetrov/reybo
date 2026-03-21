import {
    useQuery,
    useMutation,
    useQueryClient,
    UseQueryOptions,
    UseMutationOptions,
    UseQueryResult,
    UseMutationResult,
    QueryKey,
} from '@tanstack/react-query';
import { BaseEntity } from '@/shared-new/core/api/factory';

export interface CrudHooks<T extends BaseEntity> {
    useList: (
        params?: Record<string, any>,
        options?: Omit<UseQueryOptions<T[], Error, T[], QueryKey>, 'queryKey' | 'queryFn'>
    ) => UseQueryResult<T[], Error>;

    useOne: (
        id: T['id'] | null,
        options?: Omit<UseQueryOptions<T, Error, T, QueryKey>, 'queryKey' | 'queryFn' | 'enabled'>
    ) => UseQueryResult<T, Error>;

    useCreate: (
        options?: Omit<UseMutationOptions<T, Error, Omit<T, 'id'>, unknown>, 'mutationFn'>
    ) => UseMutationResult<T, Error, Omit<T, 'id'>, unknown>;

    useUpdate: (
        options?: Omit<UseMutationOptions<T, Error, { id: T['id']; data: Partial<Omit<T, 'id'>> }, unknown>, 'mutationFn'>
    ) => UseMutationResult<T, Error, { id: T['id']; data: Partial<Omit<T, 'id'>> }, unknown>;

    useRemove: (
        options?: Omit<UseMutationOptions<void, Error, T['id'], unknown>, 'mutationFn'>
    ) => UseMutationResult<void, Error, T['id'], unknown>;
}

export interface CrudHooksConfig {
    queryKey?: string;
    invalidateKeys?: string[];
    optimisticUpdate?: boolean;
    staleTime?: number;
}

export function createCrudHooks<T extends BaseEntity>(
    resource: string,
    api: ReturnType<typeof import('@/shared-new/core/api/factory').createApi<T>>,
    config: CrudHooksConfig = {}
): CrudHooks<T> {
    const queryKey = config.queryKey || resource;
    const invalidateKeys = config.invalidateKeys || [queryKey];
    const staleTime = config.staleTime ?? 1000 * 60 * 5; // 5 min

    const defaultRetry = (failureCount: number, error: any) => {
        if (error?.response?.status === 401) return false;
        if (error?.response?.status === 403) return false;
        return failureCount < 3;
    };

    const useList: CrudHooks<T>['useList'] = (params, options) => {
        return useQuery<T[], Error, T[], QueryKey>({
            queryKey: [queryKey, 'list', params ? JSON.stringify(params) : undefined],
            queryFn: () => api.getAll(params),
            staleTime,
            gcTime: 1000 * 60 * 30,
            retry: defaultRetry,
            ...options,
        });
    };

    const useOne: CrudHooks<T>['useOne'] = (id, options) => {
        return useQuery<T, Error, T, QueryKey>({
            queryKey: [queryKey, id],
            queryFn: () => api.getById(id!),
            enabled: !!id,
            staleTime,
            retry: defaultRetry,
            ...options,
        });
    };

    const useCreate: CrudHooks<T>['useCreate'] = (options) => {
        const queryClient = useQueryClient();

        return useMutation<T, Error, Omit<T, 'id'>, unknown>({
            mutationFn: (payload) => api.create(payload),
            onSuccess: (data, variables, context, mutationOptions) => {
                invalidateKeys.forEach(key => {
                    queryClient.invalidateQueries({ queryKey: [key] });
                });

                if (config.optimisticUpdate) {
                    queryClient.setQueryData<T[]>([queryKey, 'list'], (old = []) => [...old, data]);
                }

                options?.onSuccess?.(data, variables,context,mutationOptions);
            },
            ...options,
        });
    };

    const useUpdate: CrudHooks<T>['useUpdate'] = (options) => {
        const queryClient = useQueryClient();

        return useMutation<T, Error, { id: T['id']; data: Partial<Omit<T, 'id'>> }, unknown>({
            mutationFn: (variables) => api.update(variables.id, variables.data),
            onSuccess: (data, variables, context, mutationOptions) => {
                queryClient.setQueryData<T>([queryKey, variables.id], data);

                queryClient.setQueriesData<T[]>(
                    { queryKey: [queryKey], exact: false },
                    (old) => old?.map(item => item.id === variables.id ? data : item)
                );

                invalidateKeys.forEach(key => {
                    queryClient.invalidateQueries({ queryKey: [key] });
                });

                options?.onSuccess?.(data, variables, context, mutationOptions);
            },
            ...options,
        });
    };

    const useRemove: CrudHooks<T>['useRemove'] = (options) => {
        const queryClient = useQueryClient();

        return useMutation<void, Error, T['id'], unknown>({
            mutationFn: (id) => api.remove(id),
            onSuccess: (_data, id, context, mutationOptions) => {
                queryClient.removeQueries({ queryKey: [queryKey, id] });

                queryClient.setQueriesData<T[]>(
                    { queryKey: [queryKey, 'list'] },
                    (old) => old?.filter(item => item.id !== id)
                );

                invalidateKeys.forEach(key => {
                    queryClient.invalidateQueries({ queryKey: [key] });
                });

                options?.onSuccess?.(_data, id, context, mutationOptions);
            },
            ...options,
        });
    };

    return {
        useList,
        useOne,
        useCreate,
        useUpdate,
        useRemove,
    };
}