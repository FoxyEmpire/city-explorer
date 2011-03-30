package ch.bfh.CityExplorer.Application;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class PoiMarker  extends OverlayItem {
	private int id;
	
	public PoiMarker(GeoPoint point, String title, String snippet, int id) {
		super(point, title, snippet);
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
}
