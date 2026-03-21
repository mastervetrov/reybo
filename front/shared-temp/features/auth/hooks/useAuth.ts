'use client'

import { TokenService } from '@/shared-temp/core/lib/auth/tokenService'
import { useRouter } from "next/navigation";
import { authApi } from "@/shared-temp/features/auth/services/AuthApi";
import {
    LoginRequest,
    LoginResponse,
    RefreshTokenResponse,
    RegisterRequest,
    RegisterResponse
} from "@/shared-temp/features/auth/types/auth.types";
import {useApi} from "@/shared-temp/core/hooks/useApi";
import {useState} from "react";
import {AccountService} from "@/shared-temp/core/lib/account/accountService";
import {Account} from "@/shared-temp/features/account/types/account.types";

export const useAuth = () => {
    const router = useRouter();
    const registerApi = useApi<RegisterResponse>();
    const loginApi = useApi<LoginResponse>();
    const refreshApi = useApi<RefreshTokenResponse>();
    const logoutApi = useApi();
    const captchaApi = useApi<string>();

    const [captchaImage, setCaptchaImage] = useState<string | null>(null);
    const [captchaLoading, setCaptchaLoading] = useState(false);
    const [captchaError, setCaptchaError] = useState<string | null>(null);

    const register = async (data: RegisterRequest) => {
        const result = await registerApi.execute(
            () => authApi.register(data),
            (response) => {
                TokenService.setAccessToken(response.accessToken);
                const partialAccount: Partial<Account> = {
                    firstName: response.firstName,
                    email: response.email,
                }
                AccountService.setPartialAccount(partialAccount);
                router.push("/");
            }
        )
        return result;
    }

    const login = async (data: LoginRequest) => {
        const result = await loginApi.execute(
            () => authApi.login(data),
            (response) => {
                TokenService.setAccessToken(response.accessToken);
                const partialAccount: Partial<Account> = {
                    firstName: response.firstName,
                    email: response.email,
                }
                AccountService.setPartialAccount(partialAccount);
                router.push("/");
            }
        )
        return result;
    }

    const refreshToken = async () => {
        if (!TokenService.canRefresh()) {
            return;
        }
        const result = await refreshApi.execute(
            () => authApi.refreshToken(),
            (response) => {
                TokenService.setAccessToken(response.accessToken);
            }
        )
        return result;
    }


    const logout = async () => {
        const result = await logoutApi.execute(
            () => authApi.logout(),
            () => {
                TokenService.removeAccessToken();
                router.push("/login");
            }
        )
    }

    // 👇 МЕТОД ДЛЯ КАПЧИ
    const getCaptcha = async () => {
        setCaptchaLoading(true);
        setCaptchaError(null);

        const result = await captchaApi.execute(
            () => authApi.getCaptchaImage(),
            (response) => {
                setCaptchaImage(response);
            }
        )
        if (!result.success) {
            setCaptchaError(result.error || 'Failed to load captcha');
        }

        return result;
    };

    const forgotPassword = async ({ email, captchaCode }:{email:string, captchaCode: string}) => {
    //
    }

    return {
        register,
        login,
        getCaptcha,
        refreshToken,
        logout,
        forgotPassword,
        isLoading: loginApi.isLoading || registerApi.isLoading || logoutApi.isLoading,
        error: loginApi.error || registerApi.error || logoutApi.error
    };
}