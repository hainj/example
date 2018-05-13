package pia.servlet;

import pia.Email.SendMail;
import pia.Safety.Hash;
import pia.Safety.Password;
import pia.dao.AccountsDao;
import pia.dao.RightsDao;
import pia.dao.UsersDao;
import pia.dao.jpa.AccountsDaoJPA;
import pia.dao.jpa.RightsDaoJPA;
import pia.dao.jpa.UsersDaoJPA;
import pia.data.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Instant;
import java.util.Random;


/**
 * registrace uzivatele kontrola hodnot
 * Created by jakub on 31.12.2016.
 */
public class Registration extends BaseServlet {

    private int number;

    /**
     * Generate random number for turings test, redirects to registration.jsp
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        req.setAttribute("page", "registration");
        Random r = new Random();
        Date date = new Date();
        req.setAttribute("date", date);
        int rand = r.nextInt(200);
        this.number = rand;
        req.setAttribute("number", rand);
        RequestDispatcher rd = req.getRequestDispatcher("registration.jsp");
        rd.forward(req, resp);
    }

    /**
     * Get error message
     * @param type id of error
     * @return text of error
     */
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
            case 5:
                err = "Invalid card number";
                break;
            case 6:
                err = "Invalid account number";
                break;

        }
        return err;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("userName");
        int number = Integer.valueOf(req.getParameter("turTest"));
        if (number != this.number) {
            req.setAttribute("error", "Numbers don't match");
            RequestDispatcher rd = req.getRequestDispatcher("registration.jsp");
            rd.forward(req, resp);
            return;
        }
        String birthdate = req.getParameter("birthdate");

        String nid = req.getParameter("idNum");
        nid = nid.replace("\\","/");
        String email = req.getParameter("email");
        String accNumber = req.getParameter("accNumber");
        String cardNumber = req.getParameter("cardNumber");
        String phone = req.getParameter("phoneNumber");
        String address = req.getParameter("address");
        String gender = req.getParameter("gender");

        String message = req.getParameter("message");
        boolean[] valid = new boolean[7];
        valid[0] = validate.validatePhone(phone);
        valid[1] = validate.validateAddress(address);
        valid[2] = validate.validateName(name);
        valid[3] = validate.validateEmail(email);
        nid = nid.replace("\\", "/");
        valid[4] = validate.validateNid(nid);
        valid[5] = validate.validateCardNumber(cardNumber);
        valid[6] = validate.validateAccNumber(accNumber);
        Hash hash = new Hash();
        String err = null;
        for (int i = 0; i < valid.length; i++) {

            if (!valid[i]) {
                err = getMessage(i);
            }
        }

        if (err != null) {
            setRequestData(req);
            req.setAttribute("error", err);
            RequestDispatcher rd = req.getRequestDispatcher("registration.jsp");
            rd.forward(req, resp);
            return;
        }
        if (message == null) {
            message = "";
        }
        if (phone == null) {
            phone = "";
        }
        if (address == null) {
            address = "";
        }
        UserJ u =  createUser(gender,email,phone,birthdate,message,name,address,nid);
        if (u == null) {
            System.out.println("Error");
            setRequestData(req);
            String error = "Error creating user";
            req.setAttribute("error", error);
            RequestDispatcher rd = req.getRequestDispatcher("registration.jsp");
            rd.forward(req, resp);
            return;
        }
        AccountJ succ = createAccount(accNumber,cardNumber,u);
        if (succ == null) {
            String error = "Error creating account";
            usersDao.delete(u.getId());
            setRequestData(req);
            req.setAttribute("error", error);
            RequestDispatcher rd = req.getRequestDispatcher("registration.jsp");
            rd.forward(req, resp);
            return;
        }
        try {
            SendMail sm = new SendMail();
            sm.sendFromGMail(u.getEmail(), orig, u.getLogin(), u.getName(),0);
        } catch (Exception e) {
            System.out.println("Email prvdepodobne blokovan antivirem/fw");
        }
        System.out.println("Success");
        setRequestData(req);
        String success = "Succefully registered";
        req.setAttribute("success", success);
        RequestDispatcher rd = req.getRequestDispatcher("registration.jsp");
        rd.forward(req, resp);
    }

    /**
     * sets random int for comparison and lowest date
     * @param req
     */
    private void setRequestData(HttpServletRequest req) {
        Date date = new Date();
        req.setAttribute("date", date);
        Random r = new Random();
        int rand = r.nextInt(200);
        this.number = rand;
        req.setAttribute("number", rand);
    }

    /**
     * Password before hash to be send by email
     */
    String orig = "";

    /**
     * Creates user and call dao to save the user in database
     * @param gender gender of user
     * @param email email
     * @param phone phone number
     * @param birthdate date of birth
     * @param message message
     * @param name name of user
     * @param address user's address
     * @param nid nid
     * @return user or null if failed
     */
    public UserJ createUser(String gender,
                            String email, String phone,
                            String birthdate, String message,
                            String name, String address, String nid){
        Hash hash =new Hash();
        Password passw = new Password();
        String usern = passw.randomString(8);
        boolean unique = false;
        String pin;
        do {
            pin = passw.generatePin(4);
            unique = usersDao.findPin(pin);
        } while (!unique);
        orig = pin;
        try {
            pin = hash.getHash(pin);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = format.parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        UserJ user = new UserJ();
        user.setName(name);
        user.setGender(gender);
        user.setAddress(address);
        user.setMessage(message);
        user.setEmail(email);
        user.setPhone(phone);
        user.setNid(nid);
        user.setBirthDate(sqlDate);
        user.setLogin(usern);
        user.setPassword(pin);
        RightsJ rj = rightsDao.find("User");

        user.setIdRights(rj);
        usersDao.create(user);
        return user;
    }

    /**
     * Creates account
     * @param accNumber account number
     * @param cardNumber credit card number
     * @param user account owner
     * @return created account or null if failed
     */
    private AccountJ createAccount(String accNumber, String cardNumber, UserJ user){
        AccountJ acc = new AccountJ();
        acc.setBalance(2000000.0);
        acc.setCardNumber(cardNumber);
        acc.setAccNumber(accNumber);
        acc.setIdUser(user);

        AccountJ succ = accountsDao.create(acc);
        return succ;
    }

}
