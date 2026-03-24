import styles from '@/app/auth/register/Register.module.css'
import Link from "next/link";
import Image from "next/image";

export default function RegisterPage() {

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
                <form>

                    <input
                        type="text"
                        name="firstName"
                        placeholder="Имя"
                    />

                    <input
                        type="text"
                        name="lastName"
                        placeholder="Фамилия"
                    />

                    <input
                        type="email"
                        name="email"
                        placeholder="Email"
                    />

                    <input
                        type="password"
                        name="password1"
                        placeholder="Придумайте пароль"
                    />

                    <input
                        type="password"
                        name="password2"
                        placeholder="Подтвердите пароль"
                    />

                    {/* <CaptchaBlock/> */}

                    <div className={styles.buttonsContainer}>
                        <div className={styles.buttonEnterContainer}>
                            <button type="submit">
                                Зарегистрироваться
                            </button>
                        </div>

                        <div className={styles.accountIsExistLink}>
                            <Link href="/auth/login">
                                Уже есть аккаунт? Войти
                            </Link>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}