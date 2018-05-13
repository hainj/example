package pia.servlet;

import pia.Email.SendMail;
import pia.Safety.Password;
import pia.data.AccountJ;
import pia.data.TransactionJ;
import pia.data.UserJ;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jakub on 27.01.2017.
 */
public class Confirm extends BaseServlet {
    /**
     * Generated alphanumeric code used for comparison
     */
    String test = "";
    /**
     * Transaction that will be done when the code validation is right
     */
    TransactionJ ts;

    /**
     * If redirected from transaction screen, generates alphanumeric code, sets test and ts, redirects to confirmation screen
     * If redirected from confirmation it checks if the send code is valid, if valid accepts transaction, else rejects and redirects accordingly
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("button");

        if ( type== null  || type.compareTo("confirm") == 0 || type.compareTo("") == 0){
            ts = (TransactionJ) req.getAttribute("transaction");
            Password p = new Password();
            test = p.randomString(9);
            SendMail sm = new SendMail();
            UserJ u = (UserJ) req.getSession().getAttribute("user");
            sm.sendConfirmMail(u, ts, test);


            System.out.println(test);

            req.setAttribute("conf", "trans");
            req.setAttribute("transaction", ts);
            RequestDispatcher rs = req.getRequestDispatcher("confirmtrans.jsp");
            rs.forward(req,resp);
            return;
        }else if(type.compareTo("done")==0){
            UserJ u = (UserJ) req.getSession().getAttribute("user");
            String conf = req.getParameter("confirm");
            if(conf.compareTo(test) == 0){
                AccountJ acc = accountsDao.findByUser(u.getId());
                double balance = acc.getBalance() - ts.getAmount();
                TransactionJ check = transDao.create(ts);
                if (check == null) {
                    acc.setBalance(balance);
                    accountsDao.updateBalance(acc, balance);
                    req.setAttribute("success", "Transakce přijata");
                    RequestDispatcher rd = req.getRequestDispatcher("transaction");
                    rd.forward(req, resp);

                }else{
                    req.setAttribute("error", "Error while creating transaction");
                    RequestDispatcher rd = req.getRequestDispatcher("transaction");
                    rd.forward(req, resp);
                }
            }else{
                req.setAttribute("error", "Wrong confirmation code");
                RequestDispatcher rd = req.getRequestDispatcher("transaction");
                rd.forward(req, resp);
            }

        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index");
    }
}
