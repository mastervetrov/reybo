export interface MenuItemType {
    id: string;
    label: string;
    icon?: string;
    path: string;
    component?: React.ComponentType;
    subItems?: MenuItemType[];
}