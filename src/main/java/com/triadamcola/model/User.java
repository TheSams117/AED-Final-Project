/**
 * 
 */
package com.triadamcola.model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import static com.triadamcola.main.TriadamRouteIni.con;

/**
 * @author ASUS
 *
 */
@SuppressWarnings("restriction")
public class User {
	
	private String userID;
	private String userName;
	private String userLastName;
	private String userDNI;
	private String userType;
	private String userActive;
	
	public User() {
		this("-1", "NULL", "NULL", "000000000000", "-1", "0");
	}
	
	
	public User(String userID, String userName, String userLastName, String userDNI,
			String userType, String userActive) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userLastName = userLastName;
		this.userDNI = userDNI;
		this.userType = userType;
		this.userActive = userActive;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserLastName() {
		return userLastName;
	}


	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}


	public String getUserDNI() {
		return userDNI;
	}


	public void setUserDNI(String userDNI) {
		this.userDNI = userDNI;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getUserActive() {
		return userActive;
	}


	public void setUserActive(String userActive) {
		this.userActive = userActive;
	}
	
	@SuppressWarnings("restriction")
	public static User validate(String usr, String pass) {
		User user = null;
		try {
			ResultSet rs = con.execute("SELECT * FROM users WHERE BINARY user_name = ? AND user_password = MD5(?) AND user_active = 1 ", new String[] {usr, pass}, true);
			rs.next();
			if (rs.isLast()) {
				System.out.println("ENTRA AL PUTO IF");
				user = new User();
				user.setUserID(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserLastName(rs.getString("user_lastname"));
				user.setUserDNI(rs.getString("user_dni"));
				user.setUserType(rs.getString("user_type"));
				user.setUserActive(rs.getString("user_active"));
			}
		} catch (SQLException | NullPointerException exception) {
			exception.printStackTrace();			
		}
		
		return user;
	}
	
	public String insert(String pass) {
		String msg = "";
		try {
			con.execute("INSERT INTO users VALUES(DEFAULT, ?, ?, MD5(?), ?, ?, ?", new String[] {userName, userLastName, pass, userDNI, userType, userActive}, false);
			
		} catch (SQLException e) {
			msg = e.getMessage();
		}
		return msg;
	}

	public String modify(String pass) {
		String msg = "";
		try {
			con.execute("UPDATE user SET user_name = ?, user_lastname = ?, user_password = MD5(?), user_dni = ?, user_type = ?, user_active = ? WHERE user_id = ?", new String[] {userName, userLastName, pass, userDNI, userType, userActive, userID} , false);
		} catch (SQLException e) {
			msg = e.getMessage();
		}
		return msg;
	}
	
	public String remove() {
		String msg = "";
		try {
			con.execute("DELATE FROM users where user_id = ? ", new String[] {userID}, false);
		} catch (SQLException e) {
			msg = e.getMessage();
		}
		return msg;
	}
	
	public static ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<>();
		try {
			ResultSet rs = con.execute("SELECT * FROM users ORDER BY user_name ASC", null, true);
			while (rs.next()) {
				String code = rs.getString("user_id");
				String name = rs.getString("user_name");
				String dni = rs.getString("user_dni");
				String lastname = rs.getString("user_lastname");
				String type = rs.getString("user_type");
				String status = rs.getString("user_active");
				User user = new User(code, name, lastname, dni, type, status);
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static User search(String code) {
		User user = null;
		try {
			ResultSet rs = con.execute("SELECT * FROM users WHERE user_id = ?", new String[] {code} , true);
			rs.next();
			user = new User();
			user.setUserID(rs.getString("user_id"));
			user.setUserName(rs.getString("user_name"));
			user.setUserLastName(rs.getString("user_lastname"));
			user.setUserDNI(rs.getString("user_dni"));
			user.setUserType(rs.getString("user_type"));
			user.setUserActive(rs.getString("user_active"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static String nextID() {
		String id = "000000000000";
		try {
            ResultSet rs = con.execute("SELECT LPAD((SELECT COUNT(*) + 1 FROM users), 6, '0') AS nextCod", null, true);		
            rs.next();
            id = rs.getString("nextID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
		
}
