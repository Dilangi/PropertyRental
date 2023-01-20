package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import list.ObjectHelper;
import list.PropertyList;
import list.SceneSwitcher;
import model.PropertyDetail;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PropertiesController implements Initializable{

	private String filter;
	List<PropertyDetail> pdl = new ArrayList<PropertyDetail>();
	
	@FXML
	private TextField txtBedHint; 

	@FXML
	private TextField txtMaxPriceHint;

	@FXML
	private TextField txtMinPriceHint;
	    
	@FXML
	private DatePicker dpListedHint;
	
	@FXML
	private Button btnRefresh;
	
	@FXML
    private Button btnBack;

    @FXML
    private Button btnSearch;

    @FXML
    private RadioButton rbBed;

    @FXML
    private RadioButton rbDate;

    @FXML
    private RadioButton rbPrice;

    @FXML
    private VBox vbProperties;
	private int searchBed;
	private double searchMaxPrice;
	private double searchMinPrice;
	private LocalDate searchDate;

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
		PropertyList pl =ObjectHelper.readPropertyList();
		
		
		
				pdl = pl.getProperties();
		
		
		for(int i=0; i<pdl.size(); i++) {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(this.getClass().getResource("/view/PropertyItem.fxml"));
			
			try {
				HBox hBox = fxmlLoader.load();
				PropertyItemController pic = fxmlLoader.getController();
				pic.setData(pdl.get(i));
				vbProperties.getChildren().add(hBox);} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@FXML
    void reloadListener(ActionEvent event) {
		try {
    		SceneSwitcher sceneSwitcher = new SceneSwitcher();
    		sceneSwitcher.switchView(event, "/view/Properties.fxml");
		} catch (IOException e) {
			System.out.println("backListener"+e);
		}
    }
	 
	 @FXML
	    void filterSearchListener(ActionEvent event) {
		 List<PropertyDetail> pdlNew = new ArrayList<PropertyDetail>();
		 
		 if(txtBedHint.getText()!=null) {
			 searchBed = Integer.parseInt(txtBedHint.getText());
		 }
		 if(txtMaxPriceHint.getText() != null) {
			 searchMaxPrice = Double.parseDouble(txtMaxPriceHint.getText());}
		 if(txtMinPriceHint.getText() != null) {
			 searchMinPrice = Double.parseDouble(txtMinPriceHint.getText());}
		 if(dpListedHint.getValue() != null) {
			 searchDate =  dpListedHint.getValue();}
		 	
		  for(PropertyDetail pd: pdl) {
			  if(pd.getBedrooms()==searchBed && pd.getRent()>= searchMinPrice && 
					  pd.getRent()<= searchMaxPrice && searchDate.isBefore(pd.getListed())) {
				  pdlNew.add(pd);
			  }
		  }
		  
		  vbProperties.getChildren().clear();
		  for(int i=0; i<pdlNew.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(this.getClass().getResource("/view/PropertyItem.fxml"));
				
				try {
					HBox hBox = fxmlLoader.load();
					PropertyItemController pic = fxmlLoader.getController();
					pic.setData(pdlNew.get(i));
					vbProperties.getChildren().add(hBox);} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
//refresh vbProperties
	 }
}

