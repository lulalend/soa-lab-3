import {Band} from "@/app/types/bands";

export const BANDS_LIST_MOCK: Band[] = [
    {
        id: 1,
        name: "Electric Storm",
        coordinates: { id: 101, x: 45.764043, y: 4.835659 },
        creationDate: "2022-01-15",
        numberOfParticipants: 5,
        description: "High-energy rock band from Paris",
        genre: 'ROCK',
        frontMan: {
            id: 201,
            name: "Alexandre Dupont",
            birthday: "1990-03-22",
            passportID: "FR123456789",
            location: { id: 101, x: 48.856613, y: 2.352222, z: 0 }
        },
        singles: [
            { id: 301, name: "Thunderbolt" },
            { id: 302, name: "Electric Dreams" }
        ]
    },
    {
        id: 2,
        name: "Soulful Serenaders",
        coordinates: { id: 102, x: -74.0060, y: 40.7128 },
        creationDate: "2019-06-01",
        numberOfParticipants: 7,
        description: "Harmonious soul band from New York City",
        genre: 'SOUL',
        frontMan: {
            id: 202,
            name: "Maya Johnson",
            birthday: "1995-11-12",
            passportID: "US987654321",
            location: { id: 102, x: -73.935242, y: 40.778117, z: 0 }
        }
    },
    {
        id: 3,
        name: "Mathematical Melodies",
        coordinates: { id: 103, x: 51.5074, y: -0.1278 },
        creationDate: "2018-03-20",
        numberOfParticipants: 4,
        description: "Complex math rock band from London",
        genre: 'MATH_ROCK',
        singles: [
            { id: 303, name: "Fractal Patterns" },
            { id: 304, name: "Quantum Leap" }
        ]
    },
    {
        id: 4,
        name: "Postmodern Pioneers",
        coordinates: { id: 104, x: 48.8566, y: 2.3522 },
        creationDate: "2021-08-15",
        numberOfParticipants: 6,
        description: "Innovative post-rock band from Paris",
        genre: 'POST_ROCK',
        frontMan: {
            id: 203,
            name: "Léa Martin",
            birthday: "1992-07-25",
            passportID: "FR246810",
            location: { id: 104, x: 48.8583, y: 2.2945, z: 0 }
        }
    },
    {
        id: 5,
        name: "Quantum Harmonies",
        coordinates: { id: 105, x: 34.0522, y: -118.2437 },
        creationDate: "2020-02-29",
        numberOfParticipants: 3,
        description: "Experimental math rock band from Los Angeles",
        genre: 'MATH_ROCK',
        singles: [
            { id: 305, name: "Wave Function Collapse" },
            { id: 306, name: "Quantum Entanglement" }
        ]
    },
    {
        id: 6,
        name: "Parisian Post-Punks",
        coordinates: { id: 106, x: 48.8566, y: 2.3522 },
        creationDate: "2017-04-15",
        numberOfParticipants: 5,
        description: "Moody post-rock band from the City of Light",
        genre: 'POST_ROCK',
        frontMan: {
            id: 204,
            name: "Sophie Lefebvre",
            birthday: "1988-09-17",
            passportID: "FR135790",
            location: { id: 106, x: 48.8583, y: 2.2945, z: 0 }
        }
    },
    {
        id: 7,
        name: "Soul Revival",
        coordinates: { id: 107, x: 41.8781, y: -87.6298 },
        creationDate: "2015-11-01",
        numberOfParticipants: 6,
        description: "Classic soul revival band from Chicago",
        genre: 'SOUL',
        singles: [
            { id: 307, name: "Come Together" },
            { id: 308, name: "Love Train" }
        ],
        frontMan: {
            id: 205,
            name: "Marcus Thompson",
            birthday: "1993-02-14",
            passportID: "US543210",
            location: { id: 107, x: 41.8756, y: -87.6247, z: 0 }
        }
    },
    {
        id: 8,
        name: "Rockin' Renegades",
        coordinates: { id: 108, x: 51.5074, y: -0.1278 },
        creationDate: "2019-07-04",
        numberOfParticipants: 4,
        description: "Rebellious rock band from London",
        genre: 'ROCK',
        frontMan: {
            id: 206,
            name: "Ethan Patel",
            birthday: "1991-05-28",
            passportID: "UK987654",
            location: { id: 108, x: 51.5074, y: -0.1278, z: 0 }
        }
    },
    {
        id: 9,
        name: "Mathematical Minds",
        coordinates: { id: 109, x: 35.6895, y: 139.6917 },
        creationDate: "2021-03-21",
        numberOfParticipants: 5,
        description: "Intellectual math rock band from Tokyo",
        genre: 'MATH_ROCK',
        singles: [
            { id: 309, name: "Binary Code" },
            { id: 310, name: "Algorithmic Rhythm" }
        ],
        frontMan: {
            id: 207,
            name: "Yui Nakamura",
            birthday: "1994-08-15",
            passportID: "JP246809",
            location: { id: 109, x: 35.6895, y: 139.6917, z: 0 }
        }
    }
];

export const BAND_MOCK: Band = {
    id: 1,
    name: "Electric Storm",
    coordinates: { id: 101, x: 46, y: 4.835659 },
    creationDate: "2022-01-15T10:00:00.000Z",
    numberOfParticipants: 5,
    description: "High-energy rock band from Paris",
    genre: 'SOUL',
    frontMan: {
        id: 201,
        name: "Alexandre Dupont",
        birthday: "1990-03-22",
        passportID: "FR123456789",
        location: { id: 101, name: 'New York', x: 48.856613, y: 2.352222, z: 0 }
    },
    singles: [
        { id: 301, name: "Thunderbolt" },
        { id: 302, name: "Electric Dreams" }
    ]
}
