package ru.itmo.soa.ejb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(integer = 38, fraction = 0, message = "X must be an integer with up to 38 digits")
    @Column(nullable = false, precision = 38)
    private BigDecimal x;

    @NotNull(message = "Y coordinate cannot be null")
    @Min(value = -439, message = "Y coordinate cannot be less than -439")
    @Digits(integer = 30, fraction = 8, message = "Y must have up to 30 digits before the decimal point and up to 8 after")
    @Column(nullable = false, precision = 38, scale = 8)
    private BigDecimal y;
}