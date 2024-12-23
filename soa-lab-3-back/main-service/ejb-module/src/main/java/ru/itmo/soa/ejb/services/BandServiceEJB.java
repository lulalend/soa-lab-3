package ru.itmo.soa.ejb.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.jboss.ejb3.annotation.Pool;
import ru.itmo.soa.ejb.repositories.BandRepositoryEJB;
import ru.itmo.soa.ejb.exceptions.ResourceNotFoundException;
import ru.itmo.soa.ejb.model.Band;
import ru.itmo.soa.ejb.model.MusicGenre;
import ru.itmo.soa.ejb.model.Person;
import ru.itmo.soa.ejb.model.Single;
import ru.itmo.soa.ejb.model.dto.BandUpdate;
import ru.itmo.soa.ejb.model.dto.BandsInfoResponse;


import java.time.OffsetDateTime;
import java.util.*;

@Stateless
@Pool("ejb-band-pool")
public class BandServiceEJB implements BandServiceIEJB {

    @Inject
    private BandRepositoryEJB bandRepository;

    @Inject
    private SingleServiceEJB singleService;

    @Inject
    private PersonServiceEJB personService;

    public Band saveBand(Band band) {
        try {
            band.setCreationDate(OffsetDateTime.now());
            Band newBand = bandRepository.create(band);

            if (band.getFrontMan() != null) {
                newBand.getFrontMan().setBandID(newBand.getId());
            }
            return bandRepository.update(newBand);
        } catch (Exception e) {
            String errorMessage = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();

            if (errorMessage != null && errorMessage.contains("duplicate key value")) {
                String fieldName = extractFieldNameFromMessage(errorMessage);
                String value = extractValueFromMessage(errorMessage);
                throw new RuntimeException("Duplicate entry for field '" + fieldName + "': " + value, e);
            }

            // Для всех остальных ошибок
            throw new RuntimeException("An unexpected error occurred: " + errorMessage, e);
        }
    }

    public BandsInfoResponse getBands(String[] sort, String[] filter, int page, int size) {
        List<Band> bands = bandRepository.findBySpecificationAndSort(filter, sort, page, size);

        BandsInfoResponse response = new BandsInfoResponse();
        response.setData(bands);
        response.setTotal(bandRepository.count(filter));
        response.setTotalPages((int) Math.ceil(response.getTotal() / (double) size));
        response.setCurrentPage(page);
        response.setSize(size);

        return response;
    }

    public Band findById(Long id) {
        return bandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Band with id " + id + " not found"));
    }

    public void deleteById(Long id) {
        bandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Band not found with id: " + id));
        bandRepository.deleteById(id);
    }

    @Transactional
    public Band updateBand(BandUpdate bandUpdate, Long id) {
        try {
            Band existingBand = bandRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Band not found with id: " + id));

            if (bandUpdate.getName() != null) {
                existingBand.setName(bandUpdate.getName());
            }
            if (bandUpdate.getCoordinates() != null) {
                existingBand.setCoordinates(bandUpdate.getCoordinates());
            }
            if (bandUpdate.getNumberOfParticipants() != null) {
                existingBand.setNumberOfParticipants(bandUpdate.getNumberOfParticipants());
            }
            if (bandUpdate.getDescription() != null) {
                existingBand.setDescription(bandUpdate.getDescription());
            }
            if (bandUpdate.getGenre() != null) {
                existingBand.setGenre(bandUpdate.getGenre());
            }
            if (bandUpdate.getFrontMan() != null) {
                personService.createOrUpdatePerson(bandUpdate.getFrontMan());
            }
            if (bandUpdate.getSingles() != null) {
                for (Single single : bandUpdate.getSingles()) {
                    singleService.createOrUpdateSingle(single);
                }
            }

            return bandRepository.update(existingBand);
        } catch (Exception e) {
            throw new RuntimeException("Error updating band: " + e.getMessage(), e);
        }
    }

    public List<MusicGenre> getAllGenres() {
        return Arrays.asList(MusicGenre.values());
    }

    public void deleteByGenre(String genreStr) {
        try {
            MusicGenre genre = MusicGenre.valueOf(genreStr.toUpperCase());
            bandRepository.deleteByGenre(genre);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Genre not found: " + genreStr);
        }
    }

    public Band getGroupWithMinGenre() {
        List<Band> bands = bandRepository.findAllSortedByGenreAscNameAsc();
        if (bands.isEmpty()) {
            throw new ResourceNotFoundException("No bands found");
        }

        return bands.get(0);
    }

    @Transactional
    public Band addSingleToBand(Long id, Single single) {
        Band existingBand = bandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Band not found with id: " + id));

        existingBand.getSingles().add(single);
        return bandRepository.update(existingBand);
    }

    @Transactional
    public Single changeSingle(Long bandId, Long singleId, Single single) {
        Band existingBand = bandRepository.findById(bandId)
                .orElseThrow(() -> new ResourceNotFoundException("Band not found with id: " + bandId));

        Optional<Single> existingSingle = existingBand.getSingles().stream()
                .filter(s -> s.getId().equals(singleId))
                .findFirst();

        if (existingSingle.isPresent()) {
            Single updatedSingle = singleService.updateSingle(singleId, single);
            existingBand.getSingles().remove(existingSingle.get());
            existingBand.getSingles().add(updatedSingle);
            bandRepository.update(existingBand);

            return updatedSingle;
        } else {
            throw new ResourceNotFoundException("Single not found with id: " + singleId + " for band with id: " + bandId);
        }
    }

    @Transactional
    public Person addPersonToBand(Long id, Person person) {
        Band existingBand = bandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Band not found with id: " + id));

        person.setBandID(id);
        Person newPerson = personService.createPerson(person);

        existingBand.setNumberOfParticipants(existingBand.getNumberOfParticipants() + 1);
        bandRepository.update(existingBand);

        return newPerson;
    }

    private String extractFieldNameFromMessage(String message) {
        int startIndex = message.indexOf("(");
        int endIndex = message.indexOf(")");
        if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
            return message.substring(startIndex + 1, endIndex);
        }
        return "unknown_field";
    }

    private String extractValueFromMessage(String message) {
        int startIndex = message.indexOf("=(");
        int endIndex = message.indexOf(")", startIndex);
        if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
            return message.substring(startIndex + 2, endIndex);
        }
        return "unknown_value";
    }
}


