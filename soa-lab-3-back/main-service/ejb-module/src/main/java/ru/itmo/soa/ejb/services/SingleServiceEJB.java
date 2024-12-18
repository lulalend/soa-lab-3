package ru.itmo.soa.ejb.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.jboss.ejb3.annotation.Pool;
import ru.itmo.soa.ejb.repositories.SingleRepositoryEJB;
import ru.itmo.soa.ejb.exceptions.ResourceNotFoundException;
import ru.itmo.soa.ejb.model.Single;

import java.util.Optional;

@Stateless
@Pool("ejb-band-pool")
public class SingleServiceEJB {
    @Inject
    private SingleRepositoryEJB singleRepository;

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
