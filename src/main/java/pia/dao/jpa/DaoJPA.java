package pia.dao.jpa;

import pia.dao.GenericDao;
import pia.data.IEntity;

import javax.persistence.EntityManager;

import java.io.Serializable;

/**
 * Created by jakub on 03.01.2017.
 */
public class DaoJPA<E extends IEntity<PK>, PK extends Serializable> implements GenericDao<E, PK> {


    protected EntityManager entityManager;
    protected Class<E> persistedClass;

    /**
     *
     * @param em entity manager
     * @param persistedClass entity type to be persisted by this instance
     */
    public DaoJPA(EntityManager em, Class<E> persistedClass) {
        this.entityManager = em;
        this.persistedClass = persistedClass;
    }



    @Override
    public E findOne(PK id) {
        return entityManager.find(persistedClass, id);
    }

    @Override
    public int delete(PK id) {

       return 0;

    }
}
