package ch.bfh.CityExplorer.Data;

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
	
}
