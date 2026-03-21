import styles from './Slider.module.css';

export default function Slider () {
    return (
        <div className={styles.sliderContainerExternal}>
            <div className={styles.sliderContainer}>
                <div className={styles.slider}>
                    <input defaultChecked={true} className={styles.sliderInput} type="radio" name="slider" id="slide1"/>
                    <input className={styles.sliderInput} type="radio" name="slider" id="slide2"/>

                    <div className={styles.slides}>
                        <div className={styles.slide}><img src="/shop/slider/img_1.png" alt=""/></div>
                        <div className={styles.slide}><img src="/shop/slider/action.jpg" alt=""/></div>
                        <div className={styles.slide}><img src="/shop/slider/robot.webp" alt=""/></div>
                    </div>

                    <div className={styles.controls}>
                        <label className={styles.btnSld} htmlFor="slide1">◀</label>
                        <label className={styles.btnSld} htmlFor="slide2">▶</label>
                    </div>
                </div>
            </div>
        </div>
    )
}