'use client';

import Link from 'next/link';
import Image from 'next/image';
import { usePathname } from 'next/navigation';
import styles from '@/features/crm/components/sidebar/nav-menu/MenuItem.module.css';
import { useState } from "react";
import { MenuItemType } from "@/features/crm/types/sidebar/nav-menu/menu.types";

interface MenuItemProps {
    item: MenuItemType;
    level?: number;
}

export const MenuItem = ({ item, level = 0 }: MenuItemProps) => {
    const pathname = usePathname();
    const isActive = pathname === item.path;
    const [isOpen, setIsOpen] = useState(false);

    if (item.component) {
        const Component = item.component;
        return <Component />;
    }

    return (
        <div onClick={() => setIsOpen(!isOpen)} className={styles.container}>
            <Link
                href={item.path}
                className={`${styles.link} ${isActive ? styles.active : ''}`}
                style={{ paddingLeft: `${level * 20 + 16}px` }}
            >
                {item.icon && (
                    <Image
                        src={item.icon}
                        alt={item.label}
                        width={20}
                        height={20}
                        className={styles.icon}
                    />
                )}
                <span className={styles.label}>{item.label}</span>
                {item.subItems && (
                    <button
                        className={styles.arrow}
                    >
                        {isOpen ? '◎' : '◦'}
                    </button>
                )}
            </Link>

            {isOpen && item.subItems && (
                <div className={styles.submenu}>
                    {item.subItems.map(subItem => (
                        <MenuItem key={subItem.id} item={subItem} level={level + 1} />
                    ))}
                </div>
            )}
        </div>
    );
};