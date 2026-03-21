import { Account } from '@/shared-temp/features/account/types/account.types';

const ACCOUNT_KEY = 'account';

export class AccountService {
    static setAccount(account: Account): void {
        if (typeof window !== 'undefined') {
            localStorage.setItem(ACCOUNT_KEY, JSON.stringify(account));
        }
    }

    static setPartialAccount(partialAccount: Partial<Account>): void {
        if (typeof window !== 'undefined') {
            localStorage.setItem(ACCOUNT_KEY, JSON.stringify(partialAccount));
        }
    }

    static getAccount(): Account | null {
        if (typeof window !== 'undefined') {
            const stored = localStorage.getItem(ACCOUNT_KEY);
            return stored ? JSON.parse(stored) : null;
        }
        return null;
    }

    static updateAccount(updates: Partial<Account>): Account | null {
        const current = this.getAccount();
        if (!current) return null;

        const updated = { ...current, ...updates };
        this.setAccount(updated);
        return updated;
    }

    static removeAccount(): void {
        if (typeof window !== 'undefined') {
            localStorage.removeItem(ACCOUNT_KEY);
        }
    }

    static hasAccount(): boolean {
        return this.getAccount() !== null;
    }

    static getFullName(): string {
        const account = this.getAccount();
        return account ? `${account.firstName} ${account.lastName}` : '';
    }
}