package ch.bfh.CityExplorer.Activities;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import ch.bfh.CityExplorer.R;
import ch.bfh.CityExplorer.Application.Route;
import ch.bfh.CityExplorer.Application.RouteOverlay;
import ch.bfh.CityExplorer.Data.CityExplorerDatabase;
import ch.bfh.CityExplorer.Data.IPointOfInterestColumn;
import ch.bfh.CityExplorer.Data.PointOfInterestTbl;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapsActivity extends MapActivity implements  LocationListener {

	private SQLiteDatabase db;
	private GeoPoint navigateTo;
	private MapView mapView;
	private List<GeoPoint> route;
	private RouteHandler handler = new RouteHandler();
	
	final Runnable updateRoute = new Runnable() {  
        public void run() {  
    		if (route.size() < 100){
            // draw the path and then invalidate the mapview so that it redraws itself  
        	List<Overlay> overlays = mapView.getOverlays();  
 			for (int i = 1; i < route.size(); i++) {  
 			    overlays.add(new RouteOverlay(route.get(i - 1), route.get(i), Color.RED));  
 			}
            mapView.invalidate();  
    		}
        }  
    };  

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        //List<Overlay> mapOverlays = mapView.getOverlays();
        //Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
        //HelloItemizedOverlay itemizedOverlay = new HelloItemizedOverlay(drawable, this);
        //
        //GeoPoint point = new GeoPoint(19240000, -99120000);
        //OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City");
        //
        ////GeoPoint point2 = new GeoPoint(35410000, 139460000);
        ////OverlayItem overlayitem2 = new OverlayItem(point2, "Sekai, konichiwa!", "I'm in Japan!");
        //
        //itemizedOverlay.addOverlay(overlayitem);
        ////itemizedOverlay.addOverlay(overlayitem2);
        //mapOverlays.add(itemizedOverlay);
        
        int id;
        //int category_id;
        String name;
        String desc;
        double lat;
        double lng;
        GeoPoint point;
        PoiOverlayItem overlayitem;
        Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
        MapsItemizedOverlay itemizedOverlay = new MapsItemizedOverlay(drawable, this);
        List<Overlay> mapOverlays = mapView.getOverlays();
       
        db = new CityExplorerDatabase(this).getReadableDatabase();
        
        Intent intent = getIntent();
        int pointOfInterestId = 0;
        if (intent.getExtras() != null){
        	pointOfInterestId = intent.getExtras().getInt("pointOfInterestId");
        }
        Cursor cursor;
        if (pointOfInterestId > 0){
        	cursor = db.query(PointOfInterestTbl.TABLE_NAME,
            		PointOfInterestTbl.ALL_COLUMNS, PointOfInterestTbl.ID+" = ?", new String[]{String.valueOf(pointOfInterestId)},
            		IPointOfInterestColumn.NAME, null, null);
        	LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 500.0f, this);
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000L, 500.0f, this);
        }
        else{
        cursor = db.query(PointOfInterestTbl.TABLE_NAME,
        		PointOfInterestTbl.ALL_COLUMNS, null, null,
        		IPointOfInterestColumn.NAME, null, null);
        }
        cursor.moveToFirst();
        
        while (cursor.isAfterLast() == false) {
        	id = cursor.getInt(cursor.getColumnIndex(IPointOfInterestColumn.ID));
        	//category_id = cursor.getInt(cursor.getColumnIndex(IPointOfInterestColumn.CATEGORY_ID));
        	name = cursor.getString(cursor.getColumnIndex(IPointOfInterestColumn.NAME));
        	desc = cursor.getString(cursor.getColumnIndex(IPointOfInterestColumn.DESCRIPTION));
        	lat = cursor.getDouble(cursor.getColumnIndex(IPointOfInterestColumn.LATITUDE));
        	lng = cursor.getDouble(cursor.getColumnIndex(IPointOfInterestColumn.LONGITUDE));
        	point = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
        	navigateTo = point;
        	overlayitem = new PoiOverlayItem(point, name, desc, id);
        	itemizedOverlay.addOverlay(overlayitem);
        	mapOverlays.add(itemizedOverlay);

        	cursor.moveToNext();
        }
        cursor.close();
    }
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	private class PoiOverlayItem extends OverlayItem {

		private int mId;
		
		public PoiOverlayItem(GeoPoint point, String title, String snippet, int id) {
			super(point, title, snippet);
			mId = id;
		}
		
	}
	
	private class MapsItemizedOverlay extends ItemizedOverlay {
		
		private ArrayList<PoiOverlayItem> mOverlays = new ArrayList<PoiOverlayItem>();
		private Context mContext;
		
		public MapsItemizedOverlay(Drawable defaultMarker) {
			super(boundCenterBottom(defaultMarker));
		}
		
		public MapsItemizedOverlay(Drawable defaultMarker, Context context) {
			super(boundCenterBottom(defaultMarker));
			mContext = context;
		}
		
		public void addOverlay(PoiOverlayItem overlay) {
			mOverlays.add(overlay);
			populate();
		}

		@Override
		protected OverlayItem createItem(int arg0) {
			return mOverlays.get(arg0);
		}

		@Override
		public int size() {
			return mOverlays.size();
		}
		
		@Override
		protected boolean onTap(int index) {
			PoiOverlayItem item = mOverlays.get(index);
			
			//AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
			//dialog.setTitle(item.getTitle());
			//dialog.setMessage(item.getSnippet());
			//dialog.show();
			
			Intent intent = new Intent(mContext, PoiDetailActivity.class);
	    	intent.putExtra("poiId", item.mId);
			startActivity(intent);
			
			return true;
		}
		
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
			 Route route = new Route(params[0], navigateTo);
		        List<GeoPoint> points;
				try {
					points = route.GetRoutePoints();
					Message msg = new Message();  
					msg.obj = points;  
					handler.dispatchMessage(msg);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Exception ex = e;
				}
				return null;
	        }
	}

	@Override
	public void onLocationChanged(Location location) {
		GeoPoint current = new GeoPoint((int)(location.getLatitude() * 1E6), (int)(location.getLongitude() * 1E6));
		new LoadRoute().execute(current);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
