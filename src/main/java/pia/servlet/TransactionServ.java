package pia.servlet;


import pia.dao.jpa.AccountsDaoJPA;
import pia.dao.jpa.RightsDaoJPA;
import pia.dao.jpa.TemplateDaoJPA;
import pia.dao.jpa.UsersDaoJPA;
import pia.data.Template;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *presmeruje na stranku vytvareni transakce pripadne nastavi sablonu, ze ktere se vytvari
 */
//@WebServlet("/Variables1")
public class TransactionServ extends BaseServlet {
	/**
	 * Redirect to transaction.jsp
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("page","transaction");
		RequestDispatcher rd = req.getRequestDispatcher("transaction.jsp");
		rd.forward(req,resp);
	}

	/**
	 * Loads template if id is set and redirects to transaction.jsp
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		//String str = ;
		if(req.getParameter("id") != null) {
			Long id = Long.valueOf(req.getParameter("id"));
			Template temp = templatesDao.findTemplate(id);
			req.setAttribute("template", temp);
			req.setAttribute("date", dtf.format(localDate));
			doGet(req,resp);
		}else{
			doGet(req,resp);
		}






	}
}
