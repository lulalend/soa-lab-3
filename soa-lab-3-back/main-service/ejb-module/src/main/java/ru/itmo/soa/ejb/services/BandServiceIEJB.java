package ru.itmo.soa.ejb.services;

import jakarta.ejb.Remote;
import ru.itmo.soa.ejb.model.Band;
import ru.itmo.soa.ejb.model.MusicGenre;
import ru.itmo.soa.ejb.model.Person;
import ru.itmo.soa.ejb.model.Single;
import ru.itmo.soa.ejb.model.dto.BandUpdate;
import ru.itmo.soa.ejb.model.dto.BandsInfoResponse;

import java.util.List;

@Remote
public interface BandServiceIEJB {
    public Band saveBand(Band band);
    public BandsInfoResponse getBands(String[] sort, String[] filter, int page, int size);
    public Band findById(Long id);

    public void deleteById(Long id);

    public Band updateBand(BandUpdate bandUpdate, Long id);

    public List<MusicGenre> getAllGenres();

    public void deleteByGenre(String genreStr);

    public Band getGroupWithMinGenre();

    public Band addSingleToBand(Long id, Single single);

    public Single changeSingle(Long bandId, Long singleId, Single single);

    public Person addPersonToBand(Long id, Person person);
}
