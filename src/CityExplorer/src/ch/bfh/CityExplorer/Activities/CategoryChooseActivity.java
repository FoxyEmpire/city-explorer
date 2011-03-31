package ch.bfh.CityExplorer.Activities;

import ch.bfh.CityExplorer.Data.CategoryTbl;
import ch.bfh.CityExplorer.Data.CityExplorerDatabase;
import ch.bfh.CityExplorer.Data.ICategoryColumn;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class CategoryChooseActivity extends ListActivity {

	private SQLiteDatabase db;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        db = new CityExplorerDatabase(this).getReadableDatabase();
        
        Cursor cursor = db.query(
        		CategoryTbl.TABLE_NAME,
        		CategoryTbl.ALL_COLUMNS,
        		null, null,
        		ICategoryColumn.NAME,
        		null, null);
        cursor.moveToFirst();
    }
    
}
