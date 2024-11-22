import {createContext} from "react";

export const GenresContext = createContext({
    genres: [] as string[],
    // eslint-disable-next-line  @typescript-eslint/no-unused-vars
    setGenres: (genres: string[]) => {}
})