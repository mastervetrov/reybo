'use client';

import { api } from '@/shared-temp/core/lib/api/api';
import {Account, UpdateAccountRequest} from "@/shared-temp/features/account/types/account.types";

export class AccountApi {
    async getCurrentAccount(): Promise<Account> {
        return api.get<Account>('/account/me', {});
    }

     async updateAccount(data: UpdateAccountRequest): Promise<Account> {
        return api.post<Account>('/account/me', data);
     }


}

export const accountApi = new AccountApi();