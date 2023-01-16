package list;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
}
