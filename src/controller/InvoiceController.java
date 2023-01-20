package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import list.ObjectHelper;
import list.PropertyList;
import list.SceneSwitcher;
import model.Agreement;
import model.Property;
import model.PropertyDetail;

public class InvoiceController  implements Initializable{
	//todo set isRented true for singleton and update the  in .dat file
	
	private Agreement agreement;
	private String address;
	private boolean hasRented;
    
	  @FXML
	    private Button btnBack;

	    @FXML
	    private Button btnConfirm;

	    @FXML
	    private Label lblAgentFee;

	    @FXML
	    private Label lblBathCount;

	    @FXML
	    private Label lblBedCount;

	    @FXML
	    private Label lblContact;

	    @FXML
	    private Label lblCredit;

	    @FXML
	    private Label lblCustId;

	    @FXML
	    private Label lblCustName;

	    @FXML
	    private Label lblDeduction;

	    @FXML
	    private Label lblDeposit;

	    @FXML
	    private Label lblFloorSize;

	    @FXML
	    private Label lblFurnished;

	    @FXML
	    private Label lblGarden;

	    @FXML
	    private Label lblHouseHolders;

	    @FXML
	    private Label lblId;

	    @FXML
	    private Label lblPostal;

	    @FXML
	    private Label lblRent;

	    @FXML
	    private Label lblStart;

	    @FXML
	    private Label lblTerminate;

	    @FXML
	    private Label lblTitle;

	    @FXML
	    private Label lblTotal;

	    @FXML
	    private Label lblType;



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
    void confirmListener(ActionEvent event) throws IOException, ClassNotFoundException {
    	setRented();
    	
    	Property property = Property.getInstance();
		property.setNull();
		
    	if(hasRented) {
    		SceneSwitcher sceneSwitcher = new SceneSwitcher();
    		sceneSwitcher.closeWindow(event);
    	}
    }

	private void setRented() throws IOException, ClassNotFoundException {
		//write to .dat file
		PropertyList pl =ObjectHelper.readPropertyList();
		
		List<PropertyDetail> pdl = new ArrayList<PropertyDetail>();
		pdl = pl.getProperties();

		for(int i=0; i<pdl.size(); i++) {
			if(address.equals(pdl.get(i).getPostcode())) {
				pdl.get(i).setRented(hasRented);
				break;
			}
		}
		ObjectHelper.writeToFile(pl);
		
	}

	public void setAgreement(Agreement agreement, boolean hasRented) {
		this.agreement = agreement;
		this.hasRented = hasRented;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("hiii"+hasRented);
	//set deduction fields visibility
		lblTitle.setVisible(!hasRented);
		lblDeduction.setVisible(!hasRented);
		if(!hasRented) {
			lblDeduction.setText(Double.toString(agreement.getDeduction()));
		}
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String eDate = agreement.getLetDate().format(dateTimeFormatter);
		String sDate = agreement.getLetDate().format(dateTimeFormatter);
		
		double total = agreement.getAgentFee()+agreement.getDeposit();
		
		address = agreement.getPropertyDetail().getPostcode();
		
		lblId.setText(Integer.toString(agreement.getAgreementId()));
		
		lblAgentFee.setText(Double.toString(agreement.getAgentFee()));
		lblDeposit.setText(Double.toString(agreement.getDeposit()));
	    lblTotal.setText(Double.toString(total));
		lblTerminate.setText(eDate);
	    lblStart.setText(sDate);
	    
	    lblType.setText(agreement.getPropertyDetail().getType());
	    lblFurnished.setText(agreement.getPropertyDetail().getFurnishing());
	    lblGarden.setText(agreement.getPropertyDetail().getGarden());
	    lblBathCount.setText(Integer.toString(agreement.getPropertyDetail().getBathrooms()));
	    lblBedCount.setText(Integer.toString(agreement.getPropertyDetail().getBedrooms()));
	    lblPostal.setText(address);
	    lblRent.setText(Double.toString(agreement.getPropertyDetail().getRent()));
	    
	    lblContact.setText(agreement.getCustomer().getContact());
	    lblCustId.setText(Integer.toString(agreement.getCustomer().getCustomerId()));
	    lblCustName.setText(agreement.getCustomer().getName());
	    lblHouseHolders.setText(Integer.toString(agreement.getCustomer().getHouseHolder())); 
	    lblCredit.setText(Boolean.toString(agreement.getCustomer().getCreditHistory()));
	    
	  
	    }

}
