package controller;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import list.ObjectHelper;
import list.PropertyList;
import model.Agreement;
import model.Property;
import model.PropertyDetail;

public class InvoiceController  implements Initializable{
	//todo set isRented true for singleton and update the  in .dat file
	
	Agreement agreement;
	String address;
    
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
    private Label lblPostal;
    
    @FXML
    private Label lblRent;

    @FXML
    private Label lblTerminate;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblType;

    @FXML
    private Label lblStart;

    @FXML
    void backListener(ActionEvent event) {

    }

    @FXML
    void confirmListener(ActionEvent event) throws IOException {
    	setRented();
    }

	private void setRented() throws IOException {
		//write to .dat file
		PropertyList pl =ObjectHelper.readPropertyList();
		
		List<PropertyDetail> pdl = new ArrayList<PropertyDetail>();
		pdl = pl.getProperties();

		for(int i=0; i<pdl.size(); i++) {
			if(address.equals(pdl.get(i).getPostcode())) {
				pdl.get(i).setRented(true);
				break;
			}
		}
		ObjectHelper.writeToFile(pl);
PropertyList pl2 =ObjectHelper.readPropertyList();
		
		List<PropertyDetail> pdl2 = new ArrayList<PropertyDetail>();
		pdl2 = pl2.getProperties();

		for(int i=0; i<pdl2.size(); i++) {
			System.out.println(pdl2.get(i).isRented());
			}
		
		//change customer dependents
		
		
	}

	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String eDate = agreement.getLetDate().format(dateTimeFormatter);
		String sDate = agreement.getLetDate().format(dateTimeFormatter);
		
		double total = agreement.getAgentFee()+agreement.getDeposit();
		
		address = agreement.getPropertyDetail().getPostcode();
		
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
