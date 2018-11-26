package com.triadamcola.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import static com.triadamcola.main.TriadamRouteIni.con;
import static com.triadamcola.main.TriadamRouteIni.user;

import java.io.IOException;

import com.triadamcola.model.User;

import javafx.event.ActionEvent;
public class CLogin implements ILogin {

    @FXML
    private AnchorPane root;

    @FXML
    private PasswordField passUserPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private MenuItem menuItemSettings;

    @FXML
    private MenuItem menuItemQuit;

    @FXML
    private MenuItem menuItemAbout;

    @FXML
    private Button btnQuit;

    @FXML
    private Button btnLogin;

    @FXML
    void exit(ActionEvent event) {
    	exit();
    }

    @FXML
    void login(ActionEvent event) {
    	String userName = txtUserName.getText();
    	String password = passUserPassword.getText();
    	validate(userName, password);
    }

	@Override
	public void validate(String userName, String password) {
    	User u = User.validate(userName, password);
    	if(user != null) {
    		user = u;
    		nextWindow();
    	}
	}
	
	 private void nextWindow() {
			try {
				FXMLLoader loader = new FXMLLoader();
				AnchorPane anchorPane = (AnchorPane) loader.load(getClass().getResource("/fxml/StartWindow.fxml").openStream());
				Scene scene = new Scene(anchorPane);
		    	Stage stage = (Stage) root.getScene().getWindow();
		    	stage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }    
	

	@Override
	public void settings() {
				
	}

	@Override
	public void exit() {
		root.getScene().getWindow().hide();
		con.disconnect();
	}

}