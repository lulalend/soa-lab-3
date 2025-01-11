package ru.itmo.soa.ejb.repositories;

import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.TypedQuery;
import org.jboss.ejb3.annotation.Pool;
import ru.itmo.soa.ejb.model.Person;

import java.util.Optional;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Pool("ejb-band-pool")
public class PersonRepositoryEJB {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
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
