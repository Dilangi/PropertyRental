package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import list.CustomerList;
import list.ErrorHandler;
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
	    private TextField tfContact;

	    @FXML
	    private TextField tfEmail;

	    @FXML
	    private TextField tfName;

	    @FXML
	    private TextField tftfRentCount;
	    
	    

	    @FXML
	    void getGender(ActionEvent event) {//get value for gender variable
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
	    	fieldValidation();
	    }
	    
		private void fieldValidation() throws ClassNotFoundException, IOException {
			if(tfName.getText().isEmpty()) {
				tfName.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
			} else if(tfContact.getText().isEmpty()) {
				tfContact.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
			}else if(tfName.getText().isEmpty()) {
				tfName.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
			}else if(tfEmail.getText().isEmpty()) {
				tfEmail.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
			} 
			else if(!validateEmail(tfEmail.getText())) {ErrorHandler.errorMsg("", "Invalid email!");}
			else if(tftfRentCount.getText().isEmpty()) {
				tfEmail.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
			}else {
				tfName.setStyle(null);
				tfContact.setStyle(null);
				tfEmail.setStyle(null);
				tftfRentCount.setStyle(null);
	    	String name = tfName.getText();
	    	String email = tfEmail.getText();
	    	String contact = tfContact.getText();
	    	int count = Integer.parseInt(tftfRentCount.getText()); 
	    	int id;
	    	CustomerList cl = new CustomerList();
	    	File fileCustomerlist = new File(ObjectHelper.getCustomerListFileName());
			if(fileCustomerlist.exists()){
				cl=ObjectHelper.readCustomerList(cl);
				id = cl.getSize()+1;
			}else { 
				id = 1;}
			
			
	    	Customer ce = new Customer(id,name,email,contact,gender,credit,count);
			cl.addCustomer(ce);
			ObjectHelper.writeToFile(cl);
			ErrorHandler.successMsg("Confirmation", "Customer Added Successfull!");}
			
		}

		private boolean validateEmail(String email) {
			String regex = "^[A-Za-z0-9+_.-]+@(.+)$";   
	        Pattern pattern = Pattern.compile(regex);  
	        Matcher matcher = pattern.matcher(email);  
			return matcher.matches();
		}

		private boolean validateContact() {
			// TODO Auto-generated method stub
			return false;
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
			choiceBoxCreditHistory.setOnAction(this::getGender);
	    }
		
		

	}

