package ru.itmo.soa.grammyservice.model;

import lombok.Data;

@Data
public class Location {
    private Long id;

    private Float x;

    private float y;

    private int z;

    private String name;
}
