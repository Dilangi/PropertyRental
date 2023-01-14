package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import list.ObjectHelper;
import list.PropertyList;
import model.PropertyDetail;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PropertiesController implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSearch;

    @FXML
    private RadioButton rbBed;

    @FXML
    private RadioButton rbDate;

    @FXML
    private RadioButton rbPrice;

    @FXML
    private VBox vbProperties;

    @FXML
    void backListener(ActionEvent event) {
    	closeParent(btnBack);
    	try {
			showNewWindow("/view/Home.fxml");
		} catch (IOException e) {
			System.out.println("backListener"+e);
		}
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		PropertyList pl = new PropertyList();
		pl =ObjectHelper.readPropertyList();
		
		List<PropertyDetail> pdl = pl.getProperties();
		System.out.println(pdl.size());
		
		
		for(int i=0; i<pdl.size(); i++) {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(this.getClass().getResource("/view/PropertyItem.fxml"));
			
			try {
				HBox hBox = fxmlLoader.load();
				PropertyItemController pic = fxmlLoader.getController();
				pic.setData(pdl.get(i));
				vbProperties.getChildren().add(hBox);} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}


