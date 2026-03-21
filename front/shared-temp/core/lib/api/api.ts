import {TokenService} from "@/shared-temp/core/lib/auth/tokenService";
import {authApi} from "@/shared-temp/features/auth/services/AuthApi";
import {AccountService} from "@/shared-temp/core/lib/account/accountService";
import {API_CONFIG} from "@/shared-temp/config/api.config";

class ApiService {
    private baseUrl: string;
    private apiVersion = API_CONFIG.VERSION;
    private isRefreshing = false;
    private failedQueue: Array<{
        resolve: (value: any) => void;
        reject: (reason?: any) => void;
    }> = [];

    constructor() {
        this.baseUrl = process.env.NEXT_PUBLIC_API_URL || '';
        if (!this.baseUrl) {
            throw new Error('NEXT_PUBLIC_API_URL не задан в .env');
        }
    }

    private async refreshAccessToken(): Promise<string | null> {
        if (this.isRefreshing) {
            return new Promise((resolve, reject) => {
                this.failedQueue.push({ resolve, reject });
            });
        }

        this.isRefreshing = true;

        try {
            const refreshResponse = await authApi.refreshToken();

            if (refreshResponse?.accessToken) {
                TokenService.setAccessToken(refreshResponse.accessToken);

                this.failedQueue.forEach(({ resolve }) => {
                    resolve(refreshResponse.accessToken);
                });
                this.failedQueue = [];

                return refreshResponse.accessToken;
            } else {
                throw new Error('No access token in refresh response');
            }
        } catch (error) {
            this.failedQueue.forEach(({ reject }) => {
                reject(error);
            });
            this.failedQueue = [];
            TokenService.removeAccessToken();
            throw error;
        } finally {
            this.isRefreshing = false;
        }
    }

    private async request<T>(
        endpoint: string,
        options?: RequestInit,
        responseType: 'json' | 'text' | 'blob' = 'json',
    ): Promise<T> {

        const token = TokenService.getAccessToken();
        const headers = {
            'Content-Type': 'application/json',
            ...(token ? { 'Authorization': `Bearer ${token}` } : {}),
            ...options?.headers,
        };

        let response = await fetch(`${this.baseUrl}/api/${this.apiVersion}${endpoint}`, {
            ...options,
            credentials: 'include',
            headers,
        });

        if (response.status === 401) {
            try {
                const newToken = await this.refreshAccessToken();

                if (newToken) {
                    const newHeaders = {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${newToken}`,
                        ...options?.headers,
                    };

                    response = await fetch(`${this.baseUrl}/api/${this.apiVersion}${endpoint}`, {
                        ...options,
                        credentials: 'include',
                        headers: newHeaders,
                    });

                    if ((response.status === 401) || (response.status === 403)) {
                        TokenService.removeAccessToken();
                        AccountService.removeAccount();
                        if (typeof window !== 'undefined') {
                            window.location.href = '/login';
                        }
                        throw new Error('Refresh token expired');
                    }
                } else {
                    throw new Error('Failed to refresh token');
                }
            } catch (refreshError) {
                console.error('Refresh failed:', refreshError);
                TokenService.removeAccessToken();
                AccountService.removeAccount();
                if (typeof window !== 'undefined') {
                    window.location.href = '/login';
                }
                throw new Error('Session expired');
            }
        }

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        if (responseType === 'text') {
            return await response.text() as T;
        }
        if (responseType === 'blob') {
            return await response.blob() as T;
        }
        return await response.json() as T;
    }

    async get<T>(endpoint: string, options?: RequestInit): Promise<T> {
        return this.request<T>(endpoint, { ...options, method: 'GET' });
    }

    async getText(endpoint: string, options?: RequestInit): Promise<string> {
        return this.request<string>(endpoint, { ...options, method: 'GET' }, 'text');
    }

    async getBlob(endpoint: string, options?: RequestInit): Promise<Blob> {
        return this.request<Blob>(endpoint, { ...options, method: 'GET' }, 'blob');
    }

    async post<T>(endpoint: string, data?: unknown, options?: RequestInit): Promise<T> {
        return this.request<T>(endpoint, {
            ...options,
            method: 'POST',
            body: data ? JSON.stringify(data) : undefined,
        });
    }

    async put<T>(endpoint: string, data?: unknown, options?: RequestInit): Promise<T> {
        return this.request<T>(endpoint, {
            ...options,
            method: 'PUT',
            body: data ? JSON.stringify(data) : undefined,
        });
    }

    async delete<T>(endpoint: string, options?: RequestInit): Promise<T> {
        return this.request<T>(endpoint, { ...options, method: 'DELETE' });
    }
}

export const api = new ApiService();