package pia.dao.jpa;

import pia.data.RightsJ;
import pia.data.UserJ;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Created by jakub on 04.01.2017.
 */
public class RightsDaoJPA extends DaoJPA<RightsJ, Integer> implements RightsDaoJ {


    /**
     * @param em             entity manager
     * @param persistedClass entity type to be persisted by this instance
     */
    public RightsDaoJPA(EntityManager em, Class<RightsJ> persistedClass) {
        super(em, persistedClass);
    }

    /**
     * Ulozi prava do databaze
     * @param rights ukladana prava
     * @return prava
     */
    @Override
    public RightsJ create(RightsJ rights) {


        try {
        entityManager.getTransaction().begin();
        entityManager.persist(rights);
        entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

        return null;
    }

    /**
     * Najde prava uzivatel podle nazvu
     * @param role nazev prav
     * @return prava
     */
    @Override
    public RightsJ find(String role) {
        TypedQuery<RightsJ> q =  entityManager.createQuery("SELECT u FROM RightsJ u WHERE u.role = :rol", persistedClass).setParameter("rol", role);
        RightsJ u = null;
        try {
            u = q.getSingleResult();
        }catch (NoResultException e){
            String error = "no result";
        }
        return u;
    }

}
