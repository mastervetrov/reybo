'use client';

import { useEffect } from 'react';
import { useAccount } from '@/shared-temp/features/account/hooks/useAccount';

export const LoadAccount = ({ children }: { children: React.ReactNode }) => {
    const { loadAccount } = useAccount();

    useEffect(() => {
        loadAccount();
    }, []);

    return <>{children}</>;
};