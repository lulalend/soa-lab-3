package ru.itmo.soa.ejb;

import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ru.itmo.soa.mainservice.model.Person;
import ru.itmo.soa.mainservice.services.PersonService;

@Stateless
public class PersonServiceEJB {
    @Inject
    private PersonService personService;

    public Person createPerson(Person person) {
        return personService.save(person);
    }

    public void createOrUpdatePerson(Person person) {
        personService.createOrUpdatePerson(person);
    }
}
