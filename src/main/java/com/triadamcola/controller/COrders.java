package com.triadamcola.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Dialog;

import com.google.maps.errors.ApiException;
import com.triadamcola.model.Order;
import com.triadamcola.triadamroutes.Routes;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import javafx.application.Platform;


public class COrders implements Initializable {
	
    @FXML
    private TableView<Order> tableView;
    

    @FXML
    private TableColumn<Order, String> idColum;

    @FXML
    private TableColumn<Order, String> nameColum;

    @FXML
    private TableColumn<Order, String> statusColum;

    @FXML
    private TableColumn<Order, String> addressColum;

    @FXML
    private Button btnAddOrder;

    @FXML
    private Button btnDelateOrder;

    @FXML
    private Label labUserName;

    @FXML
    private Button btnDeliverOrder;

    @FXML
    void addOrder(ActionEvent event) {
    	Dialog<ArrayList<String>> dialog = new Dialog<>();
    	dialog.setTitle("Nueva Orden");
    	dialog.setHeaderText("Ingrese los datos de la orden");
    	
    	dialog.setGraphic(new ImageView(this.getClass().getResource("/img/compra.png").toString()));

    	ButtonType loginButtonType = new ButtonType("Aceptar", ButtonData.OK_DONE);
    	dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(20, 150, 10, 10));

    	TextField orderName = new TextField();
    	orderName.setPromptText("Ingrese el nombre del Cliente");
    	TextField orderAddress = new TextField();
    	orderAddress.setPromptText("Ingrese la dirección de entrega del pedido");

    	grid.add(new Label("Nombre del Cliente:"), 0, 0);
    	grid.add(orderName, 1, 0);
    	
    	grid.add(new Label("Dirección:"), 0, 1);
    	grid.add(orderAddress, 1, 1);   	
    	
    	Node acceptButton = dialog.getDialogPane().lookupButton(loginButtonType);
    	acceptButton.setDisable(true);
    	
    	orderAddress.textProperty().addListener((observable, oldValue, newValue) -> {
    		acceptButton.setDisable(newValue.trim().isEmpty() || orderName.getText().equals(""));
    	});
    	
    	orderName.textProperty().addListener((observable, oldValue, newValue) -> {
    		acceptButton.setDisable(newValue.trim().isEmpty() || orderAddress.getText().equals(""));
    	});
    	
    	
    	dialog.getDialogPane().setContent(grid);
    	Platform.runLater(() -> orderName.requestFocus());
    	
    	dialog.setResultConverter(dialogButton -> {
    	    if (dialogButton == loginButtonType) {
        	        ArrayList<String> arrayList = new ArrayList<>();
        	        arrayList.add(orderName.getText());
        	        arrayList.add(orderAddress.getText());
    	    		return arrayList;
    	    }
			return null;
    	});
    	

    	Optional<ArrayList<String>> result = dialog.showAndWait();
    	/*try {*/
    		String name = result.get().get(0);
    		String address = result.get().get(1);
    		Order order = new Order();
    		order.setOrderName(name);
    		order.setOrderAdress(address);
    		order.setOrderStatus("0");
    		order.setOrderID(order.nextID());
    		order.insert();
    		
    		tableView.getItems().add(order);
    
		/*} catch (Exception e) {
			
		}*/
    }

    @FXML
    void delateOrder(ActionEvent event) {
    	
    	if (tableView.getSelectionModel().getSelectedItem() != null){
    		tableView.getSelectionModel().getSelectedItem().remove();
    		tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
		}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error al eliminar la orden");
    		alert.setHeaderText(null);
    		alert.setContentText("No ha selecionado ningúna orden");
    	}

    }

    @FXML
    void deliverOrder(ActionEvent event) {
	    String url = "https://www.google.com/maps/dir/?api=1&origin=";
	    boolean aux = false;
	    try {
			Routes routes = new Routes();
			routes.generateAdyacency();
			Queue<String> orders = routes.generateDeliveryRoute();
			Queue<String> orders2 = routes.generateDeliveryRoute();
			
			for (int i = 0; i < orders2.size(); i++) {
				System.out.println(orders2.poll());
			}

			for (int i = 0; i < orders.size(); i++) {
				if (i == orders.size() -2 ) {
					url += "&destination="+orders.poll();
				}
				else if (i == 0 && orders.size() > 1) {
					url += orders.poll()+"&waypoints=";
				}
				else {
					url += orders.poll();
					
					if (!aux) {
						url += "|";
					}
				}
				if (i == orders.size()-3) {
					aux = true;
				}
    	}
			url += "&region=CO&key="+com.triadamcola.main.TriadamRouteIni.API_KEY;
		} catch (ApiException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    System.out.println(url.charAt(50));
	    System.out.println(url.charAt(51));
	    System.out.println(url.charAt(52));
	    
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
                System.out.println("Please open a browser and go to "+ url);
            }
        }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Order> observableList =  FXCollections.observableArrayList(Order.getOrders());
		tableView.setItems(observableList);
	    idColum.setCellValueFactory(new PropertyValueFactory<Order, String>("orderID"));
	    nameColum.setCellValueFactory(new PropertyValueFactory<Order, String>("orderName"));
	    statusColum.setCellValueFactory(new PropertyValueFactory<Order, String>("orderStatus"));
	    addressColum.setCellValueFactory(new PropertyValueFactory<Order, String>("orderAdress"));
	}

}
