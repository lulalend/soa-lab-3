package ru.itmo.soa.mainservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "ID must be greater than or equal to 1")
    private Long id;

    @Size(min = 1, message = "Name must have at least 1 character")
    @Column(columnDefinition = "TEXT")
    private String name;

    @NotNull(message = "Passport ID is required")
    @Column(unique = true, nullable = false, columnDefinition = "TEXT")
    private String passportID;

    @PastOrPresent(message = "Birthday must be a past or present date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull(message = "Location is required")
    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @Min(value = 1, message = "Band ID must be greater than or equal to 1")
    private Long bandID;
}
