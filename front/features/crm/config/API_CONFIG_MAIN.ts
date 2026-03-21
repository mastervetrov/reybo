import {Transaction} from "@/features/crm/finance/transactions/types/transaction.types";
import {Order} from "@/features/crm/orders/types/order.types";

export const API_CONFIG_MAIN = {
    BASE_URL: process.env.NEXT_PUBLIC_API_URL,
    TIMEOUT: 10000,
    VERSION: 'v1',
    ENDPOINTS: {
        ORDERS: {
            BASE: '/crm/orders',
            BY_ID: (id: string) => `/crm/orders/${id}`,
        },
        TRANSACTIONS: {
            BASE: '/crm/transactions',
            BY_ID: (id: string) => `/crm/transactions/${id}`,
        }
    }
} as const;

export interface EntityMap {
    ORDERS: Order
    TRANSACTIONS: Transaction
}