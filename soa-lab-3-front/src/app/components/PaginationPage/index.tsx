'use client'

import styles from './styles.module.css';
import {Button} from "@/app/components/Button";
import {LeftArrowIcon, RightArrowIcon} from "@/static/icons";
import {getPagesForView} from "@/app/components/PaginationPage/helpers";
import {useContext} from "react";
import {PaginationContext} from "@/app/context/pagination";

export const PaginationPage = () => {
    const { page, setPage, totalPages } = useContext(PaginationContext);
    const availablePages = getPagesForView(totalPages, page);

    const onLeftClick = () => {
        if (page >= 2) {
            setPage(page - 1);
        }
    }

    const onRightClick = () => {
        if (page <= totalPages - 1) {
            setPage(page + 1);
        }
    }

    const onPageClick = (page: number) => {
        setPage(page);
    }

    return (
        <div className={styles.container}>
            <Button style='transparent' size='m' className={page === 1 ? styles.disabled : ''} onClick={onLeftClick}>
                <LeftArrowIcon className={`${styles.icon} ${page === 1 ? styles.disabled : ''}`}/>
                Previous
            </Button>

            <div className={styles.pages}>
                {availablePages.pagesStart.map((page) => {
                    return (
                        <div key={page.item} className={`${styles.page} ${page.active ? styles.active : ''}`} onClick={() => onPageClick(page.item)}>
                            {page.item}
                        </div>
                    )
                })}
                {availablePages.pagesMiddle ? (
                    <>
                        ...
                        {availablePages.pagesMiddle.map((page) => {
                            return (
                                <div key={page.item} className={`${styles.page} ${page.active ? styles.active : ''}`} onClick={() => onPageClick(page.item)}>
                                    {page.item}
                                </div>
                            )
                        })}
                    </>
                ):''}
                {availablePages.pagesEnd ? (
                    <>
                        ...
                        {availablePages.pagesEnd.map((page) => {
                            return (
                                <div key={page.item} className={`${styles.page} ${page.active ? styles.active : ''}`} onClick={() => onPageClick(page.item)}>
                                    {page.item}
                                </div>
                            )
                        })}
                    </>
                ):''}
            </div>

            <Button style='transparent' size='m' className={page === totalPages ? styles.disabled : ''} onClick={onRightClick}>
                Next
                <RightArrowIcon className={`${styles.icon} ${page === totalPages ? styles.disabled : ''}`}/>
            </Button>
        </div>
    )
}