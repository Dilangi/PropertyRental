package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import list.AgreementList;
import list.CustomerList;
import list.ErrorHandler;
import list.ObjectHelper;
import list.SceneSwitcher;
import model.Agreement;
import model.Customer;
import model.Property;
import model.PropertyDetail;

	public class AgreementController  implements Initializable{
		
		Agreement agreement = new Agreement();

		Property property = Property.getInstance();
		PropertyDetail pe = property.getPropertyDetail();
		
		Double deposit;
    	Double agentFee;
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        

	    @FXML
	    private Button btnAgreement;

	    @FXML
	    private Button btnAddCustomer;

	    @FXML
	    private Button btnBack;

	    @FXML
	    private Button btnSearch;

	    @FXML
	    private DatePicker dpEndDate;

	    @FXML
	    private DatePicker dpLetDate;

	    @FXML
	    private Label lblBathCount;

	    @FXML
	    private Label lblBedCount;

	    @FXML
	    private Label lblContact;

	    @FXML
	    private Label lblCredit;

	    @FXML
	    private Label lblCustName;

	    @FXML
	    private Label lblFloorSize;

	    @FXML
	    private Label lblFurnished;

	    @FXML
	    private Label lblGarden;

	    @FXML
	    private Label lblPostal;

	    @FXML
	    private Label lblRent;

	    @FXML
	    private Label lblType;

	    @FXML
	    private TextField tfAgentFee;

	    @FXML
	    private TextField tfCustomerId;

	    @FXML
	    private TextField txtDeposit;

	    @FXML
	    private Label lblHouseHolders;
	    
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
	    void gerCustomerDetail(ActionEvent event) throws NumberFormatException, ClassNotFoundException, IOException {
	    	if(tfCustomerId.getText().isEmpty()) {
	    		ErrorHandler.errorMsg("Warning", "Please enter customer id");
	    	}else {
		    	String id = tfCustomerId.getText();
		    	getCustomer(Integer.parseInt(id), event);}
	    }

	    @FXML
	    void getCustomerView(ActionEvent event) throws IOException {
	    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
			sceneSwitcher.newView(event, "/view/AddCustomer.fxml");
	    }

	    @FXML
	    void rentListener(ActionEvent event) throws IOException, ClassNotFoundException {
	    	
	    	//set deposit, agent fee values into Agreement object
	    	 deposit = Double.parseDouble(txtDeposit.getText());
	    	 agentFee = Double.parseDouble(tfAgentFee.getText());
	    	 agreement.setAgentFee(agentFee);
	    	 agreement.setDeposit(deposit);
	    	 
	    	 if(agreement.getEndDate()==null) {dpEndDate.setStyle("-fx-border-color: red;-fx-border-width: 2px;");}
	    	 else if(agreement.getLetDate()==null){dpEndDate.setStyle("-fx-border-color: red;-fx-border-width: 2px;");}
	    	 else {//if date fields acceptable
	    		if(agreement.getEndDate().isBefore(agreement.getLetDate())) {
	    			ErrorHandler.errorMsg("Error", "Let date and End date mismatch");}
	    		else {
			    	AgreementList al = new AgreementList();
			 		File fileAgreementList = new File(ObjectHelper.getAgreementListFileName());
			 		int id;
			 		if(fileAgreementList.exists()){
			 			al= ObjectHelper.readAgreementList();
			 			id = al.getSize()+1;
			 		}else {id = 1;}
			 		agreement.setAgreementId(id);
			 		al.addAgreement(agreement);
			 		ObjectHelper.writeToFile(al);
			    	 
			    	Node node = (Node) event.getSource();
			    	Stage stage = (Stage) node.getScene().getWindow();
			    	stage.close();
			    	
			    	try {
			    		FXMLLoader loader  = new FXMLLoader();
			    		loader.setLocation(getClass().getResource("/view/Invoice.fxml"));
			    		//loader.load();
			    		
			    		InvoiceController controller = new InvoiceController();
			    	    controller.setAgreement(agreement, true);
			    	    loader.setController(controller);
			    	    Parent root = loader.load();
			    	    Scene scene = new Scene(root);
			    	    stage.setScene(scene);
			    	    stage.show();
			    	  } catch (IOException e) {
			    	    System.err.println(String.format("Error: %s", e.getMessage()));
			    	  }}
	    	 }
	  
	    }

		@FXML
	    void getDate(ActionEvent event) throws ParseException {
	    	LocalDate date = dpEndDate.getValue();
	    	String formattedDate = date.format(formatter);
	    	agreement.setEndDate(LocalDate.parse(formattedDate, formatter));
	    }

		@FXML
		void getLetDate(ActionEvent event) {
			LocalDate date = dpLetDate.getValue();
			String formattedDate = date.format(formatter);
	    	agreement.setLetDate(LocalDate.parse(formattedDate, formatter));
		}
		 
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			setPropertyDetails();
		}

		private void setPropertyDetails() {
			String bedrooms = Integer.toString(pe.getBedrooms());
			String bathrooms = Integer.toString(pe.getBathrooms());
			Double rent = pe.getRent(); 
			String size = Double.toString(pe.getSize());
			String postcode = pe.getPostcode();
			String furnishing =pe.getFurnishing(); 
			String type = pe.getType(); 
			String garden = pe.getGarden();
			
			lblBathCount.setText(bathrooms);
			lblBedCount.setText(bedrooms);
			lblFloorSize.setText(size);
			lblFurnished.setText(furnishing);
			lblRent.setText(Double.toString(rent));
			lblType.setText(type);
			lblGarden.setText(garden);
			lblPostal.setText(postcode);
			
			//calculate agent fee
	    	agentFee = rent*0.20;
	    	tfAgentFee.setText(Double.toString(agentFee));
	    	
	    	//calculate initial deposit
	    	deposit = rent*6;
	    	txtDeposit.setText(Double.toString(deposit));

			agreement.setPropertyDetail(pe);
		}
		
		private void getCustomer(int id, ActionEvent event) throws ClassNotFoundException, IOException {
			CustomerList cl = new CustomerList();
	    	File fileCustomerlist = new File(ObjectHelper.getCustomerListFileName());
			if(fileCustomerlist.exists()){
				cl =ObjectHelper.readCustomerList(cl);
				for(Customer cust : cl.getCustomers()) {
					if(cust.getCustomerId()==id) {
						String custName = cust.getName();
						String custContact =cust.getContact();
						Boolean creditHistory =cust.getCreditHistory();
						lblCustName.setText(custName);
						lblContact.setText(custContact);
						lblCredit.setText(Boolean.toString(creditHistory));
						lblHouseHolders.setText(Integer.toString(cust.getHouseHolder()));
						agreement.setCustomer(cust);
					}else {
						SceneSwitcher sceneSwitcher = new SceneSwitcher();
			    		sceneSwitcher.switchView(event, "/view/AddCustomer.fxml");
					}
				}
			}else { 
				SceneSwitcher sceneSwitcher = new SceneSwitcher();
	    		sceneSwitcher.switchView(event, "/view/AddCustomer.fxml");
			}
		}

	}


