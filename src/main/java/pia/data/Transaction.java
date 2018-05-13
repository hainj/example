package pia.data;

import java.sql.Timestamp;

/**
 * Created by jakub on 27.12.2016.
 */
public class Transaction {
    /**
     * Transaction id
     */
    private int id;
    /**
     * Specific symbol
     */
    private String specSymbol;
    /**
     * Variable symbol
     */
    private String varSymbol;
    /**
     * Bank code
     */
    private String bankCode;
    /**
     * transaction amount
     */
    private double amount;
    /**
     * constant symbol
     */
    private String constSymbol;
    /**
     * target account number
     */
    private String AccNumber;
    /**
     * Transaction message
     */
    private String Message;
    /**
     * Deadline for transaction
     */
    private Timestamp date;
    /**
     * Processed(1) or not(0)
     */
    private int processed;
    /**
     * Account id
     */
    private int account;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecSymbol() {
        return specSymbol;
    }

    public void setSpecSymbol(String specSymbol) {
        this.specSymbol = specSymbol;
    }

    public String getVarSymbol() {
        return varSymbol;
    }

    public void setVarSymbol(String varSymbol) {
        this.varSymbol = varSymbol;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getConstSymbol() {
        return constSymbol;
    }

    public void setConstSymbol(String constSymbol) {
        this.constSymbol = constSymbol;
    }

    public String getAccNumber() {
        return AccNumber;
    }

    public void setAccNumber(String accNumber) {
        AccNumber = accNumber;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getProcessed() {
        return processed;
    }

    public void setProcessed(int processed) {
        this.processed = processed;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }
}
