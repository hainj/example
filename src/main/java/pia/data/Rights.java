package pia.data;

/**
 * Created by jakub on 27.12.2016.
 */
public class Rights {
    /**
     * rights id
     */
    private int id;
    /**
     * name of rights
     */
    private String role;
    /**
     * add user right
     */
    private int addUser;
    /**
     * right to modify user
     */
    private int modifyUser;
    /**
     * right to create transactions, view account history
     */
    private int transaction;
    /**
     * rights to mofify self
     */
    private int modifSelf;
    /**
     * rights to modify user
     */
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
}
