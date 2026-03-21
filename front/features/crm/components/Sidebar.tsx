import { Logo } from '@/features/crm/components/sidebar/Logo';
import { NavMenu } from '@/features/crm/components/sidebar/NavMenu';
import styles from '@/features/crm/components/Sidebar.module.css';

export const Sidebar = () => {
    return (
        <aside className={styles.sidebar}>
            <Logo />
            <NavMenu />
        </aside>
    );
};