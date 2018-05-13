package pia.dao;

import org.apache.commons.dbutils.DbUtils;
import pia.data.Account;
import pia.data.DbSettings;
import pia.data.Transaction;
import pia.data.User;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 27.12.2016.
 */
public class TransactionsDao extends Dao{
    public TransactionsDao(DbSettings dbSettings) throws ClassNotFoundException {
        super(dbSettings);
    }

    /**
     * Get all transactions
     * @return list of transactions
     * @throws SQLException
     */
    public List<Transaction> getTransactions() throws SQLException {
        List<Transaction> transactions = new ArrayList<Transaction>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM Transaction");
            resultSet = preparedStatement.executeQuery();

            transactions = getTransList(resultSet);

        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return transactions;
    }

    /**
     * Add transaction
     * @param user user
     * @param acc account
     * @param trans transaction
     * @return error message
     * @throws SQLException
     */
    public String addTransaction(User user, Account acc, Transaction trans) throws SQLException {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO TRANSACTION " +
                    "(ConsSymbol,VarSymbol, AccNumber, Message, Money, Bank, SpecSymbol,Date, Processed, Account_id)" +
                    " VALUES ("+trans.getConstSymbol() +
                    "," + trans.getVarSymbol()+
                    "," + trans.getAccNumber()  +
                    "," + trans.getMessage() +
                    "," + trans.getAmount() +
                    "," + trans.getBankCode() +
                    "," + trans.getSpecSymbol() +
                    "," + trans.getDate()+
                    "," + trans.getProcessed() +
                    "," + acc.getId() +
                    ")");
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    /**
     * Updates transaction
     * @param user user
     * @param acc account
     * @param trans transaction
     * @return error message
     * @throws SQLException
     */
    public String updateTransaction(User user, Account acc, Transaction trans) throws SQLException {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("UPDATE TRANSACTION  SET" +
                    "ConstSymbol =\'"+trans.getConstSymbol() +
                    "\',VarSymbol=\'" + trans.getVarSymbol()+
                    "\', AccNumber=\'" + trans.getAccNumber()  +
                    "\',Message=\'" + trans.getMessage() +
                    "\',Money=\'" + trans.getAmount() +
                    "\', Bank=\'"+ trans.getBankCode() +
                    "\',SpecSymbol=\'" + trans.getSpecSymbol() +
                    "\',Date=\'"+ trans.getDate()+
                    "\',Processed=\'" + trans.getProcessed() +
                    "\', Account_id=\'" + acc.getId() +
                    "\'");
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    /**
     * Get all transaction by account id
     * @param id account id
     * @return list of transaction
     * @throws SQLException
     */
    public List<Transaction> getAccountTransactions(int id) throws SQLException {
        List<Transaction> transactions = new ArrayList<Transaction>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM Transaction WHERE Account_id=\'" +id + "\'");
            resultSet = preparedStatement.executeQuery();

            transactions = getTransList(resultSet);

        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return transactions;
    }

    /**
     * Gets transaction list from reultset
     * @param resultSet set of transaction query results
     * @return list of transactions
     * @throws SQLException
     */
    public List<Transaction> getTransList(  ResultSet resultSet) throws SQLException {
        List<Transaction> transactions = new ArrayList<Transaction>();
        while (resultSet.next()) {
            Transaction transaction = new Transaction();

            transaction.setId(resultSet.getInt("id"));
            transaction.setConstSymbol(resultSet.getString("ConstSymbol"));
            transaction.setVarSymbol(resultSet.getString("VarSymbol"));
            transaction.setAccNumber(resultSet.getString("AccNumber"));
            transaction.setMessage(resultSet.getString("Message"));
            transaction.setAmount(resultSet.getDouble("Money"));
            transaction.setBankCode(resultSet.getString("Bank"));
            transaction.setSpecSymbol(resultSet.getString("SpecSymbol"));
            transaction.setDate(resultSet.getTimestamp("Date"));
            transaction.setProcessed(resultSet.getInt("Processed"));
            transaction.setAccount(resultSet.getInt("Account_id"));



            transactions.add(transaction);
        }
        return transactions;
    }

    /**
     * Unprocessed transactions
     * @param id
     * @return
     * @throws SQLException
     */
    public List<Transaction> getUnprocessedTransactions(int id) throws SQLException {
        List<Transaction> transactions = new ArrayList<Transaction>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM Transaction WHERE Processed=\'0\'");
            resultSet = preparedStatement.executeQuery();

            transactions = getTransList(resultSet);

        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return transactions;
    }
}
