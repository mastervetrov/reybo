const ONLINE_TIMEOUT_MINUTES = 5;

export interface Account {
    id: string;
    email: string;
    phone?: string;
    photo?: string;
    about?: string;
    city?: string;
    country?: string;
    firstName: string;
    lastName: string;
    birthDate?: string;
    regDate: string;
    lastOnlineTime: string;
    isBlocked: boolean;
    isDeleted: boolean;
    photoName?: string;
    createdOn: string;
    updatedOn: string;
    emojiStatus?: string;
    profileCover?: string;
}

export interface AccountWithOnline extends Account {
    isOnline: boolean;
}

export interface CreateAccountRequest {
    email: string;
    phone?: string;
    firstName: string;
    lastName: string;
    birthDate?: string;
}

export interface UpdateAccountRequest {
    phone?: string;
    photo?: string;
    about?: string;
    city?: string;
    country?: string;
    firstName?: string;
    lastName?: string;
    birthDate?: string;
    emojiStatus?: string;
    profileCover?: string;
}