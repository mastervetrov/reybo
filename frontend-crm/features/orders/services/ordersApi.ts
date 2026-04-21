// import {api} from "@/shared-temp-temp/core/lib/api/api";
// import type { Order } from '@/features/orders/types/order.types';
// import {API_CONFIG} from "@/shared-temp-temp/config/api.config";
//
// // Так как в этом проекте я обучаюсь, описываю, на примере этой функции её составляющие
// //export - открывает доступ к функции,
// //const - назначет переменную
// //ordersApi - имя функции
// // = {} конструкция обычной функции не принимащей параметров
// export const ordersApi = {
//     getOrders: () => api.get<Order[]>(API_CONFIG.ENDPOINTS.ORDERS.BASE),
//     getOrderById: (id: string) => api.get<Order>(API_CONFIG.ENDPOINTS.ORDERS.BY_ID(id)),
//     deleteOrder: (id: string): Promise<void> => api.delete(API_CONFIG.ENDPOINTS.ORDERS.BY_ID(id)),
//     updateOrder: (id: string, data: Partial<Order>): Promise<Order> => api.put(API_CONFIG.ENDPOINTS.ORDERS.BY_ID(id), data),
//     createOrder: (data: Omit<Order, 'id'>):Promise<Order> => api.post(API_CONFIG.ENDPOINTS.ORDERS.BASE, data),
// }