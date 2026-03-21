import Link from 'next/link';
import styles from './Footer.module.css';

export default function Footer() {
    return (
        <div className={styles.footerContainer}>
            {/* Левая колонка - Каталог */}
            <div className={styles.leftBlockFooter}>
                <div className={styles.headerBlockFooter}>Каталог</div>
                <div className={styles.linkFooter}><Link href="#">Ремонт телефонов</Link></div>
                <div className={styles.linkFooter}><Link href="#">По дилерским ценам</Link></div>
                <div className={styles.linkFooter}><Link href="#">Товары со скидкой</Link></div>
                <div className={styles.linkFooter}><Link href="#">Новинки</Link></div>
                <div className={styles.linkFooter}><Link href="#">Предзаказ</Link></div>
                <div className={styles.linkFooter}><Link href="#">Прайс</Link></div>
                <div className={styles.linkFooter}><Link href="#">Прайс дилер</Link></div>
            </div>

            {/* Левая центральная колонка - Помощь */}
            <div className={styles.preLeftBlockFooter}>
                <div className={styles.headerBlockFooter}>Помощь</div>
                <div className={styles.linkFooter}><Link href="#">Оплата</Link></div>
                <div className={styles.linkFooter}><Link href="#">Доставка</Link></div>
                <div className={styles.linkFooter}><Link href="#">Гарантия</Link></div>
                <div className={styles.linkFooter}><Link href="#">Система скидок</Link></div>
                <div className={styles.linkFooter}><Link href="#">Публичная оферта</Link></div>
                <div className={styles.linkFooter}><Link href="#">Как сделать заказ</Link></div>
                <div className={styles.linkFooter}><Link href="#">Вопрос/ответ</Link></div>
                <div className={styles.linkFooter}><Link href="#">Персональные данные</Link></div>
                <div className={styles.linkFooter}><Link href="#">Нашли дешевле</Link></div>
                <div className={styles.linkFooter}><Link href="#">Возврат товара</Link></div>
                <div className={styles.linkFooter}><Link href="#">Карта сайта</Link></div>
            </div>

            {/* Центральная колонка - Компания */}
            <div className={styles.middleBlockFooter}>
                <div className={styles.headerBlockFooter}>Компания</div>
                <div className={styles.linkFooter}><Link href="#">О компании</Link></div>
                <div className={styles.linkFooter}><Link href="#">Отзывы</Link></div>
                <div className={styles.linkFooter}><Link href="#">Сотрудничество</Link></div>
                <div className={styles.linkFooter}><Link href="#">Регистрация юр. лица</Link></div>
                <div className={styles.linkFooter}><Link href="#">Новости</Link></div>
                <div className={styles.linkFooter}><Link href="#">Контакты</Link></div>
            </div>

            {/* Правая центральная колонка - Поставщикам */}
            <div className={styles.preRightBlockFooter}>
                <div className={styles.headerBlockFooter}>Поставщикам и партнерам</div>
                <div className={styles.linkFooter}>
                    <span>Отправляйте ваше<br /> коммерческое предложение<br /> на почту</span>
                </div>
                <div className={styles.linkFooter}>
                    <Link href="mailto:support@reybo">support@reybo</Link>
                </div>
            </div>

            {/* Правая колонка - Соцсети */}
            <div className={styles.rightBlockFooter}>
                <div className={styles.headerBlockFooter}>Соцсети</div>
                <div className={styles.linkFooter}><Link href="#">Узнай первым о скидках</Link></div>
            </div>
        </div>
    );
}