package ru.itmo.soa.mainservice.exceptions;

import lombok.Data;

@Data
public class DefaultError {
    private int code;
    private String message;

    public DefaultError(String message) {
        this.code = 404;
        this.message = message;
    }
}

