import {createContext} from "react";
import {Property} from "@/app/types/property";
import {FilterType} from "@/app/types/filter";

export type Filter = {
    operator?: FilterType,
    value?: string,
}

export type FilterMap = {
    [Property.ID]: Filter,
    [Property.NAME]: Filter,
    [Property.GENRE]: Filter,
    [Property.DESCRIPTION]: Filter,
    [Property.PERSON_NAME]: Filter,
    [Property.CREATION_DATE]: Filter,
    [Property.NUMBER_OF_PARTICIPANTS]: Filter,
    [Property.COORDINATE_Y]: Filter,
    [Property.COORDINATE_X]: Filter,
}

export const DEFAULT_FILTERS: FilterMap = {
    [Property.ID]: {},
    [Property.NAME]: {},
    [Property.GENRE]: {},
    [Property.DESCRIPTION]: {},
    [Property.PERSON_NAME]: {},
    [Property.CREATION_DATE]: {},
    [Property.NUMBER_OF_PARTICIPANTS]: {},
    [Property.COORDINATE_Y]: {},
    [Property.COORDINATE_X]: {},
}

export const FilterContext = createContext({
    filters: DEFAULT_FILTERS,
    // eslint-disable-next-line  @typescript-eslint/no-unused-vars
    setFilters: (filters: FilterMap) => {}
});