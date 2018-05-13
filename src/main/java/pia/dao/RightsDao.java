package pia.dao;

import org.apache.commons.dbutils.DbUtils;
import pia.data.DbSettings;
import pia.data.Rights;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 27.12.2016.
 */
public class RightsDao extends Dao{
    public RightsDao(DbSettings dbSettings) throws ClassNotFoundException {
        super(dbSettings);
    }

    /**
     * Get all rights
     * @return list of rights
     * @throws SQLException
     */
    public List<Rights> getRights() throws SQLException {
        List<Rights> rights = new ArrayList<Rights>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM Rights");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Rights right = new Rights();

                right.setId(resultSet.getInt("id"));
                right.setAddUser(resultSet.getInt("AddUser"));
                right.setModifyUser(resultSet.getInt("ModifyAcc"));
                right.setModifSelf(resultSet.getInt("modifSelf"));
                right.setTransaction(resultSet.getInt("Transaction"));
                right.setRole(resultSet.getString("Role"));
                right.setDeleteUser(resultSet.getInt("DeleteAcc"));




                rights.add(right);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return rights;
    }

    /**
     * Get rights by primary key
     * @param id rights primary key
     * @return rights
     * @throws SQLException
     */
    public Rights getRight(int id) throws SQLException {
        Rights right = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM Rights WHERE id=\'" + id + "'");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                right = new Rights();
                right.setId(resultSet.getInt("id"));
                right.setAddUser(resultSet.getInt("AddUsers"));
                right.setModifyUser(resultSet.getInt("ModifyAcc"));
                right.setModifSelf(resultSet.getInt("modifSelf"));
                right.setTransaction(resultSet.getInt("Transaction"));
                right.setRole(resultSet.getString("Role"));
                right.setDeleteUser(resultSet.getInt("DeleteAcc"));

            }

        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return right;
    }
}
