package ch.bfh.CityExplorer.Activities;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.Overlay;

import ch.bfh.CityExplorer.Application.PoiMarker;
import ch.bfh.CityExplorer.Application.Route;
import ch.bfh.CityExplorer.Application.RouteOverlay;
import ch.bfh.CityExplorer.Data.CityExplorerDatabase;
import ch.bfh.CityExplorer.Data.IPointOfInterestColumn;
import ch.bfh.CityExplorer.Data.PointOfInterestTbl;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class RouteMapActivity extends MapActivity {

	private SQLiteDatabase db;
	private List<GeoPoint> route;
	private RouteHandler routeHandler;
	private GeoPoint destination;
	
	final Runnable updateRoute = new Runnable() {  
        public void run() {  
            // draw the path and then invalidate the mapview so that it redraws itself  
        	List<Overlay> overlays = mapView.getOverlays();  
 			for (int i = 1; i < route.size(); i++) {  
 			    overlays.add(new RouteOverlay(route.get(i - 1), route.get(i), Color.RED));  
 			}
            mapView.invalidate();  
        }  
    };  
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	db = new CityExplorerDatabase(this).getReadableDatabase();
    	routeHandler = new RouteHandler();
    	
    	Intent intent = getIntent();
        int pointOfInterestId = 0;
        if (intent.getExtras() != null){
        	pointOfInterestId = intent.getExtras().getInt("pointOfInterestId");
        }
        
        if (pointOfInterestId > 0){
        	Cursor cursor = db.query(PointOfInterestTbl.TABLE_NAME,
            		PointOfInterestTbl.ALL_COLUMNS, PointOfInterestTbl.ID+" = ?", new String[]{String.valueOf(pointOfInterestId)},
            		IPointOfInterestColumn.NAME, null, null);
        	
        	cursor.moveToFirst();
            
            if  (cursor.isAfterLast() == false) {
            	int id = cursor.getInt(cursor.getColumnIndex(IPointOfInterestColumn.ID));
            	//category_id = cursor.getInt(cursor.getColumnIndex(IPointOfInterestColumn.CATEGORY_ID));
            	String name = cursor.getString(cursor.getColumnIndex(IPointOfInterestColumn.NAME));
            	String desc = cursor.getString(cursor.getColumnIndex(IPointOfInterestColumn.DESCRIPTION));
            	double lat = cursor.getDouble(cursor.getColumnIndex(IPointOfInterestColumn.LATITUDE));
            	double lng = cursor.getDouble(cursor.getColumnIndex(IPointOfInterestColumn.LONGITUDE));
            	destination = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
            	PoiMarker marker = new PoiMarker(destination, name, desc, id);
            	itemizedOverlay.addOverlay(marker);

            }
            cursor.close();
        }
    }
    
    protected boolean onMarkerClicked(PoiMarker marker){
		Intent intent = new Intent(this, PoiDetailActivity.class);
    	intent.putExtra("poiId", marker.getId());
    	intent.putExtra("enableNavigateTo", false);
		startActivity(intent);
		return true;
    }
    
    @Override
    public void onLocationChanged(Location location) {
    	super.onLocationChanged(location);
    	
    	GeoPoint current = new GeoPoint((int)(location.getLatitude() * 1E6), (int)(location.getLongitude() * 1E6));
		new LoadRoute().execute(current);
    }
    
    private class RouteHandler extends Handler{
		public void handleMessage(Message msg) {  
			  
            boolean error = msg.getData().getBoolean("error", false);  
  
            if (!error) {  
                // set the geopoints (we can't just add the overlays  
                // to the map here, because it's on a different thread  
                route = (List<GeoPoint>) msg.obj;  
               
                post(updateRoute); 
            }
		}
	}
    
    private class LoadRoute extends AsyncTask<GeoPoint, Void, Void> {
    	@Override
        protected Void doInBackground(GeoPoint... params) {
    		Route route = new Route(params[0], destination);
			try {
				List<GeoPoint> points = route.getRoutePoints();
				Message msg = new Message();
				msg.obj = points;
				routeHandler.dispatchMessage(msg);
			} catch (Exception e) {}
			return null;
        }
	}
}
