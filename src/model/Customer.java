package model;

import java.io.Serializable;

public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1199959999L;
	private int customerId;
	private String name;
	private String email;
	private String contact;
	private String gender;
	private String note;
	private boolean creditHistory;
	private int houseHolder;
	
	public Customer(int customerId, String name, String email, String contact, String gender,
			boolean creditHistory, String note) {
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.gender = gender;
		this.note = note;
		this.creditHistory = creditHistory;
		this.houseHolder =1;
	}
	
	public int getHouseHolder() {
		return houseHolder;
	}

	public void setHouseHolder(int houseHolder) {
		this.houseHolder = houseHolder;
	}

	public Customer() {}

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public boolean getCreditHistory() {
		return creditHistory;
	}
	public void setCreditHistory(boolean creditHistory) {
		this.creditHistory = creditHistory;
	}
	
	public String toString(){
		String str = name + " "+ contact + " "+ email+" " +note + " "+ gender+" " +creditHistory ;
		return str;
		
	} 
	
}
