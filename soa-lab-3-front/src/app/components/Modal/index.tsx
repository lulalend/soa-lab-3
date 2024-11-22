'use client'

import styles from './styles.module.css';
import {PropsWithChildren} from "react";

type ModalProps = {
    isVisible: boolean;
    onClose: () => void
}

export const Modal = ({ children, isVisible, onClose }: PropsWithChildren<ModalProps>) => {
    return (
        <div className={`${styles.modal} ${isVisible ? styles.visible : styles.hidden}`} onClick={onClose}>
            <div className={styles.content} onClick={(e) => e.stopPropagation()}>
                {children}
            </div>
        </div>
    )
}