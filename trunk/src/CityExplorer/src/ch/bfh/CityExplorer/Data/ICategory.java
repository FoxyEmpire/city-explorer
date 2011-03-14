package ch.bfh.CityExplorer.Data;

public interface ICategory {
	
	String ID = "id";
	String NAME = "name";
	
	String TABLE_NAME = "category";
	
	String SQL_CREATE = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
		+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
		+ "name VARCHAR(100),"
		+ ");";
	
	String STMT_FULL_INSERT = "INSERT INTO " + TABLE_NAME
		+ " ( name ) VALUES ( ? )";

}
