package pia.data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by jakub on 04.01.2017.
 */
@Entity
@Table(name = "transaction")
public class TransactionJ implements IEntity<Long>  {
    /**
     * primarni klic
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * specificky symbol
     */
    @Column(length = 16)
    private String specSymbol;
    /**
     * variabilni symbol
     */
    @Column(length = 16)
    private String varSymbol;
    /**
     * kod banky
     */
    @Column(length = 4,nullable = false)
    private String bankCode;
    /**
     * stav konta
     */
    @Column(nullable = false)
    private double amount;
    /**
     * konstantni symbol
     */
    @Column(length = 16)
    private String constSymbol;
    /**
     * cislo uctu
     */
    @Column(length = 10, nullable = false)
    private String AccNumber;
    /**
     * zprava
     */
    private String Message;
    @Column(nullable = false)
    /**
     * datum splatnosti
     */
    private Timestamp date;
    /**
     * zda je platba zpracovana (neni pouzito)
     */
    @Column(nullable = false)
    private boolean processed;
    /**
     * odkaz do tabulky uctu
     */
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="idAcc", nullable = false)
    private AccountJ accId;

    public TransactionJ() {
    }


    @Override
    public Long getPK() {
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }


    public AccountJ getAccId() {
        return accId;
    }

    public void setAccId(AccountJ accId) {
        this.accId = accId;
    }
}
