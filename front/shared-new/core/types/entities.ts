export interface Order {
    id: string;
    customerId: number;
    total: number;
    status: 'pending' | 'completed';
    createdAt: string;
}

export interface Transaction {
    id: string;
    amount: number;
    currency: string;
    date: string;
}

export interface Sale {
    id: string;
    productId: number;
    quantity: number;
    price: number;
}