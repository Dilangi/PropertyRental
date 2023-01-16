package model;

import java.util.Date;

public class Agreement {
	private Customer customer;
	private PropertyDetail propertyDetail;
	private double deposit;
	private double agentFee;
	private Date endDate;
	
	public Agreement(Customer customer, PropertyDetail propertyDetail, double deposit, double agentFee, Date endDate) {
		this.customer = customer;
		this.propertyDetail = propertyDetail;
		this.deposit = deposit;
		this.agentFee = agentFee;
		this.endDate = endDate;
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
