export type PaginatedResponse<T> = {
    data: T[];
    total: number;
    totalPages: number;
    currentPage: number;
    size: number;
}