package model;
	
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import list.CsvReader;
import list.ObjectHelper;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Home extends Application {
	@Override
	public void start(Stage stage) {
		try {
			initialLoad();
			Parent p = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
			Scene s = new Scene(p);
			stage.setTitle("Property Rentals");
			stage.setScene(s);
			stage.show();
		} catch(Exception e) {
			System.out.println("Initial load exeption" +e);
		} catch (Throwable e) {
			System.out.println("Initial load throwable" +e);
		}
	}

	public static void main(String[] args){
		launch(args);
	}
	
	//initial write .csv to .data files
	private void initialLoad() throws IOException, Throwable {
		File filePropertylist = new File(ObjectHelper.getPropertyListFileName());
		File filePoilist = new File(ObjectHelper.getPoiListFileName());
		if(!filePropertylist.exists()) { 
			CsvReader.readCsv("House_Rent_Dataset.csv");
		}
		if(!filePoilist.exists()){
			CsvReader.readCsv("PlacesOfInterestDataSet.csv");
		}
	}
	
	
}
