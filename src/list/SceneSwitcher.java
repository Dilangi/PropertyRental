package list;

import java.io.IOException;

import controller.InvoiceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Agreement;

public class SceneSwitcher {
	private Stage stage;
	
	public void switchView(ActionEvent btn, String path) throws IOException{
		stage =(Stage) ((Node) btn.getSource()).getScene().getWindow();
		setStage(path);
    }
	
	public void newView(ActionEvent btn, String path) throws IOException{
		stage = new Stage();
		setStage(path);
		}
	
	private void setStage(String path) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(path));
		Scene s = new Scene(root);
		stage.setTitle("Property Rentals");
		stage.setScene(s);
		stage.show();
	}
	
	public void closeWindow(ActionEvent btn) {
		Stage stage = (Stage) ((Node) btn.getSource()).getScene().getWindow();
	    stage.close();
	}
	
	public void passObject(ActionEvent btn, Agreement agreement, Boolean hasRented) throws IOException {
		closeWindow(btn);
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/Invoice.fxml"));
		//loader.load();
		
		InvoiceController controller = new InvoiceController();
	    controller.setAgreement(agreement, hasRented);
	    loader.setController(controller);
	    Parent root = loader.load();
	    Scene scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
}
