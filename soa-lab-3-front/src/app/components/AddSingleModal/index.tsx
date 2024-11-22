'use client'

import {Modal} from "@/app/components/Modal";
import {useContext, useState} from "react";
import {Button} from "@/app/components/Button";
import {Single} from "@/app/types/single";
import {addSingle, changeSingle} from "@/app/queries/grammy";
import toast from 'react-hot-toast';
import {BandsContext} from "@/app/context/bands";

type AddSingleModalProps = {
    bandId: number;
    bandName: string;
    isVisible: boolean;
    onClose: () => void;
    currentValue?: Single;
}

export const AddSingleModal = ({ bandId, currentValue, bandName, isVisible, onClose }: AddSingleModalProps) => {
    const [name, setName] = useState<string>(currentValue?.name ?? '');
    const { canFetch, setCanFetch } = useContext(BandsContext);

    function updateSingle() {
        if (currentValue?.id){
            changeSingle(bandId, currentValue?.id, { name })
                .then(() => {
                    toast.success('Data successfully updated');
                    setCanFetch(canFetch + 1);
                    onClose();
                })
                .catch(err => {
                    toast.error(`${err}`)
                })
        }
    }

    function createSingle() {
        addSingle(bandId, { name })
            .then(() => {
                toast.success('Single successfully created');
                setCanFetch(canFetch + 1);
                onClose();
            })
            .catch(err => {
                toast.error(`${err}`)
            })
    }

    return (
        <Modal isVisible={isVisible} onClose={onClose}>
            <div className='modal-container'>
                <h2>{currentValue ? `Update single for ${bandName}` : `New single for ${bandName}`}</h2>
                <label className='input-container'>
                    {currentValue ? 'New name' : 'Name'}
                    <input id='name' value={name} className='input' placeholder='e.g. Moonlight' onChange={(e) => setName(e.target.value)} />
                </label>
                <div className='buttons'>
                    <Button style='cancel' size='m' onClick={onClose}>
                        Cancel
                    </Button>
                    <Button style='primary' size='m' onClick={currentValue ? updateSingle : createSingle}>
                        {currentValue ? 'Update' : 'Create'}
                    </Button>
                </div>
            </div>
        </Modal>
    )
}