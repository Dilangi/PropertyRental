package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import list.CustomerList;
import list.ObjectHelper;
import list.SceneSwitcher;
import model.Customer;

	public class AddCustomerController implements Initializable{
		private String gender="";
		private boolean credit = false;
		private String[] creaditHistory = {"Checked", "Pending"};

	    @FXML
	    private Button btnBack;

	    @FXML
	    private Button btnRegister;

	    @FXML
	    private ChoiceBox<String> choiceBoxCreditHistory;

	    @FXML
	    private ToggleGroup genderGroup;

	    @FXML
	    private RadioButton rbFemale;

	    @FXML
	    private RadioButton rbMale;

	    @FXML
	    private TextArea taNotes;

	    @FXML
	    private TextField tfContact;

	    @FXML
	    private TextField tfEmail;

	    @FXML
	    private TextField tfName;

	    @FXML
	    void getGender(ActionEvent event) {
	    	if(rbMale.isSelected()) {
	    		gender = "Male";}
	    	else if(rbFemale.isSelected()) {
	    		gender = "Female";}
	    }
	    
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
	    void registerListener(ActionEvent event) throws ParseException, ClassNotFoundException, IOException{
	    	String name = tfName.getText();
	    	String email = tfEmail.getText();
	    	String contact = tfContact.getText();
	    	String notes = taNotes.getText();
	    	int id;
	    	CustomerList cl = new CustomerList();
	    	File fileCustomerlist = new File(ObjectHelper.getCustomerListFileName());
//			if(!fileCustomerlist.exists()){
//				File file = new File("customerlist.dat");
//			}
//			else 
			if(fileCustomerlist.exists()){
				ObjectHelper.readCustomerList(cl);
				id = cl.getSize()+1;
			}else { 
				id = 1;}

	    	Customer ce = new Customer(id,name,email,contact,gender,credit,notes);
			cl.addCustomer(ce);
			ObjectHelper.writeToFile(cl);
	    	
	    	}
		
		public void getCreditStatus(ActionEvent event) {
			if(choiceBoxCreditHistory.getValue().equals("Checked")) {
				credit=true;
			}
			else {credit=true;}
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			choiceBoxCreditHistory.getItems().addAll(creaditHistory);
	    }
		
		

	}

