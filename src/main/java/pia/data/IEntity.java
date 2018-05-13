package pia.data;

import java.io.Serializable;

/**
 * Created by jakub on 03.01.2017.
 */
public interface IEntity<PK extends Serializable> {

    /**
     *
     * @return  primary key of the instance
     */
    PK getPK();

}