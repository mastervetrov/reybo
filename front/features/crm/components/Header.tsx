'use client';

import { usePathname } from 'next/navigation';
import { menuConfig } from '@/features/crm/config/sidebar/nav-menu/menuConfig';
import styles from './Header.module.css';

export const Header = () => {
    const pathname = usePathname();

    const findMenuItemByPath = (items: typeof menuConfig, currentPath: string): string | null => {
        for (const item of items) {
            if (item.path === currentPath) {
                return item.label;
            }
            if (item.subItems) {
                const subItemLabel = findMenuItemByPath(item.subItems, currentPath);
                if (subItemLabel) return subItemLabel;
            }
        }
        return null;
    };

    const currentTitle = findMenuItemByPath(menuConfig, pathname) || 'CRM';

    return (
        <header className={styles.header}>
            <h1 className={styles.title}>{currentTitle}</h1>
        </header>
    );
};