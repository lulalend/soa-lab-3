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
import ru.itmo.soa.ejb.model.Single;

import java.util.Optional;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Pool("ejb-band-pool")
public class SingleRepositoryEJB {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public Single save(Single single) {
        entityManager.persist(single);
        return single;
    }

    public Optional<Single> findById(Long id) {
        Single single = entityManager.find(Single.class, id);
        return Optional.ofNullable(single);
    }

    public Optional<Single> findByName(String name) {
        TypedQuery<Single> query = entityManager.createQuery(
                "SELECT s FROM Single s WHERE s.name = :name", Single.class);
        query.setParameter("name", name);

        return query.getResultStream().findFirst();
    }
}
