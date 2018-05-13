package pia.dao.jpa;

import pia.dao.GenericDao;
import pia.data.AccountJ;
import pia.data.TransactionJ;

import java.util.List;

/**
 * Created by jakub on 05.01.2017.
 */
public interface TransactionsDaoJ extends GenericDao<TransactionJ,Long> {
    /**
     * Create transaction
     * @param trans created transaction
     * @return transaction
     */
    TransactionJ create(TransactionJ trans);

    /**
     * Get all transaction by account id
     * @param acc account
     * @return list of transactions
     */
    List<TransactionJ> findTransactionByAcc(AccountJ acc);
    TransactionJ findTransaction(AccountJ acc, Long id);

    /**
     * Delete all transactions by account
     * @param id account id
     * @return deleted count
     */
    int deleteUserTransaction(Long id);

}
