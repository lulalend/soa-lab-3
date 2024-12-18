package ru.itmo.soa.ejb.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jboss.ejb3.annotation.Pool;
import ru.itmo.soa.ejb.model.Band;

public class BandSpecificationEJB {

    public static CriteriaQuery<Band> createCriteriaQuery(String[] filters, CriteriaBuilder cb, Root<Band> root) {
        CriteriaQuery<Band> query = cb.createQuery(Band.class);
        Predicate predicate = cb.conjunction();

        if (filters != null) {
            for (String filter : filters) {
                if (!filter.contains("[")) {
                    throw new IllegalArgumentException("Invalid filter format: " + filter);
                }

                String[] paramParts = filter.split("\\[");
                if (paramParts.length != 2) {
                    throw new IllegalArgumentException("Invalid filter parameter format: " + filter);
                }

                String field = paramParts[0].trim();
                String operatorAndValue = paramParts[1].trim();

                String operator;
                String value;

                if (operatorAndValue.contains("]")) {
                    int operatorEndIndex = operatorAndValue.indexOf("]");
                    operator = operatorAndValue.substring(0, operatorEndIndex).trim();
                    value = operatorAndValue.substring(operatorEndIndex + 1).trim();
                } else {
                    throw new IllegalArgumentException("Invalid filter format: " + filter);
                }

                Predicate condition = switch (operator.toLowerCase()) {
                    case "eq" -> handleEqual(root, cb, field, value);
                    case "neq" -> handleNotEqual(root, cb, field, value);
                    case "gt" -> handleGreaterThan(root, cb, field, value);
                    case "gte" -> handleGreaterThanOrEqualTo(root, cb, field, value);
                    case "lt" -> handleLessThan(root, cb, field, value);
                    case "lte" -> handleLessThanOrEqualTo(root, cb, field, value);
                    case "~" -> handleLike(root, cb, field, value);
                    default -> throw new IllegalArgumentException("Unknown operator: " + operator);
                };

                predicate = cb.and(predicate, condition);
            }
        }

        query.where(predicate);
        return query;
    }

    // Реализация обработки каждого оператора
    private static Predicate handleEqual(Root<Band> root, CriteriaBuilder cb, String field, String value) {
        return cb.equal(root.get(field), value);
    }

    private static Predicate handleNotEqual(Root<Band> root, CriteriaBuilder cb, String field, String value) {
        return cb.notEqual(root.get(field), value);
    }

    private static Predicate handleGreaterThan(Root<Band> root, CriteriaBuilder cb, String field, String value) {
        return cb.greaterThan(root.get(field), value);
    }

    private static Predicate handleGreaterThanOrEqualTo(Root<Band> root, CriteriaBuilder cb, String field, String value) {
        return cb.greaterThanOrEqualTo(root.get(field), value);
    }

    private static Predicate handleLessThan(Root<Band> root, CriteriaBuilder cb, String field, String value) {
        return cb.lessThan(root.get(field), value);
    }

    private static Predicate handleLessThanOrEqualTo(Root<Band> root, CriteriaBuilder cb, String field, String value) {
        return cb.lessThanOrEqualTo(root.get(field), value);
    }

    private static Predicate handleLike(Root<Band> root, CriteriaBuilder cb, String field, String value) {
        return cb.like(cb.upper(root.get(field)), "%" + value.toUpperCase() + "%");
    }
}
