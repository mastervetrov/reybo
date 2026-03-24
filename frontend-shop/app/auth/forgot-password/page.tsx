import Image from 'next/image';
import Link from 'next/link';
import styles from '@/app/auth/forgot-password/ForgotPassword.module.css';

export default function ForgotPasswordPage() {

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
                <form>
                    <input
                        type="email"
                        placeholder="Email"
                    />

                    {/* <CaptchaBlock/> */}

                    <div className={styles.buttonsContainer}>
                        <div className={styles.buttonEnterContainer}>
                            <button type="submit">
                                Отправить запрос на восстановление пароля
                            </button>
                        </div>

                        <div className={styles.accountIsExistLink}>
                            <Link href="/auth/login">
                                Вернуться
                            </Link>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}