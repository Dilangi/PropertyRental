package controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import list.SceneSwitcher;
import model.Property;
import model.PropertyDetail;

public class PropertyItemController{

	@FXML
    private Button btnDistance;
	
	@FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnRent;

    @FXML
    private Button btnView;

    @FXML
    private Label lblBath;

    @FXML
    private Label lblBeds;

    @FXML
    private Label lblFurnish;

    @FXML
    private Label lblGarden;

    @FXML
    private Label lblLatLong;

    @FXML
    private Label lblListed;

    @FXML
    private Label lblLocation;

    @FXML
    private Label lblRent;

    @FXML
    private Label lblSize;

    @FXML
    private Label lblType;

	private int pId;

	private String postcode;

	private String lat;

	private String lon;

	  @FXML
	    void getDistanceListener(ActionEvent event) throws IOException {
		  getLocationDetails();
		  
		  Node node = (Node) event.getSource();
	    	Stage stage = (Stage) node.getScene().getWindow();
	    	stage.close();
	    	
	    	try {
	    		FXMLLoader loader  = new FXMLLoader();
	    		loader.setLocation(getClass().getResource("/view/Distance.fxml"));
	    		
	    		DistanceController controller = new DistanceController();
	    	    controller.setInDistance(postcode,lat,lon);
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
    void deletePropertyListener(ActionEvent event) {

    }

    @FXML
    void editPropertyListener(ActionEvent event)  throws IOException, ParseException {
    	passData();
    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.switchView(event, "/view/AddProperty.fxml");
    }

    @FXML
    void viewPropertyListener(ActionEvent event) throws IOException, ParseException {
    	passData();
    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.newView(event, "/view/PropertyDetail.fxml");
    }

	private void passData() throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate listed = LocalDate.parse(lblListed.getText(), formatter);
		
		
		int bedrooms = Integer.parseInt(lblBeds.getText());
		int bathrooms = Integer.parseInt(lblBath.getText());
		double rent = Double.parseDouble(lblRent.getText()); 
		double size = Double.parseDouble(lblSize.getText());
		getLocationDetails();
		String furnishing =lblFurnish.getText(); 
		String type = lblType.getText(); 
		String garden = lblGarden.getText();
		PropertyDetail pe = new PropertyDetail(pId,listed, bedrooms, bathrooms, rent, size, postcode,lat, lon, furnishing, type, garden);
		Property prop = Property.getInstance();
    	prop.setPropertyDetail(pe);
	}

	private void getLocationDetails() {
		 postcode = lblLocation.getText();
		 lat = lblLatLong.getText().split(",")[0];
		 lon = lblLatLong.getText().split(",")[1];
		
	}


	public void setData(PropertyDetail propertyDetail) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date = propertyDetail.getListed().format(formatter);
		
		String today = LocalDate.now().format(formatter);
		
		//if property already rented then hide Rent button
		if(propertyDetail.isRented())
			{btnRent.setVisible(false);}
		else
			{btnRent.setVisible(true);}
		pId = propertyDetail.getpId();
		lblListed.setText(date);
		lblRent.setText(Double.toString(propertyDetail.getRent()));
		lblType.setText(propertyDetail.getType());
		lblBeds.setText(Integer.toString(propertyDetail.getBedrooms()));
		lblFurnish.setText(propertyDetail.getFurnishing());
		lblGarden.setText(propertyDetail.getGarden());
		lblLatLong.setText(propertyDetail.getLat()+","+propertyDetail.getLon());
		lblLocation.setText(propertyDetail.getPostcode());
		lblSize.setText(Double.toString(propertyDetail.getSize()));
		lblType.setText(propertyDetail.getType());
		lblBath.setText(Integer.toString(propertyDetail.getBathrooms()));
	}

}

