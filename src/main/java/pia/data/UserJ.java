package pia.data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by jakub on 04.01.2017.
 */
@Entity
@Table(name="user")
public class UserJ implements IEntity<Long> {
    /**
     * primarni klic
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * mistny pin uzivatele
     */
    @Column(unique = true, nullable = false, length = 8)
    private String Login;
    /**
     * jmeno uzivatele
     */
    @Column(name = "Name", unique = false, nullable = false)
    private String name;
    /**
     * odkaz do tabulky prav
     */
    @ManyToOne()
    @JoinColumn(name = "idRights", nullable = false)
    private RightsJ idRights;
    /**
     * heslo uzivatele
     */
    @Column(nullable = false)
    private String Password;
    /**
     * pohlavi
     */
    @Column(nullable = false)
    private String Gender;
    /**
     * rodne cislo
     */
    @Column(unique = true, nullable = false, length = 15, name = "NID")
    private String nid;
    /**
     * datum narozeni
     */
    @Column(nullable = false)
    private Date BirthDate;
    /**
     * email
     */
    @Column(unique = true, nullable = false)
    private String Email;
    /**
     * telefoni cislo
     */
    @Column(length = 15)
    private String Phone;
    /**
     * kontaktni adresa
     */
    private String Address;
    /**
     * zprava
     */
    private String Message;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public Long getPK() {
        return getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }



    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public RightsJ getIdRights() {
        return idRights;
    }

    public void setIdRights(RightsJ idRights) {
        this.idRights = idRights;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }
}
