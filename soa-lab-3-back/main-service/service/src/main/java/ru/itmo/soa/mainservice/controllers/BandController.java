package ru.itmo.soa.mainservice.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.soa.mainservice.ejb.BandServiceEJB;
import ru.itmo.soa.mainservice.model.Band;
import ru.itmo.soa.mainservice.model.MusicGenre;
import ru.itmo.soa.mainservice.model.Person;
import ru.itmo.soa.mainservice.model.Single;
import ru.itmo.soa.mainservice.model.dto.BandUpdate;
import ru.itmo.soa.mainservice.model.dto.BandsInfoResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bands")
public class BandController {

    private BandServiceEJB bandServiceEJB;

    @PostMapping
    public ResponseEntity<Band> createBand(@Valid @RequestBody Band band) {
        Band createdBand = bandServiceEJB.createBand(band);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBand);
    }

    @GetMapping
    public ResponseEntity<BandsInfoResponse> getBands(
            @RequestParam(required = false, value = "sort") String[] sort,
            @RequestParam(required = false, value = "filter") String[] filter,
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size) {

            BandsInfoResponse bands = bandServiceEJB.getBands(sort, filter, page, size);
            return ResponseEntity.ok(bands);
    }

    @GetMapping("/{id}")
    public Band getBandById(@Valid @PathVariable(value = "id") Long id) {
        return bandServiceEJB.getBandById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Band> updateBand(@Valid @PathVariable(value = "id") Long id, @RequestBody BandUpdate bandUpdate) {
        Band updatedBand = bandServiceEJB.updateBand(bandUpdate, id);
        return ResponseEntity.ok(updatedBand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBandById(@Valid @PathVariable(value = "id") Long id) {
        bandServiceEJB.deleteBandById(id);
    }

    @GetMapping("/genre")
    public ResponseEntity<List<MusicGenre>> getAllGenres() {
        return ResponseEntity.ok(bandServiceEJB.getAllGenres());
    }

    @DeleteMapping("/genre/{genre}")
    public ResponseEntity<Void> deleteBandsByGenre(@Valid @PathVariable(value = "genre") String genre) {
        bandServiceEJB.deleteBandsByGenre(genre);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/genre/min")
    public ResponseEntity<Band> getGroupWithMinGenre() {
        Band band = bandServiceEJB.getGroupWithMinGenre();
        return ResponseEntity.ok(band);
    }

//    Запросы со второго сервера
    @PostMapping("/{id}/singles")
    public ResponseEntity<Band> addSingleToBand(@Valid @PathVariable(value = "id") Long id, @RequestBody Single single) {
        Band updatedBand = bandServiceEJB.addSingleToBand(id, single);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedBand);
    }

    @PutMapping("/{bandId}/singles/{singleId}")
    public ResponseEntity<Single> changeSingle(@Valid @PathVariable(value = "bandId") Long bandId, @PathVariable(value = "singleId") Long singleId, @RequestBody Single single) {
        Single updatedSingle = bandServiceEJB.changeSingle(bandId, singleId, single);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedSingle);
    }

    @PostMapping("/{id}/participants")
    public ResponseEntity<Person> addPersonToBand(@Valid @PathVariable(value = "id") Long id, @RequestBody Person person) {
        Person newPerson = bandServiceEJB.addPersonToBand(id, person);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
    }
}
