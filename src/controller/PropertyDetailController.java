package controller;

import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Property;
import model.PropertyDetail;

public class PropertyDetailController  implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private Button btnRent;

    @FXML
    private TextField tfBathCount;

    @FXML
    private TextField tfBedCount;

    @FXML
    private TextField tfFloorSize;

    @FXML
    private TextField tfFurnished;

    @FXML
    private TextField tfListedDate;

    @FXML
    private TextField tfRent;

    @FXML
    private TextField tfType;

    @FXML
    void backListener(ActionEvent event) {

    }

    @FXML
    void rentListener(ActionEvent event) {

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
		String latLong = pe.getLatLong();
		String furnishing =pe.getFurnishing(); 
		String type = pe.getType(); 
		String garden = pe.getGarden();
		
		tfBathCount.setText(bathrooms);
		tfBedCount.setText(bedrooms);
		tfFloorSize.setText(size);
		tfFurnished.setText(furnishing);
		tfListedDate.setText(date);
		tfRent.setText(rent);
		tfType.setText(type);
		}

}

