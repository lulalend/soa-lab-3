package ru.itmo.soa.mainservice.services;

import org.springframework.stereotype.Service;
import ru.itmo.soa.ejb.services.BandServiceIEJB;
import ru.itmo.soa.mainservice.config.JndiConfig;
import ru.itmo.soa.ejb.model.Band;
import ru.itmo.soa.ejb.model.MusicGenre;
import ru.itmo.soa.ejb.model.Person;
import ru.itmo.soa.ejb.model.Single;
import ru.itmo.soa.ejb.model.dto.BandUpdate;
import ru.itmo.soa.ejb.model.dto.BandsInfoResponse;

import java.util.List;

@Service
public class BandService {
    private final BandServiceIEJB bandServiceEJB;

    public BandService() {
        this.bandServiceEJB = JndiConfig.getBandServiceEJB();
    }


    public Band save(Band band) {
        System.out.println(bandServiceEJB.saveBand(band));
        return bandServiceEJB.saveBand(band);
    }

    public List<MusicGenre> getAllGenres() {
        return bandServiceEJB.getAllGenres();
    }

    public Band updateBand(BandUpdate band, Long id) {
        return bandServiceEJB.updateBand(band, id);
    }

    public void deleteByGenre(String genre) {
        bandServiceEJB.deleteByGenre(genre);
    }

    public Band getBandById(Long id) {
        return bandServiceEJB.findById(id);
    }

    public void deleteById(Long id) {
        bandServiceEJB.deleteById(id);
    }

    public BandsInfoResponse getBands(String[] sort, String[] filter, int page, int size) {
        System.out.println(bandServiceEJB.getBands(sort, filter, page, size));
        return bandServiceEJB.getBands(sort, filter, page, size);
    }

    public Band getGroupWithMinGenre() {
        return bandServiceEJB.getGroupWithMinGenre();
    }

    public Band addSingleToBand(Long id, Single single) {
        return bandServiceEJB.addSingleToBand(id, single);
    }

    public Single changeSingle(Long bandId, Long singleId, Single single) {
        return bandServiceEJB.changeSingle(bandId, singleId, single);
    }

    public Person addPersonToBand(Long id, Person person) {
        return bandServiceEJB.addPersonToBand(id, person);
    }
}
