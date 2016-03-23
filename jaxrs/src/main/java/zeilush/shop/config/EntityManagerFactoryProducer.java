package zeilush.shop.config;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by AAA on 15.03.2016.
 */
@ApplicationScoped
@Startup
public class EntityManagerFactoryProducer {
    @PersistenceContext
    private EntityManager em;

    @Produces
    public EntityManager em() {
        return em;
    }

    public void dispose(@Disposes EntityManager em) {
        em.close();
    }

//    @Produces
//    @ApplicationScoped
//    public EntityManagerFactory createEntityManagerFactory() {
//        return Persistence.createEntityManagerFactory("MyPU");
//    }
//
//    public void close(@Disposes EntityManagerFactory entityManagerFactory) {
//        entityManagerFactory.close();
//    }
//
//    @Produces
//    @RequestScoped
//    public EntityManager createEntityManager(EntityManagerFactory entityManagerFactory) {
//        return entityManagerFactory.createEntityManager();
//    }
//
//    public void close(@Disposes EntityManager entityManager) {
//        entityManager.close();
//    }

}
