package pia.dao;

import org.apache.commons.dbutils.DbUtils;
import pia.data.Account;
import pia.data.DbSettings;
import pia.data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Jdbc database access for accounts
 * Created by jakub on 27.12.2016.
 */
public class AccountsDao extends Dao {
    public AccountsDao(DbSettings dbSettings) throws ClassNotFoundException {
        super(dbSettings);
    }

    /**
     * Gets all accounts
     * @return list of accounts
     * @throws SQLException
     */
    public List<Account> getAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<Account>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM Account");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Account account = new Account();

                account.setId(resultSet.getInt("id"));
                account.setAccNumber(resultSet.getString("AccountNumber"));
                account.setBalance(resultSet.getDouble("Balance"));
                account.setCardNumber(resultSet.getString("CardNumber"));

                account.setAccount(resultSet.getInt("User_id"));



                accounts.add(account);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return accounts;
    }

    /**
     * Get account by primary key
     * @param id primary key
     * @return account
     * @throws SQLException
     */
    public Account getAccount(int id) throws SQLException {

        Account account = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM Account WHERE User_id = \'" + id+"\'");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                account = new Account();

                account.setId(resultSet.getInt("id"));
                account.setAccNumber(resultSet.getString("AccountNumber"));
                account.setBalance(resultSet.getDouble("Balance"));
                account.setCardNumber(resultSet.getString("CardNumber"));
                account.setAccount(resultSet.getInt("User_id"));




            }

        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return account;
    }

    /**
     * Add account to database
     * @param acc account
     * @return error message
     */
    public String addAccount(Account acc){
        String error = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int resultSet;
        try{
            preparedStatement = connection.prepareStatement("INSERT  INTO  Account(AccountNumber,Balance, CardNumber, User_id)" +
                    " VALUES (" + acc.getAccNumber() +
                    ", " + acc.getBalanceDouble() +
                    ", " + acc.getCardNumber()+
                    ", " + acc.getAccount() +
                    ")");
            resultSet = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }
        return error;
    }

    /**
     * Upadate account
     * @param acc upadted account
     * @return error message
     */
    public String updateAccount(Account acc){

        String error = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int resultSet;

        try{
            preparedStatement = connection.prepareStatement("UPDATE Account SET" +
                    " AccountNumber =" + acc.getAccNumber() +
                    ", Balance =" + acc.getBalanceDouble() +
                    ", CardNumber =" + acc.getCardNumber()+
                    ", User_id =" + acc.getAccount() +
                    " WHERE id =\'" + acc.getId()+"\'"
            );
            resultSet = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }
        return error;
    }

    /**
     * Delete account
     * @param acc deleted account
     * @return error message
     */
    public String deleteAccount(Account acc){
        String error = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int resultSet;
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM Account WHERE id =\'" + acc.getId()+"\'"
            );
            resultSet = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }
        return error;
    }

}
