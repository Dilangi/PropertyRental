package list;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.PointOfInterest;
import model.PropertyDetail;

//file reading
public class CsvReader {
   private static Scanner inputFile;
   private static String line;

   public static boolean readNextLine() throws IOException
   {
      boolean lineRead;
      lineRead = inputFile.hasNext();
      if (lineRead)
        line = inputFile.nextLine();
       
      return lineRead;
   }
   
   public static String getLine(){
	   return line;
   }
   
   public static void close() throws IOException
   {
      inputFile.close();
   }
   
   public static void	readCsv(String fileName) throws IOException, Throwable{
	   File file = new File(fileName);
	   inputFile = new Scanner(file);
	   
	   POIList poil = new POIList();	
	   PropertyList pl = new PropertyList();
		
	   readNextLine(); //reading heading line
	   while (readNextLine()){
		   String line = getLine();
		   String[] lineRead = line.split(",");
			
		   if(fileName.equals("House_Rent_Dataset.csv")) { //data set to PropertyDetail object
				Date listed = new SimpleDateFormat("dd/MM/yyyy").parse(lineRead[0]);
				int bedrooms = Integer.parseInt(lineRead[1]);
				int bathrooms = Integer.parseInt(lineRead[2]);
				double rent = Double.parseDouble(lineRead[3]); 
				double size = Double.parseDouble(lineRead[4]);
				String postcode = lineRead[5];
				String lat = lineRead[6].replace("\"", "");
				String lon = lineRead[7].replace("\"", "");
				String furnishing =lineRead[8]; 
				String type = lineRead[9]; 
				String garden = lineRead[10];
				
				PropertyDetail pe = new PropertyDetail(listed, bedrooms, bathrooms, rent, size, postcode,lat,lon, furnishing, type, garden);
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
		
	      close();
		}
}