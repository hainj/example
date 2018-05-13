package pia.servlet;

import pia.dao.jpa.*;
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
import java.sql.Timestamp;

/**
 * Vytvari a upravuje jednotlive sablony
 * Created by jakub on 09.01.2017.
 */
public class TemplateC extends BaseServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * Creates or updates template based on button pressed
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Double amount = Double.valueOf(req.getParameter("amount"));
        String varSymbol = req.getParameter("varSym");
        String constSym = req.getParameter("constSym");
        String message = req.getParameter("message");
        String specSym = req.getParameter("specSym");
        String accnum = req.getParameter("accNumber");
        String bankCode = req.getParameter("bankCode");
        String button = req.getParameter("send");
        String name = req.getParameter("tempName");

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
        UserJ user = (UserJ) req.getSession().getAttribute("user");
        AccountJ acc = null;
        acc = accountsDao.findByUser(user.getId());
        if (acc == null) {
            req.setAttribute("error", "Chybí účet");
            RequestDispatcher rd = req.getRequestDispatcher("profile");
            rd.forward(req, resp);
            return;
        }

        if (button.compareTo("create") == 0) {
            Template temp = createTemplate(name,accnum,bankCode, message,specSym,constSym,varSymbol,amount,acc);

            if(temp == null){
                req.setAttribute("page", "templ");
                req.setAttribute("error", "Chyba během vytváření šablony");
                RequestDispatcher rd = req.getRequestDispatcher("temp?cpage=0");
                rd.forward(req, resp);
            }

        } else {

            Long id = Long.valueOf(req.getParameter("id"));
            Template temp = templatesDao.findTemplate(id);
            updateTemp(temp, name,accnum,bankCode,message,specSym,constSym,varSymbol,amount);

        }
        req.setAttribute("page", "templ");
        req.setAttribute("success", "Template successfully created/edited");
        RequestDispatcher rd = req.getRequestDispatcher("temp?cpage=0");
        rd.forward(req, resp);
    }

    /**
     * Updates template
     * @param temp updated template
     * @param name name of template
     * @param accnum new account number
     * @param bankCode new bank code
     * @param message new message
     * @param specSym new specific symbol
     * @param constSym new constant symbol
     * @param varSymbol new variable symbol
     * @param amount new transaction amount
     */
    private void updateTemp(Template temp,String name, String accnum, String bankCode, String message, String specSym, String constSym, String varSymbol, Double amount) {
        temp.setName(name);
        temp.setAccNumber(accnum);
        temp.setBankCode(bankCode);
        temp.setMessage(message);
        temp.setSpecSymbol(specSym);
        temp.setConstSymbol(constSym);
        temp.setVarSymbol(varSymbol);
        temp.setAmount(amount);
        templatesDao.updateTemplate(temp);
    }

    /**
     * Creates new template
     * @param name name of template
     * @param accnum new account number
     * @param bankCode new bank code
     * @param message new message
     * @param specSym new specific symbol
     * @param constSym new constant symbol
     * @param varSymbol new variable symbol
     * @param amount new transaction amount
     * @param acc owner of template
     * @return template , null if failed
     */
    private Template createTemplate(String name, String accnum, String bankCode, String message, String specSym, String constSym, String varSymbol, Double amount, AccountJ acc) {
        Template temp = new Template();
        temp.setName(name);
        temp.setAccNumber(accnum);
        // temp.setDate(timestamp);
        temp.setBankCode(bankCode);
        temp.setMessage(message);
        temp.setSpecSymbol(specSym);
        temp.setConstSymbol(constSym);
        temp.setVarSymbol(varSymbol);
        temp.setAmount(amount);
        temp.setAccId(acc);
        Template tmp = templatesDao.create(temp);
        return tmp;
    }
}