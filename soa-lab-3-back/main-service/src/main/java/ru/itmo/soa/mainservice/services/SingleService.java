package ru.itmo.soa.mainservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.soa.mainservice.exceptions.ResourceNotFoundException;
import ru.itmo.soa.mainservice.model.Person;
import ru.itmo.soa.mainservice.model.Single;
import ru.itmo.soa.mainservice.repositories.SingleRepository;

import java.util.Optional;

@Service
public class SingleService {

    @Autowired
    private SingleRepository singleRepository;

    public Single updateSingle(Long singleId, Single updatedSingle) {
        Single existingSingle = singleRepository.findById(singleId)
                .orElseThrow(() -> new ResourceNotFoundException("Single not found with id: " + singleId));

        existingSingle.setName(updatedSingle.getName());

        return singleRepository.save(existingSingle);
    }

    public void createOrUpdateSingle(Single single) {
        Optional<Single> existingSingle = singleRepository.findByName(single.getName());

        if (existingSingle.isPresent()) {
            Single singleToUpdate = existingSingle.get();
            singleToUpdate.setName(single.getName());
            singleRepository.save(singleToUpdate);
        } else {
            singleRepository.save(single);
        }
    }
}

