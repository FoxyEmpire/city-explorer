package ch.bfh.CityExplorer.Activities;

import java.util.ArrayList;
import java.util.List;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem.OnMenuItemClickListener;
import ch.bfh.CityExplorer.R;
import ch.bfh.CityExplorer.Application.Route;
import ch.bfh.CityExplorer.Application.RouteOverlay;
import ch.bfh.CityExplorer.Data.CategoryTbl;
import ch.bfh.CityExplorer.Data.CityExplorerDatabase;
import ch.bfh.CityExplorer.Data.ICategoryColumn;
import ch.bfh.CityExplorer.Data.IPointOfInterestColumn;
import ch.bfh.CityExplorer.Data.PointOfInterestTbl;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapsActivity extends MapActivity implements LocationListener {

	private SQLiteDatabase db;
	
	private int currentCategoryType = 1;
	
	private MapView mapView = null;
	private Drawable pin = null;
	private List<Overlay> mapOverlays = null;
	private GeoPoint navigateTo;
	private List<GeoPoint> route;
	private RouteHandler handler = new RouteHandler();
	
	final Runnable updateRoute = new Runnable() {  
        public void run() {  
    		if (route.size() < 100) {
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
        
        mapView = (MapView)findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        pin = this.getResources().getDrawable(R.drawable.pin);
        
        
        mapOverlays = mapView.getOverlays();

        db = new CityExplorerDatabase(this).getReadableDatabase();
        
        Intent intent = getIntent();
        int pointOfInterestId = 0;
        if (intent.getExtras() != null){
        	pointOfInterestId = intent.getExtras().getInt("pointOfInterestId");
        }
        Cursor cursor;
        if (pointOfInterestId > 0) {
        	cursor = db.query(
        			PointOfInterestTbl.TABLE_NAME,
            		PointOfInterestTbl.ALL_COLUMNS,
            		PointOfInterestTbl.ID + " = ?",
            		new String[]{String.valueOf(pointOfInterestId)},
            		IPointOfInterestColumn.NAME,
            		null,
            		null);
        	LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 500.0f, this);
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000L, 500.0f, this);
        }
        else {
	        cursor = db.query(PointOfInterestTbl.TABLE_NAME,
	        		PointOfInterestTbl.ALL_COLUMNS, null, null,
	        		IPointOfInterestColumn.NAME, null, null);
	    }
        
        

        ResetMarkers();

        /*
        int id;
        String name;
        String desc;
        double lat;
        double lng;
       
        MapsItemizedOverlay overlay = new MapsItemizedOverlay(pin, this);
        
        Cursor cursor = db.query(
        		PointOfInterestTbl.TABLE_NAME,
        		PointOfInterestTbl.ALL_COLUMNS,
        		PointOfInterestTbl.CATEGORY_ID + " = ?",
        		new String[] { String.valueOf(1) },
        		IPointOfInterestColumn.NAME,
        		null,
        		null);
        }
        cursor.moveToFirst();
        
        GeoPoint point;
        PoiOverlayItem overlayitem;
        while (cursor.isAfterLast() == false) {
        	id = cursor.getInt(cursor.getColumnIndex(IPointOfInterestColumn.ID));
        	name = cursor.getString(cursor.getColumnIndex(IPointOfInterestColumn.NAME));
        	desc = cursor.getString(cursor.getColumnIndex(IPointOfInterestColumn.DESCRIPTION));
        	lat = cursor.getDouble(cursor.getColumnIndex(IPointOfInterestColumn.LATITUDE));
        	lng = cursor.getDouble(cursor.getColumnIndex(IPointOfInterestColumn.LONGITUDE));
        	point = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
        	navigateTo = point;
        	overlayitem = new PoiOverlayItem(point, name, desc, id);
        	overlay.addOverlay(overlayitem);

        	cursor.moveToNext();
        }
        cursor.close();
    	mapOverlays.add(overlay);
    	*/
    	
    }
    
    private void ResetMarkers() {
        int id;
        String name;
        String desc;
        double lat;
        double lng;
        GeoPoint point;
        PoiOverlayItem overlayitem;
        
        Cursor cursor = db.query(
        		PointOfInterestTbl.TABLE_NAME,
        		PointOfInterestTbl.ALL_COLUMNS,
        		PointOfInterestTbl.CATEGORY_ID + " = ?",
        		new String[] { String.valueOf(currentCategoryType) },
        		IPointOfInterestColumn.NAME,
        		null,
        		null);
        cursor.moveToFirst();
        
        mapOverlays.clear();
        
        MapsItemizedOverlay overlay = new MapsItemizedOverlay(pin, this);
        while (cursor.isAfterLast() == false) {
        	id = cursor.getInt(cursor.getColumnIndex(IPointOfInterestColumn.ID));
        	name = cursor.getString(cursor.getColumnIndex(IPointOfInterestColumn.NAME));
        	desc = cursor.getString(cursor.getColumnIndex(IPointOfInterestColumn.DESCRIPTION));
        	lat = cursor.getDouble(cursor.getColumnIndex(IPointOfInterestColumn.LATITUDE));
        	lng = cursor.getDouble(cursor.getColumnIndex(IPointOfInterestColumn.LONGITUDE));
        	point = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
        	overlayitem = new PoiOverlayItem(point, name, desc, id);
        	overlay.addOverlay(overlayitem);

        	cursor.moveToNext();
        }
        mapOverlays.add(overlay);
        //mapOverlays.notify();
        
        mapView.invalidate();
    }
    
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	getMenuInflater().inflate(R.menu.mapmenu, menu);
    	
    	SubMenu sub = menu.addSubMenu(R.string.categories);
    	
    	Cursor cursor = db.query(
        		CategoryTbl.TABLE_NAME,
        		CategoryTbl.ALL_COLUMNS,
        		null, null,
        		ICategoryColumn.NAME,
        		null, null);
        cursor.moveToFirst();
    	
        int id;
        String name;
        while (cursor.isAfterLast() == false) {
        	id = cursor.getInt(cursor.getColumnIndex(ICategoryColumn.ID));
        	name = cursor.getString(cursor.getColumnIndex(ICategoryColumn.NAME));
        	
        	MenuItem item = sub.add(Menu.NONE, id, Menu.NONE, name);
        	
        	item.setOnMenuItemClickListener(new OnMenuItemClickListener() {				
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					currentCategoryType = item.getItemId();
					ResetMarkers();
					return true;
				}
			});

        	cursor.moveToNext();
        }
    	
    	return true;
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
					points = route.getRoutePoints();
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
