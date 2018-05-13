package pia.servlet;

import com.sun.javafx.binding.StringFormatter;
import pia.dao.AccountsDao;
import pia.dao.RightsDao;
import pia.dao.TransactionsDao;
import pia.dao.UsersDao;
import pia.dao.jpa.*;
import pia.data.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Zpracovani transakce nebo presmerovani na vytvoreni sablony
 * Created by jakub on 30.12.2016.
 */
public class Payment extends BaseServlet {
    /**
     * Redirects to index.jsp
     *
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
     * Loads all required variables, checks for account, redirects to confirmation
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Double amount = Double.valueOf(req.getParameter("amount").replace(',', '.'));
        String varSymbol = req.getParameter("varSym");
        String constSym = req.getParameter("constSym");
        String message = req.getParameter("message");
        String specSym = req.getParameter("specSym");
        String accnum = req.getParameter("accNumber");
        String bankCode = req.getParameter("bankCode");
        String date = req.getParameter("payDate");
        String time = req.getParameter("payTime");
        String button = req.getParameter("send");

        UserJ user = (UserJ) req.getSession().getAttribute("user");
        AccountJ acc = null;
        acc = accountsDao.findByUser(user.getId());
        if (acc == null) {
            req.setAttribute("error", "Chybí účet");
            RequestDispatcher rd = req.getRequestDispatcher("profile");
            rd.forward(req, resp);
            return;
        }
        if (acc.getAccNumber().compareTo(accnum) == 0 && bankCode.compareTo("0000") == 0) {
            req.setAttribute("error", "Posíláte peníze na svůj účet");
            RequestDispatcher rd = req.getRequestDispatcher("profile");
            rd.forward(req, resp);
            return;
        }
        if (constSym == null) {
            constSym = "";
        }
        if (varSymbol == null) {
            varSymbol = "";
        }
        if (message == null) {
            message = "";
        }
        if (specSym == null) {
            specSym = "";
        }
        if (button.compareTo("ctemp") == 0) {
            Template temp = new Template();
            temp.setAccNumber(accnum);
            temp.setName("Template " + acc.getId());
            temp.setBankCode(bankCode);
            temp.setMessage(message);
            temp.setSpecSymbol(specSym);
            temp.setConstSymbol(constSym);
            temp.setVarSymbol(varSymbol);
            temp.setAmount(amount);
            req.setAttribute("template", temp);
            RequestDispatcher rd = req.getRequestDispatcher("edittemp");
            rd.forward(req, resp);
        } else {
            String dateTime = date.toString() + " " + time.toString() + ":00";
            Timestamp timestamp = Timestamp.valueOf(dateTime);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            if (timestamp.before(ts)) {
                req.setAttribute("error", "Date is in the past");
                RequestDispatcher rd = req.getRequestDispatcher("transaction");
                rd.forward(req, resp);
            }
            TransactionJ trans = new TransactionJ();
            trans.setAccNumber(accnum);
            trans.setAccId(acc);
            trans.setProcessed(false);
            trans.setDate(timestamp);
            trans.setBankCode(bankCode);
            trans.setMessage(message);
            trans.setSpecSymbol(specSym);
            trans.setConstSymbol(constSym);
            trans.setVarSymbol(varSymbol);
            trans.setAmount(amount);
            double balance = acc.getBalance() - trans.getAmount();
            if (balance < 0) {
                req.setAttribute("error", "Not enough money on your account");
                RequestDispatcher rd = req.getRequestDispatcher("transaction");
                rd.forward(req, resp);
                return;
            }
            req.setAttribute("button", "confirm");
            req.setAttribute("transaction", trans);
            RequestDispatcher rd = req.getRequestDispatcher("confirm");
            rd.forward(req, resp);

        }
    }
}
