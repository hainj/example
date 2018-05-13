package pia.servlet;

import pia.dao.AccountsDao;
import pia.dao.RightsDao;
import pia.dao.UsersDao;
import pia.dao.jpa.AccountsDaoJPA;
import pia.dao.jpa.RightsDaoJPA;
import pia.dao.jpa.UsersDaoJPA;
import pia.data.UserJ;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Pripravuje data pro stranku se seznamem uzivatelu
 * Created by jakub on 01.01.2017.
 */

public class UserL extends BaseServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * Load all users and picks 3 depending on page redirects to userlist.jsp
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserJ> users = usersDao.findUsers();
        users = usersDao.refreshCollection(users);
        List<UserJ> result = new ArrayList<>();
        int page = 0;
        try {
            page = Integer.valueOf(request.getParameter("cpage"));
        }catch (NumberFormatException e){
            System.out.println("Spatny parametr page");
        }
        int index = page *10;
        for (int i = 0; i < users.size(); i++) {
            if(i == 10) break;
            if(users.size() <= index) break;
            result.add(users.get(index));
            index++;
        }
        int total = (int) Math.floor(((double)users.size()-1.0) / 10.0);

        if(result.size() == 0 && users.size() != 0){


            response.sendRedirect("users?cpage="+total);
            return;
        }


        request.setAttribute("users",result);
        request.setAttribute("total", total);
        request.setAttribute("current", page);

        RequestDispatcher rd =request.getRequestDispatcher("userlist.jsp");
        rd.forward(request,response);

    }
}
