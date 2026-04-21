import { createApi } from './factory';
import type { Order, Transaction, Sale } from '@/shared-new/core/types/entities';
import axios from "axios";

const apiClient = axios.create({
    baseURL: '/api/v1',
    headers: { 'Content-Type': 'application/json' },
});

export const orderApi = createApi<Order>('orders', apiClient);
export const transactionApi = createApi<Transaction>('transactions', apiClient);
export const saleApi = createApi<Sale>('sales', apiClient);