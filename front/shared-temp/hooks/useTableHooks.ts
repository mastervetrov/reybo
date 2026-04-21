import { useState, useEffect } from 'react';
import { useApi } from '@/shared-temp/core/hooks/useApi';

interface UseTableDataProps<T> {
    fetchFn: () => Promise<T[]>;
    fetchById?: (id: string) => Promise<T>;
    deleteFn?: (id: string) => Promise<void>;
    updateFn?: (id: string, data: Partial<T>) => Promise<T>;
    createFn?: (data: Omit<T, 'id'>) => Promise<T>;
}

export const useTableHooks = <T extends { id: string }>({
                                                           fetchFn,
                                                           fetchById,
                                                           deleteFn,
                                                           updateFn,
                                                           createFn
                                                       }: UseTableDataProps<T>) => {
    const [data, setData] = useState<T[]>([]);
    const fetchApi = useApi<T[]>();
    const fetchItemApi = useApi<T>();
    const deleteApi = useApi<void>();
    const updateApi = useApi<T>();
    const createApi = useApi<T>();

    const fetch = async () => {
        const result = await fetchApi.execute(fetchFn);
        if (result.success && result.data) {
            setData(result.data);
        }
    };

    const fetchItem = async (id: string) => {
        if (!fetchById) return;
        const result = await fetchItemApi.execute(() => fetchById(id));
        if (result.success && result.data) {
            setData(prev => prev.map(item =>
            item.id === id ? result.data! : item));
        }
        return result;
    }

    const deleteItem = async (id: string) => {
        if (!deleteFn) return;
        const result = await deleteApi.execute(() => deleteFn(id));
        if (result.success) {
            await fetch(); // перезагружаем таблицу
        }
        return result;
    };

    const updateItem = async (id: string, updates: Partial<T>) => {
        if (!updateFn) return;
        const result = await updateApi.execute(() => updateFn(id, updates));
        if (result.success && result.data) {
            setData(prev => prev.map(item =>
                item.id === id ? result.data! : item
            ));
        }
        return result;
    };

    const createItem = async (newData: Omit<T, 'id'>) => {
        if (!createFn) return;
        const result = await createApi.execute(() => createFn(newData));
        if (result.success && result.data) {
            setData(prev => [...prev, result.data!]);
        }
        return result;
    };

    useEffect(() => {
        let mounted = true;

        const fetchData = async () => {
            const result = await fetchApi.execute(fetchFn);
            if (mounted && result.success && result.data) {
                setData(result.data);
            }
        };

        fetchData();

        return () => {
            mounted = false;
        };
    }, [fetchFn]);

    return {
        data,
        fetch,
        fetchItem,
        deleteItem,
        updateItem,
        createItem,
        isLoading: fetchApi.isLoading || deleteApi.isLoading ||
            updateApi.isLoading || createApi.isLoading,
        error: fetchApi.error || deleteApi.error ||
            updateApi.error || createApi.error
    };
};