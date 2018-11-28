package com.triadamcola.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;



import static com.triadamcola.main.TriadamRouteIni.con;
import static com.triadamcola.main.TriadamRouteIni.user;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.triadamcola.model.User;

import javafx.event.ActionEvent;
public class CLogin implements ILogin , Initializable {

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
    void aboutOur(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Acerca de");
    	alert.setHeaderText(null);
    	alert.setContentText("Triadam Routes\nSistema de control de rutas");
    	alert.showAndWait();
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
    		nextWindow(user);
    	}
	}
	
	private void nextWindow(User user) {
			try {
				FXMLLoader loader = new FXMLLoader();
				AnchorPane anchorPane = (AnchorPane) loader.load(getClass().getResource("/fxml/PrincipalView.fxml").openStream());
				Scene scene = new Scene(anchorPane);
		    	Stage stage = (Stage) root.getScene().getWindow();
		    	stage.setTitle("Triadam Routes");

		    	stage.getIcons().set(0, new Image(getClass().getResource("/img/Triadam Cola.png").openStream()));
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


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		  Image openIcon = new Image(getClass().getResourceAsStream("/img/configure.png"));
		  ImageView openView = new ImageView(openIcon);
		  menuItemSettings.setGraphic(openView);
		  
		  openIcon = new Image(getClass().getResourceAsStream("/img/salir.png"));
		  openView = new ImageView(openIcon);
		  menuItemQuit.setGraphic(openView);

	}
	
}