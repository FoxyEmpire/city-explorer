package ch.bfh.CityExplorer.Activities;

import ch.bfh.CityExplorer.R;
import ch.bfh.CityExplorer.Data.CityExplorerDatabase;
import ch.bfh.CityExplorer.Data.PointOfInterestTbl;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class PoiDetailActivity extends Activity {

	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poidetail);
		
		db = new CityExplorerDatabase(this).getReadableDatabase();
		
		int poiId = getIntent().getExtras().getInt("poiId");
		
		Cursor cursor = db.query(
				PointOfInterestTbl.TABLE_NAME,
				PointOfInterestTbl.ALL_COLUMNS,
				PointOfInterestTbl.ID + " = ?",
				new String[]{String.valueOf(poiId)},
        		null, null, null);
		cursor.moveToFirst();
		
		TextView tvName = (TextView)findViewById(R.id.tvPoiDetail_Name);
		tvName.setText(cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.NAME)));
		
		TextView tvDesc = (TextView)findViewById(R.id.tvPoiDetail_Description);
		tvDesc.setText(cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.DESCRIPTION)));
		
		TextView tvOpen = (TextView)findViewById(R.id.tvPoiDetail_OpeningHours);
		String sOpen = cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.OPENING_HOURS));
		if (sOpen != null && sOpen.length() > 0) {
			tvOpen.setText(sOpen);
		}
		else {
			tvOpen.setText("-");
		}
		
	}
}
