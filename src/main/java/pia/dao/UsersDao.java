package pia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import pia.data.DbSettings;
import pia.data.User;
import pia.data.UserJ;

public class UsersDao  extends Dao{

    //private final DbSettings dbSettings;


    public UsersDao(DbSettings dbSettings) throws ClassNotFoundException {
        super(dbSettings);
        //this.dbSettings = dbSettings;

        Class.forName("com.mysql.jdbc.Driver");
    }

    /**
     * Gets all users
     * @return list of users
     * @throws SQLException
     */
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<User>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM User");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("Login"));
                user.setName(resultSet.getString("Name"));
                user.setPassword(resultSet.getString("Password"));
                user.setMessage(resultSet.getString("Message"));
                user.setNid(resultSet.getString("NID"));
                user.setRights(resultSet.getInt("idRights"));
                user.setAddress(resultSet.getString("Address"));
                user.setBirthdate(resultSet.getDate("BirthDate"));
                user.setPhone(resultSet.getString("Phone"));
                user.setEmail(resultSet.getString("Email"));
                user.setGender(resultSet.getString("Gender"));


                users.add(user);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return users;
    }

    /**
     * Gets user by login and password
     * @param login
     * @param password
     * @return user
     * @throws SQLException
     */
    public User getUserByLogin(String login, String password) throws SQLException {
        User user = new User();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE Login = '" + login + "' AND Password = '" + password + "'");
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("Login"));
                user.setName(resultSet.getString("Name"));
                user.setPassword(resultSet.getString("Password"));
                user.setMessage(resultSet.getString("Message"));
                user.setNid(resultSet.getString("NID"));
                user.setRights(resultSet.getInt("idRights"));
                user.setAddress(resultSet.getString("Address"));
                user.setBirthdate(resultSet.getDate("BirthDate"));
                user.setPhone(resultSet.getString("Phone"));
                user.setEmail(resultSet.getString("Email"));
                user.setGender(resultSet.getString("Gender"));
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return user;
    }

    /**
     * Adds user to database
     * @param user
     * @return error message or null
     * @throws SQLException
     */
    public String addUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int resultSet;
        String error = "";

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO  USER (Login,Name,Password,NID,idRights,Address,Birthdate,Phone,Email,Gender) VALUES ("
                    + user.getLogin() +
                    ", " + user.getName() +
                    ", " + user.getPassword() +
                    ", " + user.getNid() +
                    ", " + user.getRights() +
                    ", " + user.getAddress() +
                    ", " + user.getBirthdate() +
                    ", " + user.getPhone() +
                    ", " + user.getEmail() +
                    ", " + user.getGender() +
                    ")");
            resultSet = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            error = "Chyba databáze";
            throw e;
        } finally {

            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return error;

    }

    /**
     * Deletes user
     * @param user
     * @return deleted user
     * @throws SQLException
     */
    public User deleteUser(User user) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int resultSet;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM User WHERE id = '" + user.getId() + "' ");
            resultSet = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return user;
    }

    /**
     * Gets user from database by id
     * @param id user id
     * @return user
     * @throws SQLException
     */
    public User getUserById(int id) throws SQLException {
        User user = new User();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE Id = '" + id + "'");
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("Login"));
                user.setName(resultSet.getString("Name"));
                user.setPassword(resultSet.getString("Password"));
                user.setMessage(resultSet.getString("Message"));
                user.setNid(resultSet.getString("NID"));
                user.setRights(resultSet.getInt("idRights"));
                user.setAddress(resultSet.getString("Address"));
                user.setBirthdate(resultSet.getDate("BirthDate"));
                user.setPhone(resultSet.getString("Phone"));
                user.setEmail(resultSet.getString("Email"));
                user.setGender(resultSet.getString("Gender"));
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return user;
    }

    /**
     * Updates user
     * @param user update user
     * @return user
     * @throws SQLException
     */
    public User updateUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int resultSet;
        String error = "";

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("UPDATE  User (Login,Name,Password,NID,idRights,Address,Birthdate,Phone,Email,Gender) VALUES ("
                    + user.getLogin() +
                    ", " + user.getName() +
                    ", " + user.getPassword() +
                    ", " + user.getNid() +
                    ", " + user.getRights() +
                    ", " + user.getAddress() +
                    ", " + user.getBirthdate() +
                    ", " + user.getPhone() +
                    ", " + user.getEmail() +
                    ", " + user.getGender() +
                    ")");
            resultSet = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            error = "Chyba databáze";
            throw e;
        } finally {

            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return user;
    }

}
