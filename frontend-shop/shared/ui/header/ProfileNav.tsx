import Link from 'next/link';
import Image from 'next/image';
import styles from './Header.module.css';

export const ProfileNav = () => {

    return (
        <div className={styles.linkNav}>
            <Link href="/auth/login" className={styles.navLink}>
                <Image
                    src="/header/profile-icon.png"
                    alt="Имя"
                    width={24}
                    height={24}
                />
                <span>Войти</span>
            </Link>
        </div>
    );
};