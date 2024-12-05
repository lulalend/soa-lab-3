package ru.itmo.soa.ejb.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "ID must be greater than or equal to 1")
    private Long id;

    @NotNull(message = "X coordinate is required")
    @Digits(integer = 30, fraction = 8, message = "X must have up to 30 digits before the decimal point and up to 8 after")
    @Column(nullable = false, precision = 38, scale = 8)
    private BigDecimal x;

    @Min(value = -439, message = "Y coordinate cannot be less than -439")
    @NotNull(message = "Y coordinate is required")
    @Digits(integer = 30, fraction = 8, message = "Y must have up to 30 digits before the decimal point and up to 8 after")
    @Column(nullable = false, precision = 38, scale = 8)
    private BigDecimal y;

    @Digits(integer = 38, fraction = 0, message = "Z must be an integer with up to 38 digits")
    @Column(nullable = false, precision = 38)
    @NotNull(message = "Z coordinate is required")
    private BigInteger z;

    @Size(min = 1, message = "Name must have at least 1 character")
    @Column(columnDefinition = "TEXT")
    private String name;
}
