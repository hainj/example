package pia.data;

/**
 * Created by jakub on 27.12.2016.
 */
public class Account {
    /**
     * Account number
     */
    private String accNumber;
    /**
     * credit card number
     */
    private String cardNumber;
    /**
     * account balance
     */
    private double balance;
    /**
     * user id
     */
    private int account;
    /**
     * account id
     */
    private int id;


    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getBalanceDouble() {
        return balance;
    }
    public String getBalance() {
        return String.format("%.2f",balance);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
