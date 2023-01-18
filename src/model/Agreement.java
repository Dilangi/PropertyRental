package model;

import java.time.LocalDate;

public class Agreement {
	private Customer customer;
	private PropertyDetail propertyDetail;
	private double deposit;
	private double agentFee;
	private LocalDate endDate;
	private LocalDate letDate;
	
	public Agreement(Customer customer, PropertyDetail propertyDetail, double deposit, double agentFee, LocalDate letDate, LocalDate endDate) {
		this.customer = customer;
		this.propertyDetail = propertyDetail;
		this.deposit = deposit;
		this.agentFee = agentFee;
		this.endDate = endDate;
		this.letDate = letDate;
	}
	
	public Agreement() {}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PropertyDetail getPropertyDetail() {
		return propertyDetail;
	}

	public void setPropertyDetail(PropertyDetail propertyDetail) {
		this.propertyDetail = propertyDetail;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getAgentFee() {
		return agentFee;
	}

	public void setAgentFee(double agentFee) {
		this.agentFee = agentFee;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getLetDate() {
		return letDate;
	}

	public void setLetDate(LocalDate letDate) {
		this.letDate = letDate;
	}
	
}
