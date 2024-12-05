package ru.itmo.soa.ejb.repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ru.itmo.soa.ejb.model.Person;

import java.util.Optional;

@Stateless
public class PersonRepositoryEJB {
    @PersistenceContext
    private EntityManager entityManager;

    public Person save(Person person) {
        entityManager.persist(person);
        return person;
    }

    public Optional<Person> findByPassportID(String passportID) {
        TypedQuery<Person> query = entityManager.createQuery(
                "SELECT p FROM Person p WHERE p.passportID = :passportID", Person.class);
        query.setParameter("passportID", passportID);

        return query.getResultStream().findFirst();
    }
}
