package model;

public class Property {
	
	private PropertyDetail propertyDetail;
	private final static Property instance = new Property();
	
	private Property() {}
	
	public static Property getInstance(){
		return instance;
	}
	
	public void setPropertyDetail(PropertyDetail propertyDetail) {
		this.propertyDetail = propertyDetail; 
	}
	
	public PropertyDetail getPropertyDetail() {
		return this.propertyDetail;
	}
}
