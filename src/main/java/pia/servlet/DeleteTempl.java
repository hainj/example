package pia.servlet;

import pia.dao.jpa.AccountsDaoJPA;
import pia.dao.jpa.RightsDaoJPA;
import pia.dao.jpa.TemplateDaoJPA;
import pia.dao.jpa.UsersDaoJPA;
import pia.data.AccountJ;
import pia.data.UserJ;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Smaze urcitou sablonu
 * Created by jakub on 09.01.2017.
 */
public class DeleteTempl extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserJ user = (UserJ)req.getSession().getAttribute("user");
        Long id = Long.valueOf( req.getParameter("id"));
        int count = templatesDao.deleteTemplate(id);
        if(count == 1){
            req.setAttribute("success", "Template deleted");
            RequestDispatcher rd = req.getRequestDispatcher("temp?cpage=0");
            rd.forward(req,resp);
        }else{
            req.setAttribute("success", "No templates changed");
            RequestDispatcher rd = req.getRequestDispatcher("temp?cpage=0");
            rd.forward(req,resp);
        }
    }
}
