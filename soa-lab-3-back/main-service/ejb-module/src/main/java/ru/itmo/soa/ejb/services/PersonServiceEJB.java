package ru.itmo.soa.ejb.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import ru.itmo.soa.ejb.repositories.PersonRepositoryEJB;
import ru.itmo.soa.ejb.model.Person;

import java.util.Optional;

@Stateless
public class PersonServiceEJB {
    @Inject
    private PersonRepositoryEJB personRepository;

    @Transactional
    public Person createPerson(Person person) {
        Optional<Person> existingPerson = personRepository.findByPassportID(person.getPassportID());

        if (existingPerson.isPresent()) {
            throw new IllegalArgumentException("Person with PassportID " + person.getPassportID() + " already exists.");
        }

        return personRepository.save(person);
    }

    @Transactional
    public void createOrUpdatePerson(Person person) {
        Optional<Person> existingPerson = personRepository.findByPassportID(person.getPassportID());

        if (existingPerson.isPresent()) {
            Person personToUpdate = existingPerson.get();
            personToUpdate.setName(person.getName());
            personToUpdate.setBirthday(person.getBirthday());
            personToUpdate.setLocation(person.getLocation());
            personToUpdate.setBandID(person.getBandID());
            personRepository.save(personToUpdate);
        } else {
            personRepository.save(person);
        }
    }
}
