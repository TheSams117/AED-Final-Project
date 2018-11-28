package com.triadamcola.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static com.triadamcola.main.TriadamRouteIni.user;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.triadamcola.model.Order;
import com.triadamcola.model.User;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class CUsers implements Initializable {

    @FXML
    private AnchorPane root;
    

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> idColum;

    @FXML
    private TableColumn<User, String> nameColum;

    @FXML
    private TableColumn<User, String> lastNameColum;

    @FXML
    private TableColumn<User, String> dniColum;

    @FXML
    private TableColumn<User, String> permissionColum;

    @FXML
    private TableColumn<User, String> activeColum;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnModify;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnBack;

    @FXML
    void back(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane anchorPane = (AnchorPane) loader.load(getClass().getResource("/fxml/PrincipalView.fxml").openStream());
			Scene scene = new Scene(anchorPane);
	    	Stage stage = (Stage) root.getScene().getWindow();
	    	stage.setTitle("Triadam Routes");
	    	stage.getIcons().add(new Image(getClass().getResource("/img/Triadam Cola.png").openStream()));
	    	stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    @FXML
    void deleteUser(ActionEvent event) {
    	if (tableView.getSelectionModel().getSelectedItem() != null){
    		tableView.getSelectionModel().getSelectedItem().remove();
    		tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
		}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error al eliminar el Usuario");
    		alert.setHeaderText(null);
    		alert.setContentText("No ha selecionado ningún usuario");
    	}
    }

    @FXML
    void insertUser(ActionEvent event) {
    	Dialog<ArrayList<String>> dialog = new Dialog<>();
    	dialog.setTitle("Nuevo Usuario");
    	dialog.setHeaderText("Ingrese los datos del Usuario");
    	dialog.setResizable(true);
    	dialog.setWidth(500);
    	dialog.setHeight(500);
    	
    	ImageView img = new ImageView(this.getClass().getResource("/img/usuario.png").toString());
    	img.setFitHeight(50);
    	img.setFitWidth(50);
    	
    	dialog.setGraphic(img);

    	ButtonType loginButtonType = new ButtonType("Aceptar", ButtonData.OK_DONE);
    	dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(20, 150, 10, 10));

    	TextField userName = new TextField();
    	userName.setPromptText("Ingrese el nombre");
    	
    	TextField lastName = new TextField();
    	lastName.setPromptText("Ingrese el apellido");
    	
    	PasswordField passwordField = new PasswordField();
    	passwordField.setPromptText("Ingrese la contraseña");
    	
    	TextField dni = new TextField();
    	dni.setPromptText("Ingrese el dni");
    	
    	RadioButton radioButton = new RadioButton("Vendedor");
    	RadioButton radioButton2 = new RadioButton("Transportador");
    	RadioButton radioButton3 = new RadioButton("Administrador");
    	
    	ImageView imageView = new ImageView();
    	
    	final ToggleGroup group = new ToggleGroup();
    	radioButton.setToggleGroup(group);
    	
    	radioButton2.setToggleGroup(group);
    	radioButton3.setToggleGroup(group);
    	
    	radioButton.setUserData("Vendedor");
    	radioButton2.setUserData("Transportador");
    	radioButton3.setUserData("Administrador");
    	
    	
    	
    	ImageView image = new ImageView();
    	
    	group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
    	    public void changed(ObservableValue<? extends Toggle> ov,
    	        Toggle old_toggle, Toggle new_toggle) {
    	            if (group.getSelectedToggle() != null) {
    	                Image image = new Image(
    	                    getClass().getResourceAsStream("/img/"+group.getSelectedToggle().getUserData().toString()+".png"
    	                    )
    	                );
    	                
    	                imageView.setImage(image);
    	            }                
    	        }
    	});
    	
    	imageView.setFitWidth(50);
    	imageView.setFitHeight(50);
    	
    	HBox hBox = new HBox(radioButton, radioButton2, radioButton3);

    	
    	
    	
    	grid.add(new Label("Nombre:"), 0, 0);
    	grid.add(userName, 1, 0);
    	
    	grid.add(new Label("Apellido:"), 0, 1);
    	grid.add(lastName, 1, 1);	
    	
    	grid.add(new Label("Contraseña"),0,2);
    	grid.add(passwordField, 1, 2);
    	
    	grid.add(new Label("DNI"),0,3);
    	grid.add(dni, 1, 3);
    	
    	grid.add(new Label("Permisos"),0,4);
    	grid.add(hBox, 1, 4);
    	
    	grid.add(imageView, 2, 3);
    	

    	Node acceptButton = dialog.getDialogPane().lookupButton(loginButtonType);
    	acceptButton.setDisable(true);
    	
    	lastName.textProperty().addListener((observable, oldValue, newValue) -> {
    		acceptButton.setDisable(newValue.trim().isEmpty() || userName.getText().equals(""));
    	});
    	
    	userName.textProperty().addListener((observable, oldValue, newValue) -> {
    		acceptButton.setDisable(newValue.trim().isEmpty() || lastName.getText().equals(""));
    	});
    	
    	
    	dialog.getDialogPane().setContent(grid);
    	Platform.runLater(() -> userName.requestFocus());
    	
    	dialog.setResultConverter(dialogButton -> {
    	    if (dialogButton == loginButtonType) {
        	        ArrayList<String> arrayList = new ArrayList<>();
        	        arrayList.add(userName.getText());
        	        arrayList.add(lastName.getText());
        	        arrayList.add(passwordField.getText());
        	        arrayList.add(dni.getText());
        	        arrayList.add(group.getSelectedToggle().getUserData().toString());
    	    		return arrayList;
    	    }
			return null;
    	});
    	

    	Optional<ArrayList<String>> result = dialog.showAndWait();
    		String name = result.get().get(0);
    		String lastname = result.get().get(1);
    		String pass = result.get().get(2);
    		String id = result.get().get(3);
    		String type = "";
    		
    		switch (result.get().get(4)) {
			case "Vendedor":
				type = "1";
				break;
			case "Transportador":
				type = "0";
				break;
			default:
				type = "2";
				break;
			}
    		
    		User u = new User();
    		u.setUserName(name);
    		u.setUserLastName(lastname);
    		u.setUserDNI(id);
    		u.setUserType(type);
    		u.setUserActive("1");
    		u.setUserID(user.nextID());
    		u.insert(pass);
    		
    		tableView.getItems().add(u);
    }

    @FXML
    void modifyUser(ActionEvent event) {

    }

    @FXML
    void searchUser(ActionEvent event) {
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<User> observableList =  FXCollections.observableArrayList(User.getUsers());	
		tableView.setItems(observableList);
	    idColum.setCellValueFactory(new PropertyValueFactory<User, String>("userID"));
	    nameColum.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
	    lastNameColum.setCellValueFactory(new PropertyValueFactory<User, String>("userLastName")); 
	    dniColum.setCellValueFactory(new PropertyValueFactory<User, String>("userDNI"));
	    permissionColum.setCellValueFactory(new PropertyValueFactory<User, String>("userType"));
	    activeColum.setCellValueFactory(new PropertyValueFactory<User, String>("userActive"));
	}

}
