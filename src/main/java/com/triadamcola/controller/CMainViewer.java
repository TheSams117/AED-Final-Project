package com.triadamcola.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;


import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.text.TabableView;

import com.triadamcola.model.Order;
import com.triadamcola.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import static com.triadamcola.main.TriadamRouteIni.user;

public class CMainViewer implements Initializable{
	

    @FXML
    private AnchorPane root;

    @FXML
    private Label labUserName;

    @FXML
    private Label labUserRol;

    @FXML
    private Button btnUsers;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnLogOut;
    @FXML
    void logOut(ActionEvent event) {
    	root.getScene().getWindow().hide();
    }

    @FXML
    void ordersWindows(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane anchorPane = (AnchorPane) loader.load(getClass().getResource("/fxml/StartWindow.fxml").openStream());
			Scene scene = new Scene(anchorPane);
	    	Stage stage = (Stage) root.getScene().getWindow();
	    	stage.setTitle("Ordenes");
	    	stage.getIcons().add(new Image(getClass().getResource("/img/ordenes.png").openStream()));
	    	stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void userWiindows(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane anchorPane = (AnchorPane) loader.load(getClass().getResource("/fxml/StartWindow.fxml").openStream());
			Scene scene = new Scene(anchorPane);
	    	Stage stage = (Stage) root.getScene().getWindow();
	    	stage.setTitle("Ordenes");
	    	stage.getIcons().add(new Image(getClass().getResource("/img/usuario.png").openStream()));
	    	stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		labUserName.setText(user.getUserName()+" "+ user.getUserLastName());
		if (user.getUserType().equals("2")) {
			String type = "Administrador";
		}
		else {
			String type = "Invitado";
		}
		labUserRol.setText(user.getUserType());
		
	}
/*
	public User getUserLogIn() {
		return userLogIn;
	}

	public void setUserLogIn(User userLogIn) {
		this.userLogIn = userLogIn;
	}*/

}
