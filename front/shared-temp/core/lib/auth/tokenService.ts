export class TokenService {

    static isAuthenticated = false;

    constructor() {
        if (typeof window !== 'undefined') {
            const token = localStorage.getItem('accessToken');
            TokenService.isAuthenticated = !!token;
        }
    }

    static setAccessToken(token: string): void {
        if (typeof window !== 'undefined') {
            localStorage.setItem('accessToken', token);
            TokenService.isAuthenticated = true;
        }
    }

    static getAccessToken(): string | null {
        if (typeof window === 'undefined') return null;
        return localStorage.getItem('accessToken');
    }

    static removeAccessToken(): void {
        if (typeof window !== 'undefined') {
            localStorage.removeItem('accessToken');
            TokenService.isAuthenticated = false;
        }
    }

    static isLoggedIn(): boolean {
        return TokenService.isAuthenticated && !!TokenService.getAccessToken();
    }

    static canRefresh(): boolean {
        return TokenService.isAuthenticated;
    }
}

export const tokenService = new TokenService();