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
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
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
		
		String sName = cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.NAME));
		TextView tvName = (TextView)findViewById(R.id.tvPoiDetail_Name);
		tvName.setText(sName);
		
		// Image URL 1
		ImageView ivImage1 = (ImageView)findViewById(R.id.ivPoiDetail_Image1);
		String sImageUrl1 = cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.IMAGE_URL_1));
		if (sImageUrl1 != null && sImageUrl1.length() > 0) {
			ivImage1.setImageBitmap(getImageBitmap(sImageUrl1));
		}
		else {
			ivImage1.setVisibility(View.GONE);
		}
		
		TextView tvDesc = (TextView)findViewById(R.id.tvPoiDetail_Description);
		tvDesc.setText(cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.DESCRIPTION)));
		
		TextView tvOpen = (TextView)findViewById(R.id.tvPoiDetail_OpeningHours);
		String sOpen = cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.OPENING_HOURS));
		if (sOpen != null && sOpen.length() > 0) {
			tvOpen.setText(sOpen);
		}
		else {
			TextView tvOpenTitle = (TextView)findViewById(R.id.tvPoiDetail_OpeningHoursTitle);
			tvOpenTitle.setVisibility(View.GONE);
			tvOpen.setVisibility(View.GONE);
		}
		
		// Address
		TextView tvAddress = (TextView)findViewById(R.id.tvPoiDetail_Address);
		String sAddress = cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.ADDRESS));
		if (sAddress != null && sAddress.length() > 0) {
			tvAddress.setText(sName + "\n" + sAddress);
		}
		else {
			TextView tvAddressTitle = (TextView)findViewById(R.id.tvPoiDetail_AddressTitle);
			tvAddressTitle.setVisibility(View.GONE);
			tvAddress.setVisibility(View.GONE);
		}
		
		// Image URL 2
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
		
		// Resize image if necessary
		Display display = getWindowManager().getDefaultDisplay(); 
		if (bm.getWidth() > display.getWidth()) {
			int newWidth = display.getWidth();
			int newHeight = bm.getHeight() * newWidth / bm.getWidth();
			
	        float scaleWidth = ((float) newWidth) / bm.getWidth();
	        float scaleHeight = ((float) newHeight) / bm.getHeight();
			
	        Matrix matrix = new Matrix();
	        matrix.postScale(scaleWidth, scaleHeight);

	        bm = Bitmap.createBitmap(bm, 0, 0,
	        		bm.getWidth(), bm.getHeight(), matrix, true);
		}
		
		return bm;
	}
	
}
