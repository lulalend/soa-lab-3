'use client'

import styles from './styles.module.css';
import {Band} from "@/app/types/bands";
import {TableRow} from "@/app/components/TableRow";
import {TableHeader} from "@/app/components/TableHeader";
import {PaginationPage} from "@/app/components/PaginationPage";

type TableProps = {
    bands: Band[];
}

export const Table = ({ bands }: TableProps) => {
    return (
        <div className={styles.table}>
            <TableHeader />
            {bands.length ? bands.map((band) => {
                return (
                    <TableRow key={band.id} band={band}/>
                )
            }) : (
                <div className={styles.empty}>
                    <span>No bands for now. You can add them by clicking button in the right corner of this page.</span>
                </div>
            )}
            <PaginationPage />
        </div>
    )
}