import { BaseEntity } from '@/features/crm/shared-temp/types/BaseEntity';

export interface Order extends BaseEntity{
    id: string;
    documentId: string;
    status:string;
    orderType: string;

    // Contractor
    surname: string;      // Фамилия
    firstname: string;    // Имя
    lastname: string;     // Отчество

    phone: string;
    advertisingSource: string;  // Откуда узнал

    // ABOUT DEVICES
    serialNumber: string;
    deviceType: string;   // Тип устройства (Телефон Планшет Ноутбук Системный блок и так далее)

    brand: string;        // Бренд
    model: string;        // Модель

    color: string;
    equipment: string;    // Комплектация
    malfunctions: string; // Неисправности
    assessedValue: string; // Оценочная стоимость
    appearance: string;   // Внешний вид
    password: string;     // Пароль

    // PARAMETERS
    agreedServices: string;   // Согласованные услуги
    agreedPrice: number;      // Согласованная цена

    currentMaster: string;    // Текущий мастер
    deadline: string;         // Срок выполнения

    createdManager: string;   // Менеджер создавший
    currentManager: string;   // Текущий менеджер
    issuingManager: string;   // Менеджер выдавший

    prepayment: number;       // Предоплата

    urgently: boolean;        // Срочно
    comment: string;          // Комментарий
}