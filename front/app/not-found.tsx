import Link from 'next/link';
import styles from '@/shared-temp/ui/not-found/not-found.module.css';

export default function NotFound() {
    return (
        <body>
            <div className={styles.container}>
                <h1 className={styles.title}>404</h1>
                <h2 className={styles.subtitle}>Страница не найдена</h2>
                <p className={styles.text}>
                    Кажется, вы забрели не туда...
                    Такой страницы не существует.
                </p>
                <Link href="/" className={styles.link}>
                    Вернуться на главную
                </Link>
            </div>
        </body>
    );
}