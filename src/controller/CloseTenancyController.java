package controller;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import list.AgreementList;
import list.ObjectHelper;
import list.SceneSwitcher;
import model.Agreement;
import model.Customer;

public class CloseTenancyController {
	private Agreement agreement;

	 @FXML
	    private Button btnBack;

	    @FXML
	    private Button btnEndTenancy;

	    @FXML
	    private Button btnSearch;

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
	    private Label lblType;

	    @FXML
	    private TextField tfDeduction;

	    @FXML
	    private TextField tfInvoiceId;

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
    void endTenancyListener(ActionEvent event) throws IOException {
    	 agreement.setDeduction(Double.parseDouble(tfDeduction.getText()));
			/*
			 * SceneSwitcher sceneSwitcher = new SceneSwitcher();
			 * sceneSwitcher.passObject(event, agreement, false);
			 */
    	 Node node = (Node) event.getSource();
	    	Stage stage = (Stage) node.getScene().getWindow();
	    	stage.close();
	    	
	    	try {
	    		FXMLLoader loader  = new FXMLLoader();
	    		loader.setLocation(getClass().getResource("/view/Invoice.fxml"));
	    		//loader.load();
	    		
	    		InvoiceController controller = new InvoiceController();
	    	    controller.setAgreement(agreement, false);
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
    void getInvoiceListener(ActionEvent event) throws NumberFormatException, ClassNotFoundException, IOException {
    	String id = tfInvoiceId.getText();
    	getAgreement(Integer.parseInt(id), event);
    }

	private void getAgreement(int id, ActionEvent event) throws ClassNotFoundException, IOException {
		AgreementList al = new AgreementList();
		File fileAgreementlist = new File(ObjectHelper.getAgreementListFileName());
		if(fileAgreementlist.exists()){
			al =ObjectHelper.readAgreementList();
			for(Agreement agreement : al.getAgreements()) {
				if(agreement.getAgreementId()==id) {
					this.agreement= agreement;
					Customer cust =agreement.getCustomer();
					String custName = cust.getName();
					String custContact =cust.getContact();
					Boolean creditHistory =cust.getCreditHistory();
					lblCustName.setText(custName);
					lblContact.setText(custContact);
					lblCredit.setText(Boolean.toString(creditHistory));
					lblHouseHolders.setText(Integer.toString(cust.getHouseHolder()));
					
					DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					String eDate = agreement.getLetDate().format(dateTimeFormatter);
					String sDate = agreement.getLetDate().format(dateTimeFormatter);
					
					lblId.setText(Integer.toString(agreement.getAgreementId()));
					
					lblAgentFee.setText(Double.toString(agreement.getAgentFee()));
					lblDeposit.setText(Double.toString(agreement.getDeposit()));
				    lblTerminate.setText(eDate);
				    lblStart.setText(sDate);
				    
				    lblType.setText(agreement.getPropertyDetail().getType());
				    lblFurnished.setText(agreement.getPropertyDetail().getFurnishing());
				    lblGarden.setText(agreement.getPropertyDetail().getGarden());
				    lblBathCount.setText(Integer.toString(agreement.getPropertyDetail().getBathrooms()));
				    lblBedCount.setText(Integer.toString(agreement.getPropertyDetail().getBedrooms()));
				    lblPostal.setText(agreement.getPropertyDetail().getPostcode());
				    lblRent.setText(Double.toString(agreement.getPropertyDetail().getRent()));
				    
				    lblContact.setText(agreement.getCustomer().getContact());
				    lblCustId.setText(Integer.toString(agreement.getCustomer().getCustomerId()));
				    lblCustName.setText(agreement.getCustomer().getName());
				    lblHouseHolders.setText(Integer.toString(agreement.getCustomer().getHouseHolder())); 
				    lblCredit.setText(Boolean.toString(agreement.getCustomer().getCreditHistory()));
				    
				}}
	}}

}

