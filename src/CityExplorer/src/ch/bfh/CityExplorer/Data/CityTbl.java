package ch.bfh.CityExplorer.Data;

public class CityTbl implements ICityColumn {
	
	/**
	  * Name der Datenbanktabelle.
	  */
	public static final String TABLE_NAME = "city";
	
	/**
	  * SQL Anweisung zur Schemadefinition.
	  */
	public static final String SQL_CREATE = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
		+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
		+ "name VARCHAR(100),"
		+ "info VARCHAR(1000),"
		+ "url VARCHAR(255),"
		+ "movie VARCHAR(100)"
		+ ");";
	
	/**
	  * SQL Anweisung zur Löschung der Tabelle.
	  */
	public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
	
	public static final String STMT_FULL_INSERT = "INSERT INTO " + TABLE_NAME
		+ " ( name, info, url, movie ) VALUES ( ?, ?, ?, ? )";
	
	private CityTbl() {
	}
	
}
