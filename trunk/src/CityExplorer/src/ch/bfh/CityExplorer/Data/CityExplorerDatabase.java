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
		
		// Cities
		Map<String, Long> cityIds = new HashMap<String, Long>();
		SQLiteStatement stmtInsertCity = db.compileStatement(CityTbl.STMT_FULL_INSERT);
		
		stmtInsertCity.bindString(1, "Z�rich");
		stmtInsertCity.bindString(2, "Information");
		stmtInsertCity.bindString(3, "http://www.zuerich.ch");
		stmtInsertCity.bindString(4, "zuerich.mp4");
		cityIds.put("Z�rich", stmtInsertCity.executeInsert());
				
		// Categories
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
				
		// Points of Interest
		Map<String, Long> poiIds = new HashMap<String, Long>();
		SQLiteStatement stmtInsertPoi = db.compileStatement(PointOfInterestTbl.STMT_FULL_INSERT);

		// Grossm�nster
		stmtInsertPoi.bindLong(1, cityIds.get("Z�rich"));
		stmtInsertPoi.bindLong(2, categoryIds.get("Bauwerke"));
		stmtInsertPoi.bindString(3, "Grossm�nster");
		stmtInsertPoi.bindString(4, "Das Wahrzeichen Z�richs beherrscht mit der m�chtigen Doppelturmfassade den oberen Limmatraum");
		stmtInsertPoi.bindLong(5, 8544167);
		stmtInsertPoi.bindLong(6, 47370000);
		stmtInsertPoi.bindString(7, "http://www.grossmuenster.ch");
		poiIds.put("Grossm�nster", stmtInsertPoi.executeInsert());

		// Zeughauskeller
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Zeughauskeller");
		stmtInsertPoi.bindString(4, "Schweizer K�che im Grossformat: W�rste, R�sti u.a. Das Traditionslokal wird von Einheimischen wir von Touristen besucht.");
		stmtInsertPoi.bindLong(5, 8539818);
		stmtInsertPoi.bindLong(6, 47370328);
		stmtInsertPoi.bindString(7, "http://www.zeughauskeller.ch");
		poiIds.put("Zeughauskeller", stmtInsertPoi.executeInsert());

		// Landesmuseum Z�rich
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Landesmuseum Z�rich");
		stmtInsertPoi.bindString(4, "Gleich neben dem Hauptbahnhofscheint ein trutziges Schloss zu stehen - tats�chlich handelt es sich bei der Geb�udegruppe um das Landesmuseum, die bedeutendste Sammlung zur Schweizer Kultur und Geschichte.");
		stmtInsertPoi.bindLong(5, 8541004);
		stmtInsertPoi.bindLong(6, 47379024);
		stmtInsertPoi.bindString(7, "http://www.landesmuseum.ch");
		poiIds.put("Landesmuseum Z�rich", stmtInsertPoi.executeInsert());
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(PointOfInterestTbl.SQL_DROP);
		db.execSQL(CategoryTbl.SQL_DROP);
		db.execSQL(CityTbl.SQL_DROP);
		onCreate(db);
	}

}