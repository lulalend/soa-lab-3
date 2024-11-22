'use client'

import {PropsWithChildren} from "react";

type ButtonProps = {
    onClick?: () => void;
    submit?: boolean;
    className?: string;
    style: 'primary' | 'secondary' | 'accent' | 'accent-green' | 'cancel' | 'danger' | 'transparent';
    size: 's' | 'm' | 'l';
}

export const Button = ({ onClick, submit, children, style, size, className }: PropsWithChildren<ButtonProps>) => {
    return (
        <button
            onClick={onClick}
            className={`button ${style} ${size} ${className}`}
            type={submit ? 'submit' : undefined}
        >
            {children}
        </button>
    )
}