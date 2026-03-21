import type { Metadata } from 'next';
import './globals.css';
import {LoadAccount} from "@/shared-temp/features/account/components/LoadAccount";

export const metadata: Metadata = {
    title: 'Запчасти для телефонов',
    description: 'Магазин запчастей для телефонов',
};



export default function RootLayout({
                                       children,
                                   }: {

    children: React.ReactNode;
}) {

    return (
        <html lang="ru">
            <LoadAccount>
                {children}
            </LoadAccount>
        </html>
    );
}