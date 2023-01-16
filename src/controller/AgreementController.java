package controller;

	import java.io.File;
import java.io.IOException;
	import java.net.URL;
	import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
	import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

	import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.fxml.Initializable;
	import javafx.scene.control.Button;
	import javafx.scene.control.DatePicker;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
import list.CustomerList;
import list.ObjectHelper;
import list.SceneSwitcher;
	import model.Agreement;
import model.Customer;
import model.Property;
	import model.PropertyDetail;

	public class AgreementController  implements Initializable{
		
		Agreement agreement = new Agreement();

	    @FXML
	    private Button btnAgreement;

	    @FXML
	    private Button btnBack;

	    @FXML
	    private Button btnSearch;

	    @FXML
	    private DatePicker dpEndDate;

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
	    private TextField tfRentCount;

	    @FXML
	    private TextField txtDeposit;

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
	    	getCustomer(Integer.parseInt(tfCustomerId.getText()));
	    }

	    @FXML
	    void rentListener(ActionEvent event) throws IOException {
	    	Double deposit = Double.parseDouble(txtDeposit.getText());
	    	Double agentFee = Double.parseDouble(tfAgentFee.getText());
	    	agreement.setAgentFee(agentFee);
	    	agreement.setDeposit(deposit);

	    	SceneSwitcher sceneSwitcher = new SceneSwitcher();
			sceneSwitcher.switchView(event, "/view/Invoice.fxml");
	    }

		@FXML
	    void getDate(ActionEvent event) throws ParseException {
	    	LocalDate date = dpEndDate.getValue();
	    	String eDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    	Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(eDate);
	    	agreement.setEndDate(endDate);
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			setPropertyDetails();
		}

		private void setPropertyDetails() {
			Property property = Property.getInstance();
			PropertyDetail pe = property.getPropertyDetail();

			Format formatter = new SimpleDateFormat("dd/MM/yyyy");
			String date = formatter.format(pe.getListed());
			
			String bedrooms = Integer.toString(pe.getBedrooms());
			String bathrooms = Integer.toString(pe.getBathrooms());
			String rent = Double.toString(pe.getRent()); 
			String size = Double.toString(pe.getSize());
			String postcode = pe.getPostcode();
			String furnishing =pe.getFurnishing(); 
			String type = pe.getType(); 
			String garden = pe.getGarden();
			
			lblBathCount.setText(bathrooms);
			lblBedCount.setText(bedrooms);
			lblFloorSize.setText(size);
			lblFurnished.setText(furnishing);
			lblRent.setText(rent);
			lblType.setText(type);
			lblGarden.setText(garden);
			lblPostal.setText(postcode);

			agreement.setPropertyDetail(pe);
		}
		
		private void getCustomer(int id) throws ClassNotFoundException, IOException {
			CustomerList cl = new CustomerList();
	    	File fileCustomerlist = new File(ObjectHelper.getCustomerListFileName());
			if(fileCustomerlist.exists()){
				ObjectHelper.readCustomerList(cl);
				for(Customer cust : cl.getCustomers()) {
					if(cust.getCustomerId()==id) {
						String custName = cust.getName();
						String custContact =cust.getContact();
						Boolean creditHistory =cust.getCreditHistory();
						lblCustName.setText(custName);
						lblContact.setText(custContact);
						lblCredit.setText(Boolean.toString(creditHistory));
						agreement.setCustomer(cust);
					}else {
						//open customer registeration seperate view and once close get customer details
					}
				}
			}else { 
				//open customer registeration seperate view and once close get customer details
				}
		}

}

