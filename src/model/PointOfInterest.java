package model;

import java.io.Serializable;

public class PointOfInterest  implements Serializable{
	private static final long serialVersionUID = 1000L;
	private String place;
	private String postcode;
	private String lat;
	private String lon;
	
	public PointOfInterest(String place, String postcode, String lat, String lon) {
		this.place = place;
		this.postcode = postcode;
		this.lat = lat;
		this.lon= lon;
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

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}
		
}
