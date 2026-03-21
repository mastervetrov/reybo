import Image from "next/image";
import styles from "@/features/crm/components/sidebar/SidebarLogo.module.css"

export const Logo = () => {

    return (
        <>
            <div className={styles.sidebar__logo_container}>
                <div className={styles.sidebar__logo}>
                    <Image src="/crm/logo/reybo-logo.png"
                           width={640}
                           height={640}
                           style={{ width: 'auto', height: '2vw' }}
                           alt="reybo-logo"></Image>
                </div>
                <div className={styles.sidebar__title}>
                    <h1><b>CRM</b></h1>
                </div>
            </div>
        </>
    )
}