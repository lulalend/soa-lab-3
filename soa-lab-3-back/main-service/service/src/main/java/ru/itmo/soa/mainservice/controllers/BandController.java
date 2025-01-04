package ru.itmo.soa.mainservice.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.soa.ejb.model.Band;
import ru.itmo.soa.ejb.model.MusicGenre;
import ru.itmo.soa.ejb.model.Person;
import ru.itmo.soa.ejb.model.Single;
import ru.itmo.soa.ejb.model.dto.BandUpdate;
import ru.itmo.soa.ejb.model.dto.BandsInfoResponse;
import ru.itmo.soa.mainservice.services.BandService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BandController {

    @Autowired
    private BandService bandService;

    @PostMapping("/bands")
    public ResponseEntity<Band> createBand(@Valid @RequestBody Band band) {
        Band createdBand = bandService.save(band);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBand);
    }

    @GetMapping("/bands")
    public ResponseEntity<BandsInfoResponse> getBands(
            @RequestParam(required = false, value = "sort") String[] sort,
            @RequestParam(required = false, value = "filter") String[] filter,
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "10", value = "size") int size) {

        BandsInfoResponse bands = bandService.getBands(sort, filter, page, size);
        return ResponseEntity.ok(bands);
    }

    @GetMapping("/bands/{id}")
    public Band getBandById(@Valid @PathVariable(value = "id") Long id) {
        return bandService.getBandById(id);
    }

    @PatchMapping("/bands/{id}")
    public ResponseEntity<Band> updateBand(@Valid @PathVariable(value = "id") Long id, @RequestBody BandUpdate bandUpdate) {
        Band updatedBand = bandService.updateBand(bandUpdate, id);
        return ResponseEntity.ok(updatedBand);
    }

    @DeleteMapping("/bands/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBandById(@Valid @PathVariable(value = "id") Long id) {
        bandService.deleteById(id);
    }

    @GetMapping("/bands/genre")
    public ResponseEntity<List<MusicGenre>> getAllGenres() {
        return ResponseEntity.ok(bandService.getAllGenres());
    }

    @DeleteMapping("/bands/genre/{genre}")
    public ResponseEntity<Void> deleteBandsByGenre(@Valid @PathVariable(value = "genre") String genre) {
        bandService.deleteByGenre(genre);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/bands/genre/min")
    public ResponseEntity<Band> getGroupWithMinGenre() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        System.out.println("Запрос пришёл в " + formattedDate);
        Band band = bandService.getGroupWithMinGenre();
        return ResponseEntity.ok(band);
    }

//    Запросы со второго сервера
    @PostMapping("/band/{id}/singles/add")
    public ResponseEntity<Band> addSingleToBand(@Valid @PathVariable(value = "id") Long id, @RequestBody Single single) {
        Band updatedBand = bandService.addSingleToBand(id, single);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedBand);
    }

    @PutMapping("/band/{bandId}/singles/{singleId}")
    public ResponseEntity<Single> changeSingle(@Valid @PathVariable(value = "bandId") Long bandId, @PathVariable(value = "singleId") Long singleId, @RequestBody Single single) {
        Single updatedSingle = bandService.changeSingle(bandId, singleId, single);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedSingle);
    }

    @PostMapping("/band/{id}/participants/add")
    public ResponseEntity<Person> addPersonToBand(@Valid @PathVariable(value = "id") Long id, @RequestBody Person person) {
        Person newPerson = bandService.addPersonToBand(id, person);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
    }
}
