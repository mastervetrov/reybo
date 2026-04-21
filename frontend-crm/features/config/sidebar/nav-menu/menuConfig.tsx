import { MenuItemType } from "@/features/types/sidebar/nav-menu/menu.types";

export const menuConfig: MenuItemType[] = [
    {
        id: 'main',
        label: 'Обзор',
        icon: '/crm/icons/icon-main.png',
        path: '/crm/dashboard',
    },
    {
        id: 'orders',
        label: 'Заказы',
        icon: '/crm/icons/icon-orders.png',
        path: '/crm/orders',
    },
    {
        id: 'sales',
        label: 'Продажи',
        icon: '/crm/icons/icon-sales.png',
        path: '/crm/sales',
    },
    {
        id: 'store',
        label: 'Склад',
        icon: '/crm/icons/icon-store.png',
        path: '#',
        subItems: [
            {
                id: 'store-remainder',
                label: 'Остатки',
                path: '/crm/store/remainder',
            },
            {
                id: 'store-receipt',
                label: 'Поступления',
                path: '/crm/store/receipt',
            },
            {
                id: 'store-moves',
                label: 'Перемещения',
                path: '/crm/store/moves',
            },
            {
                id: 'store-returns-sales',
                label: 'Возвраты от клиентов',
                path: '/crm/store/returns/sales',
            },
            {
                id: 'store-returns-purchase',
                label: 'Возвраты поставщикам',
                path: '/crm/store/returns/purchase',
            },
            {
                id: 'store-inventory',
                label: 'Инвентаризация',
                path: '/crm/store/inventory',
            },
            {
                id: 'store-cancellations',
                label: 'Списания',
                path: '/crm/store/cancellations',
            },
            {
                id: 'store-settings',
                label: 'Настройка склада',
                path: '/crm/store/settings',
            },
        ],
    },
    {
        id: 'finance',
        label: 'Финансы',
        icon: '/crm/icons/icon-finance.png',
        path: '#',
        subItems: [
            {
                id: 'finance-cashes',
                label: 'Кассы',
                path: '/crm/finance/cashes',
            },
            {
                id: 'finance-salaries',
                label: 'Зарплата',
                path: '/crm/finance/salaries',
            },
            {
                id: 'finance-transactions',
                label: 'Транзакции',
                path: '/crm/finance/transactions',
            },
            {
                id: 'finance-invoices',
                label: 'Счета',
                path: '/crm/finance/invoices',
            },
        ],
    },
    {
        id: 'analytics',
        label: 'Аналитика',
        icon: '/crm/icons/icon-analytics.png',
        path: '#',
        subItems: [
            {
                id: 'analytics-reports',
                label: 'Отчёты',
                path: '/crm/analytics/reports/orders',
            },
            {
                id: 'analytics-calls',
                label: 'Звонки',
                path: '/crm/analytics/calls',
            },
            {
                id: 'analytics-advertising',
                label: 'Реклама',
                path: '/crm/analytics/advertising',
            },
        ],
    },
    {
        id: 'compendiums',
        label: 'Справка',
        icon: '/crm/icons/icon-compendiums.png',
        path: '#',
        subItems: [
            {
                id: 'compendiums-nomenclatures',
                label: 'Товары',
                path: '/crm/compendiums/nomenclatures',
            },
            {
                id: 'compendiums-works',
                label: 'Работы',
                path: '/crm/compendiums/works',
            },
            {
                id: 'compendiums-contractors',
                label: 'Контрагенты',
                path: '/crm/compendiums/contractors',
            },
            {
                id: 'compendiums-devices',
                label: 'Устройства',
                path: '/crm/compendiums/devices',
            },
            {
                id: 'compendiums-complete-sets',
                label: 'Комплектации',
                path: '/crm/compendiums/complete-sets',
            },
            {
                id: 'compendiums-problems',
                label: 'Неисправности',
                path: '/crm/compendiums/problems',
            },
            {
                id: 'compendiums-cash-items-income',
                label: 'Статьи движения денежных средств',
                path: '/crm/compendiums/cash-items/income',
            },
            {
                id: 'compendiums-units',
                label: 'Единицы измерения',
                path: '/crm/compendiums/units',
            },
        ],
    },
    {
        id: 'settings',
        label: 'Настройки',
        icon: '/crm/icons/icon-settings.png',
        path: '/crm/settings',
    },
];