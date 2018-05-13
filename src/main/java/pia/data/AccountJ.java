package pia.data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by jakub on 04.01.2017.
 */
@Entity
@Table(name = "account")
public class AccountJ implements IEntity<Long> {
    /**
     * primarni klic
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * Cislo uctu
     */
    @Column(nullable = false, length = 10, unique = true)
    private String accNumber;
    /**
     * Cislo kreditni karty
     */
    @Column(nullable = false, length = 16, unique = true)
    private String cardNumber;
    /**
     * Stav uctu
     */
    @Column(nullable = false)
    private Double balance;
    /**
     * odkaz ke kteremu uzivateli je ucet prirazen
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private UserJ idUser;


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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public UserJ getIdUser() {
        return idUser;
    }

    public void setIdUser(UserJ idUser) {
        this.idUser = idUser;
    }
}
