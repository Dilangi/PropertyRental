package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import list.CustomerList;
import list.ObjectHelper;
import list.POIList;
import list.SceneSwitcher;
import model.Customer;
import model.PointOfInterest;

public class PointOfInterestController  implements Initializable{


    @FXML
    private Button btnAddPoI;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSearch;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField tfLatLong;

    @FXML
    private TextField tfPlace;
    
    @FXML
    private TextField tfPostalCode;

    @FXML
    private TextField tfSearch;

	private boolean isShow;

    @FXML
    void getPOI(ActionEvent event) throws ClassNotFoundException, IOException {
    	String postalCode = tfSearch.getText();
    	searchPOI(postalCode);
    }

	@FXML
    void addPoIListener(ActionEvent event) throws ClassNotFoundException, IOException {
    	String name = tfPlace.getText();
    	String postalCode = tfPostalCode.getText();
    	String latLong = tfLatLong.getText();
    	String lat = latLong.split(",")[0];
    	String lon = latLong.split(",")[1];
    	

    	/*PointOfInterest ce = new PointOfInterest(name,postalCode,latLong);
    	File file = new File(ObjectHelper.getPoiListFileName());	
    	
    	try {
	        FileOutputStream fileOut = new FileOutputStream(file, true);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(ce);
	        out.close();
	        fileOut.close();
		 } catch(IOException e){
			 System.out.println("OOps...there was a problem"+ e);}*/
    	
    	PointOfInterest poie = new PointOfInterest(name,postalCode,lat, lon);
    	File file = new File(ObjectHelper.getPoiListFileName());	
    	POIList poil = new POIList();
    
  
	if(file.exists()){
		poil=ObjectHelper.readPOIList();
	}
	poil.addPoi(poie);
	ObjectHelper.writeToFile(poil);
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tfSearch.setVisible(isShow);
		btnSearch.setVisible(isShow);
		if(isShow) {
			lblTitle.setText("Update Point of Interest");
			btnAddPoI.setText("Update POI");}
	}

	public void setComponent(boolean isShow) {
		this.isShow = isShow;	
	}
	
    private void searchPOI(String postalCode) throws ClassNotFoundException, IOException {
		POIList poil = new POIList();
		File filePoiList = new File(ObjectHelper.getPoiListFileName());
		if(filePoiList.exists()){
			poil = ObjectHelper.readPOIList();
			for(PointOfInterest poi : poil.getPOIs()) {
				if(poi.getPostcode().equals(poi.getPostcode())) {
					tfPlace.setText(poi.getPlace());
					String latLong = poi.getLat()+","+poi.getLon();
					tfLatLong.setText(latLong);
					tfPostalCode.setText(poi.getPostcode());}
				else {//pop up msg
					}
				}
			}else {
				//pop up msg
			}
		}
		
	}


