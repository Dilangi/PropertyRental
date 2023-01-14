package controller;

import java.io.IOException;
import java.net.URL;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Property;
import model.PropertyDetail;

public class PropertyItemController implements Initializable{

	@FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnRent;

    @FXML
    private Button btnView;

    @FXML
    private Label lblBeds;

    @FXML
    private Label lblListed;

    @FXML
    private Label lblRent;

    @FXML
    private Label lblType;

    @FXML
    void deletePropertyListener(ActionEvent event) {

    }

    @FXML
    void editPropertyListener(ActionEvent event)  throws IOException {
    	closeParent(btnView);
    	showNewWindow("/view/PropertyDetail.fxml");
    }

    @FXML
    void rentListener(ActionEvent event)  throws IOException {
    	closeParent(btnView);
    	showNewWindow("/view/Home.fxml");
    }

    @FXML
    void viewPropertyListener(ActionEvent event) throws IOException, ParseException {
    	passData();
  
		Stage stage = (Stage) btnView.getScene().getWindow();
		stage.close();
		Parent p = FXMLLoader.load(getClass().getResource("/view/PropertyDetail.fxml"));
    	Scene s = new Scene(p);
    	stage.setTitle("Property Rentals");
		stage.setScene(s);
		stage.show();
		
    }

	private void passData() throws ParseException {
		Date listed = new SimpleDateFormat("dd/MM/yyyy").parse(lblListed.getText());
		int bedrooms = Integer.parseInt(lblBeds.getText());
		int bathrooms = Integer.parseInt("2");
		double rent = Double.parseDouble(lblRent.getText()); 
		double size = Double.parseDouble("2.00");
		String postcode = "";
		String latLong = "";
		String furnishing ="22"; 
		String type = lblType.getText(); 
		String garden = "2";
		
		PropertyDetail pe = new PropertyDetail(listed, bedrooms, bathrooms, rent, size, postcode,latLong, furnishing, type, garden);
		Property prop = Property.getInstance();
    	prop.setPropertyDetail(pe);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	public void setData(PropertyDetail propertyDetail) {
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		String date = formatter.format(propertyDetail.getListed());
		lblListed.setText(date);
		lblRent.setText(Double.toString(propertyDetail.getRent()));
		lblType.setText(propertyDetail.getType());
		lblBeds.setText(Integer.toString(propertyDetail.getBedrooms()));
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

