package com.github.mstawowiak.money.transfer.infrastructure.jpa.common;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProvider {

    private static final String PERISTENCE_UNIT_NAME = "money-transfer-pu";

    private EntityManagerFactory emf;

    @PostConstruct
    protected void init() {
        emf = Persistence.createEntityManagerFactory(PERISTENCE_UNIT_NAME);
    }

    @PreDestroy
    protected void cleanUp() {
        emf.close();
    }

    @Produces
    @ApplicationScoped
    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EntityManager newEntityManager() {
        return emf.createEntityManager();
    }

    public void disposeEntityManager(@Disposes EntityManager em) {
        em.close();
    }
}
