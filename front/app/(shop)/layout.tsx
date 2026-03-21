import type { Metadata } from 'next';

import Header from '@/features/shop/header/Header';
import '@/app/(shop)/shop-global.css';
import Footer from '@/features/shop/footer/Footer';

export const metadata: Metadata = {
    title: 'Запчасти для телефонов',
    description: 'Магазин запчастей для телефонов',
};

export default function RootLayout({
                                       children,
                                   }: {
    children: React.ReactNode;
}) {
    return (<>
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
        </>
    );
}