package ch.bfh.CityExplorer.Data;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class CityExplorerDatabase extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "cityexplorer.db";
	private static final int DATABASE_VERSION = 8;
	
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
		
		stmtInsertCat.bindString(1, "Sehenswürdigkeiten");
		categoryIds.put("Sehenswürdigkeiten", stmtInsertCat.executeInsert());
		
		stmtInsertCat.bindString(1, "Kinos");
		categoryIds.put("Kinos", stmtInsertCat.executeInsert());
		
		stmtInsertCat.bindString(1, "Restaurants");
		categoryIds.put("Restaurants", stmtInsertCat.executeInsert());
		
		stmtInsertCat.bindString(1, "Bars");
		categoryIds.put("Bars", stmtInsertCat.executeInsert());
		
		stmtInsertCat.bindString(1, "Einkaufen");
		categoryIds.put("Einkaufen", stmtInsertCat.executeInsert());
		
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
		
		
		// QUELLE: http://www.zuerich-reisefuehrer.de/grossmuenster.html

		
		
		stmtInsertPoi.bindLong(1, cityIds.get("Zürich"));

		
		
		// Kino abaton
		stmtInsertPoi.bindLong(2, categoryIds.get("Kinos"));
		stmtInsertPoi.bindString(3, "Kino abaton");
		stmtInsertPoi.bindString(4, "12 Säle mit insgesamt 2284 Sitzplätzen.");
		stmtInsertPoi.bindDouble(5, 47.389247);
		stmtInsertPoi.bindDouble(6, 8.521519);
		stmtInsertPoi.bindString(7, "http://www.kitag.com");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.movies.ch/db_data/theaters/abatonzuerich/exterior_l.jpg");
		stmtInsertPoi.bindString(10, "http://www.movies.ch/db_data/screens/abatonzuerich11/view1_l.jpg");
		stmtInsertPoi.bindString(11, "Heinrichstrasse 269\n8005 Zürich");
		poiIds.put("Kino abaton", stmtInsertPoi.executeInsert());
		
		// Kino abc
		stmtInsertPoi.bindLong(2, categoryIds.get("Kinos"));
		stmtInsertPoi.bindString(3, "Kino abc");
		stmtInsertPoi.bindString(4, "4 Säle mit insgesamt 830 Sitzplätzen.");
		stmtInsertPoi.bindDouble(5, 47.376236);
		stmtInsertPoi.bindDouble(6, 8.540986);
		stmtInsertPoi.bindString(7, "http://www.kitag.com");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.movies.ch/db_data/theaters/abczuerich/exterior_l.jpg");
		stmtInsertPoi.bindString(10, "http://www.movies.ch/db_data/screens/abczuerich1/view1_l.jpg");
		stmtInsertPoi.bindString(11, "Waisenhausstr. 2-4\n8000 Zürich");
		poiIds.put("Kino abc", stmtInsertPoi.executeInsert());
		
		// Kino Arena
		stmtInsertPoi.bindLong(2, categoryIds.get("Kinos"));
		stmtInsertPoi.bindString(3, "Kino Arena Filmcity");
		stmtInsertPoi.bindString(4, "9 Säle mit insgesamt 2051 Sitzplätzen.");
		stmtInsertPoi.bindDouble(5, 47.358482);
		stmtInsertPoi.bindDouble(6, 8.522628);
		stmtInsertPoi.bindString(7, "http://www.arena.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.movies.ch/db_data/screens/arenazuerich08/view1_l.jpg");
		stmtInsertPoi.bindString(10, "http://www.movies.ch/db_data/screens/arenazuerich04/view2_l.jpg");
		stmtInsertPoi.bindString(11, "Kalanderplatz 8\n8045 Zürich");
		poiIds.put("Kino Arena", stmtInsertPoi.executeInsert());
		
		// Kino capitol
		stmtInsertPoi.bindLong(2, categoryIds.get("Kinos"));
		stmtInsertPoi.bindString(3, "Kino capitol");
		stmtInsertPoi.bindString(4, "6 Säle mit insgesamt 857 Sitzplätzen.");
		stmtInsertPoi.bindDouble(5, 47.377870);
		stmtInsertPoi.bindDouble(6, 8.544105);
		stmtInsertPoi.bindString(7, "http://www.kitag.com");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.movies.ch/db_data/screens/capitolzuerich1/view3_l.jpg");
		stmtInsertPoi.bindString(10, "http://www.movies.ch/db_data/screens/capitolzuerich1/view4_l.jpg");
		stmtInsertPoi.bindString(11, "Weinbergstrasse 9\n8001 Zürich");
		poiIds.put("Kino capitol", stmtInsertPoi.executeInsert());
		
		// Kino corso
		stmtInsertPoi.bindLong(2, categoryIds.get("Kinos"));
		stmtInsertPoi.bindString(3, "Kino corso");
		stmtInsertPoi.bindString(4, "4 Säle mit insgesamt 1368 Sitzplätzen.");
		stmtInsertPoi.bindDouble(5, 47.366376);
		stmtInsertPoi.bindDouble(6, 8.546727);
		stmtInsertPoi.bindString(7, "http://www.kitag.com");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.movies.ch/db_data/screens/corsozuerich1/view1_l.jpg");
		stmtInsertPoi.bindString(10, "http://www.movies.ch/db_data/screens/corsozuerich4/view1_l.jpg");
		stmtInsertPoi.bindString(11, "Theaterstr. 10\n8001 Zürich");
		poiIds.put("Kino corso", stmtInsertPoi.executeInsert());
		
		// Kino metropol
		stmtInsertPoi.bindLong(2, categoryIds.get("Kinos"));
		stmtInsertPoi.bindString(3, "Kino metropol");
		stmtInsertPoi.bindString(4, "2 Säle mit insgesamt 500 Sitzplätzen.");
		stmtInsertPoi.bindDouble(5, 47.373661);
		stmtInsertPoi.bindDouble(6, 8.530799);
		stmtInsertPoi.bindString(7, "http://www.kitag.com");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.movies.ch/db_data/screens/metropolzuerich1/view1_l.jpg");
		stmtInsertPoi.bindString(10, "http://www.movies.ch/db_data/screens/metropolzuerich2/view1_l.jpg");
		stmtInsertPoi.bindString(11, "Badenerstr. 16\n8004 Zürich");
		poiIds.put("Kino metropol", stmtInsertPoi.executeInsert());
		
		//--------------------
		
		// Acqua
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Acqua");
		stmtInsertPoi.bindString(4, "Grosse Fensterfronten lassen den Raum noch grösser erscheinen, als er schon ist. Komfortabel platzierte Tische sorgen für genügend \"Luft\", sodass auch diskretere Diskussionen möglich sind. Das warme Interieur mit Leder, Perlmutt und Holz ermuntert geradezu zu heiteren Tafelrunden. Im Zuge des Umbaus wurde der Boden des Restaurants erhöht, damit die Aussicht noch besser eingesogen werden kann. Im Sommer kommt ein stilvoll gestalteter Garten direkt am See hinzu.");
		stmtInsertPoi.bindDouble(5, 47.358850);
		stmtInsertPoi.bindDouble(6, 8.536973);
		stmtInsertPoi.bindString(7, "http://www.acqua.ch");
		stmtInsertPoi.bindString(8, "Montag bis Samstag: 11:30 – 23:30 Uhr\nSonntag: 10:00 – 23:30 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/acqua/DSC_8547.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/acqua/IMG_3981.jpg");
		stmtInsertPoi.bindString(11, "Mythenquai 61\n8002 Zürich");
		poiIds.put("Acqua", stmtInsertPoi.executeInsert());
		
		// Zeughauskeller
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Zeughauskeller");
		stmtInsertPoi.bindString(4, "Schweizer Küche im Grossformat: Würste, Rösti u.a. Das Traditionslokal wird von Einheimischen wir von Touristen besucht.");
		stmtInsertPoi.bindDouble(5, 47.370328);
		stmtInsertPoi.bindDouble(6, 8.539818);
		stmtInsertPoi.bindString(7, "http://www.zeughauskeller.ch");
		stmtInsertPoi.bindString(8, "Täglich: 11:30 – 23:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/zeughauskeller/DSC_6569.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/zeughauskeller/DSC_6577.jpg");
		stmtInsertPoi.bindString(11, "Bahnhofstrasse 28a\n8001 Zürich");
		poiIds.put("Zeughauskeller", stmtInsertPoi.executeInsert());
		
		// Brasserie Lipp
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Brasserie Lipp");
		stmtInsertPoi.bindString(4, "Das Lipp ist eine Kultstätte französischer Brasserie-Küche mit dem besonderen Flair der Belle Epoque. Hier wird marktfrische „cuisine bourgeoise“ für Gourmets und Liebhaber von Meeresfrüchten und erlesenen Weinen serviert. Besonders empfehlenswert sind die Bouillabaisse, die Muscheln und die Austern. Oben, in der Jules Verne Panoramabar, geniesst man einen unvergleichlichen Blick über Zürich.");
		stmtInsertPoi.bindDouble(5, 47.374335);
		stmtInsertPoi.bindDouble(6, 8.539357);
		stmtInsertPoi.bindString(7, "http://www.brasserie-lipp.ch");
		stmtInsertPoi.bindString(8, "Montag bis Donnerstag: 08:00 – 24:00 Uhr\nFreitag: 08:00 – 01:00 Uhr\nSamstag: 11:00 – 01:00 Uhr\nSonntag: 11:45 – 23:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/julesverne/jvturm.jpg");
		stmtInsertPoi.bindString(10, "http://images.gadmin.st.s3.amazonaws.com/n20497/images/zuerich/detail_breit/web-brasserie-lipp2.png");
		stmtInsertPoi.bindString(11, "Uraniastrasse 9, 8001 Zürich");
		poiIds.put("Brasserie Lipp", stmtInsertPoi.executeInsert());
		
		// Rôtisserie Hotel Storchen
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Rôtisserie Hotel Storchen");
		stmtInsertPoi.bindString(4, "Das Restaurant des Hotels Storchen schmiegt sich an die Limmat. Von den Tischen am Fenster und erst recht auf der Terrasse kommt man in den Genuss eines einzigartigen Blickes auf das Wasser, die Grossmünster-Türme und den Zunfthäusern am Limmatquai. Die Storchen-Küche ist bekannt für ihre zahlreichen hausgemachten Kreationen, wie etwa die selbstgemachte Pasta oder den hausgeräucherten Lachs.");
		stmtInsertPoi.bindDouble(5, 47.371415);
		stmtInsertPoi.bindDouble(6, 8.541447);
		stmtInsertPoi.bindString(7, "http://www.storchen.ch");
		stmtInsertPoi.bindString(8, "Täglich: 11:45 - 24:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.storchen.ch/files/rotisserie_xz4d9760.jpg");
		stmtInsertPoi.bindString(10, "http://www.storchen.ch/files/terrasse_xz4d9922_1.jpg");
		stmtInsertPoi.bindString(11, "Am Weinplatz 2, 8001 Zürich");
		poiIds.put("Rôtisserie Hotel Storchen", stmtInsertPoi.executeInsert());
		
		// Hiltl
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Hiltl");
		stmtInsertPoi.bindString(4, "1898 eröffnete Ambrosium Hiltl das erste vegetarische Restaurant in Europa. Heute finden hier Vegetarier und Liebhaber der vegetarischen Küche alles, was ihr Herz begehrt. Zum Beispiel das grösste Salatbuffet der Stadt mit über 40 Sorten, ein asiatisches Buffet, frischgepresste Fruchtsäfte und eine abwechslungsreiche Speisekarte.");
		stmtInsertPoi.bindDouble(5, 47.373272);
		stmtInsertPoi.bindDouble(6, 8.536674);
		stmtInsertPoi.bindString(7, "http://www.hiltl.ch");
		stmtInsertPoi.bindString(8, "Montag bis Mittwoch: 06:00 - 24:00 Uhr\nDonnerstag bis Samstag: 06:00 - fertig...\nSonntag: 08:00 - 24:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/hiltl/FrontHaus.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/hiltl/Ampore.jpg");
		stmtInsertPoi.bindString(11, "Sihlstrasse 28, 8001 Zürich");
		poiIds.put("Hiltl", stmtInsertPoi.executeInsert());
		
		// Bohemia
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Bohemia");
		stmtInsertPoi.bindString(4, "Die Bohemia Küche, bei der die Küchen Südamerikas, der Karibik, Kaliforniens und der Pazifischen Inseln auf kreative und überraschende Art fusioniert werden, ist ein Spiel mit den Aromen und Düften der südlichen Hemisphäre. Die eigene Kaffeerösterei, das reichhaltigen amerikanische Frühstück, eine grosse Cocktail-Bar und ein Humidor voller kubanischer Zigarren verführen täglich in die Welt der Genüsse.");
		stmtInsertPoi.bindDouble(5, 47.364848);
		stmtInsertPoi.bindDouble(6, 8.554983);
		stmtInsertPoi.bindString(7, "http://www.bohemia.ch");
		stmtInsertPoi.bindString(8, "Montag und Mittwoch: 06:45 - 24:00 Uhr\nDonnerstag bis Freitag: 06:45 - 01:00\nSamstag: 09:00 - 01:00 Uhr\nSonntag: 09:00 - 24:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/bohemia/IMG_0327.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/bohemia/IMG_0319.jpg");
		stmtInsertPoi.bindString(11, "Klosbachstrasse 2, 8032 Zürich");
		poiIds.put("Bohemia", stmtInsertPoi.executeInsert());
		
		// Carlton
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Carlton");
		stmtInsertPoi.bindString(4, "Das grosszügige Art déco Restaurant mit der ruhigen Gartenterrasse liegt im Herzen von Zürich an der berühmten Bahnhofstrasse. Es zeichnet sich durch ein kosmopolitisches Ambiente aus und ist Treffpunkt für kulinarische Geniesser im Allgemeinen und Weinliebhaber im Speziellen. Hier werden Ihnen neben multikulturellen und kreativen Speisen auch bewährte Klassiker serviert. Der mehrfach ausgezeichnete Weinkeller mit 990 Weinen lässt keine Wünsche offen.");
		stmtInsertPoi.bindDouble(5, 47.371409);
		stmtInsertPoi.bindDouble(6, 8.537774);
		stmtInsertPoi.bindString(7, "http://www.carlton.ch");
		stmtInsertPoi.bindString(8, "Montag bis Freitag: 11:00 - 24:00 Uhr\nSamstag: 11:00 - 18:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/carlton/IMG_0473.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/carlton/IMG_0469.jpg");
		stmtInsertPoi.bindString(11, "Bahnhofstrasse 41, 8001 Zürich");
		poiIds.put("Carlton", stmtInsertPoi.executeInsert());
		
		// Cheyenne
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Cheyenne");
		stmtInsertPoi.bindString(4, "In diesem amerikanischen Burgerrestaurant werden Speisen serviert, wie sie typisch für das Grenzgebiet zwischen Mexiko und den USA sind: Mais- und Weizentortillas in allen Variationen, verschiedene Sandwiches und saftige Burgers. Die frisch zubereiteten Tagesmenüs und diversen Speisen à la carte lassen keine Wünsche für den grossen oder kleinen Apetit offen.");
		stmtInsertPoi.bindDouble(5, 47.409931);
		stmtInsertPoi.bindDouble(6, 8.545738);
		stmtInsertPoi.bindString(7, "http://www.cheyenne-oerlikon.ch");
		stmtInsertPoi.bindString(8, "Montag bis Freitag: 10:00 - 24:00 Uhr\nSamstag: 09:00 - 24:00 Uhr\nSonntag: 10:00 - 24:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/cheyenne/cheyenne4.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/cheyenne/cheyenne3.jpg");
		stmtInsertPoi.bindString(11, "Querstrasse 3, 8050 Zürich");
		poiIds.put("Cheyenne", stmtInsertPoi.executeInsert());
		
		// Iroquois
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Iroquois");
		stmtInsertPoi.bindString(4, "Im Iroquois wird in entspannter Atmosphäre alles serviert, was die Tex-Mex-Küche zu bieten hat: Feine Burritos, Enchiladas, Fajitas zum Selberfüllen, verschiedenste Burger-Kreationen, aber auch einen Schuss kalifornische Küche für leichtere Mahlzeiten. Unbedingt den Big Mama Burger für zwei bis vier Personen probieren – den grössten Burger der Stadt. Im Sommer sitzt man an der Sonne und beobachtet das Treiben des Quartiers.");
		stmtInsertPoi.bindDouble(5, 47.358348);
		stmtInsertPoi.bindDouble(6, 8.553682);
		stmtInsertPoi.bindString(7, "http://www.iroquois.ch");
		stmtInsertPoi.bindString(8, "Montag bis Freitag: 08:00 - 24:00 Uhr\nSamstag und Sonntag: 08:30 - 24:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/iroquois/Entree.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/iroquois/DSCN5589.jpg");
		stmtInsertPoi.bindString(11, "Seefeldstrasse 120, 8008 Zürich");
		poiIds.put("Iroquois", stmtInsertPoi.executeInsert());
		
		//--------------------
		
		// Jules Verne Panoramabar
		stmtInsertPoi.bindLong(2, categoryIds.get("Bars"));
		stmtInsertPoi.bindString(3, "Jules Verne Panoramabar");
		stmtInsertPoi.bindString(4, "Bekannteste Aussichtsbar in Zürich. Hier, hoch über den Dächern von Zürich, geniesst man den Apéro doppelt.");
		stmtInsertPoi.bindDouble(5, 47.374335);
		stmtInsertPoi.bindDouble(6, 8.539357);
		stmtInsertPoi.bindString(7, "http://www.brasserie-lipp.ch");
		stmtInsertPoi.bindString(8, "Montag bis Donnerstag: 08:00 – 24:00 Uhr\nFreitag und Samstag: 11:00 – 01:00 Uhr\nSonntag: 11:45 – 23:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/julesverne/jvturm.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/julesverne/jvdrinnen.jpg");
		stmtInsertPoi.bindString(11, "Uraniastrasse 9, 8001 Zürich");
		poiIds.put("Jules Verne Panoramabar", stmtInsertPoi.executeInsert());
		
		//--------------------
		
		// Opernhaus
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Opernhaus");
		stmtInsertPoi.bindString(4, "Das Opernhaus Zürich, mit seinen ca. 1100 Plätzen das kleinste der grossen internationalen Opernhäuser, bietet einen äusserst vielseitigen Spielplan: 17 Neuproduktionen und 26 Wiederaufnahmen werden aktuell herausgebracht. Cecilia Bartoli, Renée Fleming, Vesselina Kasarova, Emily Magee, José Cura, Plácido Domingo, Thomas Hampson, Jonas Kaufmann, Matti Salminen, Erwin Schrott, Rolando Villazon sowie die Dirigenten Franz Welser-Möst, Nikolaus Harnoncourt, Christoph von Dohnanyi, Carlo Rizzi u.v.a. treten hier regelmässig auf.");
		stmtInsertPoi.bindDouble(5, 47.364430);
		stmtInsertPoi.bindDouble(6, 8.546249);
		stmtInsertPoi.bindString(7, "http://www.opernhaus.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/opernhaus/DSC_5109.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/opernhaus/DSC_4830.jpg");
		stmtInsertPoi.bindString(11, "Falkenstrasse 1\n8008 Zürich");
		poiIds.put("Opernhaus", stmtInsertPoi.executeInsert());
		
		// Landesmuseum Zürich
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Landesmuseum Zürich");
		stmtInsertPoi.bindString(4, "Gustav Gull hat das Schweizerische Landesmuseum 1898 zum 50-jährigen Bestehen der Ersten Bundesverfassung erbaut. Das Landesmuseum beherbergt die grösste kulturgeschichtliche Sammlung des Landes. Der über 100jährige Museumsbau erinnert an ein Märchenschloss. Von Türmen umsäumt bietet der Innenhof eine einzigartige Erlebnisarena. Hinter den Gemäuern wird die Vergangenheit lebendig: Wie lebten, dachten und fühlten die vorangegangenen Generationen? Ihre materielle Hinterlassenschaft - vom Kunsthandwerk bis zum unscheinbaren Alltagsgegenstand - gibt Antworten: von der Urgeschichte bis zur Gegenwart. Im Rahmen von Sonderausstellungen greift das Landesmuseum ausserdem gesellschaftlich relevante Themen auf und bietet damit temporäre Perspektivenwechsel.");
		stmtInsertPoi.bindDouble(5, 47.379024);
		stmtInsertPoi.bindDouble(6, 8.541004);
		stmtInsertPoi.bindString(7, "http://www.landesmuseum.ch");
		stmtInsertPoi.bindString(8, "Dienstag bis Sonntag: 10:00 - 17:00 Uhr\nDonnerstag: 10:00 - 19:00 Uhr");
		stmtInsertPoi.bindString(9, "http://images.gadmin.st.s3.amazonaws.com/n20507/images/zuerich/detail_breit/web_landesmuseum-1.jpg");
		stmtInsertPoi.bindString(10, "");
		stmtInsertPoi.bindString(11, "Museumstrasse 2\n8001 Zürich");
		poiIds.put("Landesmuseum Zürich", stmtInsertPoi.executeInsert());
		
		// Kunsthaus
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Kunsthaus");
		stmtInsertPoi.bindString(4, "Das Kunsthaus Zürich beherbergt die bedeutendste Modernesammlung Zürichs und ist sowohl für seine permanente Kunstsammlung als auch für die temporären Ausstellungen bekannt. Neben Werken Alberto Giacomettis sind erlesene mittelalterliche Skulpturen und Tafelbilder, Gemälde des niederländischen und italienischen Barock sowie Höhepunkte der Schweizer Malerei des 19. und 20. Jahrhunderts wie Johann Heinrich Füssli oder Ferdinand Hodler zu finden. Auch Zürcher Konkrete und zeitgenössische Schweizer Künstler wie Pipilotti Rist und Peter Fischli / David Weiss sowie Fotografie und Installationen sind vertreten.\nZu den internationalen Schwerpunkten gehören die grösste Munch-Sammlung ausserhalb Norwegens, wichtige Bilder von Picasso, die Expressonisten Kokoschka, Beckmann und Corinth sowie bedeutende Werke von Monet und Chagall. Neben der fast schon klassisch zu nennenden Popart werden u.a. auch Werke von Rothko, Merz, Twombly, Beuys, Bacon und Baselitz präsentiert. Audioguide in D/E/F/I erhältlich.");
		stmtInsertPoi.bindDouble(5, 47.370155);
		stmtInsertPoi.bindDouble(6, 8.548460);
		stmtInsertPoi.bindString(7, "http://www.kunsthaus.ch");
		stmtInsertPoi.bindString(8, "Mittwoch bis Freitag: 10:00 - 20:00 Uhr\nSamstag, Sonntag, Dienstag: 10:00 - 18:00 Uhr");
		stmtInsertPoi.bindString(9, "http://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Kunsthaus_Z%C3%BCrich.jpg/791px-Kunsthaus_Z%C3%BCrich.jpg");
		stmtInsertPoi.bindString(10, "http://images.gadmin.st.s3.amazonaws.com/n20507/images/zuerich/detail_breit/web_highlights_kunsthaus-zurich-1.jpg");
		stmtInsertPoi.bindString(11, "Heimplatz 1\n8001 Zürich");
		poiIds.put("Kunsthaus", stmtInsertPoi.executeInsert());
		
		// Tonhalle Zürich
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Tonhalle Zürich");
		stmtInsertPoi.bindString(4, "Die Tonhalle Zürich zählt bezüglich Akustik weltweit zu den besten Konzertsälen.\n\nDer berühmte Tonhalle-Saal wurde 1895 erbaut und im Beisein von Johannes Brahms eingeweiht. Er bietet Platz für 1455 Konzertbesucher.\n\nDas Tonhalle-Orchester Zürich hat sich in den letzten Jahren zu einem führenden Klangkörper Europas entwickelt. Gut hundert hoch motivierte Musikerinnen und Musiker und eine engagierte Dirigenten-Persönlichkeit (David Zinman, seit 1995) präsentieren dem Publikum rund hundert Konzerte pro Saison mit etwa fünfzig verschiedenen Programmen.");
		stmtInsertPoi.bindDouble(5, 47.366091);
		stmtInsertPoi.bindDouble(6, 8.537969);
		stmtInsertPoi.bindString(7, "http://www.tonhalle.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/kongresshaus/ZH-KH-17.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/kongresshaus/ZH-KH-09.jpg");
		stmtInsertPoi.bindString(11, "Claridenstrasse 7\n8002 Zürich");
		poiIds.put("Tonhalle Zürich", stmtInsertPoi.executeInsert());
		
		// Schauspielhaus: Pfauen
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Schauspielhaus: Pfauen");
		stmtInsertPoi.bindString(4, "Der \"Pfauen\" ist mit 750 Zuschauerplätzen die grösste und älteste Bühne des Schauspielhauses. Während den Kriegsjahren erlebte das Theater einen Aufschwung und avancierte zum Theater mit explizit antifaschistischer Stossrichtung und einem kritischen Spielplan. Viele Emigranten und SchauspielerInnen, ausdrückliche Gegner des Nationalsozialismus, wurden ins Ensemble aufgenommen.\n\nHeute lockt das Schauspielhaus mit einem abwechslungsreichen, spannenden Programm und 20 bis 30 Neuinszenzierungen pro Jahr.");
		stmtInsertPoi.bindDouble(5, 47.370265);
		stmtInsertPoi.bindDouble(6, 8.549244);
		stmtInsertPoi.bindString(7, "http://www.schauspielhaus.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Schauspielhaus_Z%C3%BCrich.jpg/800px-Schauspielhaus_Z%C3%BCrich.jpg");
		stmtInsertPoi.bindString(10, "http://images.gadmin.st.s3.amazonaws.com/n20507/images/zuerich/detail_breit/web_schauspielhaus_pfauen-1.jpg");
		stmtInsertPoi.bindString(11, "Rämistrasse 34\n8032 Zürich");
		poiIds.put("Schauspielhaus: Pfauen", stmtInsertPoi.executeInsert());
		
		// Museum für Gestaltung Zürich
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Museum für Gestaltung Zürich");
		stmtInsertPoi.bindString(4, "Design und visuelle Kommunikation, Umweltgestaltung und Kunst, Architektur und Alltagskultur, Fotografie und Medien: Das sind die Themen, mit denen sich das Museum für Gestaltung beschäftigt. Wechselausstellungen bilden das Programm und zahlreiche Veranstaltungen setzen Akzente.\n\nAls Partner der Zürcher Hochschule der Künste ist das Museum auch offen für Belange künstlerischer Ausbildung. Es führt vier bedeutende Sammlungen (Design, Grafik, Kunstgewerbe, Plakate) sowie eine öffentlich zugängliche Fachbibliothek.");
		stmtInsertPoi.bindDouble(5, 47.382870);
		stmtInsertPoi.bindDouble(6, 8.535693);
		stmtInsertPoi.bindString(7, "http://www.museum-gestaltung.ch");
		stmtInsertPoi.bindString(8, "Dienstag bis Sonntag: 10:00 – 17:00 Uhr\nMittwoch: 10:00 – 20:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.museum-gestaltung.ch/typo3temp/pics/2b7971ce99.jpg");
		stmtInsertPoi.bindString(10, "http://www.museum-gestaltung.ch/typo3temp/pics/8b390f5aac.jpg");
		stmtInsertPoi.bindString(11, "Ausstellungsstrasse 60\n8005 Zürich");
		poiIds.put("Museum für Gestaltung Zürich", stmtInsertPoi.executeInsert());
		
		// Museum Rietberg
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Museum Rietberg");
		stmtInsertPoi.bindString(4, "Das Museum Rietberg Zürich ist das einzige Kunstmuseum für aussereuropäische Kulturen in der Schweiz und besitzt eine international renommierte Sammlung mit Werken aus Asien, Afrika, Amerika und Ozeanien.\n\nMit der Präsentation von Kunstwerken will das Museum nicht nur die faszinierende Vielfalt künstlerischer Ausdrucksformen bewusst machen, sondern auch Verständnis und Interesse für fremde Kulturen, Weltanschauungen und Religionen wecken.\n\nDer architektonisch spektakuläre «Smaragd», wie der neue Museumsbau genannt wird, besteht aus ei-nem Glaspavillon und fügt sich perfekt in das bestehende Villen-Ensemble im schönen Rieterpark ein.");
		stmtInsertPoi.bindDouble(5, 47.358959);
		stmtInsertPoi.bindDouble(6, 8.530237);
		stmtInsertPoi.bindString(7, "http://www.rietberg.ch");
		stmtInsertPoi.bindString(8, "Dienstag bis Sonntag: 10:00 – 17:00 Uhr\nMittwoch und Donnerstag: 10:00 – 20:00 Uhr");
		stmtInsertPoi.bindString(9, "http://images.gadmin.st.s3.amazonaws.com/n20507/images/zuerich/detail_breit/web_museum_rietberg-zurich-1.jpg");
		stmtInsertPoi.bindString(10, "http://www.stadt-zuerich.ch/content/kultur/de/index/institutionen/museum_rietberg/sammlung/_jcr_content/mainparsys/198_1227876735414/image3.640.480.jpg");
		stmtInsertPoi.bindString(11, "Gablerstrasse 15\n8002 Zürich");
		poiIds.put("Museum Rietberg", stmtInsertPoi.executeInsert());
		
		// Museum Bellerive
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Museum Bellerive");
		stmtInsertPoi.bindString(4, "Dreimal jährlich präsentiert das Museum Bellerive in Wechselausstellungen, was internationale Künstlerinnen und Künstler geschaffen haben im weiten Feld zwischen Spiel, Dekoration und Gebrauch.\n\nWilliam Morris, Emile Gallé, Diego Giacometti und Sonia Delaunay waren hier schon zu Gast; offen sind die Räume aber auch für Mode, Design und Lifestyle der Gegenwart. Ihren Platz im Ausstellungsprogramm haben zudem ausgewählte Objekte aus den reichen Beständen der Kunstgewerblichen Sammlung. ");
		stmtInsertPoi.bindDouble(5, 47.356273);
		stmtInsertPoi.bindDouble(6, 8.550295);
		stmtInsertPoi.bindString(7, "http://www.museum-bellerive.ch");
		stmtInsertPoi.bindString(8, "Dienstag bis Sonntag: 10:00 – 17:00 Uhr");
		stmtInsertPoi.bindString(9, "http://images.gadmin.st.s3.amazonaws.com/n20507/images/zuerich/detail_breit/web_museum_bellerive-1.jpg");
		stmtInsertPoi.bindString(10, "http://www.museum-bellerive.ch/typo3temp/pics/8bbad1cc6f.jpg");
		stmtInsertPoi.bindString(11, "Höschgasse 3\n8008 Zürich");
		poiIds.put("Museum Bellerive", stmtInsertPoi.executeInsert());
		
		//--------------------
		
		// Bahnhofstrasse
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Bahnhofstrasse");
		stmtInsertPoi.bindString(4, "Die 1,4 km lange Bahnhofstrasse ist die schickste Einkaufsstrasse Zürichs und eine der schönsten Europas. Zahlreiche elegante Modegeschäfte, Warenhäuser und Boutiquen sowie Luxushotels säumen die berühmteste Bahnhofstrasse der Welt. Einst befand sich hier die Befestigungslinie der Zürcher Stadtbefestigung. Ab 1864 wurde der Fröschengraben zugeschüttet und durch einen Boulevard ersetzt, der Paradeplatz und Hauptbahnhof verbindet. Sehenswert sind unter anderem der Hauptbahnhof, die Max-Bill-Skulptur und die Banken am Paradeplatz.");
		stmtInsertPoi.bindDouble(5, 47.376271);
		stmtInsertPoi.bindDouble(6, 8.539534);
		stmtInsertPoi.bindString(7, "http://www.bahnhofstrasse-zuerich.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "");
		stmtInsertPoi.bindString(10, "");
		stmtInsertPoi.bindNull(11);
		poiIds.put("Bahnhofstrasse", stmtInsertPoi.executeInsert());
		
		// Paradeplatz
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Paradeplatz");
		stmtInsertPoi.bindString(4, "Das Herzstück der Bahnhofstrasse ist der Paradeplatz. Es ist eine der teuersten Lagen der Stadt. Der Paradeplatz steht für Banken und Wohlstand. Credit Suisse und UBS, die beiden Schweizer Grossbanken, haben ihren Sitz in der Edelgegend. Einst war der Platz als Schweinemarkt bekannt. Später wurde er Neumarkt genannt, bis er 1865 in Paradeplatz umbenannt wurde. 1838 wurde hier das erste Fremdenhotel der Stadt, das heutige Savoy Baur en Ville, eröffnet.");
		stmtInsertPoi.bindDouble(5, 47.369688);
		stmtInsertPoi.bindDouble(6, 8.538885);
		stmtInsertPoi.bindString(7, "http://www.paradeplatz.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.scheuble.ch/images/zuerich-sehenswert/zuerich-paradeplatz.jpg");
		stmtInsertPoi.bindString(10, "http://www.nzz.ch/images/paradeplatz_fullSize_1.2613447.1243149950.jpg");
		stmtInsertPoi.bindNull(11);
		poiIds.put("Paradeplatz", stmtInsertPoi.executeInsert());
		
		// Jelmoli
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Jelmoli");
		stmtInsertPoi.bindString(4, "Jelmoli - The House of Brands, in 5 Minuten zu Fuss vom Hauptbahnhof, ist das grösste Shop-in-Shop Warenhaus der Schweiz. Das Warenhaus wurde nach Vorbildern aus Paris und London 1899 erstellt und gehört heute zu den besten Adressen in Zürich. Lebensmittel und Delikatessen findet man im Untergeschoss des Jelmoli-Warenhaus (Gourmet Factory). Die meisten der Produkte kann man auch an einer der zahlreichen kleineren Bars probieren.");
		stmtInsertPoi.bindDouble(5, 47.374243);
		stmtInsertPoi.bindDouble(6, 8.537661);
		stmtInsertPoi.bindString(7, "http://www.jelmoli.ch");
		stmtInsertPoi.bindString(8, "Montag bis Samstag: 09:00 - 20:00 Uhr");
		stmtInsertPoi.bindString(9, "http://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Jelmoli.JPG/800px-Jelmoli.JPG");
		stmtInsertPoi.bindString(10, "http://www.jelmoli.ch/media/17327/hdr_oeffnungszeiten.jpg");
		stmtInsertPoi.bindString(11, "Seidengasse 1\n8001 Zürich");
		poiIds.put("Jelmoli", stmtInsertPoi.executeInsert());
		
		// Globus
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Globus");
		stmtInsertPoi.bindString(4, "Schickes Warenhaus, besonders die Delikatessenabteilung im Untergeschoss lässt dem Gourmet das Wasser im Munde zusammenlaufen. Das im Jahr 1907 gegründete Unternehmen gehört seit 1997 zum Migros-Konzern.");
		stmtInsertPoi.bindDouble(5, 47.375868);
		stmtInsertPoi.bindDouble(6, 8.537985);
		stmtInsertPoi.bindString(7, "http://www.globus.ch");
		stmtInsertPoi.bindString(8, "Montag bis Samstag: 09:00 - 20:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.globus.ch//locations/zuerich-bahnhofstrasse/large/large.jpg");
		stmtInsertPoi.bindString(10, "http://www.globus.ch//elements/0ce09873-2fb9-4233-8f48-a8f7fbf854db//image/image.jpg");
		stmtInsertPoi.bindString(11, "Schweizergasse 11\n8001 Zürich");
		poiIds.put("Globus", stmtInsertPoi.executeInsert());
		
		// Schweizer Heimatwerk
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Schweizer Heimatwerk");
		stmtInsertPoi.bindString(4, "Das 1930 gegründete Schweizer Heimatwerk hat sich zu einem bestandenen Label entwickelt. Es steht für Schweizer Kunsthandwerk von höchster Qualität, Funktionalität und ausgezeichnetem Design. Die Vielfalt der Produkte bewegt sich zwischen helvetischer Tradition und heutigen Trends. Vom Holzkreiseln über Glasschalen bis zu Schmuck – die abwechslungsreichen und erlesenen Kunstwerke «Made in Switzerland» machen das Angebot des Heimatwerks einmalig auf der Welt. Gäste wie Einheimische schätzen diese auserlesenen Geschenkideen aus der Schweiz.");
		stmtInsertPoi.bindDouble(5, 47.374340);
		stmtInsertPoi.bindDouble(6, 8.541662);
		stmtInsertPoi.bindString(7, "http://www.heimatwerk.ch/");
		stmtInsertPoi.bindString(8, "Montag bis Freitag: 09:00 - 20:00 Uhr\nSamstag: 09:00 - 18:00 Uhr");
		stmtInsertPoi.bindString(9, "http://farm3.static.flickr.com/2773/4515594428_d52351a9b5.jpg");
		stmtInsertPoi.bindString(10, "http://images.gadmin.st.s3.amazonaws.com/n20506/images/zuerich/detail_breit/web-heimatwerk11-1.png");
		stmtInsertPoi.bindString(11, "Uraniastrasse 1\n8001 Zürich");
		poiIds.put("Schweizer Heimatwerk", stmtInsertPoi.executeInsert());
		
		// Sihlcity
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Sihlcity");
		stmtInsertPoi.bindString(4, "Das Einkaufs- und Freizeitzentrum Sihlcity bietet Shopping in 80 Geschäften in einer einzigartigen architektonischen Kulisse von Tradition und Innovation, kombiniert mit diversen Restaurants, Bars und Cafés, Kinos, Nachtklub, Wellnesszentrum Asia Spa sowie dem Hotel FourPoints by Sheraton Sihlcity.");
		stmtInsertPoi.bindDouble(5, 47.358026);
		stmtInsertPoi.bindDouble(6, 8.523268);
		stmtInsertPoi.bindString(7, "http://www.sihlcity.ch");
		stmtInsertPoi.bindString(8, "Geschäfte: Montag bis Samstag: 09:00 - 20:00 Uhr\nMall: Täglich: 08:00 - 24:00 Uhr");
		stmtInsertPoi.bindString(9, "http://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Z%C3%BCrich_-_Sihlcity_IMG_0911.JPG/799px-Z%C3%BCrich_-_Sihlcity_IMG_0911.JPG");
		stmtInsertPoi.bindString(10, "http://upload.wikimedia.org/wikipedia/commons/thumb/7/7b/Sihlcity.jpg/435px-Sihlcity.jpg");
		stmtInsertPoi.bindString(11, "Kalanderplatz 1\n8045 Zürich");
		poiIds.put("Sihlcity", stmtInsertPoi.executeInsert());
		
		// Sprüngli
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Sprüngli");
		stmtInsertPoi.bindString(4, "Die Schweiz geniesst weltweiten Ruhm für die beste Schokolade, ein Image, das sie nicht zuletzt auch der Confiserie Sprüngli in Zürich verdankt. Noch heute werden die weltbekannten Sprüngli-Spezialitäten wie zum Beispiel die Luxemburgerlis, Pralinés und Truffes sowie die anderen beliebten Confiserie-Spezialitäten, nach traditionellen Rezepten hergestellt.");
		stmtInsertPoi.bindDouble(5, 47.369390);
		stmtInsertPoi.bindDouble(6, 8.539136);
		stmtInsertPoi.bindString(7, "http://www.confiserie-spruengli.ch");
		stmtInsertPoi.bindString(8, "Montag bis Freitag: 07:30 - 18:30 Uhr\nSamstag: 08:00 - 17:00 Uhr");
		stmtInsertPoi.bindString(9, "http://photos.igougo.com/images/p171608-Zurich-Confiserie_Sprngli_Bahnhofstrasse_Zrich.jpg");
		stmtInsertPoi.bindString(10, "http://kontakt.spruengli.ch/diashow/bilder/005.jpg");
		stmtInsertPoi.bindString(11, "Bahnhofstrasse 21\n8001 Zürich");
		poiIds.put("Sprüngli", stmtInsertPoi.executeInsert());
		
		// Im Viadukt
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Im Viadukt");
		stmtInsertPoi.bindString(4, "Unter den Bogen des 1894 erbauten Eisenbahnviadukts in Zürich West ist wieder Leben eingekehrt. Ein urbaner Treffpunkt von 500 Meter Länge lädt zum Flanieren, Einkaufen, Vergnügen, Essen und Trinken ein. In 36 Viaduktbögen ist ein Shopping-Paradies entstanden: Ein bunter Mix mit Delikatessenläden, Ateliers Galerien, Sport- und Modelabels präsentieren sich dem Besucher. Das Herz der Anlage ist die Markthalle, in der zwanzig Bauern und Lebensmittelhändler aus der Umgebung ihre Ware anbieten.");
		stmtInsertPoi.bindDouble(5, 47.388342);
		stmtInsertPoi.bindDouble(6, 8.525974);
		stmtInsertPoi.bindString(7, "http://www.im-viadukt.ch");
		stmtInsertPoi.bindString(8, "Markthalle: Montag bis Samstag: 08:00 - 20:00 Uhr");
		stmtInsertPoi.bindString(9, "http://markthalle.im-viadukt.ch/uploads/assets/pola/88c11593e1f0080b1e0733f714ab9df34d12d5e0.jpg");
		stmtInsertPoi.bindString(10, "http://markthalle.im-viadukt.ch/uploads/assets/pola/43bbcf7012f3f2fb3b796ed21cba1b3151d841ac.jpg");
		stmtInsertPoi.bindString(11, "Limmatstrasse 259\n8005 Zürich");
		poiIds.put("Im Viadukt", stmtInsertPoi.executeInsert());
		
		//--------------------
		
		// Hauptbahnhof
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehenswürdigkeiten"));
		stmtInsertPoi.bindString(3, "Hauptbahnhof");
		stmtInsertPoi.bindString(4, "Wichtigstes Einfallstor für Reisende ist der Hauptbahnhof. Im prunkvollen Neorenaissance-Bau aus Sandstein befindet sich der heutige Bahnhof von Zürich. Sein Haupteingang, ein Triumphbogen am Ende der Bahnhofstrasse, wirkt wie ein Tor zu einer damals neu erschlossenen Welt. Erbaut wurde der Bahnhof 1871. Unterirdisch werden heute zwei S-Bahnhöfe mit der Haupthalle verbunden. Hier befinden sich Ladenpassagen.");
		stmtInsertPoi.bindDouble(5, 47.378174);
		stmtInsertPoi.bindDouble(6, 8.540191);
		stmtInsertPoi.bindString(7, "http://www.railcity.ch/index_zuerich.htm");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/hauptbahnhof/ZH-HB-16.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/hauptbahnhof/ZH-HB-04.jpg");
		stmtInsertPoi.bindString(11, "Bahnhofplatz\n8001 Zürich");
		poiIds.put("Hauptbahnhof", stmtInsertPoi.executeInsert());
		
		// ETH Zürich
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehenswürdigkeiten"));
		stmtInsertPoi.bindString(3, "ETH Zürich");
		stmtInsertPoi.bindString(4, "Die kurz ETH genannte Eidgenössische Technische Hochschule zählt zu den renomiertesten Hochschulen der Welt mit naturwissenschaftlich-technischem Schwerpunkt. Hier forschten, lehrten und lernten kluge Köpfe wie Albert Einstein und Wolfgang Pauli.");
		stmtInsertPoi.bindDouble(5, 47.376441);
		stmtInsertPoi.bindDouble(6, 8.547991);
		stmtInsertPoi.bindString(7, "http://www.ethz.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/ETH_geradlinig_1.jpg/800px-ETH_geradlinig_1.jpg");
		stmtInsertPoi.bindString(10, "http://www.ethz.ch/media/pictures/zentrum/eth_zentrum_luftaufnahme1_thumb.jpg");
		stmtInsertPoi.bindString(11, "Rämistrasse 101\n8092 Zürich");
		poiIds.put("ETH Zürich", stmtInsertPoi.executeInsert());
		
		// Fraumünster
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehenswürdigkeiten"));
		stmtInsertPoi.bindString(3, "Fraumünster");
		stmtInsertPoi.bindString(4, "Die Kirche mit Frauenkloster wurde 853 von König Ludwig dem Deutschen gestifet und von Frauen des europäischen Hochadels bewohnt. Das Kloster genoss die Gunst von Königen und die Äbtissin hatte das Münzrecht von Zürich bis ins 13. Jh. Nach der Reformation kamen Kirche und Kloster in den Besitz der Stadt.\nBedeutende Bauteile sind der romanische Chor und das hochgewölbte Querschiff. Das Langhaus wurde 1911 letztmals umgebaut, nachdem schon im 18.Jh. der Nordturm erhöht und der Südturm abgetragen worden war.\nBedeutendster Schmuck neben der grössten Orgel im Kanton Zürich mit 5793 Pfeifen sind seine Farbfenster: die Nordfenster im Querschiff (1945) sind gefertigt von Augusto Giacometti, Alberto Giacomettis Onkel. Der fünfteilige Fensterzyklus im Chor (1970) und die Rosette im südlichen Querschiff (1978) sind Werke Marc Chagalls. Im Kreuzgang befindet sich ein Freskenzyklus von Paul Bodmer zur Gründung des Fraumünsters. Zu den Hauptattraktionen des Münsters zählen heute die Glasfenster von Chagall und Giacometti.");
		stmtInsertPoi.bindDouble(5, 47.369790);
		stmtInsertPoi.bindDouble(6, 8.541532);
		stmtInsertPoi.bindString(7, "http://www.fraumuenster.ch");
		stmtInsertPoi.bindString(8, "November - März 10:00 - 16:00 Uhr\nApril - Oktober 10:00 - 18:00 Uhr");
		stmtInsertPoi.bindString(9, "http://mw2.google.com/mw-panoramio/photos/medium/9725570.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/kirchen/fraumuenster3.jpg");
		stmtInsertPoi.bindString(11, "Münsterhof\n8001 Zürich");
		poiIds.put("Fraumünster", stmtInsertPoi.executeInsert());
		
		// Grossmünster
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehenswürdigkeiten"));
		stmtInsertPoi.bindString(3, "Grossmünster");
		stmtInsertPoi.bindString(4, "In der Altstadt befindet sich das Wahrzeichen Zürichs: das Grossmünster mit seinen Doppeltürmen. Der Name stammt erst aus dem 14. Jahrhundert. Zuvor wurde die Kirche in Urkunden lediglich als \"Zürcher Kirche\" bezeichnet. Der Innenraum des Grossmünsters ist sehr schlicht gehalten. Er enthält nur eine Kanzel und einen Taufstein. Im Chor sind drei farbige Fenster von Augusto Giacometti zu sehen, welche die Weihnachtsgeschichte erzählen. Weiterhin sehenswert ist die Krypta, der älteste Teil der Kirche, wo stark verblasste Wandmalereien aus dem 14./15. Jahrhundert zu sehen sind.");
		stmtInsertPoi.bindDouble(5, 47.370000);
		stmtInsertPoi.bindDouble(6, 8.544167);
		stmtInsertPoi.bindString(7, "http://www.grossmuenster.ch");
		stmtInsertPoi.bindString(8, "März – Oktober 10:00 - 18:00 Uhr\nNovember – Februar 10:00 - 17:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.grossmuenster.ch/typo3temp/pics/c512687fc9.jpg");
		stmtInsertPoi.bindString(10, "http://www.grossmuenster.ch/fileadmin/templates/images/tour/08_kln.jpg");
		stmtInsertPoi.bindString(11, "Zwingliplatz\n8000 Zürich");
		poiIds.put("Grossmünster", stmtInsertPoi.executeInsert());

		// Lindenhof
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehenswürdigkeiten"));
		stmtInsertPoi.bindString(3, "Lindenhof");
		stmtInsertPoi.bindString(4, "Hier geniesst man eine herrliche Aussicht auf die Altstadt, das Grossmünster, das Rathaus, die Limmat, die Universität und die Eidgenössische Technische Hochschule. Zudem ist der Lindenhof Schauplatz zahlreicher geschichtlicher Ereignisse.\nIm 4. Jahrhundert stand auf dem Lindenhof ein römisches Kastell. Es diente der römischen Besatzung und der Bevölkerung zum Schutz vor den Angriffen der Alemannen.\nIm 9. Jahrhundert baute der Enkel von Karl dem Grossen an dieser Stelle eine Pfalz, eine königliche Residenz.\nIm 13. Jahrhundert zogen die Zürcher gegen Winterthur in den Krieg. Dabei fielen so viele Männer, dass Zürich keine Krieger mehr hatte. Herzog, Stadtherr von Winterthur, versuchte Zürich einzunehmen und stationierte ein Heer vor die Stadtmauern. Die Zürcherinnen verkleideten sich daraufhin als Krieger und zogen mit langen Spiessen auf den erhöhten Lindenhof. Die Belagerer glaubten, ein starkes Heer sei zur Verstärkung nach Zürich gekommen und so gab Albrecht I. von Habsburg die Belagerung auf. Die Brunnenfigur auf dem Lindenhof zeigt die heldenhaften Zürcherinnen.\nBis in die frühe Neuzeit diente der Platz den Zürchern zu Versammlungen. 1798 wurde auf dem Lindenhof zum Beispiel der Eid auf die helvetische Verfassung geschworen.\nHeute ist der Lindenhof eine Oase der Ruhe und Erholung inmitten der Stadt und Treffpunkt passionierter Schachspieler.");
		stmtInsertPoi.bindDouble(5, 47.373005);
		stmtInsertPoi.bindDouble(6, 8.541307);
		stmtInsertPoi.bindNull(7);
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/lindenhof/DSC_3258.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/lindenhof/lindenhof-limmatquai.jpg");
		stmtInsertPoi.bindNull(11);
		poiIds.put("Lindenhof", stmtInsertPoi.executeInsert());
		
		// Niederdorf
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehenswürdigkeiten"));
		stmtInsertPoi.bindString(3, "Niederdorf");
		stmtInsertPoi.bindString(4, "Tagsüber laden die Fussgängerzone und viele, in kleinen Gässchen versteckte Läden zum \"shoppen\" ein. Abends verwandelt sich das Niederdorf mit seinen Bars, Beizen und Strassenkünstlern zum Vergnügungsviertel für ein bunt gemischtes Publikum.");
		stmtInsertPoi.bindDouble(5, 47.373241);
		stmtInsertPoi.bindDouble(6, 8.543742);
		stmtInsertPoi.bindString(7, "http://www.doerfli.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://images.gadmin.st.s3.amazonaws.com/n20505/images/zuerich/detail_breit/detailboard_niederdorf_7573-1.jpg");
		stmtInsertPoi.bindString(10, "http://upload.wikimedia.org/wikipedia/commons/thumb/1/13/Z%C3%BCrich_-_Niederdorf_-_St%C3%BCssihofstatt_-_zur_Schnidern_IMG_1375.jpg/658px-Z%C3%BCrich_-_Niederdorf_-_St%C3%BCssihofstatt_-_zur_Schnidern_IMG_1375.jpg");
		stmtInsertPoi.bindNull(11);
		poiIds.put("Niederdorf", stmtInsertPoi.executeInsert());
		
		// St. Peter
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehenswürdigkeiten"));
		stmtInsertPoi.bindString(3, "St. Peter");
		stmtInsertPoi.bindString(4, "Die Kirche St. Peter ist mit dem grössten Zifferblatt Europas bestückt: Der Durchmesser beträgt 8,7 Meter. Im Turm befinden sich fünf Glocken von 1880 - die grösste wiegt ohne Klöppel über 6000 kg.\nVom Mittelalter an bis 1911 diente der Kirchturm der Brandwache.\n\nDie Kirche St. Peter ist die älteste Pfarrkirche Zürichs und reicht in ihren Anfängen in die Zeit vor 900.\nDer erste Bürgermeister Rudolf Brun, 1360 im Chor der Kirche begraben, erwirbt 1345 die Kirche mit allen Pflichten und Rechten (Kirchensatz). Heute befindet sich sein Grab und Denkmal an der Aussenmauer des Turms. Der erste reformierter Pfarrer war Leo Jud (1523-1542), ein Freund Zwinglis und Mitarbeiter an der ersten Zürcher Bibelübersetzung. 1778-1801 wirkte der Pfarrer J.C. Lavater (Schriftsteller, Physiognomiker; Freund des jungen Goethe) in dieser Kirche. Sein Grabstein befindet sich an der Kirchenmauer, ein Denkmal im Chor der Kirche.\n\nBauelemente\nTurm: unterer Teil spätromanisch, oben gotisch\nChor: von etwa 1230\nFreskenfragmente 1300 bis 1500\nSchiff: barock");
		stmtInsertPoi.bindDouble(5, 47.371061);
		stmtInsertPoi.bindDouble(6, 8.540781);
		stmtInsertPoi.bindString(7, "http://www.st-peter-zh.ch");
		stmtInsertPoi.bindString(8, "Montag - Freitag: 8:00 - 18:00 Uhr\nSamstag: 10:00 - 16:00 Uhr\nSonntag: ca. 11:00 - 17:00 Uhr");
		stmtInsertPoi.bindString(9, "http://de.academic.ru/pictures/dewiki/115/st_peter_zuerich.jpg");
		stmtInsertPoi.bindString(10, "http://images.gadmin.st.s3.amazonaws.com/n20505/images/zuerich/detail_breit/detailboard_stpeterkirche_8086-1.jpg");
		stmtInsertPoi.bindString(11, "Schlüssel-Gasse\n8000 Zürich");
		poiIds.put("St. Peter", stmtInsertPoi.executeInsert());
		
		// Zoo Zürich
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehenswürdigkeiten"));
		stmtInsertPoi.bindString(3, "Zoo Zürich");
		stmtInsertPoi.bindString(4, "Auf dem Zürichberg liegt der Zoologische Garten mit einer Regenwaldhalle, die in Europa einmalig ist. Eröffnet wurde er im Jahre 1929. Bekannt ist der Zoo unter anderem für die artgerechte Haltung der Tiere. Auf 15 Hektar leben rund 2000 Tiere und 280 verschiedene Arten aus sechs Kontinenten. Zu den Hauptattraktionen des Zoos zählen unter anderem der südamerikanische Bergnebelwald, in dem Brillen- und Nasenbären zu Hause sind, oder die Himalaya-Anlage, in der Sibirische Tiger, Mongolische Wölfe, Schneeleoparden und Kleine Pandas leben. Selbstverständlich zählt auch die Masoala Regenwald-Halle zu einer der Hauptattraktion. Der madagassische Masoala-Regenwald wird auf gut einem Hektar abgebildet und bietet Lemuren, Makis, Chamäleons, Flughunden, Aldabra-Riesenschildkröten, Geckos, Fröschen, Echsen und Insekten Unterschlupf. 2006 wurde eine Löwenanlage für Asiatische Löwen eröffnet. Afrikahaus, Menschenaffenhaus und Schildkrötenhaus sind nur ein paar der weiteren Attraktionen. Abgerundet wird das Angebot durch Zoolino, wo die kleinen Besucher auf dem Gelände eines alten Bauernhofes Kontakt zu Schweinen, Gänsen, Hühnern, Ziegen oder Meerschweinchen aufnehmen können, und die Naturwerkstatt, in der Stadtkinder die Tiere im Siedlungsraum näher gebracht werden. Bis 2020 sollen weitere Projekte wie die asiatische Wüste, die Savanne und ein südamerikanischer und afrikanischer Regenwald realisiert werden. Im ganzen Park stehen Besuchern Bänke und Picknick-Tische zur Verfügung. Auch für die Kinder gibt es eine grosse Anzahl von Spielmöglichkeiten.");
		stmtInsertPoi.bindDouble(5, 47.384535);
		stmtInsertPoi.bindDouble(6, 8.574758);
		stmtInsertPoi.bindString(7, "http://www.zoo.ch");
		stmtInsertPoi.bindString(8, "März – Oktober 09:00 – 18:00 Uhr\nNovember – Februar 09:00 – 17:00 Uhr");
		stmtInsertPoi.bindString(9, "http://upload.wikimedia.org/wikipedia/commons/thumb/9/94/ZooZ%C3%BCrich_Eingang.jpg/800px-ZooZ%C3%BCrich_Eingang.jpg");
		stmtInsertPoi.bindString(10, "http://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Masoalah-halle-aussen.jpg/800px-Masoalah-halle-aussen.jpg");
		stmtInsertPoi.bindString(11, "Zürichbergstrasse 221\n8044 Zürich");
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
