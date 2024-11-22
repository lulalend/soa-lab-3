package ru.itmo.soa.grammyservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Person {
    private Long id;

    private String name;

    private String passportID;

    @PastOrPresent(message = "Birthday must be a past or present date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private Location location;

    private Long bandID;
}
