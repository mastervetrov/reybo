import { useTableHooks } from './useTableHooks';
import { baseCrmApi } from '@/features/crm/shared-temp/api/baseCrmApi';
import { EntityMap } from '@/features/crm/config/API_CONFIG_MAIN';

type EntityKeys = keyof EntityMap; // 'ORDERS' | 'TRANSACTIONS' | 'USERS' | и т.д.

// Фабрика хуков для сущностей!
export const createEntityHook = <K extends EntityKeys>(endpoint: K) => {
    return () => {
        return useTableHooks<EntityMap[K]>({
            fetchFn: () => baseCrmApi.getAll(endpoint),
            fetchById: (id) => baseCrmApi.getOne(endpoint, id),
            deleteFn: (id) => baseCrmApi.delete(endpoint, id),
            updateFn: (id, data) => baseCrmApi.update(endpoint, id, data),
            createFn: (data) => baseCrmApi.create(endpoint, data)
        });
    };
};