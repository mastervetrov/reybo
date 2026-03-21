interface SpriteIconProps {
    name: 'home' | 'user' | 'cart' | 'search';
    size?: number;
}

export const SpriteIcon = ({ name, size = 24 }: SpriteIconProps) => {
    return (
        <i
            className={`sprite sprite-icon-${name}`}
            style={{width: size, height: size}}
        />
    );
}