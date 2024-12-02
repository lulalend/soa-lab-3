package ru.itmo.soa.mainservice.ejb;

import jakarta.ejb.Remote;
import ru.itmo.soa.mainservice.model.Person;

@Remote
public interface PersonServiceEJB {
    Person createPerson(Person person);
    void createOrUpdatePerson(Person person);
}
