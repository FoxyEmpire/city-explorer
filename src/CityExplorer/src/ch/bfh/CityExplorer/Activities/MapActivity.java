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
	protected GeoPoint myLocation;
	
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
    
    @Override
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
    	if (point != null){
    		mapController.animateTo(point);
    	}
    }
    
    protected boolean onMarkerClicked(PoiMarker marker){
    	if (marker.getId()> 0){
			Intent intent = new Intent(this, PoiDetailActivity.class);
	    	intent.putExtra("poiId", marker.getId());
			startActivity(intent);
			return true;
		}
    	return false;
    }
    
    protected void fitPoints(List<GeoPoint> points) {
        // set min and max for two points
        int nwLat = -90 * 1000000;
        int nwLng = 180 * 1000000;
        int seLat = 90 * 1000000;
        int seLng = -180 * 1000000;
        // find bounding lats and lngs
        for (GeoPoint point : points) {
            nwLat = Math.max(nwLat, point.getLatitudeE6());
            nwLng = Math.min(nwLng, point.getLongitudeE6());
            seLat = Math.min(seLat, point.getLatitudeE6());
            seLng = Math.max(seLng, point.getLongitudeE6());
        }
        GeoPoint center = new GeoPoint((nwLat + seLat) / 2, (nwLng + seLng) / 2);
        // add padding in each direction
        int spanLatDelta = (int) (Math.abs(nwLat - seLat) * 1.1);
        int spanLngDelta = (int) (Math.abs(seLng - nwLng) * 1.1);

        // fit map to points
        mapController.animateTo(center);
        mapController.zoomToSpan(spanLatDelta, spanLngDelta);
    }
    
    public void onLocationChanged(Location argLocation) {
		myLocation = new GeoPoint((int)(argLocation.getLatitude()*1E6), (int)(argLocation.getLongitude()*1E6));
	}
	
	public void onProviderDisabled(String provider) {}
	public void onProviderEnabled(String provider) {}
	public void onStatusChanged(String provider, int status, Bundle extras) {}
    
    protected class MapsItemizedOverlay extends ItemizedOverlay {
		
		private List<PoiMarker> markers = new ArrayList<PoiMarker>();
		private Context mContext;
		
		public MapsItemizedOverlay(Drawable defaultMarker) {
			super(boundCenterBottom(defaultMarker));
		}
		
		public MapsItemizedOverlay(Drawable defaultMarker, Context context) {
			super(boundCenterBottom(defaultMarker));
			mContext = context;
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
