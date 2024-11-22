export enum Property {
    ID = 'id',
    NAME = 'name',
    COORDINATE_X = 'coordinates.x',
    COORDINATE_Y = 'coordinates.y',
    CREATION_DATE = 'creationDate',
    NUMBER_OF_PARTICIPANTS = 'numberOfParticipants',
    DESCRIPTION = 'description',
    PERSON_NAME = 'frontMan.name',
    GENRE = 'genre'
}

export const PROPERTIES_TEXT = {
    [Property.ID]: 'ID',
    [Property.NAME]: 'Name',
    [Property.COORDINATE_X]: 'Coordinate X',
    [Property.COORDINATE_Y]: 'Coordinate Y',
    [Property.CREATION_DATE]: 'Creation Date',
    [Property.NUMBER_OF_PARTICIPANTS]: 'Number of participants',
    [Property.DESCRIPTION]: 'Description',
    [Property.PERSON_NAME]: 'Front Man\'s name',
    [Property.GENRE]: 'Genre'
}

export const PROPERTIES_LIST = Object.values(Property);
