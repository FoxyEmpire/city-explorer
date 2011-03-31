package ch.bfh.CityExplorer.Activities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.bfh.CityExplorer.R;
import ch.bfh.CityExplorer.Application.Route;
import ch.bfh.CityExplorer.Application.RouteInfo;
import ch.bfh.CityExplorer.Data.CityExplorerDatabase;
import ch.bfh.CityExplorer.Data.CityExplorerStorage;
import ch.bfh.CityExplorer.Data.IPointOfInterestColumn;
import ch.bfh.CityExplorer.Data.PointOfInterestTbl;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.google.android.maps.GeoPoint;

public class PointOfInterests extends ListActivity implements  LocationListener {
	
	private PointOfInteretsListAdapter mAdapter;
	private SQLiteDatabase db;
	private CityExplorerStorage mStorage;
	private Location _currentLocation;
	private List<LoadItemsTask> tasks;
	private List<ListItem> items = new ArrayList<ListItem>();
	private Object lock = new Object();
	private PointOfInterests me = this;
	private LocationManager locationManager;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new CityExplorerDatabase(this).getReadableDatabase();
        
        int categoryId = getIntent().getExtras().getInt("categoryId");
        
        Cursor cursor = db.query(PointOfInterestTbl.TABLE_NAME,
        		PointOfInterestTbl.ALL_COLUMNS, PointOfInterestTbl.CATEGORY_ID + " = ?", new String[]{String.valueOf(categoryId)},
        		IPointOfInterestColumn.NAME, null, null);
        cursor.moveToFirst();
        
        while (cursor.isAfterLast() == false) {
        	int id = cursor.getInt(cursor.getColumnIndex(PointOfInterestTbl.ID));
        	String name = cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.NAME));
        	ListItem item = new ListItem(id, name);
        	item.longitude = cursor.getDouble(cursor.getColumnIndex(PointOfInterestTbl.LONGITUDE));
        	item.latidute = cursor.getDouble(cursor.getColumnIndex(PointOfInterestTbl.LATITUDE));
        	item.street = cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.ADDRESS));
        	items.add(item);
        	cursor.moveToNext();
        }        
        cursor.close();
       
        mAdapter = new PointOfInteretsListAdapter(this, items);
        this.setListAdapter(mAdapter);
        
        registerForContextMenu(getListView());
        
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        
        tasks = new ArrayList<LoadItemsTask>();

        mStorage = new CityExplorerStorage(this);       
    }
    
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
	  super.onCreateContextMenu(menu, v, menuInfo);
	  MenuInflater inflater = getMenuInflater();
	  inflater.inflate(R.menu.menupointofinterest, menu);
	}
	
	@Override
	public void onPause(){
		super.onPause();
		locationManager.removeUpdates(this);
		for (LoadItemsTask task : tasks){
			task.cancel(true);
		}
		tasks.clear();
	}
	
	public void onResume(){
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 120000L, 500.0f, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 120000L, 500.0f, this);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item){
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		ListItem listItem = (ListItem)getListView().getItemAtPosition(info.position);
		  switch (item.getItemId()) {
		  case R.id.miPointOfInterest_ToFavorit:
			  mStorage.InsertFavourite(listItem.id);
			  break;
		  case R.id.miPointOfInterest_NavigateTo:
			  Intent intent = new Intent(this, RouteMapActivity.class);
		    	intent.putExtra("pointOfInterestId", listItem.id);
				startActivity(intent);
				break;
		  case R.id.miPointOfInterest_Cancel:
			  return true;
		  case R.id.miPointOfInterest_Information:
			  	Intent intentInformation = new Intent(me, PoiDetailActivity.class);
			  	intentInformation.putExtra("poiId", listItem.id);
			  	intentInformation.putExtra("enableNavigateTo", true);
				startActivity(intentInformation);
				break;
		  }
		  return true;
	}
    
    private class ListItem {
    	private int id;
		private String name;
    	private String street;
    	private String distance;
    	private String duration;
    	private double latidute;
    	private double longitude;
    	private boolean geoLoaded;
    	
    	public ListItem(int id, String name){
    		this.id = id;
    		this.name = name;
    		distance = "~ m";
    		duration = "~ mins";
    	}
    }
    
    private class PointOfInteretsListAdapter extends BaseAdapter {
    	
    	private final LayoutInflater mLayoutInflater;
    	private List<ListItem> items;

    	public PointOfInteretsListAdapter(Context pContext, List<ListItem> items){
    			mLayoutInflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    			this.items = items;
    	}
    	
    	@Override
    	public int getCount() {return items.size();}

    	@Override
    	public Object getItem(int pPosition) {
    		return items.get(pPosition);
    	}
    	
    	public void setItem(ListItem item){
    		for (ListItem i : items){
    			if (i.id == item.id){
    				i = item;
    			}
    		}
    		notifyDataSetChanged();
    	}

    	@Override
    	public long getItemId(int arg0) {return 0;}

    	@Override
    	public View getView(int pPosition, View convertView, ViewGroup parent) {
    		if (convertView == null) {
    			convertView = mLayoutInflater.inflate(R.layout.listpointofintersts, parent, false);
    		}

    				((TextView) convertView.findViewById(R.id.tvPointOfInterest_Name)).setText(items.get(pPosition).name);
    				((TextView) convertView.findViewById(R.id.tvPointOfInterests_Distance)).setText(items.get(pPosition).distance);
    				
    				((TextView) convertView.findViewById(R.id.tvPointOfInterests_Street)).setText(items.get(pPosition).street);
    				((TextView) convertView.findViewById(R.id.tvPointOfInterests_Time)).setText(items.get(pPosition).duration);
    		return convertView;
    	}
    }
    
    private class LoadItemsTask extends AsyncTask<ListItem, Void, ListItem> {

        @Override
        protected ListItem doInBackground(ListItem... params) {
        	for (int i=0; i<params.length; i++){
        		UpdateListItem(params[i]);
        	}
            return params[0];
        }

        @Override
        protected void onPostExecute(ListItem result) {
            mAdapter.setItem(result);
            result.geoLoaded = true;
        }
       
        private void UpdateListItem(ListItem item){
        	try{
    			if (_currentLocation == null) return;
    			
    			double lat = _currentLocation.getLatitude();
    			double lng = _currentLocation.getLongitude();
    			
    			GeoPoint src = new GeoPoint((int)(lat * 1E6), (int)(lng*1E6));
    			GeoPoint dest = new GeoPoint((int)(item.latidute * 1E6), (int)(item.longitude*1E6));
    			
    			Route route = new Route(src, dest);
        		RouteInfo info = route.getRouteInfo();
    			
				item.distance = info.getDistance();
				item.duration = info.getDuration();
	        }catch(Exception e){
	        }
        }
    }

	@Override
	public void onLocationChanged(Location location) {
		synchronized (lock) {
			_currentLocation = location;
		}
		for (ListItem item : items) {
			if (item.geoLoaded) continue;
			
        	LoadItemsTask task = new LoadItemsTask();
        	tasks.add(task);
        	task.execute(item);
        }
	}

	@Override
	public void onProviderDisabled(String arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
}
