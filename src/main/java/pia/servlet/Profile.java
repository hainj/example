package pia.servlet;

import pia.dao.AccountsDao;
import pia.dao.RightsDao;
import pia.dao.UsersDao;
import pia.dao.jpa.AccountsDaoJPA;
import pia.dao.jpa.RightsDaoJPA;
import pia.dao.jpa.UsersDaoJPA;
import pia.data.User;
import pia.data.UserJ;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * profil uzivatele
 * Created by jakub on 27.12.2016.
 */
public class Profile extends BaseServlet {
    /**
     * Redirects to profile page
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("user") == null){
            resp.sendRedirect("index");
        }else{
            UserJ user = (UserJ) req.getSession().getAttribute("user");
            System.out.println(user.getIdRights().getRole());
            req.setAttribute("page", "profile");
            RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");

            rd.forward(req,resp);
        }
    }

    /**
     * Calls doGet
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
