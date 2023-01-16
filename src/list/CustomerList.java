package list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Customer> customerList;

	public CustomerList() {
		customerList = new ArrayList<Customer>();
	}

	public List<Customer> getCustomers() {
		return customerList;
	}

	public void addCustomer(Customer ce) {
		customerList.add(ce);
	}

	public int getSize() {
		return customerList.size();
	}
}
