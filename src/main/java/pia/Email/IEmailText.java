package pia.Email;

import pia.data.TransactionJ;
import pia.data.UserJ;

import javax.mail.*;

/**
 * Created by jakub on 25.01.2017.
 */
public interface IEmailText {
    /**
     * Gets message subject
     * @param type message type id
     * @param name user name
     * @return
     */
     String getSubject(int type, String name);

    /**
     * Gets email text for registration
     * @param login login
     * @param pw password
     * @param name name of user
     * @return body of email
     */
     String getEmailText(String login, String pw, String name);

    /**
     * Creates message to be send after editing user
     * @param user edited user
     * @return body of email
     */
     String getEmailTextEdited(UserJ user);

    /**
     * Text to be sent after deleting user
     * @return
     */
     String getEmailTextDeleted();

    /**
     * Text of email with confirmation code and transaction details
     * @param ts transaction
     * @param code code for confirmation
     * @return text of email
     */
    String getPaymentConfirm(TransactionJ ts, String code);

    /**
     * Text of email with few account details and confirmation code
     * @param u user
     * @param code confirmation code
     * @return body of email
     */
    String getLoginText(UserJ u, String code);
}
