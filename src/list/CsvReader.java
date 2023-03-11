package list;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import model.Agreement;
import model.Customer;
import model.PointOfInterest;
import model.PropertyDetail;

//file reading
public class CsvReader {
   private static Scanner inputFile;
   private static String line;
   
	private static String seperator = ",";
	
	public static final String CUSTOMER_EXPORT_FILE_NAME = "customerlist.csv";
	public static final String PROPERTY_EXPORT_FILE_NAME  = "propertylist.csv";
	public static final String POI_EXPORT_FILE_NAME = "poilist.csv";
	public static final String AGREEMENT_EXPORT_FILE_NAME = "agreementist.csv";

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
	   int i =0;
	   while (readNextLine()){
		   i+=1;
		   String line = getLine();
		   String[] lineRead = line.split(",");
			
		   if(fileName.equals("House_Rent_Dataset.csv")) {
			   //data set to PropertyDetail object
			   
			   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate listed = LocalDate.parse(lineRead[0], formatter);
				
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
				
				PropertyDetail pe = new PropertyDetail(i,listed, bedrooms, bathrooms, rent, size, postcode,lat,lon, furnishing, type, garden);
				pl.addProperty(pe);
				
			}
			
			else if(fileName.equals("PlacesOfInterestDataSet.csv")) { // data set to PointOfInterest object
				String place = lineRead[0]; 
				String postcode = lineRead[1]; 
				String lat = lineRead[2].replace("\"", "");
				String lon = lineRead[3].replace("\"", "");
				PointOfInterest poie = new PointOfInterest(place, postcode, lat, lon);
				poil.addPoi(poie);
				
			}
			
	      }
			
		if(fileName.equals("House_Rent_Dataset.csv")) {ObjectHelper.writeToFile(pl);} 				//save PropertyDetail object list in .dat file
		else if(fileName.equals("PlacesOfInterestDataSet.csv")) {ObjectHelper.writeToFile(poil);}	//save PointOfInterest object list in .dat file
		
	      close();
		}
   

	
	public static void writeToCSVFile(String fileName) throws ClassNotFoundException, IOException {
		try {
			File file = new File(fileName);
			PrintWriter pw = new PrintWriter(file);
			switch(fileName) {
			case CUSTOMER_EXPORT_FILE_NAME:
				List<Customer> listCustomer = ObjectHelper.readCustomerList(new CustomerList()).getCustomers();
				for(Customer cust: listCustomer) {
					pw.write(cust.getCustomerId()+seperator+cust.getName()+seperator+cust.getEmail()+seperator+cust.getContact()
					+seperator+cust.getGender()+seperator+cust.getCreditHistory()+seperator+cust.getHouseHolder()+"\n");}
				break;
				
			case PROPERTY_EXPORT_FILE_NAME:
				List<PropertyDetail> listProperty = ObjectHelper.readPropertyList().getProperties();
				for(PropertyDetail prop: listProperty) {
					pw.write(prop.getpId()+seperator+prop.getListed()+seperator+prop.getBedrooms()+seperator+prop.getBathrooms()
					+seperator+prop.getRent()+seperator+prop.getSize()+seperator+prop.getPostcode()+seperator+"\""+prop.getLat()
					+seperator+prop.getLon()+"\""+seperator+prop.getFurnishing()+seperator+prop.getType()+seperator+prop.getGarden()+"\n");
				}
				break;
				
			case POI_EXPORT_FILE_NAME:
				List<PointOfInterest> listPoI = ObjectHelper.readPOIList().getPOIs();
				for(PointOfInterest poi: listPoI) {
						pw.write(poi.getPlace()+seperator+poi.getPostcode()+seperator+"\""+poi.getLat()+seperator+poi.getLon()+"\""+"\n");
					
				}
				break;
			case AGREEMENT_EXPORT_FILE_NAME:
				List<Agreement> listAgreement = ObjectHelper.readAgreementList().getAgreements();
				for(Agreement agreement: listAgreement) {
						pw.write(agreement.getCustomer().getCustomerId()+seperator+agreement.getPropertyDetail().getpId()
								+seperator+agreement.getDeposit()+seperator+agreement.getAgentFee()+seperator+agreement.getLetDate()
								+seperator+agreement.getEndDate()+"\n");}
				break;
			}
			pw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}