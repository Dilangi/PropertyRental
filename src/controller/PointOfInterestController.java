package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import list.ObjectHelper;
import list.SceneSwitcher;
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
    	File file = new File(ObjectHelper.getPoiListFileName());	
    	
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
    	try {
        	SceneSwitcher sceneSwitcher = new SceneSwitcher();
    		sceneSwitcher.switchView(event, "/view/Home.fxml");
		} catch (IOException e) {
			System.out.println("backListener"+e);
		}
    }

}
