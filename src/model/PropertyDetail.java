package model;

import java.io.Serializable;
import java.util.Date;

public class PropertyDetail  implements Serializable{

	private static final long serialVersionUID = 2000L;
	private Date listed;
	private int bedrooms;
	private int bathrooms;
	private double rent;
	private double size;
	private String postcode;
	private String latLong;
	private String furnishing;
	private String type;
	private String garden;
	
	public Date getListed() {
		return listed;
	}

	public void setListed(Date listed) {
		this.listed = listed;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	public int getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getLatLong() {
		return latLong;
	}

	public void setLatLong(String latLong) {
		this.latLong = latLong;
	}

	public String getFurnishing() {
		return furnishing;
	}

	public void setFurnishing(String furnishing) {
		this.furnishing = furnishing;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGarden() {
		return garden;
	}

	public void setGarden(String garden) {
		this.garden = garden;
	}

	public PropertyDetail(Date listed, int bedrooms, int bathrooms, double rent, double size, String postcode,
			String latLong, String furnishing, String type, String garden) {
		this.listed = listed;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.rent = rent;
		this.size = size;
		this.postcode = postcode;
		this.latLong = latLong;
		this.furnishing = furnishing;
		this.type = type;
		this.garden = garden;
	}

	public PropertyDetail() {
		super();
	}

	
}
