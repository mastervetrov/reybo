import type { Metadata } from 'next';

import Header from '@/shared/ui/header/Header';
import Footer from '@/shared/ui/footer/Footer';
import '@/app/(shop)/shop-global.css';

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
        <head>
            {/*<CustomHead />*/}
        </head>
        <body>
        <Header />
        <main className="body-container">
            {children}
        </main>
        <Footer />
        </body>
        </html>
    );
}