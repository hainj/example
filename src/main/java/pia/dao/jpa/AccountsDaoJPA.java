package pia.dao.jpa;

import pia.data.AccountJ;
import pia.servlet.Update;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Created by jakub on 05.01.2017.
 */
public class AccountsDaoJPA extends DaoJPA<AccountJ, Long> implements AccountsDaoJ {
    /**
     * @param em             entity manager
     * @param persistedClass entity type to be persisted by this instance
     */
    public AccountsDaoJPA(EntityManager em, Class<AccountJ> persistedClass) {
        super(em, persistedClass);
    }

    /**
     * Ulozi ucet do databaze
     * @param acc ukladany ucet
     * @return ucet
     */
    @Override
    public AccountJ create(AccountJ acc) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(acc);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
        return acc;
    }


    /**
     * Najde ucet podle primarniho klice
     * @param id primarni klic
     * @return ucet
     */
    @Override
    public AccountJ findByUser(Long id) {
        TypedQuery<AccountJ> q =  entityManager.createQuery("SELECT acc FROM AccountJ acc WHERE acc.idUser.id = :id", AccountJ.class).setParameter("id", id);
        AccountJ u = null;
        try {
            u = q.getSingleResult();
        }catch (NoResultException e){
            String error = "no result";
        }
        return u;
    }

    /**
     * Vybere ucet podle primarniho klice uctu
     * @param id primarni klic uctu
     * @return ucet
     */
    @Override
    public AccountJ findAcc(Long id) {

            TypedQuery<AccountJ> q =  entityManager.createQuery("SELECT acc FROM AccountJ acc WHERE acc.id = :id", AccountJ.class).setParameter("id", id);
            AccountJ u = null;
            try {
                u = q.getSingleResult();
            }catch (NoResultException e){
                String error = "no result";
            }
            return u;
    }

    /**
     * Update uctu na nove hodnoty
     * @param acc upraveny ucet
     * @return ucet
     */
    @Override
    public AccountJ update(AccountJ acc) {
        entityManager.getTransaction().begin();
       Query query = entityManager.createQuery("UPDATE AccountJ a SET a.accNumber=:accN, a.cardNumber=:cardN,a.balance=:bal WHERE a.id=:id");
        query.setParameter("accN",acc.getAccNumber());
        query.setParameter("cardN",acc.getCardNumber());
        query.setParameter("bal", acc.getBalance());
        query.setParameter("id", acc.getId());
        int i = query.executeUpdate();
        entityManager.getTransaction().commit();
        if(i == 1){
            AccountJ accountJ = entityManager.find(persistedClass, acc.getPK());
            return accountJ;
        }
        return null;
    }

    /**
     * Upravi soucasny stav uctu
     * @param acc upravovany ucet
     * @param value nova hodnoty
     * @return ucet
     */
    @Override
    public AccountJ updateBalance(AccountJ acc, double value) {
        entityManager.getTransaction().begin();
       Query query = entityManager.createQuery("UPDATE AccountJ a SET a.balance=:bal WHERE a.id=:id");

        query.setParameter("bal", acc.getBalance());
        query.setParameter("id",acc.getId());
        int i = query.executeUpdate();
        entityManager.getTransaction().commit();
        if(i == 0){
            return  null;
        }

        return acc;
    }

    /**
     * Smaze ucet podle id uzivatele
     * @param id id uzivatele
     * @return pocet smazanych
     */
    @Override
    public int deleteAcc(Long id) {
        entityManager.getTransaction().begin();
        int count = entityManager.createQuery("delete FROM AccountJ a WHERE a.idUser.id =:id").setParameter("id",id).executeUpdate();
        entityManager.getTransaction().commit();
        return count;
    }


}
