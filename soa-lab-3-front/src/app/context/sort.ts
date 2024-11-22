import {createContext} from "react";
import {SortOrder} from "@/app/types/sort";
import {Property} from "@/app/types/property";

export type SortOrderMap = {
    [key in Property]: SortOrder;
};

export const DEFAULT_SORT_ORDER: SortOrderMap = {
    [Property.ID]: SortOrder.UNDEFINED,
    [Property.NAME]: SortOrder.UNDEFINED,
    [Property.DESCRIPTION]: SortOrder.UNDEFINED,
    [Property.CREATION_DATE]: SortOrder.UNDEFINED,
    [Property.COORDINATE_X]: SortOrder.UNDEFINED,
    [Property.COORDINATE_Y]: SortOrder.UNDEFINED,
    [Property.NUMBER_OF_PARTICIPANTS]: SortOrder.UNDEFINED,
    [Property.GENRE]: SortOrder.UNDEFINED,
    [Property.PERSON_NAME]: SortOrder.UNDEFINED,
}

export const SortContext = createContext({
    sortOrder: DEFAULT_SORT_ORDER,
    // eslint-disable-next-line  @typescript-eslint/no-unused-vars
    setSortOrder: (update: SortOrderMap) => {}
});

export const transitionSortOrder = (sortOrder: SortOrder) => {
    switch(sortOrder) {
        case SortOrder.ASC:
            return SortOrder.DESC;
        case SortOrder.DESC:
            return SortOrder.UNDEFINED;
        case SortOrder.UNDEFINED:
            return SortOrder.ASC;
    }
}
