package list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.PropertyDetail;

public class PropertyList implements Serializable {
	
	private static final long serialVersionUID = 2L;
	private List<PropertyDetail> propertyList;

	public PropertyList() {
		propertyList = new ArrayList<PropertyDetail>();
	}

	public List<PropertyDetail> getProperties() {
		return propertyList;
	}

	public void addProperty(PropertyDetail pe) {
		propertyList.add(pe);
	}

}