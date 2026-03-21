'use client';

import { useState, useEffect } from 'react';
import styles from './Login.module.css';

interface CaptchaBlockProps {
    getCaptcha: () => Promise<any>;
    captchaCode: string;
    onCaptchaChange: (code: string) => void;
    disabled?: boolean;
}

export const CaptchaBlock = ({
                                 getCaptcha,
                                 captchaCode,
                                 onCaptchaChange,
                                 disabled
                             }: CaptchaBlockProps) => {
    const [captchaImage, setCaptchaImage] = useState<string>('');
    const [captchaLoading, setCaptchaLoading] = useState(false);

    const loadCaptcha = async () => {
        setCaptchaLoading(true);
        try {
            const result = await getCaptcha();
            if (result.success && result.data) {
                setCaptchaImage(result.data);
            }
        } catch (err) {
            console.error('Captcha error:', err);
        } finally {
            setCaptchaLoading(false);
        }
    };

    useEffect(() => {
        loadCaptcha();
    }, []);

    return (
        <div className={styles.captchaContainer}>
            {captchaLoading ? (
                <div className={styles.captchaLoader}>Загрузка капчи...</div>
            ) : captchaImage ? (
                <>
                    <img
                        src={captchaImage}
                        alt="captcha"
                        className={styles.captchaImage}
                        onClick={loadCaptcha}
                        style={{ cursor: 'pointer' }}
                    />
                    <div className={styles.captchaHint}>
                        (кликните на картинку для обновления)
                    </div>
                </>
            ) : null}

            <input
                type="text"
                name="captchaCode"
                placeholder="Введите код с картинки"
                value={captchaCode}
                onChange={(e) => onCaptchaChange(e.target.value)}
                required
                disabled={disabled || captchaLoading}
                className={styles.captchaInput}
            />
        </div>
    );
};