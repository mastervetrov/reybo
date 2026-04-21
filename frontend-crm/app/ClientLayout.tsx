'use client';

import { Sidebar } from "@/features/components/Sidebar";
import { Header } from "@/features/components/Header";
import styles from "@/app/CRMLayout.module.css";

export default function ClientLayout({
    children,
}: {
    children: React.ReactNode;
}) {
    return (
        <div className={styles.layout}>
            <Sidebar />
            <div className={styles.main}>
                <Header />
                <main className={styles.content}>
                    {children}
                </main>
            </div>
        </div>
    );
}