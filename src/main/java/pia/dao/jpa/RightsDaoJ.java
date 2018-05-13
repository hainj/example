package pia.dao.jpa;

import pia.dao.GenericDao;
import pia.data.RightsJ;

/**
 * Created by jakub on 04.01.2017.
 */
public interface RightsDaoJ extends GenericDao<RightsJ, Integer> {
    /**
     * Creates rights
     *
     * @param rights created rights
     * @return rights
     */
    RightsJ create(RightsJ rights);

    /**
     * Finds rights by rights name
     * @param role rights name
     * @return rights
     */
    RightsJ find(String role);


}
