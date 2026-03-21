export interface LoginRequest {
    email: string;
    password: string;
    captchaCode: string;
}

export interface LoginResponse {
    accessToken: string;
    firstName: string;
    email: string;
}

export interface RefreshTokenResponse {
    accessToken: string;
}

export interface CaptchaResponse {
    captchaImg: string;
}

// --REGISTRATION--
export interface RegisterRequest {
    email: string;
    password1: string;
    password2: string;
    lastName: string;
    firstName: string;
    captchaCode: string;
}

export interface RegisterResponse {
    accessToken: string;
    firstName: string;
    email: string;
}
// --/REGISTRATION--
