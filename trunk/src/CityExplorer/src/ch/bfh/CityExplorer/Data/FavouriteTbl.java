package ch.bfh.CityExplorer.Data;

public class FavouriteTbl implements IFavouriteColumn {

	/**
	  * Name der Datenbanktabelle.
	  */
	public static final String TABLE_NAME = "favourite";
	
	/**
	  * SQL Anweisung zur Schemadefinition.
	  */
	public static final String SQL_CREATE = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
		+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
		+ "poi_id INTEGER REFERENCES " + PointOfInterestTbl.TABLE_NAME + "(id)"
		+ ");";
	
	/**
	  * SQL Anweisung zur Löschung der Tabelle.
	  */
	public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
	
	public static final String STMT_FULL_INSERT = "INSERT INTO " + TABLE_NAME
		+ " ( poi_id ) VALUES ( ? )";
		
	/** Liste aller bekannten Attribute. */
	public static final String[] ALL_COLUMNS = new String[] {
		ID,
		POI_ID
	};
	
	private FavouriteTbl() {
	}
	
}
