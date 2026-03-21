import { TokenService } from "@/shared-temp/core/lib/auth/tokenService";

interface RefreshResponse {
    accessToken: string;
}

interface QueueItem {
    resolve: (value: string) => void;
    reject: (reason?: unknown) => void;
}

export class RefreshHandler {
    private static instance: RefreshHandler;
    private isRefreshing = false;
    private failedQueue: QueueItem[] = [];

    private constructor() {}

    static getInstance(): RefreshHandler {
        if (!RefreshHandler.instance) {
            RefreshHandler.instance = new RefreshHandler();
        }
        return RefreshHandler.instance;
    }

    async handleRefresh(): Promise<string> {
        if (this.isRefreshing) {
            return new Promise((resolve, reject) => {
                this.failedQueue.push({ resolve, reject });
            });
        }

        this.isRefreshing = true;

        try {
            const response = await fetch('/auth/refresh', {
                method: 'POST',
                credentials: 'include',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error('Refresh failed');
            }

            const data: RefreshResponse = await response.json();

            TokenService.setAccessToken(data.accessToken);

            this.failedQueue.forEach(({ resolve }) => resolve(data.accessToken));
            this.failedQueue = [];

            return data.accessToken;
        } catch (error) {
            TokenService.removeAccessToken();
            this.failedQueue.forEach(({ reject }) => reject(error));
            this.failedQueue = [];

            if (typeof window !== 'undefined') {
                window.location.href = '/login';
            }

            throw error;
        } finally {
            this.isRefreshing = false;
        }
    }

    reset() {
        this.isRefreshing = false;
        this.failedQueue = [];
    }
}

export const refreshHandler = RefreshHandler.getInstance();