package pia.data;

import java.text.MessageFormat;

public class DbSettings {
	private static final String DB_ENCODING = "UTF-8";
	private String server;
	private int port;
	private String user;
	private String password;
	private String database;

	/**
	 * Sets jdbc variables
	 * @param server server
	 * @param port
	 * @param user username
	 * @param password db password
	 * @param database database name
	 */
	public DbSettings(String server, int port, String user, String password, String database) {
		this.server = server;
		this.port = port;
		this.user = user;
		this.password = password;
		this.database = database;
	}

	public String getConnectionUrl() {
		return MessageFormat.format("jdbc:mysql://{0}:{1,number,#}/{2}?characterEncoding={3}", this.server, this.port, this.database, DB_ENCODING);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
