import { api } from '@/shared-temp/core/lib/api/api';
import {
    CaptchaResponse,
    LoginRequest,
    LoginResponse,
    RefreshTokenResponse,
    RegisterRequest,
    RegisterResponse
} from '../types/auth.types';
import {API_CONFIG} from "@/shared-temp/config/api.config";

export class AuthApi {
    async register(data: RegisterRequest): Promise<RegisterResponse> {
        return api.post<RegisterResponse>(API_CONFIG.ENDPOINTS.AUTH.REGISTER, data);
    }

    async login(data: LoginRequest): Promise<LoginResponse> {
        return api.post<LoginResponse>(API_CONFIG.ENDPOINTS.AUTH.LOGIN, data);
    }

    async logout(): Promise<void> {
        return api.post(API_CONFIG.ENDPOINTS.AUTH.LOGOUT, {});
    }

    async refreshToken(): Promise<RefreshTokenResponse> {
        return api.post<RefreshTokenResponse>(API_CONFIG.ENDPOINTS.AUTH.REFRESH, {});
    }

    async getCaptchaImage(): Promise<string> {
        return api.getText(API_CONFIG.ENDPOINTS.AUTH.CAPTCHA);
    }

}

export const authApi = new AuthApi();