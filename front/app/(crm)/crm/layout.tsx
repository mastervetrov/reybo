import {Sidebar} from "@/features/crm/components/Sidebar";
import {Header} from "@/features/crm/components/Header"
import styles from "@/app/(crm)/crm/CRMLayout.module.css"
import {QueryClientProvider} from "@tanstack/react-query";
import {queryClient} from "@/shared-new/core/api/queryClient"
export default function CRMLayout({
                                                 children,
                                             }: {
    children: React.ReactNode;
}) {

    return (
        <>
            <head>
            </head>
            <body>
            <QueryClientProvider client={queryClient}>
                <div className={styles.layout}>
                    <Sidebar />
                    <div className={styles.main}>
                        <Header />
                        <main className={styles.content}>
                            {children}
                        </main>
                    </div>
                </div>
            </QueryClientProvider>

            </body>
        </>
    );

}