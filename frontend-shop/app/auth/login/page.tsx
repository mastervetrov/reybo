import Image from 'next/image';
import Link from 'next/link';
import styles from '@/app/auth/login/Login.module.css';

export default function LoginPage() {

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
                    <form>
                        <input
                            type="email"
                            name="email"
                            placeholder="Email"
                        />

                        <input
                            type="password"
                            name="password"
                            placeholder="Пароль"
                        />

                        {/* <CaptchaBlock/> */}

                        <div className={styles.buttonEnterContainer}>
                            <button type="submit">
                               Войти
                            </button>
                        </div>

                        <div className={styles.buttonForgotPasswordContainer}>
                            <Link href="/auth/forgot-password">
                                Забыли пароль?
                            </Link>
                        </div>

                        <div className={styles.buttonRegistrationContainer}>
                            <Link href="/auth/register">
                                Регистрация
                            </Link>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}