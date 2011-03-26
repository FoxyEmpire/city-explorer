package ch.bfh.CityExplorer.Activities;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import ch.bfh.CityExplorer.R;
import ch.bfh.CityExplorer.Data.CityExplorerDatabase;
import ch.bfh.CityExplorer.Data.PointOfInterestTbl;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PoiDetailActivity extends Activity {

	private static final String TAG = "PoiDetailActivity";

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
		
		ImageView ivImage1 = (ImageView)findViewById(R.id.ivPoiDetail_Image1);
		String sImageUrl1 = cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.IMAGE_URL_1));
		if (sImageUrl1 != null && sImageUrl1.length() > 0) {
			ivImage1.setImageBitmap(getImageBitmap(sImageUrl1));
		}
		else {
			ivImage1.setVisibility(View.GONE);
		}
		
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
		
		ImageView ivImage2 = (ImageView)findViewById(R.id.ivPoiDetail_Image2);
		String sImageUrl2 = cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.IMAGE_URL_2));
		if (sImageUrl2 != null && sImageUrl2.length() > 0) {
			ivImage2.setImageBitmap(getImageBitmap(sImageUrl2));
		}
		else {
			ivImage2.setVisibility(View.GONE);
		}
		
	}

	private Bitmap getImageBitmap(String url) {
		Bitmap bm = null;
		try {
		    URL aURL = new URL(url);
		    URLConnection conn = aURL.openConnection();
		    conn.connect();
		    InputStream is = conn.getInputStream();
		    BufferedInputStream bis = new BufferedInputStream(is);
		    bm = BitmapFactory.decodeStream(bis);
		    bis.close();
		    is.close();
		} catch (IOException e) {
			Log.e(TAG, "Error getting bitmap: " + url, e);
		}
		return bm;
	}
	
}
