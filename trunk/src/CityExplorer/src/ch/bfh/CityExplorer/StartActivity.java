package ch.bfh.CityExplorer;

import ch.bfh.CityExplorer.Data.CityExplorerDatabase;
import ch.bfh.CityExplorer.Data.ICityTbl;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

public class StartActivity extends Activity {
	final static String DB_NAME = "CityExplorer";
	
	private CityExplorerDatabase cityExplorerDb;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                
        new CityExplorerDatabase(this).getReadableDatabase();
        cityExplorerDb = new CityExplorerDatabase(this);
        
        String[] DB_QUERY_COLUMNS = new String[] {
        	ICityTbl.ID, ICityTbl.NAME, ICityTbl.INFO, ICityTbl.URL, ICityTbl.MOVIE };
        
        Cursor mcCities = cityExplorerDb.getReadableDatabase().query(
        	ICityTbl.TABLE_NAME, DB_QUERY_COLUMNS, null, null, ICityTbl.NAME, null, null);
        startManagingCursor(mcCities);
                
        //while( mcCities.moveToNext() ) {
        //	String id = mcCities.getString(0); // _id
        //	String name = mcCities.getString(1); // name
        //	String info = mcCities.getString(2); // info
        //	String url = mcCities.getString(3); // url
        //	String movie = mcCities.getString(4); // movie
        //}
                
        setContentView(R.layout.main);
    }
}