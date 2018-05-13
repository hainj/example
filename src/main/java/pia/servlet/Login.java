package pia.servlet;

import pia.Safety.Hash;
import pia.dao.AccountsDao;
import pia.dao.RightsDao;
import pia.dao.UsersDao;
import pia.dao.jpa.AccountsDaoJPA;
import pia.dao.jpa.RightsDaoJPA;
import pia.dao.jpa.UsersDaoJPA;
import pia.data.User;
import pia.data.UserJ;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * prihlaseni uzivatele
 * Created by jakub on 27.12.2016.
 */
public class Login extends BaseServlet {
    /**
     * Redirects to index.jsp
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

    /**
     * Load login and password from request, hashes loaded password and forward to confirmation
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("userName");
        String password = req.getParameter("password");
        //User user = null;

        Hash hash = new Hash();
        try {
            password = hash.getHash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        UserJ user = usersDao.findUser(login, password);


        // user = usersDao.getUserByLogin(login, password);
        if (user != null) {
            req.setAttribute("user", user);
            RequestDispatcher rd = req.getRequestDispatcher("confirml");
            rd.forward(req,resp);


        } else {
            req.setAttribute("error", "login fail");
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }


}
