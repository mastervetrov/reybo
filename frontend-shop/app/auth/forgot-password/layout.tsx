import {Metadata} from "next";

export const metadata: Metadata = {
    title: 'Восстановление доступа к интернет-магазину',
    description: 'Восстановление доступа к интернет-магазину',
};

export default function ForgotPasswordLayout({
                                        children,
                                    }: {
    children: React.ReactNode;
}) {
    return (
        <body>{children}</body>
    );

}