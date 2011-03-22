package ch.bfh.CityExplorer.Data;

public class CategoryTbl implements ICategoryColumn {
	
	/**
	  * Name der Datenbanktabelle.
	  */
	public static final String TABLE_NAME = "category";
	
	/**
	  * SQL Anweisung zur Schemadefinition.
	  */
	public static final String SQL_CREATE = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
		+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
		+ "name VARCHAR(100)"
		+ ");";
	
	/**
	  * SQL Anweisung zur Löschung der Tabelle.
	  */
	public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
		
	public static final String STMT_FULL_INSERT = "INSERT INTO " + TABLE_NAME
		+ " ( name ) VALUES ( ? )";
	
	/** Liste aller bekannten Attribute. */
	public static final String[] ALL_COLUMNS = new String[] {
		ID,
		NAME
	};
	
	private CategoryTbl() {
	}
	
}
