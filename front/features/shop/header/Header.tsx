'use client'

import Link from 'next/link';
import Image from 'next/image';
import styles from './Header.module.css';
import {ProfileNav} from "@/features/shop/header/ProfileNav";

export default function Header() {

    return (
        <header className={styles.header}>
            <div className={styles.headerContainer}>

                {/* Верхняя часть */}
                <div className={styles.headerTopPart}>
                    <div className={styles.headerTopPartLeftLink}>
                        <Link href="#">г. Томск</Link>
                    </div>
                    <div className={styles.headerTopPartRightLink}>
                        <Link href="#">О магазине</Link>
                        <Link href="#">Гарантия и возврат</Link>
                        <Link href="#">Доставка</Link>
                        <Link href="#">Положение об обработке ПД</Link>
                        <Link href="#">Вакансии</Link>
                    </div>
                </div>

                {/* Средняя часть */}
                <div className={styles.headerMiddlePart}>

                    {/* Логотип */}
                    <div className={styles.logoWrap}>
                        <Link href="/public">
                            <Image
                                src="/header/reybo-logo.png"
                                alt="Main page Reybo"
                                width={640}
                                height={640}
                                style={{ width: 'auto', height: '0.1vw' }}
                                priority
                            />
                        </Link>
                    </div>

                    {/* Поиск */}
                    <div className={styles.searchFormWrap}>
                        <form action="/shop/search">
                            <div className={styles.inputContainer}>
                                <input
                                    type="text"
                                    placeholder="Введите текст"
                                    className={styles.searchInput}
                                />
                                <button type="submit" className={styles.searchButton}>
                                    Поиск
                                </button>
                            </div>
                        </form>
                    </div>

                    {/* Навигация */}
                    <div className={styles.navigationWrap}>
                        <nav>
                            <div className={styles.linksNavGroup}>

                                {/* Профиль */}
                                <ProfileNav />

                                {/* Заказы */}
                                <div className={styles.linkNav}>
                                    <Link href="/shop/orders" className={styles.navLink}>
                                        <Image
                                            src="/header/order-icon.png"
                                            alt="order-icon"
                                            width={24}
                                            height={24}
                                        />
                                        <span>Заказы</span>
                                    </Link>
                                </div>

                                {/* Избранное */}
                                <div className={styles.linkNav}>
                                    <Link href="#" className={styles.navLink}>
                                        <Image
                                            src="/header/favorite-icon.png"
                                            alt="favorite-icon"
                                            width={24}
                                            height={24}
                                        />
                                        <span>Избранное</span>
                                    </Link>
                                </div>

                                {/* Корзина */}
                                <div className={styles.linkNav}>
                                    <Link href="#" className={styles.navLink}>
                                        <Image
                                            src="/header/shopping-cart-icon.png"
                                            alt="shopping-cart-icon"
                                            width={24}
                                            height={24}
                                        />
                                        <span>Корзина</span>
                                    </Link>
                                </div>

                                {/* Меню */}
                                <div className={styles.menuButton}>
                                    <Link href="#" id="menu" className={styles.icon}>
                                        ☰
                                    </Link>
                                </div>

                            </div>
                        </nav>
                    </div>
                </div>

                {/* Нижняя часть */}
                <div className={styles.headerLowPart}>
                    <div className={styles.horizontalMenu}>
                        <Link href="#">Товары на расхват</Link>
                        <Link href="#">Для iPhone</Link>
                        <Link href="#">Для Android</Link>
                        <Link href="#">Дисплей</Link>
                        <Link href="#">Аккумуляторы</Link>
                        <Link href="#">Снятые запчасти</Link>
                    </div>
                </div>

            </div>
        </header>
    );
}