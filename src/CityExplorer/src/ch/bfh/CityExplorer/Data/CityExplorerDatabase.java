package ch.bfh.CityExplorer.Data;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class CityExplorerDatabase extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "cityexplorer.db";
	private static final int DATABASE_VERSION = 1;
	
	public CityExplorerDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(ICityTbl.SQL_CREATE);
		db.execSQL(ICategory.SQL_CREATE);
		db.execSQL(IPointOfInterestTbl.SQL_CREATE);
		
		SQLiteStatement stmtInsertCity = db.compileStatement(ICityTbl.STMT_FULL_INSERT);
		stmtInsertCity.bindString(1, "Zürich");
		stmtInsertCity.bindString(2, "Information");
		stmtInsertCity.bindString(3, "http://www.zuerich.ch");
		stmtInsertCity.bindString(4, "zuerich.mp4");

		Map<String, Long> cityIds = new HashMap<String, Long>();
		cityIds.put("Zürich", stmtInsertCity.executeInsert());
		
		
		SQLiteStatement stmtInsertCat = db.compileStatement(ICategory.STMT_FULL_INSERT);
		stmtInsertCat.bindString(1, "Bauwerke");
		
		Map<String, Long> categoryIds = new HashMap<String, Long>();
		categoryIds.put("Bauwerke", stmtInsertCat.executeInsert());
		
		
		SQLiteStatement stmtInsertPoi = db.compileStatement(IPointOfInterestTbl.STMT_FULL_INSERT);
		stmtInsertPoi.bindLong(1, cityIds.get("Zürich"));
		stmtInsertPoi.bindString(2, "Grossmünster");
		stmtInsertPoi.bindString(3, "Das Wahrzeichen Zürichs beherrscht mit der mächtigen Doppelturmfassade den oberen Limmatraum");
		stmtInsertPoi.bindLong(4, 8544167);
		stmtInsertPoi.bindLong(5, 47370000);
		stmtInsertPoi.bindString(6, "http://www.grossmuenster.ch");

		Map<String, Long> poiIds = new HashMap<String, Long>();
		poiIds.put("Grossmünster", stmtInsertPoi.executeInsert());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + IPointOfInterestTbl.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + ICategory.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + ICityTbl.TABLE_NAME);
		onCreate(db);
	}

}
