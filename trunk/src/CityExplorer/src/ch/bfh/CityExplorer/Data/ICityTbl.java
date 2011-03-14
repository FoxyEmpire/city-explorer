package ch.bfh.CityExplorer.Data;

public interface ICityTbl {
	
	String ID = "id";
	String NAME = "name";
	String INFO = "info";
	String URL = "url";
	String MOVIE = "movie";
	
	String TABLE_NAME = "city";
	
	String SQL_CREATE = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
		+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
		+ "name VARCHAR(100),"
		+ "info VARCHAR(1000),"
		+ "url VARCHAR(255),"
		+ "movie VARCHAR(100)"
		+ ");";
	
	String STMT_FULL_INSERT = "INSERT INTO " + TABLE_NAME
		+ " ( name, info, url, movie ) VALUES ( ?, ?, ?, ? )";
	
}
