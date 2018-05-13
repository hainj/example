package pia.dao.jpa;

import pia.data.AccountJ;
import pia.data.TransactionJ;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by jakub on 05.01.2017.
 */
public class TransactionsDaoJPA extends DaoJPA<TransactionJ,Long> implements TransactionsDaoJ {
    /**
     * @param em             entity manager
     * @param persistedClass entity type to be persisted by this instance
     */
    public TransactionsDaoJPA(EntityManager em, Class<TransactionJ> persistedClass) {
        super(em, persistedClass);
    }

    /**
     * Ulozi do databaze transakci
     * @param trans ukladana transakce
     * @return transakce
     */
    @Override
    public TransactionJ create(TransactionJ trans) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(trans);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return null;
    }

    /**
     * Najde transakce podle uctu
     * @param acc ucet,podle ktereho se hleda
     * @return seznam transakci uctu
     */
    @Override
    public List<TransactionJ> findTransactionByAcc(AccountJ acc) {
        TypedQuery<TransactionJ> q =  entityManager.createQuery("SELECT t FROM TransactionJ t where t.accId.id=:id", persistedClass).setParameter("id",acc.getId());
        List<TransactionJ> u = null;
        try {
            u = q.getResultList();
        }catch (NoResultException e){
            String error = "no result";
        }
        return u;
    }

    @Override
    public TransactionJ findTransaction(AccountJ acc, Long id) {

        return null;
    }

    /**
     * Smaze vsechny transakce uctu
     * @param id id uctu
     * @return pocet smazanych
     */
    @Override
    public int deleteUserTransaction(Long id) {
        entityManager.getTransaction().begin();
        int q =  entityManager.createQuery("DELETE FROM TransactionJ t WHERE t.accId.id=:id").setParameter("id",id).executeUpdate();
        entityManager.getTransaction().commit();
        return 0;
    }


    @Override
    public TransactionJ findOne(Long id) {

        return null;
    }

    /**
     * Smaze 1 transakci podle primarniho klice
     * @param id if of the entity instance
     * @return 1 / 0
     */
    @Override
    public int delete(Long id) {
        entityManager.getTransaction().begin();
        int q =  entityManager.createQuery("DELETE FROM TransactionJ t WHERE t.id=:id").setParameter("id",id).executeUpdate();
        entityManager.getTransaction().commit();
        return q;
    }
}
