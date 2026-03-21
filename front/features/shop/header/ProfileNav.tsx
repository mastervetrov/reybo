'use client';

import Link from 'next/link';
import Image from 'next/image';
import { useState, useLayoutEffect } from 'react';
import { useAccount } from '@/shared-temp/features/account/hooks/useAccount';
import styles from './Header.module.css';

export const ProfileNav = () => {
    const { account, isLoading } = useAccount();
    const [isMounted, setIsMounted] = useState(false);

    // useLayoutEffect + comment для линтера
    useLayoutEffect(() => {
        setIsMounted(true);
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    // Fallback до монтирования — одинаковый для SSR и клиента
    if (!isMounted) {
        return (
            <div className={styles.linkNav}>
                <div className={styles.navLink}>
                    <Image
                        src="/header/profile-icon.png"
                        alt="profile"
                        width={24}
                        height={24}
                    />
                    <span>...</span>
                </div>
            </div>
        );
    }

    if (isLoading) {
        return (
            <div className={styles.linkNav}>
                <div className={styles.navLink}>
                    <Image
                        src="/header/profile-icon.png"
                        alt="profile"
                        width={24}
                        height={24}
                    />
                    <span>...</span>
                </div>
            </div>
        );
    }

    if (!account) {
        return (
            <div className={styles.linkNav}>
                <Link href="/login" className={styles.navLink}>
                    <Image
                        src="/header/profile-icon.png"
                        alt="profile-icon"
                        width={24}
                        height={24}
                    />
                    <span>Войти</span>
                </Link>
            </div>
        );
    }

    return (
        <div className={styles.linkNav}>
            <Link href="/profile" className={styles.navLink}>
                <Image
                    src="/header/profile-icon.png"
                    alt={account.firstName}
                    width={24}
                    height={24}
                />
                <span>{account.firstName}</span>
            </Link>
        </div>
    );
};