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
import java.util.ArrayList;
import java.util.List;

/**
 * pripravuje data pro stranku se seznamem sablon
 * Created by jakub on 07.01.2017.
 */
public class TemplateList extends BaseServlet {
    /**
     * Load all account templates picks 10 by page, redirects to templatelist.jsp
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserJ user = (UserJ) req.getSession().getAttribute("user");
        AccountJ acc = accountsDao.findByUser(user.getPK());
        List<Template> templates = templatesDao.findAccTemplates(acc.getId());
        int page = 0;
        try {
            page = Integer.valueOf(req.getParameter("cpage"));
        }catch (NumberFormatException e){
            System.out.println("Spatny parametr page");
        }
        List<Template> result = new ArrayList<>();
        int index = page *10;
        for (int i = 0; i < templates.size(); i++) {
            if(i == 10) break;
            if(templates.size() <= index) break;
            result.add(templates.get(index));
            index++;
        }
        int total = (int) Math.ceil((double)page / 10.0);
        if(templates.size() == 0){
            req.setAttribute("templates", null);
            req.setAttribute("total", total);
            req.setAttribute("current", page);
            RequestDispatcher rd =req.getRequestDispatcher("templatelist.jsp");
            rd.forward(req,resp);
            return;
        }
        if(result.size() == 0){

            resp.sendRedirect("temp?cpage="+total);
            return;
        }
        if(total ==0){
            total = 1;
        }

        req.setAttribute("templates",result);
        req.setAttribute("total", total);
        req.setAttribute("current", page);

        RequestDispatcher rd =req.getRequestDispatcher("templatelist.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
