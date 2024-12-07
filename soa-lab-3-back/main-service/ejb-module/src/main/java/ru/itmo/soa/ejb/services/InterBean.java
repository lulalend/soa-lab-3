package ru.itmo.soa.ejb.services;

import jakarta.ejb.Stateless;

@Stateless
public class InterBean implements Inter {

    @Override
    public void hello() {
        System.out.println("hello");
    }
}
