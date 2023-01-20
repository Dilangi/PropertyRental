package list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.PointOfInterest;

public class POIList implements Serializable {
	
	private static final long serialVersionUID = 3L;
	private List<PointOfInterest> poiList;

	public POIList() {
		poiList = new ArrayList<PointOfInterest>();
	}

	public List<PointOfInterest> getPOIs() {
		return poiList;
	}

	public void addPoi(PointOfInterest poi) {
		poiList.add(poi);
	}

	public int getSize() {
		return poiList.size();
	}

}
