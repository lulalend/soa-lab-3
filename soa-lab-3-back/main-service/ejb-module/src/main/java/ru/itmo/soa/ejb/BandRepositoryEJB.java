package ru.itmo.soa.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import ru.itmo.soa.ejb.model.Band;
import ru.itmo.soa.ejb.model.MusicGenre;

import java.util.List;
import java.util.Optional;


@Stateless
public class BandRepositoryEJB {

    @PersistenceContext
    private EntityManager entityManager;

    public Band create(Band band) {
        entityManager.persist(band);
        return band;
    }

    public Band update(Band band) {
        return entityManager.merge(band);
    }

    public Optional<Band> findById(Long id) {
        Band band = entityManager.find(Band.class, id);
        return Optional.ofNullable(band);
    }

    public void deleteById(Long id) {
        Band band = entityManager.find(Band.class, id);
        if (band != null) {
            entityManager.remove(band);
        }
    }

    public void deleteByGenre(MusicGenre genre) {
        Query query = entityManager.createQuery("DELETE FROM Band b WHERE b.genre = :genre");
        query.setParameter("genre", genre);
        query.executeUpdate();
    }

    public List<Band> findAllSortedByGenreAscNameAsc() {
        return entityManager.createQuery("SELECT b FROM Band b ORDER BY b.genre ASC, b.name ASC", Band.class)
                .getResultList();
    }

    public int count(String[] filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Band> root = countQuery.from(Band.class);

        // Создаем запрос для фильтрации
        CriteriaQuery<Band> filteredQuery = BandSpecificationEJB.createCriteriaQuery(filters, cb, root);

        // Используем COUNT для подсчета записей
        countQuery.select(cb.count(root)).where(filteredQuery.getRestriction());

        return entityManager.createQuery(countQuery).getSingleResult();
    }

    public List<Band> findBySpecificationAndSort(String[] filters, String[] sort, int page, int size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Band> query = cb.createQuery(Band.class);
        Root<Band> root = query.from(Band.class);

        // Применение фильтров через BandSpecificationEJB
        CriteriaQuery<Band> filteredQuery = BandSpecificationEJB.createCriteriaQuery(filters, cb, root);

        // Применение сортировки
        if (sort != null) {
            for (String sortParam : sort) {
                String[] parts = sortParam.split("\\[");
                if (parts.length < 1 || parts.length > 2) {
                    throw new IllegalArgumentException("Invalid sort format: " + sortParam);
                }

                String property = parts[0].trim();
                String direction = (parts.length > 1 && parts[1].endsWith("]"))
                        ? parts[1].substring(0, parts[1].length() - 1).trim()
                        : "asc";

                if (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
                    throw new IllegalArgumentException("Invalid sort direction: " + direction);
                }

                if (direction.equalsIgnoreCase("asc")) {
                    query.orderBy(cb.asc(root.get(property)));
                } else {
                    query.orderBy(cb.desc(root.get(property)));
                }
            }
        }

        // Объединяем отфильтрованный запрос с сортировкой
        query.select(root).where(filteredQuery.getRestriction());

        // Выполнение запроса с пагинацией
        return entityManager.createQuery(query)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
