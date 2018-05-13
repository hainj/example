package pia.data;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class User {
	/**
	 * id user
	 */
	private int id;
	/**
	 * user login
	 */
	private String login;
	/**
	 * user name
	 */
	private String name;
	/**
	 * gender of user
	 */
	private String gender;
	/**
	 * date of birth
	 */
	private Date birthdate;
	/**
	 * address
	 */
	private String address;
	/**
	 * phone number
	 */
	private String phone;
	/**
	 * email address
	 */
	private String email;
	/**
	 * rights id
	 */
	private int rights;
	/**
	 * nid
	 */
	private String nid;
	/**
	 * message
	 */
	private String message;
	/**
	 * hashed password
	 */
	private String password;


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthdate() {
		DateFormat df = new SimpleDateFormat("dd. MMMM yyyy");

		return df.format(birthdate);
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRights() {
		return rights;
	}

	public void setRights(int rights) {
		this.rights = rights;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
