/**
 * 
 */
package com.triadamcola.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * @author ASUS
 *
 */
public class Connexion {
    
    private Connection con;
    private String host;
    private String database;
    private String user;
    private String password;
    
    
    public Connexion(String host, String database, String user, String password) {
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
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
    
    public boolean connect(boolean verify) {
        boolean ok = true;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, user, password);
                    
        } catch (ClassNotFoundException | SQLException ex) {
            if (!verify) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error al conectar con la base de datos \n Configure la conexión correctamente e intente de nuevo", ButtonType.OK);
            }
        }
        return ok;
    }
    
    public ResultSet ejecutar(String command, String[] data, boolean receive) throws SQLException {
        ResultSet rs = null;
        if (con != null) {
            PreparedStatement preparedStatement = con.prepareStatement(command);
            if (data != null) {
                for (int i = 0; i < data.length;i++) {
                    preparedStatement.setString(i+1, data[i]);
                                       
                }
             
            }
            if(receive) {
                 rs = preparedStatement.executeQuery();
             }
             else {
                 preparedStatement.execute();
             }
        }
        return rs;
    }
    
    public void desconectar() {
        try {
            con.close();
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Access denied for User: " + user + ", Password: " + password + ". Configure DB connection.");
        }
    }
  
}
