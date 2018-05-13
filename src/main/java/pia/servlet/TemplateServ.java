package pia.servlet;

import pia.dao.jpa.AccountsDaoJPA;
import pia.dao.jpa.RightsDaoJPA;
import pia.dao.jpa.TemplateDaoJPA;
import pia.dao.jpa.UsersDaoJPA;
import pia.data.AccountJ;
import pia.data.Template;
import pia.data.UserJ;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * pripravuje sablonu pro upravu
 * Created by jakub on 07.01.2017.
 */
public class TemplateServ extends BaseServlet {
    /**
     * Redirects to template creation
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Template temp = (Template) req.getAttribute("template");

        RequestDispatcher rd = req.getRequestDispatcher("template.jsp");
        rd.forward(req, resp);
    }

    /**
     * Sets template atribute if id is set, redirects to template creation
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") != null){
            Template temp = templatesDao.findTemplate(Long.valueOf(req.getParameter("id")));
            req.setAttribute("template", temp);
        }

    doGet(req,resp);
    }
}
