import {Coordinates} from "@/app/types/location";
import {PersonToBand, PersonToBandDTO} from "@/app/types/person";
import {Single} from "@/app/types/single";

export type Band = {
    id: number;
    name: string;
    coordinates: Coordinates;
    creationDate: string;
    numberOfParticipants: number;
    description: string;
    genre: string;
    frontMan?: PersonToBand;
    singles?: Single[];
}

export type BandDTO = {
    name: string;
    coordinates: Omit<Coordinates, 'id'>;
    numberOfParticipants: number;
    description: string;
    genre: string;
    frontMan?: PersonToBandDTO;
    singles?: Omit<Single, 'id'>[];
}

export type BandUpdateDTO = {
    name?: string;
    coordinates?: Omit<Coordinates, 'id'>;
    numberOfParticipants?: number;
    description?: string;
    genre?: string;
    frontMan?: PersonToBandDTO;
    singles?: Omit<Single, 'id'>[];
}
