import {PersonToBandDTO} from "@/app/types/person";
import {Location} from "@/app/types/location";

type GetFrontManProps = {
    name?: string;
    birthday?: string;
    passportID?: string;
    location: Omit<Partial<Location>, 'id'>;
}

export const getFrontMan = (frontMan: GetFrontManProps): PersonToBandDTO |  undefined => {
    if (frontMan.passportID !== undefined && frontMan.location.x !== undefined && frontMan.location.y !== undefined && frontMan.location.z !== undefined) {
        const locationBase = {
            x: frontMan.location.x,
            y: frontMan.location.y,
            z: frontMan.location.z
        }
        const name = frontMan.name ? { name: frontMan.name } : undefined;
        const birthday = frontMan.birthday ? { birthday: frontMan.birthday } : undefined;

        const location = !frontMan.location.name ? locationBase : { name: frontMan.location.name , ...locationBase };

        return {
            ...name,
            ...birthday,
            passportID: frontMan.passportID,
            location: location
        };
    }
    else if ((frontMan.passportID === undefined || frontMan.location.x === undefined || frontMan.location.y === undefined || frontMan.location.z === undefined) && (frontMan.name || frontMan.birthday || frontMan.location.name)){
        throw new Error("If you want to add front man, enter all the required values or leave it untouched");
    }
    return;
}