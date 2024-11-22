'use client'

import styles from './styles.module.css';
import {TableCell} from "@/app/components/TableCell";
import {Property} from "@/app/types/property";

export const TableHeader = () => {
    return (
        <>
            <div className={`table-grid ${styles.container}`}>
                <TableCell withSort cellKey={Property.ID}>
                    ID
                </TableCell>
                <TableCell withSort cellKey={Property.NAME}>
                    Name
                </TableCell>
                <TableCell withSort cellKey={Property.DESCRIPTION}>
                    Description
                </TableCell>
                <TableCell withSort cellKey={Property.CREATION_DATE}>
                    Creation date
                </TableCell>
                <TableCell withSort cellKey={Property.COORDINATE_X}>
                    X
                </TableCell>
                <TableCell withSort cellKey={Property.COORDINATE_Y}>
                    Y
                </TableCell>
                <TableCell withSort cellKey={Property.NUMBER_OF_PARTICIPANTS}>
                    Members
                </TableCell>
                <TableCell withSort cellKey={Property.GENRE}>
                    Genre
                </TableCell>
                <TableCell withSort cellKey={Property.PERSON_NAME}>
                    {"Front Man\'s name"}
                </TableCell>
                <TableCell>
                    Singles
                </TableCell>
                <TableCell>
                    Actions
                </TableCell>
            </div>
            <div className='divider'></div>
        </>
    )
}