package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Customer;

	public class AddCustomerController {

	    @FXML
	    private Button btnBack;

	    @FXML
	    private Button btnRegister;

	    @FXML
	    private ChoiceBox<?> choiceBoxCreditHistory;

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
	    private TextArea taNotes;
	    
	    @FXML
	    void backListener(ActionEvent event) {
	    	closeParent(btnBack);
	    	try {
				showNewWindow("/view/Home.fxml");
			} catch (IOException e) {
				System.out.println("backListener"+e);
			}
	    }

	    @FXML
	    void registerListener(ActionEvent event) throws ParseException{
	    	String name = tfName.getText();
	    	String email = tfEmail.getText();
	    	String contact = tfContact.getText();
	    	String notes = taNotes.getText();
	    	String gender = "female";
	    	boolean credit = true;
	    	int id = 1;
	    	
	    	ToggleGroup tg = new ToggleGroup();
	    	rbFemale.setToggleGroup(tg);
	    	rbMale.setToggleGroup(tg);
	    	
	    	//todo: set Id, gender radio, credit 
	    	Customer ce = new Customer(1,name,email,contact,"male",true,notes);
	    	
	    	File file = new File("customerlist.file");	
	    	
	    	try {
		        FileOutputStream fileOut = new FileOutputStream(file, true);
		        ObjectOutputStream out = new ObjectOutputStream(fileOut);
		        out.writeObject(ce);
		        out.close();
		        fileOut.close();
			 } catch(IOException e){
				 System.out.println("OOps...there was a problem"+ e);}
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
