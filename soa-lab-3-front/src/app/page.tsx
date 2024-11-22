'use client'

import styles from "./page.module.css";
import {Table} from "@/app/components/Table";
import {DEFAULT_SORT_ORDER, SortContext, SortOrderMap} from "@/app/context/sort";
import {useEffect, useState} from "react";
import {Button} from "@/app/components/Button";
import {FilterIcon, PlusIcon} from "@/static/icons";
import {AddBandModal} from "@/app/components/AddBandModal";
import {SpecialActions} from "@/app/components/SpecialActions";
import {DEFAULT_PAGE, DEFAULT_SIZE, PaginationContext} from "@/app/context/pagination";
import {PaginationSize} from "@/app/components/PaginationSize";
import {DEFAULT_FILTERS, FilterContext, FilterMap} from "@/app/context/filter";
import {Filters} from "@/app/components/Filters";
import {getAllGenres, getBands} from "@/app/queries/bands";
import {Band} from "@/app/types/bands";
import {getSortQuery} from "@/app/utils/sort";
import {getFilterQuery} from "@/app/utils/filter";
import { BandsContext } from "./context/bands";
import toast, { Toaster } from 'react-hot-toast';
import {GenresContext} from "@/app/context/genres";

export default function Home() {
    const [sortOrder, setSortOrder] = useState<SortOrderMap>(DEFAULT_SORT_ORDER);
    const [page, setPage] = useState<number>(DEFAULT_PAGE);
    const [size, setSize] = useState<number>(DEFAULT_SIZE);
    const [totalPages, setTotalPages] = useState<number>(DEFAULT_SIZE);
    const [filters, setFilters] = useState<FilterMap>(DEFAULT_FILTERS);
    const [canFetch, setCanFetch] = useState<number>(0);

    const [genres, setGenres] = useState<string[]>([]);
    const [bands, setBands] = useState<Band[]>([]);

    const [isAddBandModalVisible, setAddBandModalVisible] = useState<boolean>(false);
    const [isFiltersModalVisible, setFiltersModalVisible] = useState<boolean>(false);

    useEffect(() => {
        getBands({
            sort: getSortQuery(sortOrder),
            filter: getFilterQuery(filters),
            page: page,
            size: size
        }).then((data) => {
            setBands(data.data);
            setPage(data.currentPage);
            setSize(data.size);
            setTotalPages(data.totalPages);
        }).catch((err) => {
            toast.error(`${err}`);
        });

        getAllGenres()
            .then((data) => {
                setGenres(data);
            })
            .catch((err) => {
                toast.error(`${err}`);
            })
    }, [sortOrder, page, size, canFetch]);

    return (
        <SortContext.Provider value={{sortOrder, setSortOrder}}>
            <PaginationContext.Provider value={{page, setPage, size, setSize, totalPages, setTotalPages}}>
                <FilterContext.Provider value={{filters, setFilters}}>
                    <BandsContext.Provider value={{canFetch, setCanFetch}}>
                        <GenresContext.Provider value={{genres, setGenres}}>
                            <Toaster
                                position="bottom-right"
                                reverseOrder={false}
                            />
                            <div className={styles.page}>
                                <div className={styles.header}>
                                    <h1 className={styles.title}>Bands</h1>
                                    <div className={styles.controls}>
                                        <PaginationSize />
                                        <Button style='secondary' size='m' onClick={() => setFiltersModalVisible(!isFiltersModalVisible)}>
                                            <FilterIcon className={styles.icon}/>
                                            Filter
                                        </Button>
                                        {isFiltersModalVisible && <Filters onClose={() => setFiltersModalVisible(false)}/>}
                                        <Button style='primary' size='l' onClick={() => {setAddBandModalVisible(true)}}>
                                            <PlusIcon className={`${styles.icon} ${styles.inverted}`}/>
                                            Create new band
                                        </Button>
                                    </div>
                                </div>
                                <Table bands={bands}/>
                                <SpecialActions />
                            </div>
                            {isAddBandModalVisible &&
                                <AddBandModal isVisible={isAddBandModalVisible} onClose={() => setAddBandModalVisible(false)}/>
                            }
                        </GenresContext.Provider>
                    </BandsContext.Provider>
                </FilterContext.Provider>
            </PaginationContext.Provider>
        </SortContext.Provider>
    );
}
