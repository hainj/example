package pia.servlet;

import pia.Email.SendMail;
import pia.Safety.Password;
import pia.data.TransactionJ;
import pia.data.UserJ;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Login confirmation
 * Created by jakub on 27.01.2017.
 */
public class ConfirmLogin extends BaseServlet {
    /**
     * Generated alphanumeric code used for comparison
     */
    String test = "";
    /**
     * Transaction that will be logged in when the code validation is right
     */
    UserJ user = null;

    /**
     * If sent from login form, sets test and user variables, generates alphanumeric code and redirects to confirmation
     * If sent from confirmation screen, compares values and redirects accordingly to result
     * @param req request
     * @param resp response
     * @throws ServletException error while forwarding or redirecting
     * @throws IOException error while forwarding or redirecting
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("button");
        if (type == null) {

            Password p = new Password();
            test = p.randomString(9);
            SendMail sm = new SendMail();
            user = (UserJ) req.getAttribute("user");
            sm.sendConfirmLogMail(user, test);

            System.out.println(test);
            req.setAttribute("conf", "login");
            RequestDispatcher rs = req.getRequestDispatcher("confirmtrans.jsp");
            rs.forward(req, resp);
            return;
        } else if (type.compareTo("done") == 0) {

            String conf = req.getParameter("confirm");
            if (conf.compareTo(test) == 0) {


                req.getSession().setAttribute("user", user);
                req.setAttribute("page", "profile");
                req.getSession().setAttribute("rights", user.getIdRights());
                if(user.getIdRights().getTransaction() != 0) {
                    req.getSession().setAttribute("account", accountsDao.findByUser(user.getId()));
                }

                resp.sendRedirect("profile");

            } else {
                req.setAttribute("page", "index");
                req.setAttribute("error", "Wrong confirmation code");
                RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                rd.forward(req, resp);
            }


        }

    }

    /**
     * Redirects to index
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index");
    }
}
