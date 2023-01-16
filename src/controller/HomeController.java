package controller;

import java.io.IOException;
import java.text.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import list.SceneSwitcher;

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
    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.switchView(event, "/view/AddCustomer.fxml");
    }

    @FXML
    void addPoIListener(ActionEvent event) throws IOException {
    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.switchView(event, "/view/PointOfInterest.fxml");
    }

    @FXML
    void addPropertyListener(ActionEvent event) throws IOException {
    	
    }

    @FXML
    void updatePropertyListener(ActionEvent event) {
    	
    }

    @FXML
    void viewPropertiesListener(ActionEvent event) throws IOException, ClassNotFoundException, ParseException {
    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.switchView(event, "/view/Properties.fxml");
    } 
}
