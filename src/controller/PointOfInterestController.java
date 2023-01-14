package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;
import model.PointOfInterest;

public class PointOfInterestController {

    @FXML
    private Button btnAddPoI;

    @FXML
    private Button btnBack;

    @FXML
    private TextField tfLatLong;

    @FXML
    private TextField tfPlace;

    @FXML
    private TextField tfPostalCode;

    @FXML
    void addPoIListener(ActionEvent event) {
    	String name = tfPlace.getText();
    	String postalCode = tfPostalCode.getText();
    	String latLong = tfLatLong.getText();
    	

    	PointOfInterest ce = new PointOfInterest(name,postalCode,latLong);
File file = new File("poilist.file");	
    	
    	try {
	        FileOutputStream fileOut = new FileOutputStream(file, true);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(ce);
	        out.close();
	        fileOut.close();
		 } catch(IOException e){
			 System.out.println("OOps...there was a problem"+ e);}
    	}

    @FXML
    void backListener(ActionEvent event) {
    	closeParent(btnBack);
    	try {
			showNewWindow("/view/Home.fxml");
		} catch (IOException e) {
			System.out.println("backListener"+e);
		}
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
