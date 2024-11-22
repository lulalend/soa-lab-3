package ru.itmo.soa.grammyservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Band {
    private Long id;

    private String name;

    private Coordinates coordinates;

    private LocalDateTime creationDate;

    private Integer numberOfParticipants;

    private String description;

    private MusicGenre genre;

    private Person frontMan;

    private List<Single> singles;
}