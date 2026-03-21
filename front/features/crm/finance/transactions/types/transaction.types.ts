import { BaseEntity } from '@/features/crm/shared-temp/types/BaseEntity';

export interface Transaction extends BaseEntity {
    id: string;

    createdAt: Date;
    editedAt: Date;
    deletedAt: Date;

    type: TransactionType;
    category: string;
    description: string;

    recipientId: string;
    senderId: string;

    operationDate: string;

    status: TransactionStatus;
    parentId: string;
    cashId: string;
    documentId: string;
    title: string;
    hardCashIsAllow: boolean;
    hardCashSum: number;
    nonCashIsAllow: boolean;
    nonCashSum: number;
    authorId: string;
    editorId: string;
    removerId: string;

    currency: string;

}

type TransactionType = 'income' | 'expense' | 'transfer' | 'refund' | 'write-off';
type TransactionStatus = 'draft' | 'pending' | 'completed' | 'cancelled' | 'failed';