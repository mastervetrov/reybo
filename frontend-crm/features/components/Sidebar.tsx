import { Logo } from '@/features/components/sidebar/Logo';
import { NavMenu } from '@/features/components/sidebar/NavMenu';
import styles from '@/features/components/Sidebar.module.css';

export const Sidebar = () => {
    return (
        <aside className={styles.sidebar}>
            <Logo />
            <NavMenu />
        </aside>
    );
};