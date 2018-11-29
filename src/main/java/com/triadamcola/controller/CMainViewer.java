package com.triadamcola.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Queue;
import java.util.ResourceBundle;

import com.google.maps.errors.ApiException;
import com.triadamcola.triadamroutes.Routes;

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
    private Button btnRoutes;

    
    
    @FXML
    void logOut(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
		AnchorPane anchorPane;
		try {
			anchorPane = (AnchorPane) loader.load(getClass().getResource("/fxml/Login.fxml").openStream());
			Scene scene = new Scene(anchorPane);
	    	Stage stage = (Stage) root.getScene().getWindow();
	    	stage.setTitle("Ordenes");
	    	stage.getIcons().set(0, new Image(getClass().getResource("/img/key-security.png").openStream()));
	    	stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    @FXML
    void routesWindows(ActionEvent event) {
    	if (user.getUserType().equals("2")||user.getUserType().equals("0")) {
			String url = "https://www.google.com/maps/dir/?api=1&origin=";
		    try {
				Routes routes = new Routes();
				routes.generateAdyacency();
				Queue<String> orders = routes.generateDeliveryRoute();
							
				int size = orders.size();
		
				for (int i = 0; !orders.isEmpty(); i++)
				{
					if (i == 0 && size > 1) {
						url += orders.poll()+"&waypoints=";
					}
					else if (i == size -1 ) {
						url += "&destination="+orders.poll();
					}
					
					else {
						url += orders.poll()+"|";
					}
	    	}
				url += "&region=CO&key="+com.triadamcola.main.TriadamRouteIni.API_KEY;
				System.out.println(url);
			} catch (ApiException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		    
		    if(!Desktop.isDesktopSupported()){
	            Desktop desktop = Desktop.getDesktop();
	            try {
	                desktop.browse(new URI(url));
	            } catch (IOException | URISyntaxException e) {
	                e.printStackTrace();
	            }
	        }else{
	            String osName = System.getProperty("os.name");
	            if (osName.startsWith("Windows")) {
	                try {
						Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            } else if (osName.startsWith("Mac OS X")) {
	                // Runtime.getRuntime().exec("open -a safari " + url);
	                // Runtime.getRuntime().exec("open " + url + "/index.html");
	                try {
						Runtime.getRuntime().exec("open " + url);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            } else {
	            	Alert alert = new Alert(AlertType.ERROR);
	            	alert.setTitle("URL Error");
	            	alert.setHeaderText("No se pudo Abrir la URL");
	            	alert.setContentText("Copia la siguiente URL y pegala en un navegador");
	            	
	            	Label label = new Label("URL:");
	            	TextArea textArea = new TextArea(url);
	            	textArea.setEditable(false);
	            	textArea.setWrapText(true);
	            	
	            	textArea.setMaxWidth(Double.MAX_VALUE);
	            	textArea.setMaxHeight(Double.MAX_VALUE);
	            	GridPane.setVgrow(textArea, Priority.ALWAYS);
	            	GridPane.setHgrow(textArea, Priority.ALWAYS);

	            	GridPane expContent = new GridPane();
	            	expContent.setMaxWidth(Double.MAX_VALUE);
	            	expContent.add(label, 0, 0);
	            	expContent.add(textArea, 0, 1);

	            	// Set expandable Exception into the dialog pane.
	            	alert.getDialogPane().setExpandableContent(expContent);

	            	alert.showAndWait();
	            }
	        }
    	}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Permisos insuficientes");
			alert.setHeaderText("No tienes los permisos suficientes para esta función");
			alert.setContentText("Tus permisos como "+user.getPermission()+" no te permiten entrar en esta función"
					+ "\nSi necesitas esta función contacta con un superior\nSi crees que se trata de un error contacta con el administrador"
					+ "del sistema");
			alert.showAndWait();

		}
    }

    @FXML
    void ordersWindows(ActionEvent event) {
    	if (user.getUserType().equals("2") || user.getUserType().equals("1")) {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			AnchorPane anchorPane = (AnchorPane) loader.load(getClass().getResource("/fxml/StartWindow.fxml").openStream());
    			Scene scene = new Scene(anchorPane);
    	    	Stage stage = (Stage) root.getScene().getWindow();
    	    	stage.setTitle("Ordenes");
    	    	stage.getIcons().set(0, new Image(getClass().getResource("/img/ordenes.png").openStream()));
    	    	stage.setScene(scene);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Permisos insuficientes");
			alert.setHeaderText("No tienes los permisos suficientes para esta función");
			alert.setContentText("Tus permisos como "+user.getPermission()+" no te permiten entrar en esta función"
					+ "\nSi necesitas esta función contacta con un superior\nSi crees que se trata de un error contacta con el administrador"
					+ "del sistema");
			alert.showAndWait();
		}
    }

    @FXML
    void userWiindows(ActionEvent event) {
    	if (user.getUserType().equals("2")) {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			AnchorPane anchorPane = (AnchorPane) loader.load(getClass().getResource("/fxml/UsersView.fxml").openStream());
    			Scene scene = new Scene(anchorPane);
    	    	Stage stage = (Stage) root.getScene().getWindow();
    	    	stage.setTitle("Ordenes");
    	    	stage.getIcons().set(0, new Image(getClass().getResource("/img/usuario.png").openStream()));
    	    	stage.setScene(scene);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Permisos insuficientes");
			alert.setHeaderText("No tienes los permisos suficientes para esta función");
			alert.setContentText("Tus permisos como "+user.getPermission()+" no te permiten entrar en esta función"
					+ "\nSi necesitas esta función contacta con un superior\nSi crees que se trata de un error contacta con el administrador"
					+ "del sistema");
			alert.showAndWait();

		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labUserName.setText(user.getUserName()+" "+ user.getUserLastName());
		labUserRol.setText(user.getPermission());
	}

}
