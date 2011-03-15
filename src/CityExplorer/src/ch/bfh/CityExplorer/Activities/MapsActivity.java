package ch.bfh.CityExplorer.Activities;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import ch.bfh.CityExplorer.R;
import ch.bfh.CityExplorer.Data.CityExplorerDatabase;
import ch.bfh.CityExplorer.Data.IPointOfInterestColumn;
import ch.bfh.CityExplorer.Data.PointOfInterestTbl;

import com.google.android.maps.GeoPoint;
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
        
        
        
        db = new CityExplorerDatabase(this).getReadableDatabase();
        
        Cursor cursor = db.query(PointOfInterestTbl.TABLE_NAME,
        		PointOfInterestTbl.ALL_COLUMNS, null, null,
        		IPointOfInterestColumn.NAME, null, null);
        cursor.moveToFirst();
        
        while (cursor.isAfterLast() == false) {
        	
        	cursor.moveToNext();
        }        
    }
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
