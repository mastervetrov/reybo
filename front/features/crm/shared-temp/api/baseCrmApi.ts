import {api} from "@/shared-temp/core/lib/api/api";
import {API_CONFIG_MAIN, EntityMap} from "@/features/crm/config/API_CONFIG_MAIN";

type EndpointKey = keyof typeof API_CONFIG_MAIN.ENDPOINTS; // 'ORDERS' | 'TRANSACTIONS'

type EntityByEndpoint = {
    ORDERS: EntityMap['ORDERS']      // Order
    TRANSACTIONS: EntityMap['TRANSACTIONS'] // Transaction
}

export const baseCrmApi = {
    getAll: <K extends EndpointKey>(endpoint: K) =>
        api.get<EntityByEndpoint[K][]>(API_CONFIG_MAIN.ENDPOINTS[endpoint].BASE),

    getOne: <K extends EndpointKey>(endpoint: K, id: string) =>
        api.get<EntityByEndpoint[K]>(API_CONFIG_MAIN.ENDPOINTS[endpoint].BY_ID(id)),

    delete: <K extends EndpointKey>(endpoint: K, id: string) =>
        api.delete<void>(API_CONFIG_MAIN.ENDPOINTS[endpoint].BY_ID(id)),

    update: <K extends EndpointKey>(endpoint: K, id: string, data: Partial<EntityByEndpoint[K]>) =>
        api.put<EntityByEndpoint[K]>(API_CONFIG_MAIN.ENDPOINTS[endpoint].BY_ID(id), data),

    create: <K extends EndpointKey>(endpoint: K, data: Omit<EntityByEndpoint[K], 'id'>) =>
        api.post<EntityByEndpoint[K]>(API_CONFIG_MAIN.ENDPOINTS[endpoint].BASE, data)
}