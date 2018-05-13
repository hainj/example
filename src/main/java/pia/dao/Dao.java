package pia.dao;

import pia.data.DbSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jakub on 27.12.2016.
 */
public class Dao {
    /**
     * Database settings
     */
    protected DbSettings dbSettings;

    public Dao(DbSettings dbSettings) throws ClassNotFoundException{
        this.dbSettings = dbSettings;
        Class.forName("com.mysql.jdbc.Driver");
    }

    /**
     * Gets database connection
     * @return db connection
     * @throws SQLException
     */
    protected Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(this.dbSettings.getConnectionUrl(), this.dbSettings.getUser(), this.dbSettings.getPassword());
        return connection;
    }
}
