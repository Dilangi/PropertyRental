package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import list.ErrorHandler;
import list.FormatHelper;
import list.ObjectHelper;
import list.POIList;
import model.PointOfInterest;

public class DistanceController implements Initializable{

	private String postcode,lat,lon,latX,lonX;

	private String unit;

	private String[] locationList;
	private String[] latList;
	private String[] lonList;
    
	
	@FXML
    private Button btnCalculate;
	
	@FXML
    private ChoiceBox<String> cbUnit;
	
	@FXML
    private ChoiceBox<String> cbPoi;

    @FXML
    private Label lblDistance;

    @FXML
    private Label lblProperty;

	
    public void getPoINames(ActionEvent event) {
    	int indx =  Arrays.asList(locationList).indexOf(cbPoi.getValue());
    	latX = latList[indx].trim();
    	lonX = lonList[indx].trim();
	}
    
    public void getUnit(ActionEvent event) {
		if(cbUnit.getValue().equals("Km")) {
			unit="K";
		}
		else {unit="M";}
	}
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	lblProperty.setText(postcode);
    	setUnitChoices();
    	try {
			setPoiChoices();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	cbPoi.setOnAction(this::getPoINames);
    	cbUnit.setOnAction(this::getUnit);
    }

	private void setUnitChoices() {
		String[] unitList= {"Km","Miles"};
    	cbUnit.getItems().addAll(unitList);
	}

	private void setPoiChoices() throws ClassNotFoundException, IOException {
		File file = new File(ObjectHelper.getPoiListFileName());
		POIList poil = new POIList();
		
		if(file.exists()) {
			poil = ObjectHelper.readPOIList();
			int size = poil.getSize();
			List<PointOfInterest> pList = poil.getPOIs();
			
			locationList = new String[size];
			latList = new String[size];
			lonList = new String[size];
			for(int i=0; i<size; i++) {
				PointOfInterest poi = pList.get(i);
				
				locationList[i]=poi.getPlace();
				latList[i]=poi.getLat();
				lonList[i]=poi.getLon();
			}
			cbPoi.getItems().addAll(locationList);
		}
	}

	public void setInDistance(String postcode, String lat, String lon) {
		this.postcode = postcode;
		this.lat=lat;
		this.lon = lon;
	}
	
	@FXML
    void calculateListener(ActionEvent event) {
		Double latProp = Double.parseDouble(lat);
		Double lonProp = Double.parseDouble(lon);
		Double latPoi=0.0, lonPoi=0.0;
		if(latX!=null)
			latPoi = Double.parseDouble(latX);
		else
			ErrorHandler.errorMsg("", "Select Place");
		if(latX!=null)
			lonPoi = Double.parseDouble(lonX);
		else
			ErrorHandler.errorMsg("", "Select Place");
		if ((latProp == latPoi) && (lonProp == lonPoi)) {
			lblDistance.setText("0");
		}
		else {
			double lonDif = lonProp - lonPoi;
			double distance = Math.sin(Math.toRadians(latProp)) * Math.sin(Math.toRadians(latPoi)) + Math.cos(Math.toRadians(latProp)) * Math.cos(Math.toRadians(latPoi)) * Math.cos(Math.toRadians(lonDif));
			distance = Math.acos(distance);
			distance = Math.toDegrees(distance);
			distance = distance * 60 * 1.1515;
			if (unit.equals("K")) {
				distance = distance * 1.609344;
			} else if (unit.equals("N")) {
				distance = distance * 0.8684;
			}else {
				ErrorHandler.errorMsg("", "Select the Unit");}
			lblDistance.setText(FormatHelper.twoDecimalString(distance));
		}
		}

}

