'use client'

import styles from './styles.module.css';
import {useContext, useState} from "react";
import {DEFAULT_SIZE, PaginationContext} from "@/app/context/pagination";
import {Button} from "@/app/components/Button";
import {SubmitIcon} from "@/static/icons";

export const PaginationSize = () => {
    const { setSize } = useContext(PaginationContext);
    const [curSize, setCurSize] = useState<number>(DEFAULT_SIZE);

    const onSubmitClick = () => {
        setSize(curSize);
    }

    return (
        <div className={styles.container}>
            <form action={onSubmitClick} className={styles.form}>
                <label className={styles.inputContainer}>
                    Per page showing:
                    <input type='number' min="1" className="input"
                           step="1" value={curSize} onChange={(e) => setCurSize(Number(e.target.value))}/>
                </label>
                <Button style='secondary' size='m' submit>
                    <SubmitIcon className={styles.icon}/>
                    Submit
                </Button>
            </form>
        </div>
    )
}