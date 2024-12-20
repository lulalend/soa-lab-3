package ru.itmo.soa.ejb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Band implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "Id must be greater than or equal to 1")
    private Long id;

    @NotNull(message = "Name is required")
    @Size(min = 1, message = "Name must have at least 1 character")
    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    private String name;

    @NotNull(message = "Coordinates are required")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Coordinates coordinates;

    @Column(nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime creationDate;

    @Min(value = 1, message = "Number of participants must be greater than 0")
    @Column(nullable = false)
    private Integer numberOfParticipants;

    @NotNull(message = "Description is required")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Genre is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MusicGenre genre;

    @OneToOne(cascade = CascadeType.ALL)
    private Person frontMan;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Single> singles;

    @PrePersist
    protected void onCreate() {
        if (creationDate == null) {
            creationDate = OffsetDateTime.now();
        }
    }
}