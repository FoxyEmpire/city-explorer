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
		
		stmtInsertCity.bindString(1, "Z�rich");
		stmtInsertCity.bindString(2, "Information");
		stmtInsertCity.bindString(3, "http://www.zuerich.ch");
		stmtInsertCity.bindString(4, "zuerich.mp4");
		cityIds.put("Z�rich", stmtInsertCity.executeInsert());
		
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
		
		stmtInsertCat.bindString(1, "Aussichtspunkte");
		categoryIds.put("Aussichtspunkte", stmtInsertCat.executeInsert());
		
		stmtInsertCat.bindString(1, "Einkaufen");
		categoryIds.put("Einkaufen", stmtInsertCat.executeInsert());
		
		return categoryIds;
	}

	private Map<String, Long> insertPointsOfInterest(SQLiteDatabase db, Map<String, Long> cityIds, Map<String, Long> categoryIds) {
		Map<String, Long> poiIds = new HashMap<String, Long>();
		SQLiteStatement stmtInsertPoi = db.compileStatement(PointOfInterestTbl.STMT_FULL_INSERT);

		
		//PointOfInterestTbl.InsertPointOfInterest(
		//		db,
		//		cityIds.get("Z�rich"),
		//		categoryIds.get("Bauwerke"),
		//		"Grossm�nster",
		//		"Das Wahrzeichen Z�richs beherrscht mit der m�chtigen Doppelturmfassade den oberen Limmatraum",
		//		47.370000,
		//		8.544167,
		//		"http://www.grossmuenster.ch");
		
		
		// QUELLE: http://www.zuerich-reisefuehrer.de/grossmuenster.html
		
		// Grossm�nster
		stmtInsertPoi.bindLong(1, cityIds.get("Z�rich"));
		stmtInsertPoi.bindLong(2, categoryIds.get("Bauwerke"));
		stmtInsertPoi.bindString(3, "Grossm�nster");
		stmtInsertPoi.bindString(4, "Das Wahrzeichen Z�richs beherrscht mit der m�chtigen Doppelturmfassade den oberen Limmatraum");
		stmtInsertPoi.bindDouble(5, 47.370000);
		stmtInsertPoi.bindDouble(6, 8.544167);
		stmtInsertPoi.bindString(7, "http://www.grossmuenster.ch");
		poiIds.put("Grossm�nster", stmtInsertPoi.executeInsert());

		// Zeughauskeller
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Zeughauskeller");
		stmtInsertPoi.bindString(4, "Schweizer K�che im Grossformat: W�rste, R�sti u.a. Das Traditionslokal wird von Einheimischen wir von Touristen besucht.");
		stmtInsertPoi.bindDouble(5, 47.370328);
		stmtInsertPoi.bindDouble(6, 8.539818);
		stmtInsertPoi.bindString(7, "http://www.zeughauskeller.ch");
		poiIds.put("Zeughauskeller", stmtInsertPoi.executeInsert());

		// Landesmuseum Z�rich
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Landesmuseum Z�rich");
		stmtInsertPoi.bindString(4, "Gleich neben dem Hauptbahnhof scheint ein trutziges Schloss zu stehen - tats�chlich handelt es sich bei der Geb�udegruppe um das Landesmuseum, die bedeutendste Sammlung zur Schweizer Kultur und Geschichte.");
		stmtInsertPoi.bindDouble(5, 47.379024);
		stmtInsertPoi.bindDouble(6, 8.541004);
		stmtInsertPoi.bindString(7, "http://www.landesmuseum.ch");
		poiIds.put("Landesmuseum Z�rich", stmtInsertPoi.executeInsert());
		
		//Hauptbahnhof
		stmtInsertPoi.bindLong(2, categoryIds.get("Bauwerke"));
		stmtInsertPoi.bindString(3, "Hauptbahnhof");
		stmtInsertPoi.bindString(4, "Wichtigstes Einfallstor f�r Reisende ist der Hauptbahnhof. Im prunkvollen Neorenaissance-Bau aus Sandstein befindet sich der heutige Bahnhof von Z�rich. Sein Haupteingang, ein Triumphbogen am Ende der Bahnhofstrasse, wirkt wie ein Tor zu einer damals neu erschlossenen Welt. Erbaut wurde der Bahnhof 1871. Unterirdisch werden heute zwei S-Bahnh�fe mit der Haupthalle verbunden. Hier befinden sich Ladenpassagen.");
		stmtInsertPoi.bindDouble(5, 47.378174);
		stmtInsertPoi.bindDouble(6, 8.540191);
		stmtInsertPoi.bindString(7, "http://www.railcity.ch");
		poiIds.put("Hauptbahnhof", stmtInsertPoi.executeInsert());
		
		//Fraum�nster
		stmtInsertPoi.bindLong(2, categoryIds.get("Bauwerke"));
		stmtInsertPoi.bindString(3, "Fraum�nster");
		stmtInsertPoi.bindString(4, "Wichtigstes Zentrum der Stadt, auch in politischer Hinsicht, bildete lange das Fraum�nster. Die jeweilige �btissin hatte in Z�rich bis zur Reformation das Sagen. Zu den Hauptattraktionen des M�nsters z�hlen heute die Glasfenster von Chagall und Giacometti.");
		stmtInsertPoi.bindDouble(5, 47.372084);
		stmtInsertPoi.bindDouble(6, 8.543691);
		stmtInsertPoi.bindString(7, "http://www.fraumuenster.ch");
		poiIds.put("Fraum�nster", stmtInsertPoi.executeInsert());
		
		//ETH Z�rich
		stmtInsertPoi.bindLong(2, categoryIds.get("Bauwerke"));
		stmtInsertPoi.bindString(3, "ETH Z�rich");
		stmtInsertPoi.bindString(4, "Die kurz ETH genannte Eidgen�ssische Technische Hochschule z�hlt zu den renomiertesten Hochschulen der Welt mit naturwissenschaftlich-technischem Schwerpunkt. Hier forschten, lehrten und lernten kluge K�pfe wie Albert Einstein und Wolfgang Pauli.");
		stmtInsertPoi.bindDouble(5, 47.376441);
		stmtInsertPoi.bindDouble(6, 8.547991);
		stmtInsertPoi.bindString(7, "http://www.ethz.ch");
		poiIds.put("ETH Z�rich", stmtInsertPoi.executeInsert());
		
		//Kunsthaus
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Kunsthaus");
		stmtInsertPoi.bindString(4, "F�r Kulturfreunde ist allein der Besuch des Kunsthaues eine Reise nach Z�rich wert. Die Wechselausstellungen sind ausgezeichnet, und das Haus verf�gt �ber eine der bedeutendsten Gem�lde- und Skulpturensammlungen Europas, darunter die umfangreichste Giacometti-Sammlung weltweit sowie die gr�sste Munch-Sammlung ausserhalb Norwegens.");
		stmtInsertPoi.bindDouble(5, 47.370113);
		stmtInsertPoi.bindDouble(6, 8.548463);
		stmtInsertPoi.bindString(7, "http://www.kunsthaus.ch");
		poiIds.put("Kunsthaus", stmtInsertPoi.executeInsert());
		
		// Lindenhof
		stmtInsertPoi.bindLong(2, categoryIds.get("Aussichtspunkte"));
		stmtInsertPoi.bindString(3, "Lindenhof");
		stmtInsertPoi.bindString(4, "");
		stmtInsertPoi.bindDouble(5, 47.373005);
		stmtInsertPoi.bindDouble(6, 8.541307);
		stmtInsertPoi.bindNull(7);
		poiIds.put("Lindenhof", stmtInsertPoi.executeInsert());
		
		//Bahnhofstrasse
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Bahnhofstrasse");
		stmtInsertPoi.bindString(4, "Die 1,4 km lange Bahnhofstrasse ist die schickste Einkaufsstrasse Z�richs und eine der sch�nsten Europas. Zahlreiche elegante Modegesch�fte, Warenh�user und Boutiquen sowie Luxushotels s�umen die ber�hmteste Bahnhofstrasse der Welt. Einst befand sich hier die Befestigungslinie der Z�rcher Stadtbefestigung. Ab 1864 wurde der Fr�schengraben zugesch�ttet und durch einen Boulevard ersetzt, der Paradeplatz und Hauptbahnhof verbindet. Sehenswert sind unter anderem der Hauptbahnhof, die Max-Bill-Skulptur und die Banken am Paradeplatz.");
		// TODO!!
		stmtInsertPoi.bindNull(5);
		stmtInsertPoi.bindNull(6);
		stmtInsertPoi.bindString(7, "http://www.bahnhofstrasse-zuerich.ch");
		poiIds.put("Bahnhofstrasse", stmtInsertPoi.executeInsert());

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
