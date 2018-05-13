package pia.dao.jpa;

import pia.data.UserJ;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 03.01.2017.
 */
public class UsersDaoJPA extends DaoJPA<UserJ, Long> implements UsersDaoJ {


    /**
     * @param em             entity manager
     * @param persistedClass entity type to be persisted by this instance
     */
    public UsersDaoJPA(EntityManager em, Class<UserJ> persistedClass) {
        super(em, persistedClass);
    }


    /**
     * Najde uzivatele podle primarniho klice
     * @param id prim. klic uzivatele
     * @return uzivatele
     */
    @Override
    public UserJ findOne(Long id) {
        TypedQuery<UserJ> q =  entityManager.createQuery("SELECT u FROM UserJ u WHERE u.id = :id", UserJ.class).setParameter("id", id);
        UserJ u = null;
        try {
            u = q.getSingleResult();
        }catch (NoResultException e){
            String error = "no result";
        }
        return u;
    }

    /**
     * Smaze uzivatele podle primarniho klice
     * @param id if of the entity instance
     * @return pocet smaz ( 1/ 0)
     */
    @Override
    public int delete(Long id) {
        entityManager.getTransaction().begin();
        int deletedCount = entityManager.createQuery("DELETE FROM UserJ u where u.id=:id").setParameter("id", id).executeUpdate();
        entityManager.getTransaction().commit();
        return deletedCount;
    }

    /**
     * Vytvori uzivatele v databazi
     * @param user uzivatel
     * @return vytvoreny uzivatel
     */
    @Override
    public UserJ create(UserJ user) {
        try {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }

        return user;
    }

    /**
     * Najdee uzivatele podle loginu a hesla
     * @param login login
     * @param password helso
     * @return uzivatel
     */
    @Override
    public UserJ findUser(String login, String password) {
        TypedQuery<UserJ> q =  entityManager.createQuery("SELECT u FROM UserJ u WHERE u.Login = :log AND u.Password = :pass", UserJ.class).setParameter("log", login).setParameter("pass", password);
        UserJ u = null;
        try {
            u = q.getSingleResult();
        }catch (NoResultException e){
            String error = "no result";
        }
        return u;
    }

    /**
     * Vraci seznam vsech uzivatelu
     * @return seznam uzivatelu
     */
    @Override
    public List<UserJ> findUsers() {
        TypedQuery<UserJ> q =  entityManager.createQuery("SELECT u FROM UserJ u", UserJ.class);
        q.setHint("javax.persistence.cache.storeMode", "REFRESH");

        List<UserJ> u = new ArrayList<>();
        try {
            u = q.getResultList();
        }catch (NoResultException e){
            String error = "no result";
        }
        return u;
    }

    /**
     * Zjisti zda je pin unikatní
     * @param pin testovany pin
     * @return true/false
     */
    @Override
    public boolean findPin(String pin) {
        TypedQuery<UserJ> q =  entityManager.createQuery("SELECT u FROM UserJ u WHERE u.Login=:pin", UserJ.class).setParameter("pin",pin);
        UserJ u = null;
        try {
            u = q.getSingleResult();
        }catch (NoResultException e){
            String error = "no result";
            return true;
        }

        return false;
    }

    /**
     * Upravi uzivatele na nove hodnoty
     * @param user1 upravovany uzivatel
     * @return int
     */
    @Override
    public int upadate(UserJ user1) {

        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("UPDATE UserJ u set u.name=:name, u.BirthDate=:bd, u.nid=:nid,u.Email=:mail, u.Phone=:phone, u.Address=:addr WHERE u.id=:id");
        q.setParameter("name",user1.getName());
        q.setParameter("bd",user1.getBirthDate());
        q.setParameter("nid",user1.getNid());
        q.setParameter("mail",user1.getEmail());
        q.setParameter("phone",user1.getPhone());
        q.setParameter("addr", user1.getAddress());
        q.setParameter("id", user1.getId());
        int count = q.executeUpdate();
        entityManager.getTransaction().commit();
        return count;
    }

    public List<UserJ> refreshCollection(List<UserJ> entityCollection)
    {
        List<UserJ> result = new ArrayList<UserJ>();
        UserJ mergedEntity;
        for (UserJ entity : entityCollection) {
            entityManager.getEntityManagerFactory().getCache().evict(entity.getClass(), entity.getId());
            result.add(entityManager.find(entity.getClass(), entity.getId()));
        }
        return result;
    }
}
