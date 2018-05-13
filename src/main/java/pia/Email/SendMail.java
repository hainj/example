package pia.Email;

import pia.data.TransactionJ;
import pia.data.UserJ;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by jakub on 06.01.2017.
 */
public class SendMail {

    String from = "piaapptest@gmail.com";
    String pass = "pia123app";

    String subject = "Java send mail example";
    String body = "Welcome to JavaMail!";
    String host = "smtp.gmail.com";

    /**
     * Posle email s heslem a pinem uzivatele na udanou adresu
     *
     * @param to   email uzivatele
     * @param pw   heslo
     * @param pin  pin
     * @param name jmeno uzivatele
     */
    public void sendFromGMail(String to, String pw, String pin, String name, int type) {
        Session session = initSession();
        EmailText em = new EmailText();
        subject = em.getSubject(type, name);
        body = em.getEmailText(pin, pw, name);
        send(session, to);

    }

    /**
     * Sends email after changing user information
     * @param to target email
     * @param user edited user
     * @param type type of message
     */
    public void sendEditGMail(String to, UserJ user, int type) {
        Session session = initSession();
        EmailText em = new EmailText();
        subject = em.getSubject(type, "");
        body = em.getEmailTextEdited(user);
        send(session, to);

    }

    /**
     * Sends email after deleting account
     * @param to target email
     * @param type delete message
     */
    public void sendDeleteGMail(String to, int type) {
        Session session = initSession();
        EmailText em = new EmailText();
        subject = em.getSubject(type, "");
        body = em.getEmailTextDeleted();
        send(session, to);

    }

    /**
     * Sends message to email address, sets body, subject etc
     * @param session contains
     * @param to target email
     */
    private void send(Session session, String to) {
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress(to);


            message.addRecipient(Message.RecipientType.TO, toAddress);


            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            System.out.println("emailova adresa error");
        } catch (MessagingException me) {
            System.out.println("Timeout(antivir/firewall)");
        }
    }

    /**
     * Initializes session, and properties to send email
     * @return
     */
    private Session initSession() {
        Properties props = System.getProperties();

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        return session;
    }

    /**
     * Sends confirmation email for transaction
     * @param u user
     * @param ts transaction
     * @param code confirmation code
     */
    public void sendConfirmMail(UserJ u, TransactionJ ts, String code) {
        Session session = initSession();
        EmailText em = new EmailText();
        subject = em.getSubject(4, "");
        body = em.getPaymentConfirm(ts, code);
        send(session, u.getEmail());
    }

    /**
     * Sends confirmation email for login
     * @param u user
     * @param code confirmation code
     */
    public void sendConfirmLogMail(UserJ u, String code) {
        Session session = initSession();
        EmailText em = new EmailText();
        subject = em.getSubject(4, "");
        body = em.getLoginText(u, code);
        send(session, u.getEmail());
    }
}
