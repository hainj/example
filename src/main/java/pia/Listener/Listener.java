package pia.Listener;


import pia.Email.SendMail;
import pia.Generate.GenerateData;
import pia.dao.AccountsDao;
import pia.dao.RightsDao;
import pia.dao.TransactionsDao;
import pia.dao.UsersDao;
import pia.dao.jpa.*;
import pia.data.*;
import pia.validate.Validate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.List;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Created by jakub on 27.12.2016.
 */
public class Listener implements ServletContextListener {

    private static final String PERSISTENCE_UNIT = "org.danekja.edu.pia";

    /**
     * Inicializace proměnných při stratu aplikace.
     * Pokud se nelze připojit k databázi, zkontrolujte, zda máte správně nastaveny
     * hodnoty pro připojení ve web.xml.
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("START");
        DbSettings dbSettings = new DbSettings(
                event.getServletContext().getInitParameter("dbTest"),
                Integer.valueOf(event.getServletContext().getInitParameter("dbPort")),
                event.getServletContext().getInitParameter("dbUser"),
                event.getServletContext().getInitParameter("dbPassword"),
                event.getServletContext().getInitParameter("dbDatabase")
        );


        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager em = factory.createEntityManager();
        UsersDaoJPA udao = new UsersDaoJPA(em, UserJ.class);
        RightsDaoJPA rdao = new RightsDaoJPA(em, RightsJ.class);
        AccountsDaoJPA adao = new AccountsDaoJPA(em, AccountJ.class);
        TransactionsDaoJPA tdao = new TransactionsDaoJPA(em, TransactionJ.class);
        TemplateDaoJPA tempdao = new TemplateDaoJPA(em, Template.class);
        Validate validate = new Validate();

        event.getServletContext().setAttribute("usersDao", udao);
        event.getServletContext().setAttribute("templatesDao", tempdao);
        event.getServletContext().setAttribute("transDao", tdao);
        event.getServletContext().setAttribute("rightsDao", rdao);
        event.getServletContext().setAttribute("accountsDao", adao);
        event.getServletContext().setAttribute("validate", validate);
        System.out.println("OK");

    }

    /**
     * Destroys drivers
     *
     * @param arg0
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);

            } catch (SQLException e) {

            }

        }
    }

}