package com.triadamcola.main;

import java.io.IOException;

import javax.swing.plaf.ColorUIResource;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application {
	
	public Main() {
		
	}
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		new TriadamRouteIni();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(root);       
        primaryStage.setTitle("Login - TriadamRoutes");
        primaryStage.getIcons().add(new Image(getClass().getResource("/img/key-security.png").openStream()));
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
