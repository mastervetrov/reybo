import axios, { AxiosInstance } from "axios";
import {Property} from "csstype";
import {Transaction} from "@/shared-new/core/types/entities";
import Order = Property.Order;
import {useMutation, useQuery, useQueryClient} from "@tanstack/react-query";

export interface BaseEntity {
    id: number | string;
}

type ApiMethods<T extends BaseEntity, ID = T['id']> = {
    getAll: (params?: Record<string, any>) => Promise<T[]>;
    getById: (id: ID) => Promise<T>;
    create: (payload: Omit<T, 'id'>) => Promise<T>;
    update: (id: ID, payload: Partial<Omit<T, 'id'>>) => Promise<T>;
    remove: (id: ID) => Promise<void>;
};

export function createApi<T = any, ID = string | number>(
    resource: string,
    instance: AxiosInstance = axios
) {
    return {
        getAll: (params?: any) => instance.get<T[]>(`/${resource}`, { params }).then(r => r.data),
        getById: (id: ID) => instance.get<T>(`/${resource}/${id}`).then(r => r.data),
        create: (payload: Omit<T, 'id'>) => instance.post<T>(`/${resource}`, payload).then(r => r.data),
        update: (id: ID, payload: Partial<Omit<T, 'id'>>) => instance.patch<T>(`/${resource}/${id}`, payload).then(r => r.data),
        remove: (id: ID) => instance.delete(`/${resource}/${id}`).then(() => undefined),
    };
}

const apiClient = axios.create({ baseURL: '/api/v1' });

apiClient.interceptors.request.use((config) => {
    const token = localStorage.getItem('access_token');
    if (token) config.headers.Authorization = `Bearer ${token}`;
    return config;
});

export const orderApi = createApi<Order>('orders', apiClient);
export const transactionApi = createApi<Transaction>('transactions', apiClient);

export const useOrders = (params?: { page?: number; status?: string }) => {
    return useQuery({
        queryKey: ['orders', params],
        queryFn: () => orderApi.getAll(params),
        // Опции TanStack Query
        staleTime: 1000 * 60 * 5,
        gcTime: 1000 * 60 * 30,
        retry: (failureCount, error: any) => {
            // Не ретраить, если 401/403 — это не временная ошибка
            if (error?.response?.status === 401) return false;
            return failureCount < 3;
        },
    });
};

export const useCreateOrder = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: (payload: Omit<Order, 'id'>) => orderApi.create(payload),
        onSuccess: (newOrder) => {
            queryClient.invalidateQueries({ queryKey: ['orders'] });
        },
    });
};