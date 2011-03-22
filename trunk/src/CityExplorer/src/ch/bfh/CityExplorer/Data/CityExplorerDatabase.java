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
		db.execSQL(CityTbl.SQL_CREATE);
		db.execSQL(CategoryTbl.SQL_CREATE);
		db.execSQL(PointOfInterestTbl.SQL_CREATE);
		db.execSQL(FavouriteTbl.SQL_CREATE);
		
		Map<String, Long> cityIds = insertCities(db);
		Map<String, Long> categoryIds = insertCategories(db);
		Map<String, Long> poiIds = insertPointsOfInterest(db, cityIds, categoryIds);
	}
	
	private Map<String, Long> insertCities(SQLiteDatabase db) {
		Map<String, Long> cityIds = new HashMap<String, Long>();
		SQLiteStatement stmtInsertCity = db.compileStatement(CityTbl.STMT_FULL_INSERT);
		
		stmtInsertCity.bindString(1, "Zürich");
		stmtInsertCity.bindString(2, "Information");
		stmtInsertCity.bindString(3, "http://www.zuerich.ch");
		stmtInsertCity.bindString(4, "zuerich.mp4");
		cityIds.put("Zürich", stmtInsertCity.executeInsert());
		
		return cityIds;
	}
	
	private Map<String, Long> insertCategories(SQLiteDatabase db) {
		Map<String, Long> categoryIds = new HashMap<String, Long>();
		SQLiteStatement stmtInsertCat = db.compileStatement(CategoryTbl.STMT_FULL_INSERT);
		
		stmtInsertCat.bindString(1, "Bauwerke");		
		categoryIds.put("Bauwerke", stmtInsertCat.executeInsert());
		
		stmtInsertCat.bindString(1, "Kinos");
		categoryIds.put("Kinos", stmtInsertCat.executeInsert());
		
		stmtInsertCat.bindString(1, "Restaurants");
		categoryIds.put("Restaurants", stmtInsertCat.executeInsert());
		
		stmtInsertCat.bindString(1, "Kultur");
		categoryIds.put("Kultur", stmtInsertCat.executeInsert());
		
		return categoryIds;
	}

	private Map<String, Long> insertPointsOfInterest(SQLiteDatabase db, Map<String, Long> cityIds, Map<String, Long> categoryIds) {
		Map<String, Long> poiIds = new HashMap<String, Long>();
		SQLiteStatement stmtInsertPoi = db.compileStatement(PointOfInterestTbl.STMT_FULL_INSERT);

		
		//PointOfInterestTbl.InsertPointOfInterest(
		//		db,
		//		cityIds.get("Zürich"),
		//		categoryIds.get("Bauwerke"),
		//		"Grossmünster",
		//		"Das Wahrzeichen Zürichs beherrscht mit der mächtigen Doppelturmfassade den oberen Limmatraum",
		//		47.370000,
		//		8.544167,
		//		"http://www.grossmuenster.ch");
		
		
		// Grossmünster
		stmtInsertPoi.bindLong(1, cityIds.get("Zürich"));
		stmtInsertPoi.bindLong(2, categoryIds.get("Bauwerke"));
		stmtInsertPoi.bindString(3, "Grossmünster");
		stmtInsertPoi.bindString(4, "Das Wahrzeichen Zürichs beherrscht mit der mächtigen Doppelturmfassade den oberen Limmatraum");
		stmtInsertPoi.bindDouble(5, 47.370000);
		stmtInsertPoi.bindDouble(6, 8.544167);
		stmtInsertPoi.bindString(7, "http://www.grossmuenster.ch");
		poiIds.put("Grossmünster", stmtInsertPoi.executeInsert());

		// Zeughauskeller
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Zeughauskeller");
		stmtInsertPoi.bindString(4, "Schweizer Küche im Grossformat: Würste, Rösti u.a. Das Traditionslokal wird von Einheimischen wir von Touristen besucht.");
		stmtInsertPoi.bindDouble(5, 47.370328);
		stmtInsertPoi.bindDouble(6, 8.539818);
		stmtInsertPoi.bindString(7, "http://www.zeughauskeller.ch");
		poiIds.put("Zeughauskeller", stmtInsertPoi.executeInsert());

		// Landesmuseum Zürich
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Landesmuseum Zürich");
		stmtInsertPoi.bindString(4, "Gleich neben dem Hauptbahnhofscheint ein trutziges Schloss zu stehen - tatsächlich handelt es sich bei der Gebäudegruppe um das Landesmuseum, die bedeutendste Sammlung zur Schweizer Kultur und Geschichte.");
		stmtInsertPoi.bindDouble(5, 47.379024);
		stmtInsertPoi.bindDouble(6, 8.541004);
		stmtInsertPoi.bindString(7, "http://www.landesmuseum.ch");
		poiIds.put("Landesmuseum Zürich", stmtInsertPoi.executeInsert());
		
		return poiIds;
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(FavouriteTbl.SQL_DROP);
		db.execSQL(PointOfInterestTbl.SQL_DROP);
		db.execSQL(CategoryTbl.SQL_DROP);
		db.execSQL(CityTbl.SQL_DROP);
		onCreate(db);
	}
	
	

}
