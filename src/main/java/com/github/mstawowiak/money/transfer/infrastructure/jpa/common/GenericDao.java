package com.github.mstawowiak.money.transfer.infrastructure.jpa.common;

import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class GenericDao<T> {

    @Inject
    protected EntityManager em;

    public List<T> findAll(Class<T> clazz) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        cq.select(root);

        return em.createQuery(cq).getResultList();
    }

    public T find(Class<T> clazz, Object entity) {
        return em.find(clazz, entity);
    }

    public void merge(Collection<T> entities) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            for (T entity : entities) {
                em.merge(entity);
            }

            transaction.commit();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                em.getTransaction().rollback();
            }
            log.error("Exception occured during merge: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

}