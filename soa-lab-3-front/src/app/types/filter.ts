export enum FilterType {
    GT = 'gt',
    GTE = 'gte',
    LT = 'lt',
    LTE = 'lte',
    EQ = 'eq',
    NEQ = 'neq',
    CONTAIN = '~'
}

export const FILTER_LIST = Object.values(FilterType);
export const FILTER_LIST_NUMBER = [
    FilterType.GT,
    FilterType.GTE,
    FilterType.LT,
    FilterType.LTE,
    FilterType.EQ,
    FilterType.NEQ
];

export const FILTER_TEXT = {
    [FilterType.GT]: '>',
    [FilterType.GTE]: '>=',
    [FilterType.LT]: '<',
    [FilterType.LTE]: '<=',
    [FilterType.EQ]: '=',
    [FilterType.NEQ]: '!=',
    [FilterType.CONTAIN]: '~',
}