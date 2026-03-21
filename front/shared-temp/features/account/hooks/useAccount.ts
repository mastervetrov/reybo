 'use client'

import { useApi } from "@/shared-temp/core/hooks/useApi";
import { Account, UpdateAccountRequest } from "@/shared-temp/features/account/types/account.types";
import { accountApi } from "@/shared-temp/features/account/services/accountApi";
import { AccountService } from "@/shared-temp/core/lib/account/accountService";
import { useState } from "react";
 import {TokenService} from "@/shared-temp/core/lib/auth/tokenService";

export const useAccount = () => {
    const getAccountApi = useApi<Account>();
    const updateAccountApi = useApi<Account>();

    const [account, setAccount] = useState<Account | null>(
        AccountService.getAccount()
    );

    const loadAccount = async () => {

        if (!TokenService.isLoggedIn()) {
            return;
        }
        const result = await getAccountApi.execute(
            () => accountApi.getCurrentAccount(),
            (response) => {
                AccountService.setAccount(response);
                setAccount(response); // 👈 ВАЖНО!
            }
        );
        return result;
    }

    const updateAccount = async (data: UpdateAccountRequest) => {

        if (!TokenService.isLoggedIn()) {
            return;
        }

        const result = await updateAccountApi.execute(
            () => accountApi.updateAccount(data), // 👈 передаем data
            (response) => {
                AccountService.setAccount(response); // 👈 set, не update!
                setAccount(response);
            }
        );
        return result;
    }

    const clearAccount = () => {
        setAccount(null);
        AccountService.removeAccount();
    };

    return {
        account,
        loadAccount,
        updateAccount,
        clearAccount,
        isLoading: getAccountApi.isLoading || updateAccountApi.isLoading,
        error: getAccountApi.error || updateAccountApi.error
    };
};