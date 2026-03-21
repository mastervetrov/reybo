import { useState } from "react";
import { extractErrorMessage } from "@/shared-temp/core/lib/error/errorHelpers";

interface UseApiState<T> {
    isLoading: boolean
    
    error: string | null;
    data: T | null;
}

export const useApi = <T>() => {
    const [state, setState] = useState<UseApiState<T>>({
        isLoading: false,
        error: null,
        data: null,
    });

    const execute = async (
        apiCall: () => Promise<T>,
        onSuccess?: (data: T) => void | Promise<void>,
        onError?: (error: string) => void,
    ) => {
        setState(prev => ({ ...prev, isLoading: true, error: null }));

        try {
            const data = await apiCall();

            if (onSuccess) {
                await onSuccess(data);
            }

            setState({ data, isLoading: false, error: null });
            return { success: true, data } as const;

        } catch (err) {
            const errorMessage = extractErrorMessage(err, 'Operation failed');

            try {
                onError?.(errorMessage);
            } catch (cbErr) {
                console.error('onError callback failed:', cbErr);
            }

            setState(prev => ({ ...prev, isLoading: false, error: errorMessage }));
            return { success: false, error: errorMessage } as const;

        }
    };

    return {
        ...state,
        execute,
        reset: () => setState({ isLoading: false, error: null, data: null })
    };
};