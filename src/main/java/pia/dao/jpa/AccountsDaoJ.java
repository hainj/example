package pia.dao.jpa;

import pia.dao.GenericDao;
import pia.data.AccountJ;

/**
 * Created by jakub on 05.01.2017.
 */
public interface AccountsDaoJ extends GenericDao<AccountJ, Long> {
    /**
     * Creates bank account
     * @param acc created account1
     * @return created account
     */
    AccountJ create(AccountJ acc);

    /**
     * Finds account by user id
     * @param id user id
     * @return account
     */
    AccountJ findByUser(Long id);

    /**
     * Find account by account id
     * @param id account id
     * @return account
     */
    AccountJ findAcc(Long id);

    /**
     * upadte account
     * @param acc upadted account
     * @return account
     */
    AccountJ update(AccountJ acc);

    /**
     * Upadte account balance
     * @param acc updated balance
     * @param value +-amount
     * @return account
     */
    AccountJ updateBalance(AccountJ acc, double value);
    int deleteAcc(Long id);
}
