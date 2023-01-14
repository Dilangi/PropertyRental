package model;

import java.io.Serializable;

public class PointOfInterest  implements Serializable{
	private static final long serialVersionUID = 1000L;
	private String place;
	private String postcode;
	private String latlong;
	
	public PointOfInterest(String place, String postcode, String latlong) {
		this.place = place;
		this.postcode = postcode;
		this.latlong = latlong;
	}

	public PointOfInterest() {
		super();
	}

	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getLatlong() {
		return latlong;
	}
	public void setLatlong(String latlong) {
		this.latlong = latlong;
	}	
}
