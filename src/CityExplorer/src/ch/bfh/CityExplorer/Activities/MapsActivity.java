package ch.bfh.CityExplorer.Activities;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import ch.bfh.CityExplorer.R;
import ch.bfh.CityExplorer.Data.CityExplorerDatabase;
import ch.bfh.CityExplorer.Data.IPointOfInterestColumn;
import ch.bfh.CityExplorer.Data.PointOfInterestTbl;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapsActivity extends MapActivity {

	private SQLiteDatabase db;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        MapView mapView = (MapView) findViewById(R.id.mapview);
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
        
        Cursor cursor = db.query(PointOfInterestTbl.TABLE_NAME,
        		PointOfInterestTbl.ALL_COLUMNS, null, null,
        		IPointOfInterestColumn.NAME, null, null);
        cursor.moveToFirst();
        
        while (cursor.isAfterLast() == false) {
        	id = cursor.getInt(cursor.getColumnIndex(IPointOfInterestColumn.ID));
        	name = cursor.getString(cursor.getColumnIndex(IPointOfInterestColumn.NAME));
        	desc = cursor.getString(cursor.getColumnIndex(IPointOfInterestColumn.DESCRIPTION));
        	lat = cursor.getDouble(cursor.getColumnIndex(IPointOfInterestColumn.LATITUDE));
        	lng = cursor.getDouble(cursor.getColumnIndex(IPointOfInterestColumn.LONGITUDE));
        	point = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
        	overlayitem = new PoiOverlayItem(point, name, desc, id);
        	itemizedOverlay.addOverlay(overlayitem);
        	mapOverlays.add(itemizedOverlay);

        	cursor.moveToNext();
        }        
    }
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
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

}
