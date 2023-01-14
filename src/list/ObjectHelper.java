package list;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectHelper {
	private static String customerListFileName = "customerlist.dat";
	private static String propertyListFileName = "propertylist.dat";
	private static String poiListFileName = "poilist.dat";
	
	
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
	      doSerialize(list, customerListFileName);
	      System.out.println("The serialized objects were written to "+ customerListFileName);	
	}
	
	// write data into propertylist.dat
	public static void writeToFile(PropertyList list) throws IOException {
	      doSerialize(list, propertyListFileName);
	      System.out.println("The serialized objects were written to "+ propertyListFileName);	
	}
	
	// write data into poilist.dat
	public static void writeToFile(POIList list) throws IOException {
	      doSerialize(list, poiListFileName);
	      System.out.println("The serialized objects were written to "+ poiListFileName);	
	}
    
	// read data from customerlist.dat
	public static CustomerList readCustomerList() throws IOException, ClassNotFoundException {
		CustomerList list = new CustomerList();
		Object obj;
		obj = doDeserialize(customerListFileName);
		if (obj instanceof CustomerList)
			list = (CustomerList) obj;
		System.out.println("list size: "+list.getCustomers().size());
		
		// to do remove this 
		if (list.getCustomers().size() > 0) {
			System.out.println("Customers in the list are: ");
			for (int i = 0; i < list.getCustomers().size(); i++) {
				System.out.println("Customer Name: " + list.getCustomers().get(i));
			}
		}//to do end
		return list;
	}
	
	// read data from propertylist.dat
	public static PropertyList readPropertyList(){
		PropertyList list = new PropertyList();
		Object obj;
		try {
			obj = doDeserialize(propertyListFileName);
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
		obj = doDeserialize(poiListFileName);
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
	
	

}
