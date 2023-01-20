package list;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.List;

import model.Agreement;
import model.Customer;
import model.PointOfInterest;
import model.PropertyDetail;

public class ObjectHelper {
	private static final String CUSTOMER_LIST_FILE_NAME = "customerlist.dat";
	private static final String PROPERTY_LIST_FILE_NAME  = "propertylist.dat";
	private static final String POI_LIST_FILE_NAME = "poilist.dat";
	private static final String AGREEMENT_LIST_FILE_NAME = "agreementist.dat";
	
	public static final String CUSTOMER_EXPORT_FILE_NAME = "customerlist.csv";
	public static final String PROPERTY_EXPORT_FILE_NAME  = "propertylist.csv";
	public static final String POI_EXPORT_FILE_NAME = "poilist.csv";
	public static final String AGREEMENT_EXPORT_FILE_NAME = "agreementist.csv";
	
	private static String seperator = ",";
	
	public static String getCustomerListFileName() {
		return CUSTOMER_LIST_FILE_NAME;
	}
	
	public static String getPropertyListFileName() {
		return PROPERTY_LIST_FILE_NAME;
	}

	public static String getPoiListFileName() {
		return POI_LIST_FILE_NAME;
	}

	public static String getAgreementListFileName() {
		return AGREEMENT_LIST_FILE_NAME;
	}
	
	// Serialize the object to a file
	public static void doSerialize(Object obj, String outputFile) throws IOException {
        FileOutputStream fileTowrite = new FileOutputStream(outputFile);
        ObjectOutputStream objTowrite = new ObjectOutputStream(fileTowrite);
        objTowrite.writeObject(obj);
        fileTowrite.close();
    }

    // Deserialize the Java object from a given file
    public static Object doDeserialize(String inputFile) throws IOException, ClassNotFoundException {
    	Object obj = new Object();
    	File f = new File (inputFile);
    	if (f.exists()) {
    	FileInputStream fileToread = new FileInputStream(inputFile);
        ObjectInputStream objToread = new ObjectInputStream(fileToread);
    	  if (f.length() > 0) {
            obj = objToread.readObject();
            objToread.close();
    	   } else {
    		   System.out.println("File " + inputFile + " is empty");
    	   }
    	}
    	  else {
    		  System.out.println("File " + inputFile + " does not exist");
    	  }
        return obj;
    }
    
    // write data into customerlist.dat
	public static void writeToFile(CustomerList list) throws IOException {
	      doSerialize(list, CUSTOMER_LIST_FILE_NAME);
	      System.out.println("The serialized objects were written to "+ CUSTOMER_LIST_FILE_NAME);	
	}
	
	// write data into propertylist.dat
	public static void writeToFile(PropertyList list) throws IOException {
	      doSerialize(list, PROPERTY_LIST_FILE_NAME);
	      System.out.println("The serialized objects were written to "+ PROPERTY_LIST_FILE_NAME);	
	}
	
	// write data into poilist.dat
	public static void writeToFile(POIList list) throws IOException {
	      doSerialize(list, POI_LIST_FILE_NAME);
	      System.out.println("The serialized objects were written to "+ POI_LIST_FILE_NAME);	
	}
	
	 // write data into customerlist.dat
	public static void writeToFile(AgreementList list) throws IOException {
	      doSerialize(list, AGREEMENT_LIST_FILE_NAME);
	      System.out.println("The serialized objects were written to "+ AGREEMENT_LIST_FILE_NAME);	
	}
		
	// read data from customerlist.dat
	public static CustomerList readCustomerList(CustomerList list) throws IOException, ClassNotFoundException {
		Object obj;
		obj = doDeserialize(CUSTOMER_LIST_FILE_NAME);
		if (obj instanceof CustomerList)
			list = (CustomerList) obj;
		System.out.println("list size: "+list.getCustomers().size());
		
		// todo remove this 
		if (list.getCustomers().size() > 0) {
			System.out.println("Customers in the list are: ");
			for (int i = 0; i < list.getCustomers().size(); i++) {
				System.out.println("Customer Name: " + list.getCustomers().get(i));
			}
		}//todo end
		return list;
	}
	
	// read data from propertylist.dat
	public static PropertyList readPropertyList(){
		PropertyList list = new PropertyList();
		Object obj;
		try {
			obj = doDeserialize(PROPERTY_LIST_FILE_NAME);
			if (obj instanceof PropertyList)
				list = (PropertyList) obj;
			System.out.println("list size: "+list.getProperties().size());
			
			// to do remove this
			if (list.getProperties().size() > 0) {
				for (int i = 0; i < list.getProperties().size(); i++) {
					System.out.println("Property Name: " + list.getProperties().get(i).getType());
				}
			}//to do end
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Error here"+e);
		}
		
		return list;
	}
	
	// read data from poilist.dat
	public static POIList readPOIList() throws IOException, ClassNotFoundException {
		POIList list = new POIList();
		Object obj;
		obj = doDeserialize(POI_LIST_FILE_NAME);
		if (obj instanceof POIList)
			list = (POIList) obj;
		System.out.println("list size: "+list.getPOIs().size());
		
		// display list
		if (list.getPOIs().size() > 0) {
			System.out.println("size: ");
			// to do remove this
			for (int i = 0; i < list.getPOIs().size(); i++) {
				System.out.println("POI Name: " + list.getPOIs().get(i));
			}//to do end
		}
		return list;
	}
	
	// read data from agreementlist.dat
		public static AgreementList readAgreementList() throws IOException, ClassNotFoundException {
			AgreementList list = new AgreementList();
			Object obj;
			obj = doDeserialize(AGREEMENT_LIST_FILE_NAME);
			if (obj instanceof AgreementList)
				list = (AgreementList) obj;
			System.out.println("list size: "+list.getAgreements().size());
			
			// display list
			if (list.getAgreements().size() > 0) {
				System.out.println("size: ");
				// to do remove this
				for (int i = 0; i < list.getAgreements().size(); i++) {
					System.out.println("POI Name: " + list.getAgreements().get(i));
				}//to do end
			}
			return list;
		}
		
		public static void writeToCSVFile(String fileName) throws ClassNotFoundException, IOException {
			try {
				File file = new File(fileName);
				PrintWriter pw = new PrintWriter(file);
				switch(fileName) {
				case CUSTOMER_EXPORT_FILE_NAME:
					List<Customer> listCustomer = readCustomerList(new CustomerList()).getCustomers();
					for(Customer cust: listCustomer) {
						pw.write(cust.getCustomerId()+seperator+cust.getName()+seperator+cust.getEmail()+seperator+cust.getContact()
						+seperator+cust.getGender()+seperator+cust.getCreditHistory()+seperator+cust.getHouseHolder()+"\n");}
					break;
					
				case PROPERTY_EXPORT_FILE_NAME:
					List<PropertyDetail> listProperty = readPropertyList().getProperties();
					for(PropertyDetail prop: listProperty) {
						pw.write(prop.getpId()+seperator+prop.getListed()+seperator+prop.getBedrooms()+seperator+prop.getBathrooms()
						+seperator+prop.getRent()+seperator+prop.getSize()+seperator+prop.getPostcode()+seperator+"\""+prop.getLat()
						+seperator+prop.getLon()+"\""+seperator+prop.getFurnishing()+seperator+prop.getType()+seperator+prop.getGarden()+"\n");
					}
					break;
					
				case POI_EXPORT_FILE_NAME:
					List<PointOfInterest> listPoI = readPOIList().getPOIs();
					for(PointOfInterest poi: listPoI) {
							pw.write(poi.getPlace()+seperator+poi.getPostcode()+seperator+"\""+poi.getLat()+seperator+poi.getLon()+"\""+"\n");
						
					}
					break;
				case AGREEMENT_EXPORT_FILE_NAME:
					List<Agreement> listAgreement = readAgreementList().getAgreements();
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
