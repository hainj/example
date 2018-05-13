package pia.Email;

import pia.data.TransactionJ;
import pia.data.UserJ;

import javax.mail.Message;

/**
 * Created by jakub on 25.01.2017.
 */
public class EmailText implements IEmailText{
    @Override
    public String getSubject(int type, String name) {
        String subject = "";
        switch (type)
        {
            case 0:
                subject = "Welcome " + name;
                break;
            case 1:
                subject = "Account modified";
                break;
            case 2:
                subject = "Account deleted";
                break;
            case 3:
                subject = "Transaction successful";
                break;
            case 4:
                subject = "Confirmation code";
                break;
            default:
                subject = "Hello";
                break;

        }
        return subject;
    }

    public String getEmailText(String login, String pw, String name) {
        String body = "Welcome user " + name + " login to your account is " + login + " and pin is " + pw;
        return body;

    }

    @Override
    public String getEmailTextEdited(UserJ user) {
        String body = "Your account was edited by admin. \n" +
                "Name: " + user.getName() + "\n" +
                "Date of birth: " + user.getBirthDate() + "\n"+
                "Nid: " + user.getNid() + "\n" +
                "Email: " + user.getEmail() + "\n" +
                "Phone: " + user.getPhone() + "\n" +
                "Address: " + user.getAddress() + "\n" +
                "Please contact us if these changes were wrong";
        return body;
    }

    @Override
    public String getEmailTextDeleted() {
        String body = "Your account was deleted";
        return body;
    }

    @Override
    public String getPaymentConfirm(TransactionJ ts, String code) {
        String body = "Target account: " +  ts.getAccNumber() + "\n" +
                "Bank code: " +  ts.getBankCode() + "\n" +
                "Amount: " + ts.getAmount() + " \n";
        if(ts.getVarSymbol() != null){
            body= body + "Variable symbol: " + ts.getVarSymbol() + "\n";
        }
        if(ts.getSpecSymbol() != null){
            body= body + "Specific symbol: " + ts.getSpecSymbol() + "\n";
        }
        if(ts.getConstSymbol() != null){
            body= body + "Constant symbol: " + ts.getConstSymbol() + "\n";
        }
        if(ts.getMessage() != null){
            body= body + "Message: " + ts.getMessage() + "\n";
        }
        body = body + "Confirmation code: "+ code;
        return body;
    }

    @Override
    public String getLoginText(UserJ u, String code) {
        String body = "Name: " + u.getName() + "\n"+
                "Date of birth: " + u.getBirthDate() +"\n" +
               "Confirmation code: " + code ;

        return body;
    }
}
