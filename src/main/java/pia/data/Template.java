package pia.data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by jakub on 06.01.2017.
 */
@Entity
@Table(name = "templates")
public class Template implements IEntity<Long>{
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
     * nazev sablony
     */
    @Column(nullable = false)
    private String name;
    /**
     * variabilni symbol
     */
    @Column(length = 16)
    private String varSymbol;
    /**
     * cislo banky
     */
    @Column(length = 4,nullable = false)
    private String bankCode;
    /**
     * posilana castka
     */
    @Column(nullable = false)
    private double amount;
    @Column(length = 16)
    /**
     * konstantni symbol
     */
    private String constSymbol;
    /**
     * Cislo ciloveho uctu
     */
    @Column(length = 10, nullable = false)
    private String accNumber;
    /**
     * zprava
     */
    private String message;

    /**
     * Odkaz, ke kteremu uctu sablona patri
     */
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="idAcc", nullable = false)
    private AccountJ accId;

    @Override
    public Long getPK() {
        return getId();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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




    public AccountJ getAccId() {
        return accId;
    }

    public void setAccId(AccountJ accId) {
        this.accId = accId;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
