'use client';

import { useState } from 'react';
import Image from 'next/image';
import Link from 'next/link';
import { useAuth } from '@/shared-temp/features/auth/hooks/useAuth';
import { CaptchaBlock } from '@/shared-temp/features/auth/components/login-form/CaptchaBlock';
import styles from '@/shared-temp/features/auth/components/forgot/ForgotPassword.module.css';
import { useGuestGuard } from '@/shared-temp/features/auth/hooks/useGuestGuard';

export default function ForgotPasswordPage() {
    useGuestGuard();

    const { getCaptcha, forgotPassword, isLoading } = useAuth();
    const [email, setEmail] = useState('');
    const [captchaCode, setCaptchaCode] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');


    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError('');
        setSuccess('');

        if (!email) {
            setError('Введите email');
            return;
        }
    };

    return (
        <div className={styles.enterFormForgot}>
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
                <h1>Восстановление доступа</h1>
            </div>

            <div className={styles.formContainer}>
                <form onSubmit={handleSubmit}>
                    {error && (
                        <div className={styles.error}>
                            {error}
                        </div>
                    )}

                    {success && (
                        <div className={styles.success}>
                            {success}
                        </div>
                    )}

                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        disabled={isLoading}
                    />

                    <CaptchaBlock
                        getCaptcha={getCaptcha}
                        captchaCode={captchaCode}
                        onCaptchaChange={setCaptchaCode}
                        disabled={isLoading}
                    />

                    <div className={styles.buttonsContainer}>
                        <div className={styles.buttonEnterContainer}>
                            <button type="submit" disabled={isLoading}>
                                {isLoading ? 'Отправка...' : 'Отправить запрос на восстановление пароля'}
                            </button>
                        </div>

                        <div className={styles.accountIsExistLink}>
                            <Link href="/login">
                                Вернуться
                            </Link>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}