package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import list.ObjectHelper;
import list.PropertyList;
import list.SceneSwitcher;
import model.Property;
import model.PropertyDetail;

public class AddPropertyController implements Initializable{

	Property property = Property.getInstance();
	PropertyDetail pe = property.getPropertyDetail();
	
    @FXML
    private Button btnAddProperty;
    
    @FXML
    private Button btnBack;

    @FXML
    private TextField tfBathCount;

    @FXML
    private TextField tfBedCount;

    @FXML
    private TextField tfFloorSize;

    @FXML
    private TextField tfFurnished;

    @FXML
    private TextField tfGarden;

    @FXML
    private TextField tfLat;

    @FXML
    private TextField tfListedDate;

    @FXML
    private TextField tfLon;

    @FXML
    private TextField tfPostal;

    @FXML
    private TextField tfRent;

    @FXML
    private TextField tfType;
    
    @FXML
    private DatePicker dpListedDate;

	private LocalDate listed;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private int pId;
    
	@FXML
    void backListener(ActionEvent event) {
		try {
    		SceneSwitcher sceneSwitcher = new SceneSwitcher();
    		sceneSwitcher.switchView(event, "/view/Home.fxml");
		} catch (IOException e) {
			System.out.println("backListener"+e);
		}
    }
	
	@FXML
    void getListedDate(ActionEvent event) {
    	LocalDate date = dpListedDate.getValue();
		String formattedDate = date.format(formatter);
		listed = LocalDate.parse(formattedDate, formatter);
    }

    @FXML
    void setPropertyListener(ActionEvent event) throws IOException {

		int bathrooms=Integer.parseInt(tfBathCount.getText());
    	int bedrooms=Integer.parseInt(tfBedCount.getText());
        Double.parseDouble(tfFloorSize.getText());
        String furnishing=tfFurnished.getText();
        String garden=tfGarden.getText();
        String lat=tfLat.getText();
        String lon=tfLon.getText();
        String postcode=tfPostal.getText();
        double rent=Double.parseDouble(tfRent.getText());
        String type=tfType.getText();
        double size=Double.parseDouble(tfFloorSize.getText());
        
        
    	if(pe!= null) {
    		listed = dpListedDate.getValue();
    		
    	}
    	PropertyDetail pd = new PropertyDetail(0,listed, bedrooms, bathrooms, rent, size, postcode, lat, lon, furnishing, type, garden);
    	
    	
    		File file = new File(ObjectHelper.getPropertyListFileName());	
        	PropertyList pl=new PropertyList();
      
    	if(file.exists()){
    		pl=ObjectHelper.readPropertyList();
    		
    	}

    	if(pe!= null) {
    		for(PropertyDetail p: pl.getProperties()) {
    			if(p.getpId()==pId) {
    				p.setpId(pId);
    				p.setListed(listed);
    				p.setBedrooms(bedrooms);
    				p.setBathrooms(bathrooms);
    				p.setRent(rent);
    				p.setSize(size);
    				p.setPostcode(postcode);
    				p.setLat(lat);
    				p.setLon(lon);
    				p.setFurnishing(furnishing);
    				p.setType(type);
    				p.setGarden(garden);
    			}
    		}
    	}
    	else {
    		pId=pl.getSize()+1;
    		pd.setpId(pId);
    		pl.addProperty(pd);
    	}
    	
    	
    	
    	ObjectHelper.writeToFile(pl);
    	
}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if(pe!=null) {
			btnAddProperty.setText("Update Property");
		setFields(pe);}
	}

	private void setFields(PropertyDetail pe) {
		String bedrooms = Integer.toString(pe.getBedrooms());
		String bathrooms = Integer.toString(pe.getBathrooms());
		Double rent = pe.getRent(); 
		String size = Double.toString(pe.getSize());
		String postcode = pe.getPostcode();
		String furnishing =pe.getFurnishing(); 
		String type = pe.getType(); 
		String garden = pe.getGarden();
		
		pId=pe.getpId();
		dpListedDate.setValue(pe.getListed());
		tfBathCount.setText(bathrooms);
		tfBedCount.setText(bedrooms);
		tfFloorSize.setText(size);
		tfFurnished.setText(furnishing);
		tfRent.setText(Double.toString(rent));
		tfType.setText(type);
		tfGarden.setText(garden);
		tfPostal.setText(postcode);
		tfLat.setText(pe.getLat());
		tfLon.setText(pe.getLon());
		
	}
	}
