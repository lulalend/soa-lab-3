import {Band, BandDTO, BandUpdateDTO} from "@/app/types/bands";
import {PaginatedResponse} from "@/app/types/response";
import {PaginatedRequest} from "@/app/types/request";
import {fetchUrl, getErrorMessage, getErrorMessages, SearchParamType} from "@/app/utils/fetch";

async function createBand(bandData: BandDTO): Promise<Band> {
    try {
        const response = await fetchUrl({ path: '/bands', options: {
            method: 'POST',
            body: JSON.stringify(bandData),
            headers: { 'Content-Type': 'application/json' }
        }});

        if (!response.ok) return response.text().then(text => { throw new Error(getErrorMessages(text))})

        return await response.json();
    } catch (error) {
        throw error;
    }
}

async function getBands({
    sort,
    filter,
    page,
    size
}: PaginatedRequest): Promise<PaginatedResponse<Band>> {
    try {
        const params: SearchParamType[] = [];
        if (page !== undefined) params.push({ key: 'page', value: String(page)});
        if (size !== undefined) params.push({ key: 'size', value: String(size)});
        if (sort !== undefined) params.push({ key: 'sort', value: sort});
        if (filter !== undefined) params.push({ key: 'filter', value: filter});

        const response = await fetchUrl({path: '/bands', params, options: {
            method: 'GET',
        }});

        if (!response.ok) return response.text().then(text => { throw new Error(getErrorMessage(text)) })

        return await response.json();
    } catch (error) {
        throw error;
    }
}

async function getBandById(id: number): Promise<Band> {
    try {
        const response = await fetchUrl({path: `/bands/${id}`,  options: {
                method: 'GET',
            }});

        if (!response.ok) return response.text().then(text => { throw new Error(getErrorMessage(text)) })

        return await response.json();
    } catch (error) {
        throw error;
    }
}

async function deleteBandById(id: number): Promise<void> {
    try {
        const response = await fetchUrl({path: `/bands/${id}`,  options: {
                method: 'DELETE',
            }});

        if (!response.ok) return response.text().then(text => { throw new Error(getErrorMessage(text)) })
    } catch (error) {
        throw error;
    }
}

async function updateBandById(id: number, bandData: Partial<BandUpdateDTO>): Promise<Band> {
    try {
        const response = await fetchUrl({path: `/bands/${id}`,  options: {
                method: 'PATCH',
                body: JSON.stringify(bandData),
                headers: { 'Content-Type': 'application/json' }
            }});

        if (!response.ok) return response.text().then(text => { throw new Error(getErrorMessages(text)) })

        return await response.json();
    } catch (error) {
        throw error;
    }
}

async function getAllGenres(): Promise<string[]> {
    try {
        const response = await fetchUrl({path: `/bands/genre`,  options: {
                method: 'GET',
            }});

        if (!response.ok) return response.text().then(text => { throw new Error(getErrorMessage(text)) })

        return await response.json();
    } catch (error) {
        throw error;
    }
}

async function deleteBandsByGenre(genre: string): Promise<void> {
    try {
        const response = await fetchUrl({path: `/bands/genre/${genre}`,  options: {
                method: 'DELETE',
            }});

        if (!response.ok) return response.text().then(text => { throw new Error(getErrorMessage(text)) })
    } catch (error) {
        throw error;
    }
}

async function getBandWithMinGenre(): Promise<Band> {
    try {
        const response = await fetchUrl({path: `/bands/genre/min`,  options: {
                method: 'GET',
            }});

        if (!response.ok) return response.text().then(text => { throw new Error(getErrorMessage(text)) })

        return await response.json();
    } catch (error) {
        throw error;
    }
}

export {
    createBand,
    getBands,
    getBandById,
    deleteBandById,
    updateBandById,
    getAllGenres,
    deleteBandsByGenre,
    getBandWithMinGenre
};

