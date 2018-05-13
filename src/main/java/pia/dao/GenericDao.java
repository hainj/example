package pia.dao;

import pia.data.IEntity;

import java.io.Serializable;

/**
 * Created by jakub on 03.01.2017.
 */
public interface GenericDao<E extends IEntity<PK>, PK extends Serializable> {

    /**
     * Either inserts new or updates existing instance.
     *
     * @param instance to be persisted
     * @return persisted instance
     */


    /**
     *
     * @param id
     * @return instance with the given id or null if not found
     */
    E findOne(PK id);

    /**
     * Removes the given entity from persistence.
     *
     * @param id if of the entity instance
     */
    int delete(PK id);

}