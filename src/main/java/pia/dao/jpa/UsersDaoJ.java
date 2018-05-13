package pia.dao.jpa;

import pia.dao.GenericDao;
import pia.data.UserJ;

import java.util.List;

public interface UsersDaoJ extends GenericDao<UserJ, Long> {
    /**
     * Create userr
     * @param user created user
     * @return user
     */
    UserJ create(UserJ user);

    /**
     * Finds user by login and password
     * @param login login
     * @param password pin
     * @return user
     */
    UserJ findUser(String login,  String password);

    /**
     * Gets all users
     * @return list of users
     */
    List<UserJ> findUsers();

    /**
     * Checks if login is unique
     * @param pin login
     * @return true if unique else false
     */
    boolean findPin(String pin);

    /**
     * updates user
     * @param user1 updated user
     * @return updated count
     */
    int upadate(UserJ user1);
}
