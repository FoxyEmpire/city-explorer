package ch.bfh.CityExplorer.Data;

public interface IPointOfInterestTbl {
	
	String ID = "id";
	String NAME = "name";
	String DESCRIPTION = "desc";
	String LONGITUDE = "long";
	String LATITUDE = "lat";
	String URL = "url";
	String CITY_ID = "city_id";
	
	String TABLE_NAME = "pointOfInterest";
	
	String SQL_CREATE = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
		+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
		+ "city_id INTEGER REFERENCES " + ICityTbl.TABLE_NAME + "(id),"
		+ "name VARCHAR(100),"
		+ "desc VARCHAR(1000),"
		+ "long INTEGER,"
		+ "lat INTEGER,"
		+ "url VARCHAR(255)"
		+ ");";
	
	String STMT_FULL_INSERT = "INSERT INTO " + TABLE_NAME
		+ " ( city_id, name, desc, long, lat, url ) VALUES ( ?, ?, ?, ?, ?, ? )";

}
