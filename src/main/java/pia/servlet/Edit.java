package pia.servlet;

import pia.dao.jpa.AccountsDaoJPA;
import pia.dao.jpa.RightsDaoJPA;
import pia.dao.jpa.UsersDaoJPA;
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
 * priprava dat pro stranku s upravou uzivate
 * Created by jakub on 31.12.2016.
 */
public class Edit extends BaseServlet {
    /**
     * Redirects to index
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
     * Sets user id, page, and redirects to edit.jsp
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("page", "profile");
        int id = Integer.valueOf(req.getParameter("id"));
        UserJ user = null;
        Long l = new Long(id);
        user = usersDao.findOne(l);

        req.setAttribute("editUser", user);
        RequestDispatcher rd = req.getRequestDispatcher("edit.jsp");
        rd.forward(req,resp);
    }
}
