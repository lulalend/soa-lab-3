import {Band} from "@/app/types/bands";
import {Single} from "@/app/types/single";
import {Person, PersonToBandDTO} from "@/app/types/person";
import {fetchUrl, getErrorMessage} from "@/app/utils/fetch";

async function addSingle(bandId: number, singleData: Single): Promise<Band> {
    try {
        const response = await fetchUrl({ path: `/band/${bandId}/singles/add`, options: {
                method: 'POST',
                body: JSON.stringify(singleData),
                headers: { 'Content-Type': 'application/json' }
            }, mainApi: false});

        if (!response.ok) return response.text().then(text => { throw new Error(getErrorMessage(text)) })

        return await response.json();
    } catch (error) {
        throw error;
    }
}

async function changeSingle(bandId: number, singleId: number, singleData: Omit<Single, 'id'>): Promise<Single> {
    try {
        const response = await fetchUrl({ path: `/bands/${bandId}/singles/${singleId}`, options: {
                method: 'PUT',
                body: JSON.stringify(singleData),
                headers: { 'Content-Type': 'application/json' }
            }, mainApi: false});

        if (!response.ok) return response.text().then(text => { throw new Error(getErrorMessage(text)) })

        return await response.json();
    } catch (error) {
        throw error;
    }
}

async function addParticipant(bandId: number, participantData: PersonToBandDTO): Promise<Person> {
    try {
        const response = await fetchUrl({ path: `/band/${bandId}/participants/add`, options: {
                method: 'POST',
                body: JSON.stringify(participantData),
                headers: { 'Content-Type': 'application/json' }
            }, mainApi: false});

        if (!response.ok) return response.text().then(text => { throw new Error(getErrorMessage(text)) })

        return await response.json();
    } catch (error) {
        throw error;
    }
}

export {
    addSingle,
    changeSingle,
    addParticipant
};