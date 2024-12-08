package ru.itmo.soa.ejb.model.dto;

import lombok.Data;
import ru.itmo.soa.ejb.model.Coordinates;
import ru.itmo.soa.ejb.model.MusicGenre;
import ru.itmo.soa.ejb.model.Person;
import ru.itmo.soa.ejb.model.Single;

import java.io.Serializable;
import java.util.List;

@Data
public class BandUpdate implements Serializable {
    private String name;
    private Coordinates coordinates;
    private Integer numberOfParticipants;
    private String description;
    private MusicGenre genre;
    private Person frontMan;
    private List<Single> singles;
}
