'use client';

import { useState } from 'react';
import {
    flexRender,
    getCoreRowModel,
    useReactTable,
    ColumnDef,
} from '@tanstack/react-table';

interface DataTableProps<T extends object> {  // 👈 T extends object
    data: T[];
    columns: ColumnDef<T>[];
    onRowClick?: (row: T) => void;
}

export function DataTable<T extends object>({
                                                data,
                                                columns,
                                                onRowClick
                                            }: DataTableProps<T>) {
    const [selectedRowId, setSelectedRowId] = useState<string | null>(null);

    const table = useReactTable({
        data,
        columns,
        getCoreRowModel: getCoreRowModel(),
    });

    const handleRowClick = (row: T, rowId: string) => {
        setSelectedRowId(rowId);
        onRowClick?.(row);
    };

    return (
        <div className="overflow-hidden rounded-xl border border-gray-200 bg-white shadow-sm">
            <table className="min-w-full divide-y divide-gray-200">
                <thead className="bg-gray-50">
                {table.getHeaderGroups().map(headerGroup => (
                    <tr key={headerGroup.id}>
                        {headerGroup.headers.map(header => (
                            <th
                                key={header.id}
                                className="px-6 py-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                            >
                                {flexRender(header.column.columnDef.header, header.getContext())}
                            </th>
                        ))}
                    </tr>
                ))}
                </thead>
                <tbody className="bg-white divide-y divide-gray-200">
                {table.getRowModel().rows.map(row => (
                    <tr
                        key={row.id}
                        onClick={() => handleRowClick(row.original, row.id)}
                        className={`
                                cursor-pointer transition-colors duration-150
                                ${selectedRowId === row.id
                            ? 'bg-blue-50 hover:bg-blue-100'
                            : 'hover:bg-gray-50'
                        }
                            `}
                    >
                        {row.getVisibleCells().map(cell => (
                            <td key={cell.id} className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                                {flexRender(cell.column.columnDef.cell, cell.getContext())}
                            </td>
                        ))}
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}