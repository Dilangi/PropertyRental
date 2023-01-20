package controller;

import java.io.IOException;
import java.text.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import list.SceneSwitcher;

public class HomeController {

	@FXML
    private Button btnExport;
	
	@FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnAddPoI;

    @FXML
    private Button btnAddProperty;

    @FXML
    private Button btnViewProperties;

    @FXML
    private Button btnCloseTenancy;
    
    @FXML
    private Button btnUpdatePOI;

    @FXML
    void exportListener(ActionEvent event) throws IOException {
    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.newView(event, "/view/Export.fxml");
    }
    
    @FXML
    void addCustomerListener(ActionEvent event) throws IOException {
    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.switchView(event, "/view/AddCustomer.fxml");
    }

    @FXML
    void addPoIListener(ActionEvent event) throws IOException {
    	Node node = (Node) event.getSource();
    	Stage stage = (Stage) node.getScene().getWindow();
    	stage.close();
    	
    	try {
    		FXMLLoader loader  = new FXMLLoader();
    		loader.setLocation(getClass().getResource("/view/PointOfInterest.fxml"));
    		
    		PointOfInterestController controller = new PointOfInterestController();
    	    controller.setComponent(false);
    	    loader.setController(controller);
    	    Parent root = loader.load();
    	    Scene scene = new Scene(root);
    	    stage.setScene(scene);
    	    stage.show();
    	  } catch (IOException e) {
    	    System.err.println(String.format("Error: %s", e.getMessage()));
    	  }
    }

    @FXML
    void addPropertyListener(ActionEvent event) throws IOException {
    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.switchView(event, "/view/AddProperty.fxml");
    }
    
    @FXML
    void updatePoIListener(ActionEvent event) {
    	Node node = (Node) event.getSource();
    	Stage stage = (Stage) node.getScene().getWindow();
    	stage.close();
    	
    	try {
    		FXMLLoader loader  = new FXMLLoader();
    		loader.setLocation(getClass().getResource("/view/PointOfInterest.fxml"));
    		
    		PointOfInterestController controller = new PointOfInterestController();
    	    controller.setComponent(true);
    	    loader.setController(controller);
    	    Parent root = loader.load();
    	    Scene scene = new Scene(root);
    	    stage.setScene(scene);
    	    stage.show();
    	  } catch (IOException e) {
    	    System.err.println(String.format("Error: %s", e.getMessage()));
    	  }
    }

    @FXML
    void viewPropertiesListener(ActionEvent event) throws IOException, ClassNotFoundException, ParseException {
    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.switchView(event, "/view/Properties.fxml");
    } 

    @FXML
    void closeTenancyListener(ActionEvent event) throws IOException {
    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.switchView(event, "/view/CloseTenancy.fxml");
    }

}
