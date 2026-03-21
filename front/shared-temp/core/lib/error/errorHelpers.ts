// shared-temp-temp/lib/errorHelpers.ts
export const extractErrorMessage = (err: unknown, defaultMessage: string): string => {
    if (err instanceof Error) return err.message;
    if (typeof err === 'string') return err;
    if (err && typeof err === 'object') {
        if ('message' in err) return String(err.message);
        if ('error' in err) return String(err.error);
        if ('data' in err && err.data && typeof err.data === 'object' && 'message' in err.data) {
            return String(err.data.message);
        }
    }
    return defaultMessage;
};