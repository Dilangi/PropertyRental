package controller;

import java.io.IOException;
import java.text.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnAddPoI;

    @FXML
    private Button btnUpdateProperty;

    @FXML
    private Button btnAddProperty;

    @FXML
    private Button btnViewProperties;

    @FXML
    void addCustomerListener(ActionEvent event) throws IOException {
    	closeParent(btnAddCustomer);
    	showNewWindow("/view/AddCustomer.fxml");
    }

    @FXML
    void addPoIListener(ActionEvent event) throws IOException {
    	closeParent(btnAddPoI);
    	showNewWindow("/view/PointOfInterest.fxml");
    }

    @FXML
    void addPropertyListener(ActionEvent event) throws IOException {
    	
    }

    @FXML
    void updatePropertyListener(ActionEvent event) {
    	
    }

    @FXML
    void viewPropertiesListener(ActionEvent event) throws IOException, ClassNotFoundException, ParseException {
    	closeParent(btnAddCustomer);
    	showNewWindow("/view/Properties.fxml");
    }
    
    void closeParent(Button btn) {
    	Stage primaryStage = (Stage) btn.getScene().getWindow();
    	primaryStage.close();
    }
    
    void showNewWindow(String path) throws IOException{
    	Stage stage = new Stage();
    	Parent p = FXMLLoader.load(getClass().getResource(path));
		Scene s = new Scene(p);
    	stage.setTitle("Property Rentals");
		stage.setScene(s);
		stage.show();
    }
    


    
    
}
