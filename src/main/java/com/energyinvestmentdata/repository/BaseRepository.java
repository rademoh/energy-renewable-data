package com.energyinvestmentdata.repository;

import com.energyinvestmentdata.model.request.PaginationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

@Repository
@Slf4j
public class BaseRepository {

    @Autowired
    protected EntityManager entityManager;

    public <T> T save(T entity) {

        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }
    
    public <T> T update(T entity) {
        entityManager.merge(entity);
        return entity;

    }

 

    public <T> void delete(T entity) {
        entityManager.remove(entity);
    }

    public <T> List<T> saveAll(Iterable<T> entities) {
        List<T> result = new ArrayList<>();
        for (T entity : entities) {
            result.add(save(entity));
        }
        return result;
    }

    public <T> T findOne(Class<T> type, Long id) {
        T obj = entityManager.find(type, id);
        if (!StringUtils.isEmpty(obj)) {
            entityManager.detach(obj);
            return obj;
        }
        return null;
    }

    public <T> Optional<T> findOneOptional(Class<T> type, Long id) {
        T obj = entityManager.find(type, id);
        if (!StringUtils.isEmpty(obj)) {
            entityManager.detach(obj);
            return Optional.of(obj);
        }
        return Optional.empty();
    }


    public <T> List<T> findAllById(Class<T> tableName, Iterable<T> ids) {
        TypedQuery<T> typeQuery = entityManager.createQuery("SELECT k FROM " + tableName.getSimpleName() + " k WHERE k.id in :Ids",
                tableName);
        typeQuery.setParameter("Ids", ids);

        return typeQuery.getResultList();
    }

    public <T, K> List<T> findAllById(Class<T> tableName, Set<K> ids) {
        TypedQuery<T> typeQuery = entityManager.createQuery("SELECT k FROM " + tableName.getSimpleName() + " k WHERE k.id in :Ids",
                tableName);
        typeQuery.setParameter("Ids", ids);
        return typeQuery.getResultList();
    }

    public <T> List<T> findAll(Class<T> tableName) {
        TypedQuery<T> typeQuery = entityManager.createQuery("SELECT k FROM " + tableName.getSimpleName(), tableName);
        return typeQuery.getResultList();
    }

    public <T> List<T> findAllBy(Class<T> tableName, String columnName, Object value) {
        String sqlQuery = "select e from  " + tableName.getSimpleName() + " e where LOWER(e." + columnName
                + ") = LOWER(:value)";
        TypedQuery<T> typeQuery = entityManager.createQuery(sqlQuery, tableName).setParameter("value", value);

        return typeQuery.getResultList();
    }

    public <T, V, K> List<T> findAllBy(Class<T> tableName, V columnName, K value) {
        String sqlQuery = "select e from  " + tableName.getSimpleName() + " e where LOWER(e." + columnName
                + ") = LOWER(:value)";
        TypedQuery<T> typeQuery = entityManager.createQuery(sqlQuery, tableName).setParameter("value", value);
        return typeQuery.getResultList();
    }

    public <T, V, K> T findOneBy(Class<T> tableName, V columnName, K value) {
        String sqlQuery = "select e from  " + tableName.getSimpleName() + " e where LOWER(e." + columnName
                + ") = LOWER(:value)";
        TypedQuery<T> typeQuery = entityManager.createQuery(sqlQuery, tableName).setParameter("value", value);

        return typeQuery.getResultList().stream().findFirst().orElse(null);
    }

    public <T, V, K> Optional<T> findOneByOptional(Class<T> tableName, V columnName, K value) {
        String sqlQuery = "select e from  " + tableName.getSimpleName() + " e where LOWER(e." + columnName
                + ") = LOWER(:value)";

        TypedQuery<T> typeQuery = entityManager.createQuery(sqlQuery, tableName).setParameter("value", value);
        return Optional.ofNullable(typeQuery.getResultList().stream().findFirst().orElse(null));
    }

    public <T> Optional<T> findOneByOptional(Class<T> tableName, Map<String, Object> filter) {

        AtomicReference<String> sqlQuery = new AtomicReference<>();
        sqlQuery.set("select e from  " + tableName.getSimpleName() + " e where");
        filter.keySet().forEach(i -> sqlQuery.set(sqlQuery.get() + " e." + i + " = :" + i + " AND"));
        sqlQuery.set(sqlQuery.get().substring(0, sqlQuery.get().length() - 4));
        log.info("SLQ: {}", sqlQuery.get());

        TypedQuery<T> typeQuery = entityManager.createQuery(sqlQuery.get(), tableName);

        filter.forEach(typeQuery::setParameter);

        return Optional.ofNullable(typeQuery.getResultList().stream().findFirst().orElse(null));
    }

    public <T> Page<T> findAllByOr(Class<T> tableName, Map<String, Object> filter, PaginationRequest page) {
        AtomicReference<String> sqlQuery = new AtomicReference<>();
        sqlQuery.set("select e from  " + tableName.getSimpleName() + " e " + (filter.isEmpty() ? "" : "where "));

        filter.keySet().stream().forEach(i -> sqlQuery.set(sqlQuery.get() + " " + i + " = :" + i + " OR"));

        sqlQuery.set(filter.isEmpty() ? sqlQuery.get() : sqlQuery.get().substring(0, sqlQuery.get().length() - 3));

        TypedQuery<Long> countQuery = entityManager.createQuery(sqlQuery.get().replace("select e from", "select count(e) from"),
                Long.class);
        TypedQuery<T> typeQuery = entityManager.createQuery(sqlQuery.get(), tableName);

        filter.forEach(typeQuery::setParameter);
        filter.forEach(countQuery::setParameter);

        log.info("SLQ: {}", sqlQuery.get());
        Long contentSize = countQuery.getSingleResult();
        page.setSize(page.getSize() == 0 ? (contentSize.intValue() == 0 ? 1 : contentSize.intValue()) : page.getSize());

        typeQuery.setFirstResult((page.getPage() - 1) * page.getSize()).setMaxResults(page.getSize());

        return new PageImpl<>(typeQuery.getResultList(), PageRequest.of(page.getPage() - 1, page.getSize()),
                contentSize);
    }

    //The filter map object takes care of both OR, AND & LIKE;
    //If the value is of type List means it represents OR;
    //If the value is of type Map means it represents LIKE;
    //By default all value are chained with AND
    @SuppressWarnings("unchecked")
    public <T> Page<T> findAllBy(Class<T> tableName, Map<String, Object> filterTemp, PaginationRequest page) {

        Map<String, Object> filter = new HashMap<>(filterTemp);


        AtomicReference<String> sqlQuery = new AtomicReference<>();
        sqlQuery.set("select e from  " + tableName.getSimpleName() + " e " + (filter.isEmpty() ? "" : "where "));

        filter.entrySet().stream().filter(o -> o.getValue() == null).forEach(i -> {
            sqlQuery.set(sqlQuery.get() + " " + i.getKey() + " IS NULL AND");
        });

        filter.entrySet().stream().filter(o -> (o.getValue() != null && !(o.getValue() instanceof List<?>) && !(o.getValue() instanceof Map)))
                .forEach(i -> {
                    sqlQuery.set(sqlQuery.get() + " " + i.getKey() + " = :" + i.getKey() + " AND");
                });

        filter.keySet().stream().filter(o -> (filter.get(o) instanceof Map))
                .forEach(i -> ((Map<String, String>) filter.get(i)).keySet().stream().forEach(k -> {
                    String value = ((Map<String, String>) filter.get(i)).get(k);
                    sqlQuery.set(sqlQuery.get() + " " + k + " LIKE :" + k + " AND");
                }));


        for (String s : filter.keySet()) {
            if ((filter.get(s) != null && filter.get(s) instanceof List<?>)) {
                sqlQuery.set(sqlQuery.get() + "(");
                IntStream.range(0, ((List<?>) filter.get(s)).size()).forEach(index -> {
                    sqlQuery.set(sqlQuery.get() + " " + s + " = :" + s + "" + index + " OR");
                });
                sqlQuery.set(sqlQuery.get().substring(0, sqlQuery.get().length() - 3) + ") AND");
            }
        }

        sqlQuery.set(filter.isEmpty() ? sqlQuery.get() : sqlQuery.get().substring(0, sqlQuery.get().length() - 4));

        TypedQuery<Long> countQuery = entityManager.createQuery(sqlQuery.get().replace("select e from", "select count(e) from"),
                Long.class);
        TypedQuery<T> typeQuery = entityManager.createQuery(sqlQuery.get(), tableName);

        filter.keySet().stream().filter(i -> filter.get(i) != null && !(filter.get(i) instanceof List<?>) && !(filter.get(i) instanceof Map)).forEach(i -> {
            typeQuery.setParameter(i, filter.get(i));
            countQuery.setParameter(i, filter.get(i));
        });

        filter.keySet().stream().filter(o -> filter.get(o) != null && (filter.get(o) instanceof List<?>)).forEach(i -> {
            List<?> values = (List<?>) filter.get(i);

            IntStream.range(0, values.size()).forEach(index -> {
                typeQuery.setParameter(i + "" + index, values.get(index));
                countQuery.setParameter(i + "" + index, values.get(index));
            });
        });

        filter.keySet().stream().filter(o -> filter.get(o) != null && (filter.get(o) instanceof Map))
                .forEach(i -> ((Map<String, String>) filter.get(i)).keySet().stream().forEach(k -> {
                    String value = ((Map<String, String>) filter.get(i)).get(k);

                    typeQuery.setParameter(k, value);
                    countQuery.setParameter(k, value);

                }));

        Long contentSize = countQuery.getSingleResult();
        page.setSize(page.getSize() == 0 ? (contentSize.intValue() == 0 ? 1 : contentSize.intValue()) : page.getSize());

        typeQuery.setFirstResult((page.getPage() - 1) * page.getSize()).setMaxResults(page.getSize());

        return new PageImpl<>(typeQuery.getResultList(), PageRequest.of(page.getPage() - 1, page.getSize()),
                contentSize);
    }
}
