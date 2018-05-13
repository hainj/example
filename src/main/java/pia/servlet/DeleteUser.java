package pia.servlet;

import pia.Email.SendMail;
import pia.dao.AccountsDao;
import pia.dao.RightsDao;
import pia.dao.UsersDao;
import pia.dao.jpa.*;
import pia.data.AccountJ;
import pia.data.User;
import pia.data.UserJ;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Smazani uzivatele, uctu, transakci a sablon podle potreby
 * Created by jakub on 01.01.2017.
 */
@WebServlet(name = "DeleteUser")
public class DeleteUser extends BaseServlet {
    /**
     * Deletes user, if userid matches logged user id returns error
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));

        UserJ userJ = (UserJ) request.getSession().getAttribute("user");
        if(userJ.getId() == id){
            request.setAttribute("error", "Can't delete yourself");
            RequestDispatcher rd = request.getRequestDispatcher("users?cpage=0");
            rd.forward(request,response);
            return;
        }

        Long iduser = new Long(id);
        UserJ user = usersDao.findOne(iduser);

        if(user == null)
        {
            request.setAttribute("error", "user doesn't exist");
            RequestDispatcher rd = request.getRequestDispatcher("users?cpage=0");
            rd.forward(request,response);
            return;
        }
        String email = user.getEmail();
        if(user.getIdRights().getTransaction() == 1) {
            AccountJ acc = accountsDao.findByUser(user.getPK());
            if(acc!=null){
                transDao.deleteUserTransaction(acc.getId());
                templatesDao.deleteUserTemplates(acc.getId());
                accountsDao.deleteAcc(user.getId());
            }
            int count = usersDao.delete(iduser);
            if (count == 1) {
                SendMail sm = new SendMail();
                sm.sendDeleteGMail(email, 2);
                request.setAttribute("success", "User deleted");
                RequestDispatcher rd = request.getRequestDispatcher("users?cpage=0");
                rd.forward(request,response);
            }else {
                request.setAttribute("success", "No users deleted");
                RequestDispatcher rd = request.getRequestDispatcher("users?cpage=0");
                rd.forward(request,response);
            }
        }else {
            int count = usersDao.delete(iduser);
            if (count == 1) {
                SendMail sm = new SendMail();
                sm.sendDeleteGMail(email, 2);
                request.setAttribute("success", "No users deleted");
                RequestDispatcher rd = request.getRequestDispatcher("users?cpage=0");
                rd.forward(request, response);
            }else{
                request.setAttribute("success", "No users deleted");
                RequestDispatcher rd = request.getRequestDispatcher("users?cpage=0");
                rd.forward(request,response);
            }
        }
    }

    /**
     * Redirects to index
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index");
    }
}
