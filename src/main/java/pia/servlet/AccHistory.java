package pia.servlet;


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
import java.util.ArrayList;
import java.util.List;

/**
 * Historie pohybu na uctu
 * vybira zobrazene polozky
 */

public class AccHistory extends BaseServlet {


    /**
     * Loads all account transactions picks 3 depending on page and redirects to history,jsp
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserJ user = (UserJ) req.getSession().getAttribute("user");
        AccountJ acc;
        List<TransactionJ> transactions;
        acc = accountsDao.findByUser(user.getId());
        List<TransactionJ> result = new ArrayList<>();
        transactions = transDao.findTransactionByAcc(acc);
        int page = 0;
        try {
            page = Integer.valueOf(req.getParameter("cpage"));
        }catch (NumberFormatException e){
            System.out.println("Spatny parametr page");
        }
        int index = page * 3;
        for (int i = 0; i < transactions.size(); i++) {
            if (i == 3) break;
            if (transactions.size() <= index) break;
            result.add(transactions.get(index));
            index++;
        }
        int total = (int) Math.floor(((double) transactions.size() - 1.0) / 3.0);
        if (transactions.size() == 0) {
            req.setAttribute("transactions", null);
            req.setAttribute("total", total);
            req.setAttribute("current", page);
            req.setAttribute("page", "history");
            RequestDispatcher rd = req.getRequestDispatcher("history.jsp");

            rd.forward(req, resp);
            return;
        }
        if (result.size() == 0 && transactions.size() != 0) {


            resp.sendRedirect("history?cpage=" + total);
            return;
        }


        req.setAttribute("transactions", result);
        req.setAttribute("total", total);
        req.setAttribute("current", page);

        req.setAttribute("page", "history");


        RequestDispatcher rd = req.getRequestDispatcher("history.jsp");

        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
