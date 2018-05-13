package pia.servlet;

import pia.Email.SendMail;
import pia.Safety.Hash;
import pia.Safety.Password;
import pia.dao.AccountsDao;
import pia.dao.RightsDao;
import pia.dao.UsersDao;
import pia.dao.jpa.AccountsDaoJPA;
import pia.dao.jpa.RightsDaoJPA;
import pia.dao.jpa.TemplateDaoJPA;
import pia.dao.jpa.UsersDaoJPA;
import pia.data.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

/**
 * Upravuje uzivatele na nove hodnoty
 * Created by jakub on 01.01.2017.
 */
public class Update extends BaseServlet {
    /**
     * Updates user, sends email with new details
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("userName");
        String birthdate = req.getParameter("birthdate");
        String nid = req.getParameter("idNum");
        String email = req.getParameter("email");
        String phone = req.getParameter("phoneNumber");
        String address = req.getParameter("address");
        Long id = Long.valueOf(req.getParameter("id"));
        boolean[] error = new boolean[5];
        error[0] = validate.validatePhone(phone);
        error[1] = validate.validateAddress(address);
        error[2] = validate.validateName(name);
        error[3] = validate.validateEmail(email);
        nid = nid.replace("\\", "/");
        error[4] = validate.validateNid(nid);
        UserJ original = (UserJ) req.getSession().getAttribute("user");
        RightsJ rights = original.getIdRights();

        /*try {
            rights = rightsDao.getRight(original.getRights());
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        if (rights.getModifyUser() == 1 && original.getId() != id) {
            String err = null;
            for (int i = 0; i < error.length; i++) {
                if (!error[i]) {
                    err = getMessage(i);
                }
            }
            if(err != null){
                req.setAttribute("error", err);
                RequestDispatcher rd = req.getRequestDispatcher("users?cpage=0");
                rd.forward(req, resp);
                return;
            }


            UserJ userEdit = usersDao.findOne(id);
            int count = editUser(name,address,birthdate,email,phone,nid,userEdit);

            if (count == 1) {
                SendMail sm = new SendMail();
                sm.sendEditGMail(userEdit.getEmail(), userEdit, 1);
                req.setAttribute("success", "User " + userEdit.getName() + " successfully edited");
                RequestDispatcher rd = req.getRequestDispatcher("users?cpage=0");
                rd.forward(req, resp);

                return;
            } else {
                req.setAttribute("error", "No users have been changed");
                RequestDispatcher rd = req.getRequestDispatcher("users?cpage=0");
                rd.forward(req, resp);
                return;
            }
        } else {
            if (id != original.getId()) {
                req.setAttribute("error", "Not allowed to edit other users");
                RequestDispatcher rd = req.getRequestDispatcher("profile");
                rd.forward(req, resp);
                return;
            }
            String err = null;
            for (int i = 0; i < error.length; i++) {
                if (!error[i]) {
                    err = getMessage(i);
                }
            }
            if(err != null){
                req.setAttribute("error", err);
                RequestDispatcher rd = req.getRequestDispatcher("profile");
                rd.forward(req, resp);
                return;
            }



            int count = editUser(name,address,birthdate,email,phone,nid,original);

            if (count == 1) {
                req.setAttribute("success", "Successfully edited");
                req.getSession().setAttribute("user", usersDao.findOne(id));
                RequestDispatcher rd = req.getRequestDispatcher("profile");
                rd.forward(req, resp);
                return;
            } else {
                req.setAttribute("error", "No users have been changed");
                RequestDispatcher rd = req.getRequestDispatcher("profile");
                rd.forward(req, resp);
                return;
            }
        }
    }

    private int editUser(String name, String address, String birthdate, String email, String phone, String nid, UserJ userEdit) {
        userEdit.setName(name);
        userEdit.setGender(userEdit.getGender());
        userEdit.setAddress(address);
        userEdit.setMessage(userEdit.getMessage());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = format.parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        userEdit.setBirthDate(sqlDate);
        userEdit.setEmail(email);
        userEdit.setPhone(phone);
        userEdit.setNid(nid);

        int count = usersDao.upadate(userEdit);
        return count;
    }

    private String getMessage(int type) {
        String err = null;
        switch (type) {
            case 0:
                err = "Invalid phone number";
                break;
            case 1:
                err = "Invalid address";
                break;
            case 2:
                err = "Invalid name";
                break;
            case 3:
                err = "Invalid email";
                break;
            case 4:
                err = "Invalid nid";
                break;

        }
        return err;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
