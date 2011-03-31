package ch.bfh.CityExplorer.Activities;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.Overlay;

import ch.bfh.CityExplorer.R;
import ch.bfh.CityExplorer.Application.PoiMarker;
import ch.bfh.CityExplorer.Data.CategoryTbl;
import ch.bfh.CityExplorer.Data.CityExplorerDatabase;
import ch.bfh.CityExplorer.Data.ICategoryColumn;
import ch.bfh.CityExplorer.Data.IPointOfInterestColumn;
import ch.bfh.CityExplorer.Data.PointOfInterestTbl;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.MenuItem.OnMenuItemClickListener;

public class PoiMapActivity extends MapActivity {
	
	private SQLiteDatabase db;
	private int currentCategoryType = 1;

	private Drawable pin = null;
	private List<Overlay> mapOverlays = null;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	pin = this.getResources().getDrawable(R.drawable.pin);
    	mapOverlays = mapView.getOverlays();
    	
    	db = new CityExplorerDatabase(this).getReadableDatabase();
    	
    	ResetMarkers();
    }
    
    private void ResetMarkers() {
        int id;
        String name;
        String desc;
        double lat;
        double lng;
        GeoPoint point;
        PoiMarker overlayitem;
        
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
        	overlayitem = new PoiMarker(point, name, desc, id);
        	overlay.addOverlay(overlayitem);

        	cursor.moveToNext();
        }
        mapOverlays.add(overlay);
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
	
}
