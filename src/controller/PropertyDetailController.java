package controller;

import java.io.IOException;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import list.SceneSwitcher;
import model.Property;
import model.PropertyDetail;

public class PropertyDetailController  implements Initializable{

    @FXML
    private Button btnRent1;

    @FXML
    private Label lblBathCount;

    @FXML
    private Label lblBedCount;

    @FXML
    private Label lblFloorSize;

    @FXML
    private Label lblFurnished;

    @FXML
    private Label lblGarden;

    @FXML
    private Label lblLatLong;

    @FXML
    private Label lblListedDate;

    @FXML
    private Label lblLocation;

    @FXML
    private Label lblRent;

    @FXML
    private Label lblType;

    @FXML
    private Label lblPostal;

    @FXML
    void rentListener(ActionEvent event) throws IOException {
    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.switchView(event, "/view/Agreement.fxml");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Property property = Property.getInstance();
		PropertyDetail pe = property.getPropertyDetail();

		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		String date = formatter.format(pe.getListed());
		String bedrooms = Integer.toString(pe.getBedrooms());
		String bathrooms = Integer.toString(pe.getBathrooms());
		String rent = Double.toString(pe.getRent()); 
		String size = Double.toString(pe.getSize());
		String postcode = pe.getPostcode();
		String lat = pe.getLat();
		String lon = pe.getLon();
		String furnishing =pe.getFurnishing(); 
		String type = pe.getType(); 
		String garden = pe.getGarden();
		
		lblBathCount.setText(bathrooms);
		lblBedCount.setText(bedrooms);
		lblFloorSize.setText(size);
		lblFurnished.setText(furnishing);
		lblListedDate.setText(date);
		lblRent.setText(rent);
		lblType.setText(type);
		lblGarden.setText(garden);
		lblLatLong.setText(lat+","+lon);
		lblPostal.setText(postcode);
		}

}


