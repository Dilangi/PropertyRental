package model;
	
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import list.CsvReader;
import list.ObjectHelper;
import list.POIList;
import list.PropertyList;
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
		File filePropertylist = new File("propertylist.dat");
		File filePoilist = new File("poilist.dat");
		if(!filePropertylist.exists()) { 
			readCsv("House_Rent_Dataset.csv");
		}
		if(!filePoilist.exists()){
			readCsv("PlacesOfInterestDataSet.csv");
		}
	}
	
	public void	readCsv(String fileName) throws IOException, Throwable{
		CsvReader csvReader = new CsvReader(fileName);
		POIList poil = new POIList();	
		PropertyList pl = new PropertyList();
		
		csvReader.readNextLine(); //reading heading line
		while (csvReader.readNextLine())
	      {
			String line = csvReader.getLine();
			String[] lineRead = line.split(",");
			
			if(fileName.equals("House_Rent_Dataset.csv")) { //data set to PropertyDetail object
				Date listed = new SimpleDateFormat("dd/MM/yyyy").parse(lineRead[0]);
				int bedrooms = Integer.parseInt(lineRead[1]);
				int bathrooms = Integer.parseInt(lineRead[2]);
				double rent = Double.parseDouble(lineRead[3]); 
				double size = Double.parseDouble(lineRead[4]);
				String postcode = lineRead[5];
				String latLong = lineRead[6];
				String furnishing =lineRead[7]; 
				String type = lineRead[8]; 
				String garden = lineRead[9];
				
				PropertyDetail pe = new PropertyDetail(listed, bedrooms, bathrooms, rent, size, postcode,latLong, furnishing, type, garden);
				pl.addProperty(pe);
				
			}
			
			else if(fileName.equals("PlacesOfInterestDataSet.csv")) { // data set to PointOfInterest object
				String place = lineRead[0]; 
				String postcode = lineRead[1]; 
				String latlong = lineRead[2];
				PointOfInterest poie = new PointOfInterest(place, postcode, latlong);
				poil.addPoi(poie);
				
			}
			
	      }
			
		if(fileName.equals("House_Rent_Dataset.csv")) {ObjectHelper.writeToFile(pl);} 				//save PropertyDetail object list in .dat file
		else if(fileName.equals("PlacesOfInterestDataSet.csv")) {ObjectHelper.writeToFile(poil);}	//save PointOfInterest object list in .dat file
		
	      csvReader.close();
		}
}
