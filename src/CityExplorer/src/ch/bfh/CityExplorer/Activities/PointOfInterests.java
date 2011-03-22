package ch.bfh.CityExplorer.Activities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.bfh.CityExplorer.R;
import ch.bfh.CityExplorer.Data.CityExplorerDatabase;
import ch.bfh.CityExplorer.Data.CityExplorerStorage;
import ch.bfh.CityExplorer.Data.IPointOfInterestColumn;
import ch.bfh.CityExplorer.Data.PointOfInterestTbl;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.AdapterContextMenuInfo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.*;

public class PointOfInterests extends ListActivity {
	
	private PointOfInteretsListAdapter mAdapter;
	private SQLiteDatabase db;
	private CityExplorerStorage mStorage;
	
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
        ArrayList<ListItem> items = new ArrayList<ListItem>();
        while (cursor.isAfterLast() == false) {
        	int id = cursor.getInt(cursor.getColumnIndex(PointOfInterestTbl.ID));
        	String name = cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.NAME));
        	ListItem item = new ListItem(id, name);
        	item.setLongitude(cursor.getDouble(cursor.getColumnIndex(PointOfInterestTbl.LONGITUDE)));
        	item.setLatidute(cursor.getDouble(cursor.getColumnIndex(PointOfInterestTbl.LATITUDE)));
        	items.add(item);
        	cursor.moveToNext();
        }        
        
       
        mAdapter = new PointOfInteretsListAdapter(this, items);
        this.setListAdapter(mAdapter);
        
        registerForContextMenu(getListView());
       
        for (ListItem item : items) {
        	new LoadItemsTask().execute(item);
        }

        mStorage = new CityExplorerStorage(this);       
    }
    
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
	  super.onCreateContextMenu(menu, v, menuInfo);
	  MenuInflater inflater = getMenuInflater();
	  inflater.inflate(R.menu.menupointofinterest, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item){
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		  switch (item.getItemId()) {
		  case R.id.miPointOfInterest_ToFavorit:
			  ListItem listItem = (ListItem)getListView().getItemAtPosition(info.position);
			  mStorage.InsertFavourite(listItem.getId());
			  break;
		  case R.id.miPointOfInterest_Cancel:
			  return true;
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
    	
    	public ListItem(int id, String name){
    		this.id = id;
    		this.name = name;
    		distance = "~ m";
    		duration = "~ mins";
    	}
    	
    	public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getDistance() {
			return distance;
		}

		public void setDistance(String distance) {
			this.distance = distance;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}

		public double getLongitude() {
			return longitude;
		}

		public void setLatidute(double latidute) {
			this.latidute = latidute;
		}

		public double getLatidute() {
			return latidute;
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
    			if (i.getId() == item.getId()){
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

    				((TextView) convertView.findViewById(R.id.tvPointOfInterest_Name)).setText(items.get(pPosition).getName());
    				((TextView) convertView.findViewById(R.id.tvPointOfInterests_Distance)).setText(items.get(pPosition).getDistance());
    				
    				((TextView) convertView.findViewById(R.id.tvPointOfInterests_Street)).setText(items.get(pPosition).getStreet());
    				((TextView) convertView.findViewById(R.id.tvPointOfInterests_Time)).setText(items.get(pPosition).getDuration());
    			
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
        }
       
        private void UpdateListItem(ListItem item){
        	try{
		        HttpClient client = new DefaultHttpClient();
		        HttpGet request = new HttpGet();
		        String url = String.format("http://maps.googleapis.com/maps/api/directions/json?origin=Adliswil&destination=%f,%f&sensor=false", item.getLatidute(), item.getLongitude());
		        request.setURI(new URI(url));
		        HttpResponse response = client.execute(request);
		        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		        StringBuffer sb = new StringBuffer("");
		        String line = "";
		        while ((line = in.readLine()) != null) {
		            sb.append(line);
		        }
		        in.close();

		        JSONObject jObject = new JSONObject(sb.toString());
		        
		        JSONArray jsonArray = jObject.getJSONArray("routes");
		        jsonArray = jsonArray.getJSONObject(0).getJSONArray("legs");
		        JSONObject leg = jsonArray.getJSONObject(0);
		        JSONObject jsonObject = leg.getJSONObject("distance");
				String distance = jsonObject.getString("text");
				
				jsonObject = leg.getJSONObject("duration");
				String duration = jsonObject.getString("text");
				
				String street = leg.getString("end_address").split(",")[0];
				
				item.setDistance(distance);
				item.setDuration(duration);
				item.setStreet(street);
	        }catch(Exception e){
	        	// TODO:
	        	Exception ex = e;
	        }
        }
    }
}