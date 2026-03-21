'use client';

import {useAuth} from "@/shared-temp/features/auth/hooks/useAuth";
import {useState} from "react";
import {RegisterRequest} from "@/shared-temp/features/auth/types/auth.types";
import styles from '@/shared-temp/features/auth/components/register-form/Register.module.css'

import Link from "next/link";
import {CaptchaBlock} from "@/shared-temp/features/auth/components/login-form/CaptchaBlock";
import Image from "next/image";
import {useGuestGuard} from "@/shared-temp/features/auth/hooks/useGuestGuard";

export default function RegisterPage() {
    useGuestGuard();

    const { register, getCaptcha, isLoading } = useAuth();
    const [formData, setFormData] = useState<RegisterRequest>({
        email: '',
        password1: '',
        password2: '',
        lastName: '',
        firstName: '',
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

        // Простейшая валидация
        if (formData.password1 !== formData.password2) {
            setError('Пароли не совпадают');
            return;
        }

        try {
            const result = await register(formData);

            if (!result) {
                setError('Нет ответа от сервера');
                return;
            }

            if (!result.success) {
                setError(result.error || 'Ошибка регистрации');
            }
        } catch (err: any) {
            console.error('Register error:', err);
            setError(err.message || 'Произошла непредвиденная ошибка');
        }
    };

    return (
        <div className={styles.enterFormRegistration}>
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
                <h1>Регистрация</h1>
            </div>

            <div className={styles.formContainer}>
                <form onSubmit={handleSubmit}>
                    {error && (
                        <div className={styles.error}>
                            {error}
                        </div>
                    )}

                    <input
                        type="text"
                        name="firstName"
                        placeholder="Имя"
                        value={formData.firstName}
                        onChange={handleChange}
                        required
                        disabled={isLoading}
                    />

                    <input
                        type="text"
                        name="lastName"
                        placeholder="Фамилия"
                        value={formData.lastName}
                        onChange={handleChange}
                        required
                        disabled={isLoading}
                    />

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
                        name="password1"
                        placeholder="Придумайте пароль"
                        value={formData.password1}
                        onChange={handleChange}
                        required
                        disabled={isLoading}
                    />

                    <input
                        type="password"
                        name="password2"
                        placeholder="Подтвердите пароль"
                        value={formData.password2}
                        onChange={handleChange}
                        required
                        disabled={isLoading}
                    />

                    <CaptchaBlock
                        getCaptcha={getCaptcha}
                        captchaCode={formData.captchaCode}
                        onCaptchaChange={(code) => setFormData(prev => ({ ...prev, captchaCode: code }))}
                        disabled={isLoading}
                    />

                    <div className={styles.buttonsContainer}>
                        <div className={styles.buttonEnterContainer}>
                            <button type="submit" disabled={isLoading}>
                                {isLoading ? 'Регистрация...' : 'Зарегистрироваться'}
                            </button>
                        </div>

                        <div className={styles.accountIsExistLink}>
                            <Link href="/login">
                                Уже есть аккаунт? Войти
                            </Link>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}