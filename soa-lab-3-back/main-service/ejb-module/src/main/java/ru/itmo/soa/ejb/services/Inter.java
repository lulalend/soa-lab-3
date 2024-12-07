package ru.itmo.soa.ejb.services;

import jakarta.ejb.Remote;

@Remote
public interface Inter {
    void hello();
}
