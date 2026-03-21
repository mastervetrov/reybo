'use client';

import { useEffect } from 'react';
import { useRouter } from 'next/navigation';
import { TokenService } from '@/shared-temp/core/lib/auth/tokenService';

export const useGuestGuard = () => {
    const router = useRouter();

    useEffect(() => {
        const token = TokenService.getAccessToken();

        if (token !== null) {
            router.replace('/');
        }
    }, [router]);
};