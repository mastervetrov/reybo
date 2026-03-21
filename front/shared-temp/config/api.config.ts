import {Order} from "@/features/crm/orders/types/order.types";
import {Transaction} from "@/features/crm/finance/transactions/types/transaction.types";

export const API_CONFIG = {
    BASE_URL: process.env.NEXT_PUBLIC_API_URL,
    TIMEOUT: 10000,
    VERSION: 'v1',
    ENDPOINTS: {
        AUTH: {
            LOGIN: '/auth/login',
            REGISTER: '/auth/register',
            REFRESH: '/auth/refresh',
            LOGOUT: '/auth/logout',
            CAPTCHA: '/auth/captcha',
        },
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
