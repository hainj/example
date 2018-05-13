package pia.servlet;

import pia.dao.jpa.*;
import pia.validate.Validate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Base servlet every other servlet extends this one
 * Created by jakub on 20.01.2017.
 */
@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    /**
     * Dao for users
     */
    public UsersDaoJPA usersDao;
    /**
     * Dao for rights
     */
    public RightsDaoJPA rightsDao;
    /**
     * Dao for accounts
     */
    public AccountsDaoJPA accountsDao;
    /**
     * Dao for transactions
     */
    public TransactionsDaoJPA transDao;
    /**
     * Dao for templates
     */
    public TemplateDaoJPA templatesDao;
    /**
     * Validate
     */
    public Validate validate;
    //private UsersDaoJPA udao;

    /**
     *Sets all class variables from servlet context
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.usersDao = (UsersDaoJPA) config.getServletContext().getAttribute("usersDao");
        this.rightsDao = (RightsDaoJPA) config.getServletContext().getAttribute("rightsDao");
        this.accountsDao = (AccountsDaoJPA) config.getServletContext().getAttribute("accountsDao");
        this.transDao = (TransactionsDaoJPA) config.getServletContext().getAttribute("transDao");
        this.templatesDao = (TemplateDaoJPA) config.getServletContext().getAttribute("templatesDao");
        this.validate = (Validate) config.getServletContext().getAttribute("validate");
    }

}
