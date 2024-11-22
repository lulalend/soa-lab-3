export enum Genre {
    ROCK = 'Rock',
    SOUL = 'Soul',
    MATH_ROCK = 'Math Rock',
    POST_ROCK = 'Post Rock'
}

export const GenreText = {
    [Genre.ROCK]: 'ROCK',
    [Genre.SOUL]: 'SOUL',
    [Genre.MATH_ROCK]: 'MATH_ROCK',
    [Genre.POST_ROCK]: 'POST_ROCK',
}

export const GENRES: Genre[] = Object.values(Genre);