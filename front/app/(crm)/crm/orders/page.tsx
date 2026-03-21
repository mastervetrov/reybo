'use client'

import {useRouter} from "next/navigation";
import {DataTable} from "@/features/crm/shared-temp/ui/table/DataTable";
import {ColumnDef} from "@tanstack/react-table";
import {Order} from "@/features/crm/orders/types/order.types";
import {createEntityHook} from "@/features/crm/shared-temp/hooks/createEntityHook";

export default function TransactionPage() {
    const router = useRouter();

    const useOrders = createEntityHook('ORDERS');
    const { data: orders, isLoading, error } = useOrders();

    const columns: ColumnDef<Order>[] = [
        {
            accessorKey: 'id',
            header: 'ID',
            cell: info => info.getValue(),
        },
        {
            accessorKey: 'number',
            header: 'Номер',
        },
        {
            accessorKey: 'total',
            header: 'Сумма',
        },
        {
            accessorKey: 'status',
            header: 'Статус',
        },
    ];

    if (isLoading) return <div>Загрузка...</div>;
    if (error) return <div>Ошибка: {error}</div>;

    return (
        <DataTable
            data={orders ?? []}
            columns={columns}
        />
    );
}