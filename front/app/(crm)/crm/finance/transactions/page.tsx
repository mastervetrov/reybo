'use client';

import { Transaction } from "@/features/crm/finance/transactions/types/transaction.types";
import { ColumnDef, createColumnHelper } from '@tanstack/react-table';
import { DataTable } from "@/features/crm/shared-temp/ui/table/DataTable";
import { useRouter } from "next/navigation";
import {baseCrmApi} from "@/features/crm/shared-temp/api/baseCrmApi";
import {useEffect, useState} from "react";

const columnHelper = createColumnHelper<Transaction>();

const columns: ColumnDef<Transaction>[] = [
    {
        accessorKey: 'id',
        header: 'ID',
        cell: info => info.getValue(),
    },
    // 👇 Добавь другие колонки по необходимости
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

export default function TransactionPage() {
    const router = useRouter();
    const [transactions, setTransactions] = useState<Transaction[]>([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        loadTransactions();
    }, [])

    const loadTransactions = async () => {
        try {
            setIsLoading(true);
            setError(null);
            const data = await baseCrmApi.getAll('TRANSACTIONS');
            setTransactions(data);
        } catch (err) {
            setError(err instanceof Error ? err.message : 'Ошибка загрузки данных');
            console.error('Ошибка загрузки транзакций:', err);
        } finally {
            setIsLoading(false);
        }
    };

    if (isLoading) {
        return <div>Загрузка...</div>;
    }

    if (error) {
        return <div>Ошибка: {error}</div>;
    }

    return (
        <DataTable
            data={transactions ?? []}
            columns={columns}
        />
    );
}