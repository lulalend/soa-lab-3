package ru.itmo.soa.grammyservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.itmo.soa.grammyservice.model.Band;
import ru.itmo.soa.grammyservice.model.Person;
import ru.itmo.soa.grammyservice.model.Single;
import ru.itmo.soa.grammyservice.services.RibbonCustomService;

@RestController
@RequestMapping("/api/v1/grammy")
public class GrammyController {

    private final RestTemplate restTemplate;

    private final RibbonCustomService ribbonService;

    @Autowired
    public GrammyController(RestTemplate restTemplate, RibbonCustomService ribbonService) {
        this.restTemplate = restTemplate;
        this.ribbonService = ribbonService;
    }

    @PostMapping("/band/{bandId}/singles/add")
    public ResponseEntity<Band> addSingle(@PathVariable(value = "bandId") Long bandId, @RequestBody Single single) {
        String url = String.format(ribbonService.getURI() + "/api/v1/band/%d/singles/add", bandId);
        return restTemplate.postForEntity(url, single, Band.class);
    }

    @PutMapping("/bands/{bandId}/singles/{singleId}")
    public ResponseEntity<Single> changeSingle(@PathVariable(value = "bandId") Long bandId, @PathVariable(value = "singleId") Long singleId, @RequestBody Single single) {
        String url = String.format(ribbonService.getURI() + "/api/v1/band/%d/singles/%d", bandId, singleId);
        return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(single), Single.class);
    }

    @PostMapping("/band/{bandId}/participants/add")
    public ResponseEntity<Person> addParticipant(@PathVariable(value = "bandId") Long bandId, @RequestBody Person participant) {
        String url = String.format(ribbonService.getURI() + "/api/v1/band/%d/participants/add", bandId);
        return restTemplate.postForEntity(url, participant, Person.class);
    }
}
