package com.triadamcola.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login implements Initializable {
          
               
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    }


}