import { menuConfig } from '@/features/config/sidebar/nav-menu/menuConfig';
import { MenuItem } from '@/features/components/sidebar/nav-menu/MenuItem';
import styles from '@/features/components/sidebar/nav-menu/MenuItem.module.css';

export const NavMenu = () => {
    return (
        <nav className={styles.nav}>
            {menuConfig.map(item => (
                <MenuItem key={item.id} item={item} />
            ))}
        </nav>
    );
};