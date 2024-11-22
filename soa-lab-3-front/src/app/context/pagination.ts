import {createContext} from "react";

export const DEFAULT_PAGE = 1;
export const DEFAULT_SIZE = 10;
export const DEFAULT_TOTAL_PAGES = 15;

export const PaginationContext = createContext({
    page: DEFAULT_PAGE,
    // eslint-disable-next-line  @typescript-eslint/no-unused-vars
    setPage: (page: number) => {},
    size: DEFAULT_SIZE,
    // eslint-disable-next-line  @typescript-eslint/no-unused-vars
    setSize: (size: number) => {},
    totalPages: DEFAULT_TOTAL_PAGES,
    // eslint-disable-next-line  @typescript-eslint/no-unused-vars
    setTotalPages: (pages: number) => {}
})