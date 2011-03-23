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
		//		cityIds.get("Zürich"),
		//		categoryIds.get("Bauwerke"),
		//		"Grossmünster",
		//		"Das Wahrzeichen Zürichs beherrscht mit der mächtigen Doppelturmfassade den oberen Limmatraum",
		//		47.370000,
		//		8.544167,
		//		"http://www.grossmuenster.ch");
		
		
		// QUELLE: http://www.zuerich-reisefuehrer.de/grossmuenster.html
		
		// Grossmünster
		stmtInsertPoi.bindLong(1, cityIds.get("Zürich"));
		stmtInsertPoi.bindLong(2, categoryIds.get("Bauwerke"));
		stmtInsertPoi.bindString(3, "Grossmünster");
		stmtInsertPoi.bindString(4, "In der Altstadt befindet sich das Wahrzeichen Zürichs: das Grossmünster mit seinen Doppeltürmen. Der Name stammt erst aus dem 14. Jahrhundert. Zuvor wurde die Kirche in Urkunden lediglich als \"Zürcher Kirche\" bezeichnet. Der Innenraum des Grossmünsters ist sehr schlicht gehalten. Er enthält nur eine Kanzel und einen Taufstein. Im Chor sind drei farbige Fenster von Augusto Giacometti zu sehen, welche die Weihnachtsgeschichte erzählen. Weiterhin sehenswert ist die Krypta, der älteste Teil der Kirche, wo stark verblasste Wandmalereien aus dem 14./15. Jahrhundert zu sehen sind.");
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
		stmtInsertPoi.bindString(4, "Gleich neben dem Hauptbahnhof scheint ein trutziges Schloss zu stehen - tatsächlich handelt es sich bei der Gebäudegruppe um das Landesmuseum, die bedeutendste Sammlung zur Schweizer Kultur und Geschichte.");
		stmtInsertPoi.bindDouble(5, 47.379024);
		stmtInsertPoi.bindDouble(6, 8.541004);
		stmtInsertPoi.bindString(7, "http://www.landesmuseum.ch");
		poiIds.put("Landesmuseum Zürich", stmtInsertPoi.executeInsert());
		
		//Hauptbahnhof
		stmtInsertPoi.bindLong(2, categoryIds.get("Bauwerke"));
		stmtInsertPoi.bindString(3, "Hauptbahnhof");
		stmtInsertPoi.bindString(4, "Wichtigstes Einfallstor für Reisende ist der Hauptbahnhof. Im prunkvollen Neorenaissance-Bau aus Sandstein befindet sich der heutige Bahnhof von Zürich. Sein Haupteingang, ein Triumphbogen am Ende der Bahnhofstrasse, wirkt wie ein Tor zu einer damals neu erschlossenen Welt. Erbaut wurde der Bahnhof 1871. Unterirdisch werden heute zwei S-Bahnhöfe mit der Haupthalle verbunden. Hier befinden sich Ladenpassagen.");
		stmtInsertPoi.bindDouble(5, 47.378174);
		stmtInsertPoi.bindDouble(6, 8.540191);
		stmtInsertPoi.bindString(7, "http://www.railcity.ch");
		poiIds.put("Hauptbahnhof", stmtInsertPoi.executeInsert());
		
		//Fraumünster
		stmtInsertPoi.bindLong(2, categoryIds.get("Bauwerke"));
		stmtInsertPoi.bindString(3, "Fraumünster");
		stmtInsertPoi.bindString(4, "Wichtigstes Zentrum der Stadt, auch in politischer Hinsicht, bildete lange das Fraumünster. Die jeweilige Äbtissin hatte in Zürich bis zur Reformation das Sagen. Zu den Hauptattraktionen des Münsters zählen heute die Glasfenster von Chagall und Giacometti.");
		stmtInsertPoi.bindDouble(5, 47.372084);
		stmtInsertPoi.bindDouble(6, 8.543691);
		stmtInsertPoi.bindString(7, "http://www.fraumuenster.ch");
		poiIds.put("Fraumünster", stmtInsertPoi.executeInsert());
		
		//ETH Zürich
		stmtInsertPoi.bindLong(2, categoryIds.get("Bauwerke"));
		stmtInsertPoi.bindString(3, "ETH Zürich");
		stmtInsertPoi.bindString(4, "Die kurz ETH genannte Eidgenössische Technische Hochschule zählt zu den renomiertesten Hochschulen der Welt mit naturwissenschaftlich-technischem Schwerpunkt. Hier forschten, lehrten und lernten kluge Köpfe wie Albert Einstein und Wolfgang Pauli.");
		stmtInsertPoi.bindDouble(5, 47.376441);
		stmtInsertPoi.bindDouble(6, 8.547991);
		stmtInsertPoi.bindString(7, "http://www.ethz.ch");
		poiIds.put("ETH Zürich", stmtInsertPoi.executeInsert());
		
		//Kunsthaus
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Kunsthaus");
		stmtInsertPoi.bindString(4, "Für Kulturfreunde ist allein der Besuch des Kunsthaues eine Reise nach Zürich wert. Die Wechselausstellungen sind ausgezeichnet, und das Haus verfügt über eine der bedeutendsten Gemälde- und Skulpturensammlungen Europas, darunter die umfangreichste Giacometti-Sammlung weltweit sowie die grösste Munch-Sammlung ausserhalb Norwegens.");
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
		stmtInsertPoi.bindString(4, "Die 1,4 km lange Bahnhofstrasse ist die schickste Einkaufsstrasse Zürichs und eine der schönsten Europas. Zahlreiche elegante Modegeschäfte, Warenhäuser und Boutiquen sowie Luxushotels säumen die berühmteste Bahnhofstrasse der Welt. Einst befand sich hier die Befestigungslinie der Zürcher Stadtbefestigung. Ab 1864 wurde der Fröschengraben zugeschüttet und durch einen Boulevard ersetzt, der Paradeplatz und Hauptbahnhof verbindet. Sehenswert sind unter anderem der Hauptbahnhof, die Max-Bill-Skulptur und die Banken am Paradeplatz.");
		stmtInsertPoi.bindDouble(5, 47.376271);
		stmtInsertPoi.bindDouble(6, 8.539534);
		stmtInsertPoi.bindString(7, "http://www.bahnhofstrasse-zuerich.ch");
		poiIds.put("Bahnhofstrasse", stmtInsertPoi.executeInsert());
		
		//Paradeplatz
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Paradeplatz");
		stmtInsertPoi.bindString(4, "Das Herzstück der Bahnhofstrasse ist der Paradeplatz. Es ist eine der teuersten Lagen der Stadt. Der Paradeplatz steht für Banken und Wohlstand. Credit Suisse und UBS, die beiden Schweizer Grossbanken, haben ihren Sitz in der Edelgegend. Einst war der Platz als Schweinemarkt bekannt. Später wurde er Neumarkt genannt, bis er 1865 in Paradeplatz umbenannt wurde. 1838 wurde hier das erste Fremdenhotel der Stadt, das heutige Savoy Baur en Ville, eröffnet.");
		stmtInsertPoi.bindDouble(5, 47.369688);
		stmtInsertPoi.bindDouble(6, 8.538885);
		stmtInsertPoi.bindString(7, "http://www.paradeplatz.ch");
		poiIds.put("Paradeplatz", stmtInsertPoi.executeInsert());
		
		//Zoo Zürich
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Zoo Zürich");
		stmtInsertPoi.bindString(4, "Auf dem Zürichberg liegt der Zoologische Garten mit einer Regenwaldhalle, die in Europa einmalig ist. Eröffnet wurde er im Jahre 1929. Bekannt ist der Zoo unter anderem für die artgerechte Haltung der Tiere. Auf 15 Hektar leben rund 2000 Tiere und 280 verschiedene Arten aus sechs Kontinenten. Zu den Hauptattraktionen des Zoos zählen unter anderem der südamerikanische Bergnebelwald, in dem Brillen- und Nasenbären zu Hause sind, oder die Himalaya-Anlage, in der Sibirische Tiger, Mongolische Wölfe, Schneeleoparden und Kleine Pandas leben. Selbstverständlich zählt auch die Masoala Regenwald-Halle zu einer der Hauptattraktion. Der madagassische Masoala-Regenwald wird auf gut einem Hektar abgebildet und bietet Lemuren, Makis, Chamäleons, Flughunden, Aldabra-Riesenschildkröten, Geckos, Fröschen, Echsen und Insekten Unterschlupf. 2006 wurde eine Löwenanlage für Asiatische Löwen eröffnet. Afrikahaus, Menschenaffenhaus und Schildkrötenhaus sind nur ein paar der weiteren Attraktionen. Abgerundet wird das Angebot durch Zoolino, wo die kleinen Besucher auf dem Gelände eines alten Bauernhofes Kontakt zu Schweinen, Gänsen, Hühnern, Ziegen oder Meerschweinchen aufnehmen können, und die Naturwerkstatt, in der Stadtkinder die Tiere im Siedlungsraum näher gebracht werden. Bis 2020 sollen weitere Projekte wie die asiatische Wüste, die Savanne und ein südamerikanischer und afrikanischer Regenwald realisiert werden. Im ganzen Park stehen Besuchern Bänke und Picknick-Tische zur Verfügung. Auch für die Kinder gibt es eine grosse Anzahl von Spielmöglichkeiten.");
		stmtInsertPoi.bindDouble(5, 47.384462);
		stmtInsertPoi.bindDouble(6, 8.574792);
		stmtInsertPoi.bindString(7, "http://www.zoo.ch");
		poiIds.put("Zoo Zürich", stmtInsertPoi.executeInsert());
		

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
