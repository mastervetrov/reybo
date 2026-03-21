import type { Metadata } from 'next';

export const metadata: Metadata = {
    title: 'Регистрация в интернет-магазине',
    description: 'Регистрация',
};

export default function RegisterLayout({
                                       children,
                                   }: {
    children: React.ReactNode;
}) {
    return (
        <body>
            {children}
        </body>
    );
}