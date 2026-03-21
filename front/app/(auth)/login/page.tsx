'use client';

import { useState } from 'react';
import Image from 'next/image';
import Link from 'next/link';
import { useAuth } from '@/shared-temp/features/auth/hooks/useAuth';
import { CaptchaBlock } from '@/shared-temp/features/auth/components/login-form/CaptchaBlock';
import styles from '@/shared-temp/features/auth/components/login-form/Login.module.css';
import { LoginRequest } from "@/shared-temp/features/auth/types/auth.types";
import {useGuestGuard} from "@/shared-temp/features/auth/hooks/useGuestGuard";

export default function LoginPage() {

    useGuestGuard();

    const { login, getCaptcha, isLoading } = useAuth();

    const [formData, setFormData] = useState<LoginRequest>({
        email: '',
        password: '',
        captchaCode: ''
    });

    const [error, setError] = useState('');

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError('');

        try {
            const result = await login(formData);

            if (!result) {
                setError('Нет ответа от сервера');
                return;
            }

            if (!result.success) {
                setError(result.error || 'Ошибка входа');
                setFormData(prev => ({ ...prev, captchaCode: '' }));
            }
        } catch (err: any) {
            console.error('Login error:', err);
            setError(err.message || 'Произошла непредвиденная ошибка');
        }
    };

    return (
        <div className={styles.enterFormContainer}>
            <div className={styles.enterForm}>
                <div className={styles.logoWrap}>
                    <Link href="/public">
                        <Image
                            src="/header/reybo-logo.png"
                            alt="Logo"
                            width={640}
                            height={640}
                            style={{ width: 'auto', height: '4vw' }}
                            priority
                        />
                    </Link>
                </div>

                <div className={styles.titleLoginContainer}>
                    <h2>Вход в личный кабинет</h2>
                </div>

                <div className={styles.formContainer}>
                    <form onSubmit={handleSubmit}>
                        {error && (
                            <div className={styles.error}>
                                {error}
                            </div>
                        )}

                        <input
                            type="email"
                            name="email"
                            placeholder="Email"
                            value={formData.email}
                            onChange={handleChange}
                            required
                            disabled={isLoading}
                        />

                        <input
                            type="password"
                            name="password"
                            placeholder="Пароль"
                            value={formData.password}
                            onChange={handleChange}
                            required
                            disabled={isLoading}
                        />

                        {/* 👇 ВОТ ТУТ ВСТАВЛЯЕМ КАПЧУ */}
                        <CaptchaBlock
                            getCaptcha={getCaptcha}
                            captchaCode={formData.captchaCode}
                            onCaptchaChange={(code) => setFormData(prev => ({ ...prev, captchaCode: code }))}
                            disabled={isLoading}
                        />

                        <div className={styles.buttonEnterContainer}>
                            <button
                                type="submit"
                                disabled={isLoading}
                            >
                                {isLoading ? 'Входим...' : 'Войти'}
                            </button>
                        </div>

                        <div className={styles.buttonForgotPasswordContainer}>
                            <Link href="/forgot-password">
                                Забыли пароль?
                            </Link>
                        </div>

                        <div className={styles.buttonRegistrationContainer}>
                            <Link href="/register">
                                Регистрация
                            </Link>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}