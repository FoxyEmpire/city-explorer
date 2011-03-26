package ch.bfh.CityExplorer.Activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ch.bfh.CityExplorer.R;
import ch.bfh.CityExplorer.Data.CityExplorerDatabase;
import ch.bfh.CityExplorer.Data.IPointOfInterestColumn;
import ch.bfh.CityExplorer.Data.PointOfInterestTbl;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

public class CalendarActivity extends Activity {
	private final int DialogDateFrom = 1;
	private final int DialogDateUntil = 2;
	private final int DialogTimeFrom = 3;
	private final int DialogTimeUntil = 4;
	
	private Date from;
	private Date until;
	private SQLiteDatabase db;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        
        int pointOfInterestId = getIntent().getExtras().getInt("pointOfInterestId");
        db = new CityExplorerDatabase(this).getReadableDatabase();
        Cursor cursor = db.query(PointOfInterestTbl.TABLE_NAME,
        		PointOfInterestTbl.ALL_COLUMNS, PointOfInterestTbl.ID + " = ?", new String[]{String.valueOf(pointOfInterestId)},
        		IPointOfInterestColumn.NAME, null, null);
        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.NAME));
        ((TextView)findViewById(R.id.etCalendar_Topic)).setText(name);
        
        Calendar c = Calendar.getInstance();
	    int year = c.get(Calendar.YEAR);
	    int month = c.get(Calendar.MONTH);
	    int day = c.get(Calendar.DAY_OF_MONTH);
	    int hour = c.get(Calendar.HOUR_OF_DAY);
	    int min = c.get(Calendar.MINUTE);
	    
	    from = new Date(year, month, day, hour, min);
	    until = new Date(year, month, day, hour + 1, min);

        Button btnFrom = (Button)findViewById(R.id.btnCalendar_From);
        btnFrom.setText(from.getDate()+"."+(from.getMonth()+1)+"."+from.getYear());
        btnFrom.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		showDialog(DialogDateFrom);
        	}
    	});
        
        Button btnTimeFrom = (Button)findViewById(R.id.btnCalendar_TimeFrom);
        // it seems to be a bug with HH:mm
        btnTimeFrom.setText(from.getHours() + ":" + from.getMinutes());
        btnTimeFrom.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		showDialog(DialogTimeFrom);
        	}
    	});
        
        Button btnUntil = (Button)findViewById(R.id.btnCalendar_Until);
        btnUntil.setText(until.getDate()+"."+(until.getMonth()+1)+"."+until.getYear());
        btnUntil.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		showDialog(DialogDateUntil);
        	}
    	});
        
        Button btnTimeUntil = (Button)findViewById(R.id.btnCalendar_TimeUntil);
        // it seems to be a bug with HH:mm
        btnTimeUntil.setText(until.getHours() + ":" + until.getMinutes());
        btnTimeUntil.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		showDialog(DialogTimeUntil);
        	}
    	});
        
        String[] projection = new String[] { "_id", "name" };
        Uri calendars = Uri.parse("content://calendar/calendars");
             
        Cursor managedCursor = managedQuery(calendars, projection, "selected=1", null, null);
        String[] from = new String[]{"name", "_id"};
        SimpleCursorAdapter adapter =
        	  new SimpleCursorAdapter(this, R.id.spCalendar_Calendar, managedCursor, from, new int[] {android.R.id.text1});

    }
   
 // Creating dialog
    @Override
    protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DialogDateFrom:
		    return new DatePickerDialog(this,  new DatePickerDialog.OnDateSetListener() {
		    	// onDateSet method
		    	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		    		from.setYear(year);
		    		from.setMonth(monthOfYear + 1);
		    		from.setDate(dayOfMonth);
		    		((Button)findViewById(R.id.btnCalendar_From)).setText(from.getDate()+"."+(from.getMonth()+1)+"."+from.getYear());
		    	}
		    },  from.getYear(), from.getMonth(), from.getDate());
	    case DialogTimeFrom:
	    	return new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					from.setHours(hourOfDay);
					from.setMinutes(minute);
					// it seems to be a bug with HH:mm
					((Button)findViewById(R.id.btnCalendar_TimeFrom)).setText(from.getHours() + ":" + from.getMinutes());
				}
			}, from.getHours(), from.getMinutes(), true);
		    case DialogDateUntil:
			    return new DatePickerDialog(this,  new DatePickerDialog.OnDateSetListener() {
			    	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			    		until.setYear(year);
			    		until.setMonth(monthOfYear);
			    		until.setDate(dayOfMonth);
			    		((Button)findViewById(R.id.btnCalendar_Until)).setText(until.getDate()+"."+(until.getMonth()+1)+"."+until.getYear());
			    	}
			    },  until.getYear(), until.getMonth(), until.getDate());
		    case DialogTimeUntil:
		    	return new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						until.setHours(hourOfDay);
						until.setMinutes(minute);
						// it seems to be a bug with HH:mm
						((Button)findViewById(R.id.btnCalendar_TimeUntil)).setText(until.getHours() + ":" + until.getMinutes());
					}
				}, until.getHours(), until.getMinutes(), true);
	    }
	    return null;
    }
}
