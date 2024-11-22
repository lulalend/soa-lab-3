'use client'

import {Button} from "@/app/components/Button";
import {Modal} from "@/app/components/Modal";
import styles from './styles.module.css';
import {useContext} from "react";
import {BandsContext} from "@/app/context/bands";
import {deleteBandById} from "@/app/queries/bands";
import toast from 'react-hot-toast';

type DeleteBandModalProps = {
    bandId: number;
    bandName: string;
    isVisible: boolean;
    onClose: () => void;
}

export const DeleteBandModal = ({ bandId, bandName, isVisible, onClose }: DeleteBandModalProps) => {
    const { canFetch, setCanFetch } = useContext(BandsContext);

    function onDeleteBandClick() {
        deleteBandById(bandId)
            .then(() => {
                setCanFetch(canFetch + 1);
                toast.success("Successfully deleted");
                onClose();
            })
            .catch((err) => {
                toast.error(`${err}`);
            })
    }

    return (
        <Modal isVisible={isVisible} onClose={onClose}>
            <div className='modal-container'>
                <h2>Do you want to delete {bandName}?</h2>
                <span className={styles.caption}>This action cannot be undone. Think wisely</span>
                <div className='buttons'>
                    <Button style='cancel' size='m' onClick={onClose}>
                        Cancel
                    </Button>
                    <Button style='danger' size='m' onClick={onDeleteBandClick}>
                        Delete
                    </Button>
                </div>
            </div>
        </Modal>
    )
}