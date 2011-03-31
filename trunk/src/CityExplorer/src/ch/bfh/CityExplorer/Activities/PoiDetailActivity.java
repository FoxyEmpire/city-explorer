package ch.bfh.CityExplorer.Activities;

import ch.bfh.CityExplorer.R;
import ch.bfh.CityExplorer.Data.CityExplorerDatabase;
import ch.bfh.CityExplorer.Data.PointOfInterestTbl;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PoiDetailActivity extends Activity implements ImageReceivedCallback {

	private SQLiteDatabase db;
	private int poiId;
	private boolean enableNavigateTo;
	private ImageReceiver imgReceiver1, imgReceiver2;
	private Bitmap img1, img2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poidetail);
		
		db = new CityExplorerDatabase(this).getReadableDatabase();
		
		poiId = getIntent().getExtras().getInt("poiId");
		enableNavigateTo = getIntent().getExtras().getBoolean("enableNavigateTo", false);
		
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
		Display display = getWindowManager().getDefaultDisplay(); 
		ImageView ivImage1 = (ImageView)findViewById(R.id.ivPoiDetail_Image1);
		String sImageUrl1 = cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.IMAGE_URL_1));
		if (sImageUrl1 != null && sImageUrl1.length() > 0) {
			ProgressBar pbar1 = (ProgressBar)findViewById(R.id.pbPoiDetail_Image1);
			imgReceiver1 = new ImageReceiver(sImageUrl1, this, ivImage1, display, pbar1);
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
			ProgressBar pbar2 = (ProgressBar)findViewById(R.id.pbPoiDetail_Image2);
			imgReceiver2 = new ImageReceiver(sImageUrl2, this, ivImage2, display, pbar2);
		}
		else {
			ivImage2.setVisibility(View.GONE);
		}
	}

	@Override
	public void onImageReceived(ImageDisplayer displayer) {
		// run the ImageDisplayer on the UI thread
        this.runOnUiThread(displayer);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (poiId >= 0){
		    MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.poidetailmenu, menu);
		    menu.getItem(0).setEnabled(enableNavigateTo);
		    return true;
		}
		return false;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.navigateTo:
	    	Intent intent = new Intent(this, RouteMapActivity.class);
	    	intent.putExtra("pointOfInterestId", poiId);
			startActivity(intent);
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
}
