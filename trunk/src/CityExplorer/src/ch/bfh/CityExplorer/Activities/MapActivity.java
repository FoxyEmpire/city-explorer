package ch.bfh.CityExplorer.Activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import ch.bfh.CityExplorer.R;
import ch.bfh.CityExplorer.Application.PoiMarker;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public abstract class MapActivity extends com.google.android.maps.MapActivity implements LocationListener {
	protected MapView mapView;
	protected MapController mapController;
	protected LocationManager locationManager;
	protected MapsItemizedOverlay itemizedOverlay;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        mapController = mapView.getController();
        mapController.setZoom(15);
        
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        
        Drawable drawable = this.getResources().getDrawable(R.drawable.pin);
        itemizedOverlay = new MapsItemizedOverlay(drawable);
        mapView.getOverlays().add(itemizedOverlay);
    }
    
    protected boolean isRouteDisplayed() {
		return false;
	}
    
    @Override
    public void onResume(){
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 120000L, 500.0f, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 120000L, 500.0f, this);
	}
    
    @Override
	public void onPause(){
		super.onPause();
		locationManager.removeUpdates(this);
	}
    
    /**
     * Zentriert die Map an diesem Punkt.
     * @param point
     */
    protected void moveTo(GeoPoint point){
    	mapController.animateTo(point);
    }
    
    protected boolean onMarkerClicked(PoiMarker marker){
    	return false;
    }
    
    public void onLocationChanged(Location argLocation) {
		GeoPoint point = new GeoPoint((int)(argLocation.getLatitude()*1E6), (int)(argLocation.getLongitude()*1E6));
		moveTo(point);
	}
	
	public void onProviderDisabled(String provider) {}
	public void onProviderEnabled(String provider) {}
	public void onStatusChanged(String provider, int status, Bundle extras) {}
    
    protected class MapsItemizedOverlay extends ItemizedOverlay {
		
		private List<PoiMarker> markers = new ArrayList<PoiMarker>();
		
		public MapsItemizedOverlay(Drawable defaultMarker) {
			super(boundCenterBottom(defaultMarker));
		}
		
		public void addOverlay(PoiMarker marker) {
			markers.add(marker);
			populate();
		}

		@Override
		protected OverlayItem createItem(int arg0) {
			return markers.get(arg0);
		}

		@Override
		public int size() {
			return markers.size();
		}
		
		@Override
		protected boolean onTap(int index) {
			PoiMarker marker = markers.get(index);
			
			return onMarkerClicked(marker);
		}
		
	}
}
