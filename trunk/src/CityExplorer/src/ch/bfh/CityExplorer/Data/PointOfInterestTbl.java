package ch.bfh.CityExplorer.Data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class PointOfInterestTbl implements IPointOfInterestColumn {
	
	/**
	  * Name der Datenbanktabelle.
	  */
	public static final String TABLE_NAME = "pointOfInterest";
	
	/**
	  * SQL Anweisung zur Schemadefinition.
	  */
	public static final String SQL_CREATE = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
		+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
		+ "city_id INTEGER REFERENCES " + CityTbl.TABLE_NAME + "(id),"
		+ "category_id INTEGER REFERENCES " + CategoryTbl.TABLE_NAME + "(id),"
		+ "name VARCHAR(100),"
		+ "desc VARCHAR(1000),"
		+ "lat FLOAT,"
		+ "long FLOAT,"
		+ "url VARCHAR(255)"
		+ ");";
	
	/**
	  * SQL Anweisung zur Löschung der Tabelle.
	  */
	public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
	
	public static final String STMT_FULL_INSERT = "INSERT INTO " + TABLE_NAME
		+ " ( city_id, category_id, name, desc, lat, long, url ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
		
	/** Liste aller bekannten Attribute. */
	public static final String[] ALL_COLUMNS = new String[] {
		ID,
		CITY_ID,
		CATEGORY_ID,
		NAME,
		DESCRIPTION,
		LATITUDE,
		LONGITUDE,
		URL
	};
	
	private PointOfInterestTbl() {
	}
	
	//private static final String TAG = "PointOfInterestTbl";

	//public static long InsertPointOfInterest(CityExplorerDatabase db, long cityId, long categoryId,
	//		String name, String description, float latitude, float longitude, String url) {
	//	final ContentValues daten = new ContentValues();
	//	daten.put(CITY_ID, cityId);
	//	daten.put(CATEGORY_ID, categoryId);
	//	daten.put(NAME, name);
	//	daten.put(DESCRIPTION, description);
	//	daten.put(LATITUDE, latitude);
	//	daten.put(LONGITUDE, longitude);
	//	daten.put(URL, url);
	//
	//  final SQLiteDatabase dbCon = db.getWritableDatabase();

	//	try {
	//		final long id = dbCon.insertOrThrow(TABLE_NAME, null, daten);
	//		Log.i(TAG, "PointOfInterest mit id=" + id + " erzeugt.");
	//		return id;
	//	} finally {
	//		dbCon.close();
	//	}
	//}
	
}
