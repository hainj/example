package pia.data;

import javax.persistence.*;

/**
 * Created by jakub on 27.12.2016.
 */
@Entity
@Table(name="rights")
public class RightsJ implements IEntity<Integer> {
    /**
     * primarni klic
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    /**
     * nazev prav
     */
    @Column(unique = true, nullable = false)
    private String role;
    /**
     * zda muze pridavat uzivatele
     */
    @Column(nullable = false)
    private int addUser;
    /**
     * zda muze upravovat jine uzivatele nez sebe
     */
    @Column(nullable = false)
    private int modifyUser;
    /**
     * moznost provadeni transakci
     */
    @Column(nullable = false)
    private int transaction;
    /**
     * zda muze upravit sebe ( vzdy 1)
     */
    @Column(nullable = false)
    private int modifSelf;
    /**
     * zda muze mazat uzivatele
     */
    @Column(nullable = false)
    private int deleteUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAddUser() {
        return addUser;
    }

    public void setAddUser(int addUser) {
        this.addUser = addUser;
    }

    public int getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(int modifyUser) {
        this.modifyUser = modifyUser;
    }

    public int getTransaction() {
        return transaction;
    }

    public void setTransaction(int transaction) {
        this.transaction = transaction;
    }

    public int getModifSelf() {
        return modifSelf;
    }

    public void setModifSelf(int modifSelf) {
        this.modifSelf = modifSelf;
    }

    public int getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(int deleteUser) {
        this.deleteUser = deleteUser;
    }

    @Override
    public Integer getPK() {
        return getId();
    }
}
