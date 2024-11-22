import {createContext} from "react";

export const BandsContext = createContext({
    canFetch: 0,
    // eslint-disable-next-line  @typescript-eslint/no-unused-vars
    setCanFetch: (canFetch: number) => {}
})