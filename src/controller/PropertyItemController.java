package controller;

import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import list.SceneSwitcher;
import model.Property;
import model.PropertyDetail;

public class PropertyItemController{

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

    @FXML
    void deletePropertyListener(ActionEvent event) {

    }

    @FXML
    void editPropertyListener(ActionEvent event)  throws IOException {
    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.switchView(event, "/view/PropertyDetail.fxml");
    }

    @FXML
    void rentListener(ActionEvent event)  throws IOException {
    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.switchView(event, "/view/Home.fxml");
    }

    @FXML
    void viewPropertyListener(ActionEvent event) throws IOException, ParseException {
    	passData();
    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.newView(event, "/view/PropertyDetail.fxml");
    }

	private void passData() throws ParseException {
		Date listed = new SimpleDateFormat("dd/MM/yyyy").parse(lblListed.getText());
		int bedrooms = Integer.parseInt(lblBeds.getText());
		int bathrooms = Integer.parseInt(lblBath.getText());
		double rent = Double.parseDouble(lblRent.getText()); 
		double size = Double.parseDouble(lblSize.getText());
		String postcode = lblLocation.getText();
		String lat = lblLatLong.getText().split(",")[0];
		String lon = lblLatLong.getText().split(",")[1];
		String furnishing =lblFurnish.getText(); 
		String type = lblType.getText(); 
		String garden = lblGarden.getText();
		PropertyDetail pe = new PropertyDetail(listed, bedrooms, bathrooms, rent, size, postcode,lat, lon, furnishing, type, garden);
		Property prop = Property.getInstance();
    	prop.setPropertyDetail(pe);
	}

	public void setData(PropertyDetail propertyDetail) {
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		String date = formatter.format(propertyDetail.getListed());
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

