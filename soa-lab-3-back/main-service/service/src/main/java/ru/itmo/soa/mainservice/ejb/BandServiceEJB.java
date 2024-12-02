package ru.itmo.soa.mainservice.ejb;

import jakarta.ejb.Remote;
import ru.itmo.soa.mainservice.model.Band;
import ru.itmo.soa.mainservice.model.MusicGenre;
import ru.itmo.soa.mainservice.model.Person;
import ru.itmo.soa.mainservice.model.Single;
import ru.itmo.soa.mainservice.model.dto.BandUpdate;
import ru.itmo.soa.mainservice.model.dto.BandsInfoResponse;
import java.util.List;

@Remote
public interface BandServiceEJB {
    Band createBand(Band band);
    BandsInfoResponse getBands(String[] sort, String[] filter, int page, int size);
    Band getBandById(Long id);
    void deleteBandById(Long id);
    Band updateBand(BandUpdate bandUpdate, Long id);
    List<MusicGenre> getAllGenres();
    void deleteBandsByGenre(String genreStr);
    Band getGroupWithMinGenre();
    Band addSingleToBand(Long id, Single single);
    Single changeSingle(Long bandId, Long singleId, Single single);
    Person addPersonToBand(Long id, Person person);
}
