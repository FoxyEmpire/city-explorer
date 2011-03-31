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
		
		stmtInsertCat.bindString(1, "Sehensw�rdigkeiten");
		categoryIds.put("Sehensw�rdigkeiten", stmtInsertCat.executeInsert());
		
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
		//		cityIds.get("Z�rich"),
		//		categoryIds.get("Bauwerke"),
		//		"Grossm�nster",
		//		"Das Wahrzeichen Z�richs beherrscht mit der m�chtigen Doppelturmfassade den oberen Limmatraum",
		//		47.370000,
		//		8.544167,
		//		"http://www.grossmuenster.ch");
		
		
		// QUELLE: http://www.zuerich-reisefuehrer.de/grossmuenster.html

		
		
		stmtInsertPoi.bindLong(1, cityIds.get("Z�rich"));

		
		
		// Kino abaton
		stmtInsertPoi.bindLong(2, categoryIds.get("Kinos"));
		stmtInsertPoi.bindString(3, "Kino abaton");
		stmtInsertPoi.bindString(4, "12 S�le mit insgesamt 2284 Sitzpl�tzen.");
		stmtInsertPoi.bindDouble(5, 47.389247);
		stmtInsertPoi.bindDouble(6, 8.521519);
		stmtInsertPoi.bindString(7, "http://www.kitag.com");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.movies.ch/db_data/theaters/abatonzuerich/exterior_l.jpg");
		stmtInsertPoi.bindString(10, "http://www.movies.ch/db_data/screens/abatonzuerich11/view1_l.jpg");
		stmtInsertPoi.bindString(11, "Heinrichstrasse 269\n8005 Z�rich");
		poiIds.put("Kino abaton", stmtInsertPoi.executeInsert());
		
		// Kino abc
		stmtInsertPoi.bindLong(2, categoryIds.get("Kinos"));
		stmtInsertPoi.bindString(3, "Kino abc");
		stmtInsertPoi.bindString(4, "4 S�le mit insgesamt 830 Sitzpl�tzen.");
		stmtInsertPoi.bindDouble(5, 47.376236);
		stmtInsertPoi.bindDouble(6, 8.540986);
		stmtInsertPoi.bindString(7, "http://www.kitag.com");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.movies.ch/db_data/theaters/abczuerich/exterior_l.jpg");
		stmtInsertPoi.bindString(10, "http://www.movies.ch/db_data/screens/abczuerich1/view1_l.jpg");
		stmtInsertPoi.bindString(11, "Waisenhausstr. 2-4\n8000 Z�rich");
		poiIds.put("Kino abc", stmtInsertPoi.executeInsert());
		
		// Kino Arena
		stmtInsertPoi.bindLong(2, categoryIds.get("Kinos"));
		stmtInsertPoi.bindString(3, "Kino Arena Filmcity");
		stmtInsertPoi.bindString(4, "9 S�le mit insgesamt 2051 Sitzpl�tzen.");
		stmtInsertPoi.bindDouble(5, 47.358482);
		stmtInsertPoi.bindDouble(6, 8.522628);
		stmtInsertPoi.bindString(7, "http://www.arena.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.movies.ch/db_data/screens/arenazuerich08/view1_l.jpg");
		stmtInsertPoi.bindString(10, "http://www.movies.ch/db_data/screens/arenazuerich04/view2_l.jpg");
		stmtInsertPoi.bindString(11, "Kalanderplatz 8\n8045 Z�rich");
		poiIds.put("Kino Arena", stmtInsertPoi.executeInsert());
		
		// Kino capitol
		stmtInsertPoi.bindLong(2, categoryIds.get("Kinos"));
		stmtInsertPoi.bindString(3, "Kino capitol");
		stmtInsertPoi.bindString(4, "6 S�le mit insgesamt 857 Sitzpl�tzen.");
		stmtInsertPoi.bindDouble(5, 47.377870);
		stmtInsertPoi.bindDouble(6, 8.544105);
		stmtInsertPoi.bindString(7, "http://www.kitag.com");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.movies.ch/db_data/screens/capitolzuerich1/view3_l.jpg");
		stmtInsertPoi.bindString(10, "http://www.movies.ch/db_data/screens/capitolzuerich1/view4_l.jpg");
		stmtInsertPoi.bindString(11, "Weinbergstrasse 9\n8001 Z�rich");
		poiIds.put("Kino capitol", stmtInsertPoi.executeInsert());
		
		// Kino corso
		stmtInsertPoi.bindLong(2, categoryIds.get("Kinos"));
		stmtInsertPoi.bindString(3, "Kino corso");
		stmtInsertPoi.bindString(4, "4 S�le mit insgesamt 1368 Sitzpl�tzen.");
		stmtInsertPoi.bindDouble(5, 47.366376);
		stmtInsertPoi.bindDouble(6, 8.546727);
		stmtInsertPoi.bindString(7, "http://www.kitag.com");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.movies.ch/db_data/screens/corsozuerich1/view1_l.jpg");
		stmtInsertPoi.bindString(10, "http://www.movies.ch/db_data/screens/corsozuerich4/view1_l.jpg");
		stmtInsertPoi.bindString(11, "Theaterstr. 10\n8001 Z�rich");
		poiIds.put("Kino corso", stmtInsertPoi.executeInsert());
		
		// Kino metropol
		stmtInsertPoi.bindLong(2, categoryIds.get("Kinos"));
		stmtInsertPoi.bindString(3, "Kino metropol");
		stmtInsertPoi.bindString(4, "2 S�le mit insgesamt 500 Sitzpl�tzen.");
		stmtInsertPoi.bindDouble(5, 47.373661);
		stmtInsertPoi.bindDouble(6, 8.530799);
		stmtInsertPoi.bindString(7, "http://www.kitag.com");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.movies.ch/db_data/screens/metropolzuerich1/view1_l.jpg");
		stmtInsertPoi.bindString(10, "http://www.movies.ch/db_data/screens/metropolzuerich2/view1_l.jpg");
		stmtInsertPoi.bindString(11, "Badenerstr. 16\n8004 Z�rich");
		poiIds.put("Kino metropol", stmtInsertPoi.executeInsert());
		
		//--------------------
		
		// Acqua
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Acqua");
		stmtInsertPoi.bindString(4, "Grosse Fensterfronten lassen den Raum noch gr�sser erscheinen, als er schon ist. Komfortabel platzierte Tische sorgen f�r gen�gend \"Luft\", sodass auch diskretere Diskussionen m�glich sind. Das warme Interieur mit Leder, Perlmutt und Holz ermuntert geradezu zu heiteren Tafelrunden. Im Zuge des Umbaus wurde der Boden des Restaurants erh�ht, damit die Aussicht noch besser eingesogen werden kann. Im Sommer kommt ein stilvoll gestalteter Garten direkt am See hinzu.");
		stmtInsertPoi.bindDouble(5, 47.358850);
		stmtInsertPoi.bindDouble(6, 8.536973);
		stmtInsertPoi.bindString(7, "http://www.acqua.ch");
		stmtInsertPoi.bindString(8, "Montag bis Samstag: 11:30 � 23:30 Uhr\nSonntag: 10:00 � 23:30 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/acqua/DSC_8547.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/acqua/IMG_3981.jpg");
		stmtInsertPoi.bindString(11, "Mythenquai 61\n8002 Z�rich");
		poiIds.put("Acqua", stmtInsertPoi.executeInsert());
		
		// Zeughauskeller
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Zeughauskeller");
		stmtInsertPoi.bindString(4, "Schweizer K�che im Grossformat: W�rste, R�sti u.a. Das Traditionslokal wird von Einheimischen wir von Touristen besucht.");
		stmtInsertPoi.bindDouble(5, 47.370328);
		stmtInsertPoi.bindDouble(6, 8.539818);
		stmtInsertPoi.bindString(7, "http://www.zeughauskeller.ch");
		stmtInsertPoi.bindString(8, "T�glich: 11:30 � 23:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/zeughauskeller/DSC_6569.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/zeughauskeller/DSC_6577.jpg");
		stmtInsertPoi.bindString(11, "Bahnhofstrasse 28a\n8001 Z�rich");
		poiIds.put("Zeughauskeller", stmtInsertPoi.executeInsert());
		
		// Brasserie Lipp
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Brasserie Lipp");
		stmtInsertPoi.bindString(4, "Das Lipp ist eine Kultst�tte franz�sischer Brasserie-K�che mit dem besonderen Flair der Belle Epoque. Hier wird marktfrische �cuisine bourgeoise� f�r Gourmets und Liebhaber von Meeresfr�chten und erlesenen Weinen serviert. Besonders empfehlenswert sind die Bouillabaisse, die Muscheln und die Austern. Oben, in der Jules Verne Panoramabar, geniesst man einen unvergleichlichen Blick �ber Z�rich.");
		stmtInsertPoi.bindDouble(5, 47.374335);
		stmtInsertPoi.bindDouble(6, 8.539357);
		stmtInsertPoi.bindString(7, "http://www.brasserie-lipp.ch");
		stmtInsertPoi.bindString(8, "Montag bis Donnerstag: 08:00 � 24:00 Uhr\nFreitag: 08:00 � 01:00 Uhr\nSamstag: 11:00 � 01:00 Uhr\nSonntag: 11:45 � 23:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/julesverne/jvturm.jpg");
		stmtInsertPoi.bindString(10, "http://images.gadmin.st.s3.amazonaws.com/n20497/images/zuerich/detail_breit/web-brasserie-lipp2.png");
		stmtInsertPoi.bindString(11, "Uraniastrasse 9, 8001 Z�rich");
		poiIds.put("Brasserie Lipp", stmtInsertPoi.executeInsert());
		
		// R�tisserie Hotel Storchen
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "R�tisserie Hotel Storchen");
		stmtInsertPoi.bindString(4, "Das Restaurant des Hotels Storchen schmiegt sich an die Limmat. Von den Tischen am Fenster und erst recht auf der Terrasse kommt man in den Genuss eines einzigartigen Blickes auf das Wasser, die Grossm�nster-T�rme und den Zunfth�usern am Limmatquai. Die Storchen-K�che ist bekannt f�r ihre zahlreichen hausgemachten Kreationen, wie etwa die selbstgemachte Pasta oder den hausger�ucherten Lachs.");
		stmtInsertPoi.bindDouble(5, 47.371415);
		stmtInsertPoi.bindDouble(6, 8.541447);
		stmtInsertPoi.bindString(7, "http://www.storchen.ch");
		stmtInsertPoi.bindString(8, "T�glich: 11:45 - 24:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.storchen.ch/files/rotisserie_xz4d9760.jpg");
		stmtInsertPoi.bindString(10, "http://www.storchen.ch/files/terrasse_xz4d9922_1.jpg");
		stmtInsertPoi.bindString(11, "Am Weinplatz 2, 8001 Z�rich");
		poiIds.put("R�tisserie Hotel Storchen", stmtInsertPoi.executeInsert());
		
		// Hiltl
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Hiltl");
		stmtInsertPoi.bindString(4, "1898 er�ffnete Ambrosium Hiltl das erste vegetarische Restaurant in Europa. Heute finden hier Vegetarier und Liebhaber der vegetarischen K�che alles, was ihr Herz begehrt. Zum Beispiel das gr�sste Salatbuffet der Stadt mit �ber 40 Sorten, ein asiatisches Buffet, frischgepresste Fruchts�fte und eine abwechslungsreiche Speisekarte.");
		stmtInsertPoi.bindDouble(5, 47.373272);
		stmtInsertPoi.bindDouble(6, 8.536674);
		stmtInsertPoi.bindString(7, "http://www.hiltl.ch");
		stmtInsertPoi.bindString(8, "Montag bis Mittwoch: 06:00 - 24:00 Uhr\nDonnerstag bis Samstag: 06:00 - fertig...\nSonntag: 08:00 - 24:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/hiltl/FrontHaus.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/hiltl/Ampore.jpg");
		stmtInsertPoi.bindString(11, "Sihlstrasse 28, 8001 Z�rich");
		poiIds.put("Hiltl", stmtInsertPoi.executeInsert());
		
		// Bohemia
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Bohemia");
		stmtInsertPoi.bindString(4, "Die Bohemia K�che, bei der die K�chen S�damerikas, der Karibik, Kaliforniens und der Pazifischen Inseln auf kreative und �berraschende Art fusioniert werden, ist ein Spiel mit den Aromen und D�ften der s�dlichen Hemisph�re. Die eigene Kaffeer�sterei, das reichhaltigen amerikanische Fr�hst�ck, eine grosse Cocktail-Bar und ein Humidor voller kubanischer Zigarren verf�hren t�glich in die Welt der Gen�sse.");
		stmtInsertPoi.bindDouble(5, 47.364848);
		stmtInsertPoi.bindDouble(6, 8.554983);
		stmtInsertPoi.bindString(7, "http://www.bohemia.ch");
		stmtInsertPoi.bindString(8, "Montag und Mittwoch: 06:45 - 24:00 Uhr\nDonnerstag bis Freitag: 06:45 - 01:00\nSamstag: 09:00 - 01:00 Uhr\nSonntag: 09:00 - 24:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/bohemia/IMG_0327.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/bohemia/IMG_0319.jpg");
		stmtInsertPoi.bindString(11, "Klosbachstrasse 2, 8032 Z�rich");
		poiIds.put("Bohemia", stmtInsertPoi.executeInsert());
		
		// Carlton
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Carlton");
		stmtInsertPoi.bindString(4, "Das grossz�gige Art d�co Restaurant mit der ruhigen Gartenterrasse liegt im Herzen von Z�rich an der ber�hmten Bahnhofstrasse. Es zeichnet sich durch ein kosmopolitisches Ambiente aus und ist Treffpunkt f�r kulinarische Geniesser im Allgemeinen und Weinliebhaber im Speziellen. Hier werden Ihnen neben multikulturellen und kreativen Speisen auch bew�hrte Klassiker serviert. Der mehrfach ausgezeichnete Weinkeller mit 990 Weinen l�sst keine W�nsche offen.");
		stmtInsertPoi.bindDouble(5, 47.371409);
		stmtInsertPoi.bindDouble(6, 8.537774);
		stmtInsertPoi.bindString(7, "http://www.carlton.ch");
		stmtInsertPoi.bindString(8, "Montag bis Freitag: 11:00 - 24:00 Uhr\nSamstag: 11:00 - 18:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/carlton/IMG_0473.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/carlton/IMG_0469.jpg");
		stmtInsertPoi.bindString(11, "Bahnhofstrasse 41, 8001 Z�rich");
		poiIds.put("Carlton", stmtInsertPoi.executeInsert());
		
		// Cheyenne
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Cheyenne");
		stmtInsertPoi.bindString(4, "In diesem amerikanischen Burgerrestaurant werden Speisen serviert, wie sie typisch f�r das Grenzgebiet zwischen Mexiko und den USA sind: Mais- und Weizentortillas in allen Variationen, verschiedene Sandwiches und saftige Burgers. Die frisch zubereiteten Tagesmen�s und diversen Speisen � la carte lassen keine W�nsche f�r den grossen oder kleinen Apetit offen.");
		stmtInsertPoi.bindDouble(5, 47.409931);
		stmtInsertPoi.bindDouble(6, 8.545738);
		stmtInsertPoi.bindString(7, "http://www.cheyenne-oerlikon.ch");
		stmtInsertPoi.bindString(8, "Montag bis Freitag: 10:00 - 24:00 Uhr\nSamstag: 09:00 - 24:00 Uhr\nSonntag: 10:00 - 24:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/cheyenne/cheyenne4.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/cheyenne/cheyenne3.jpg");
		stmtInsertPoi.bindString(11, "Querstrasse 3, 8050 Z�rich");
		poiIds.put("Cheyenne", stmtInsertPoi.executeInsert());
		
		// Iroquois
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Iroquois");
		stmtInsertPoi.bindString(4, "Im Iroquois wird in entspannter Atmosph�re alles serviert, was die Tex-Mex-K�che zu bieten hat: Feine Burritos, Enchiladas, Fajitas zum Selberf�llen, verschiedenste Burger-Kreationen, aber auch einen Schuss kalifornische K�che f�r leichtere Mahlzeiten. Unbedingt den Big Mama Burger f�r zwei bis vier Personen probieren � den gr�ssten Burger der Stadt. Im Sommer sitzt man an der Sonne und beobachtet das Treiben des Quartiers.");
		stmtInsertPoi.bindDouble(5, 47.358348);
		stmtInsertPoi.bindDouble(6, 8.553682);
		stmtInsertPoi.bindString(7, "http://www.iroquois.ch");
		stmtInsertPoi.bindString(8, "Montag bis Freitag: 08:00 - 24:00 Uhr\nSamstag und Sonntag: 08:30 - 24:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/iroquois/Entree.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/iroquois/DSCN5589.jpg");
		stmtInsertPoi.bindString(11, "Seefeldstrasse 120, 8008 Z�rich");
		poiIds.put("Iroquois", stmtInsertPoi.executeInsert());
		
		//--------------------
		
		// Jules Verne Panoramabar
		stmtInsertPoi.bindLong(2, categoryIds.get("Bars"));
		stmtInsertPoi.bindString(3, "Jules Verne Panoramabar");
		stmtInsertPoi.bindString(4, "Bekannteste Aussichtsbar in Z�rich. Hier, hoch �ber den D�chern von Z�rich, geniesst man den Ap�ro doppelt.");
		stmtInsertPoi.bindDouble(5, 47.374335);
		stmtInsertPoi.bindDouble(6, 8.539357);
		stmtInsertPoi.bindString(7, "http://www.brasserie-lipp.ch");
		stmtInsertPoi.bindString(8, "Montag bis Donnerstag: 08:00 � 24:00 Uhr\nFreitag und Samstag: 11:00 � 01:00 Uhr\nSonntag: 11:45 � 23:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/julesverne/jvturm.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/julesverne/jvdrinnen.jpg");
		stmtInsertPoi.bindString(11, "Uraniastrasse 9, 8001 Z�rich");
		poiIds.put("Jules Verne Panoramabar", stmtInsertPoi.executeInsert());
		
		//--------------------
		
		// Opernhaus
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Opernhaus");
		stmtInsertPoi.bindString(4, "Das Opernhaus Z�rich, mit seinen ca. 1100 Pl�tzen das kleinste der grossen internationalen Opernh�user, bietet einen �usserst vielseitigen Spielplan: 17 Neuproduktionen und 26 Wiederaufnahmen werden aktuell herausgebracht. Cecilia Bartoli, Ren�e Fleming, Vesselina Kasarova, Emily Magee, Jos� Cura, Pl�cido Domingo, Thomas Hampson, Jonas Kaufmann, Matti Salminen, Erwin Schrott, Rolando Villazon sowie die Dirigenten Franz Welser-M�st, Nikolaus Harnoncourt, Christoph von Dohnanyi, Carlo Rizzi u.v.a. treten hier regelm�ssig auf.");
		stmtInsertPoi.bindDouble(5, 47.364430);
		stmtInsertPoi.bindDouble(6, 8.546249);
		stmtInsertPoi.bindString(7, "http://www.opernhaus.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/opernhaus/DSC_5109.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/opernhaus/DSC_4830.jpg");
		stmtInsertPoi.bindString(11, "Falkenstrasse 1\n8008 Z�rich");
		poiIds.put("Opernhaus", stmtInsertPoi.executeInsert());
		
		// Landesmuseum Z�rich
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Landesmuseum Z�rich");
		stmtInsertPoi.bindString(4, "Gustav Gull hat das Schweizerische Landesmuseum 1898 zum 50-j�hrigen Bestehen der Ersten Bundesverfassung erbaut. Das Landesmuseum beherbergt die gr�sste kulturgeschichtliche Sammlung des Landes. Der �ber 100j�hrige Museumsbau erinnert an ein M�rchenschloss. Von T�rmen ums�umt bietet der Innenhof eine einzigartige Erlebnisarena. Hinter den Gem�uern wird die Vergangenheit lebendig: Wie lebten, dachten und f�hlten die vorangegangenen Generationen? Ihre materielle Hinterlassenschaft - vom Kunsthandwerk bis zum unscheinbaren Alltagsgegenstand - gibt Antworten: von der Urgeschichte bis zur Gegenwart. Im Rahmen von Sonderausstellungen greift das Landesmuseum ausserdem gesellschaftlich relevante Themen auf und bietet damit tempor�re Perspektivenwechsel.");
		stmtInsertPoi.bindDouble(5, 47.379024);
		stmtInsertPoi.bindDouble(6, 8.541004);
		stmtInsertPoi.bindString(7, "http://www.landesmuseum.ch");
		stmtInsertPoi.bindString(8, "Dienstag bis Sonntag: 10:00 - 17:00 Uhr\nDonnerstag: 10:00 - 19:00 Uhr");
		stmtInsertPoi.bindString(9, "http://images.gadmin.st.s3.amazonaws.com/n20507/images/zuerich/detail_breit/web_landesmuseum-1.jpg");
		stmtInsertPoi.bindString(10, "");
		stmtInsertPoi.bindString(11, "Museumstrasse 2\n8001 Z�rich");
		poiIds.put("Landesmuseum Z�rich", stmtInsertPoi.executeInsert());
		
		// Kunsthaus
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Kunsthaus");
		stmtInsertPoi.bindString(4, "Das Kunsthaus Z�rich beherbergt die bedeutendste Modernesammlung Z�richs und ist sowohl f�r seine permanente Kunstsammlung als auch f�r die tempor�ren Ausstellungen bekannt. Neben Werken Alberto Giacomettis sind erlesene mittelalterliche Skulpturen und Tafelbilder, Gem�lde des niederl�ndischen und italienischen Barock sowie H�hepunkte der Schweizer Malerei des 19. und 20. Jahrhunderts wie Johann Heinrich F�ssli oder Ferdinand Hodler zu finden. Auch Z�rcher Konkrete und zeitgen�ssische Schweizer K�nstler wie Pipilotti Rist und Peter Fischli / David Weiss sowie Fotografie und Installationen sind vertreten.\nZu den internationalen Schwerpunkten geh�ren die gr�sste Munch-Sammlung ausserhalb Norwegens, wichtige Bilder von Picasso, die Expressonisten Kokoschka, Beckmann und Corinth sowie bedeutende Werke von Monet und Chagall. Neben der fast schon klassisch zu nennenden Popart werden u.a. auch Werke von Rothko, Merz, Twombly, Beuys, Bacon und Baselitz pr�sentiert. Audioguide in D/E/F/I erh�ltlich.");
		stmtInsertPoi.bindDouble(5, 47.370155);
		stmtInsertPoi.bindDouble(6, 8.548460);
		stmtInsertPoi.bindString(7, "http://www.kunsthaus.ch");
		stmtInsertPoi.bindString(8, "Mittwoch bis Freitag: 10:00 - 20:00 Uhr\nSamstag, Sonntag, Dienstag: 10:00 - 18:00 Uhr");
		stmtInsertPoi.bindString(9, "http://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Kunsthaus_Z%C3%BCrich.jpg/791px-Kunsthaus_Z%C3%BCrich.jpg");
		stmtInsertPoi.bindString(10, "http://images.gadmin.st.s3.amazonaws.com/n20507/images/zuerich/detail_breit/web_highlights_kunsthaus-zurich-1.jpg");
		stmtInsertPoi.bindString(11, "Heimplatz 1\n8001 Z�rich");
		poiIds.put("Kunsthaus", stmtInsertPoi.executeInsert());
		
		// Tonhalle Z�rich
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Tonhalle Z�rich");
		stmtInsertPoi.bindString(4, "Die Tonhalle Z�rich z�hlt bez�glich Akustik weltweit zu den besten Konzerts�len.\n\nDer ber�hmte Tonhalle-Saal wurde 1895 erbaut und im Beisein von Johannes Brahms eingeweiht. Er bietet Platz f�r 1455 Konzertbesucher.\n\nDas Tonhalle-Orchester Z�rich hat sich in den letzten Jahren zu einem f�hrenden Klangk�rper Europas entwickelt. Gut hundert hoch motivierte Musikerinnen und Musiker und eine engagierte Dirigenten-Pers�nlichkeit (David Zinman, seit 1995) pr�sentieren dem Publikum rund hundert Konzerte pro Saison mit etwa f�nfzig verschiedenen Programmen.");
		stmtInsertPoi.bindDouble(5, 47.366091);
		stmtInsertPoi.bindDouble(6, 8.537969);
		stmtInsertPoi.bindString(7, "http://www.tonhalle.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/kongresshaus/ZH-KH-17.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/kongresshaus/ZH-KH-09.jpg");
		stmtInsertPoi.bindString(11, "Claridenstrasse 7\n8002 Z�rich");
		poiIds.put("Tonhalle Z�rich", stmtInsertPoi.executeInsert());
		
		// Schauspielhaus: Pfauen
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Schauspielhaus: Pfauen");
		stmtInsertPoi.bindString(4, "Der \"Pfauen\" ist mit 750 Zuschauerpl�tzen die gr�sste und �lteste B�hne des Schauspielhauses. W�hrend den Kriegsjahren erlebte das Theater einen Aufschwung und avancierte zum Theater mit explizit antifaschistischer Stossrichtung und einem kritischen Spielplan. Viele Emigranten und SchauspielerInnen, ausdr�ckliche Gegner des Nationalsozialismus, wurden ins Ensemble aufgenommen.\n\nHeute lockt das Schauspielhaus mit einem abwechslungsreichen, spannenden Programm und 20 bis 30 Neuinszenzierungen pro Jahr.");
		stmtInsertPoi.bindDouble(5, 47.370265);
		stmtInsertPoi.bindDouble(6, 8.549244);
		stmtInsertPoi.bindString(7, "http://www.schauspielhaus.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Schauspielhaus_Z%C3%BCrich.jpg/800px-Schauspielhaus_Z%C3%BCrich.jpg");
		stmtInsertPoi.bindString(10, "http://images.gadmin.st.s3.amazonaws.com/n20507/images/zuerich/detail_breit/web_schauspielhaus_pfauen-1.jpg");
		stmtInsertPoi.bindString(11, "R�mistrasse 34\n8032 Z�rich");
		poiIds.put("Schauspielhaus: Pfauen", stmtInsertPoi.executeInsert());
		
		// Museum f�r Gestaltung Z�rich
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Museum f�r Gestaltung Z�rich");
		stmtInsertPoi.bindString(4, "Design und visuelle Kommunikation, Umweltgestaltung und Kunst, Architektur und Alltagskultur, Fotografie und Medien: Das sind die Themen, mit denen sich das Museum f�r Gestaltung besch�ftigt. Wechselausstellungen bilden das Programm und zahlreiche Veranstaltungen setzen Akzente.\n\nAls Partner der Z�rcher Hochschule der K�nste ist das Museum auch offen f�r Belange k�nstlerischer Ausbildung. Es f�hrt vier bedeutende Sammlungen (Design, Grafik, Kunstgewerbe, Plakate) sowie eine �ffentlich zug�ngliche Fachbibliothek.");
		stmtInsertPoi.bindDouble(5, 47.382870);
		stmtInsertPoi.bindDouble(6, 8.535693);
		stmtInsertPoi.bindString(7, "http://www.museum-gestaltung.ch");
		stmtInsertPoi.bindString(8, "Dienstag bis Sonntag: 10:00 � 17:00 Uhr\nMittwoch: 10:00 � 20:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.museum-gestaltung.ch/typo3temp/pics/2b7971ce99.jpg");
		stmtInsertPoi.bindString(10, "http://www.museum-gestaltung.ch/typo3temp/pics/8b390f5aac.jpg");
		stmtInsertPoi.bindString(11, "Ausstellungsstrasse 60\n8005 Z�rich");
		poiIds.put("Museum f�r Gestaltung Z�rich", stmtInsertPoi.executeInsert());
		
		// Museum Rietberg
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Museum Rietberg");
		stmtInsertPoi.bindString(4, "Das Museum Rietberg Z�rich ist das einzige Kunstmuseum f�r aussereurop�ische Kulturen in der Schweiz und besitzt eine international renommierte Sammlung mit Werken aus Asien, Afrika, Amerika und Ozeanien.\n\nMit der Pr�sentation von Kunstwerken will das Museum nicht nur die faszinierende Vielfalt k�nstlerischer Ausdrucksformen bewusst machen, sondern auch Verst�ndnis und Interesse f�r fremde Kulturen, Weltanschauungen und Religionen wecken.\n\nDer architektonisch spektakul�re �Smaragd�, wie der neue Museumsbau genannt wird, besteht aus ei-nem Glaspavillon und f�gt sich perfekt in das bestehende Villen-Ensemble im sch�nen Rieterpark ein.");
		stmtInsertPoi.bindDouble(5, 47.358959);
		stmtInsertPoi.bindDouble(6, 8.530237);
		stmtInsertPoi.bindString(7, "http://www.rietberg.ch");
		stmtInsertPoi.bindString(8, "Dienstag bis Sonntag: 10:00 � 17:00 Uhr\nMittwoch und Donnerstag: 10:00 � 20:00 Uhr");
		stmtInsertPoi.bindString(9, "http://images.gadmin.st.s3.amazonaws.com/n20507/images/zuerich/detail_breit/web_museum_rietberg-zurich-1.jpg");
		stmtInsertPoi.bindString(10, "http://www.stadt-zuerich.ch/content/kultur/de/index/institutionen/museum_rietberg/sammlung/_jcr_content/mainparsys/198_1227876735414/image3.640.480.jpg");
		stmtInsertPoi.bindString(11, "Gablerstrasse 15\n8002 Z�rich");
		poiIds.put("Museum Rietberg", stmtInsertPoi.executeInsert());
		
		// Museum Bellerive
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Museum Bellerive");
		stmtInsertPoi.bindString(4, "Dreimal j�hrlich pr�sentiert das Museum Bellerive in Wechselausstellungen, was internationale K�nstlerinnen und K�nstler geschaffen haben im weiten Feld zwischen Spiel, Dekoration und Gebrauch.\n\nWilliam Morris, Emile Gall�, Diego Giacometti und Sonia Delaunay waren hier schon zu Gast; offen sind die R�ume aber auch f�r Mode, Design und Lifestyle der Gegenwart. Ihren Platz im Ausstellungsprogramm haben zudem ausgew�hlte Objekte aus den reichen Best�nden der Kunstgewerblichen Sammlung. ");
		stmtInsertPoi.bindDouble(5, 47.356273);
		stmtInsertPoi.bindDouble(6, 8.550295);
		stmtInsertPoi.bindString(7, "http://www.museum-bellerive.ch");
		stmtInsertPoi.bindString(8, "Dienstag bis Sonntag: 10:00 � 17:00 Uhr");
		stmtInsertPoi.bindString(9, "http://images.gadmin.st.s3.amazonaws.com/n20507/images/zuerich/detail_breit/web_museum_bellerive-1.jpg");
		stmtInsertPoi.bindString(10, "http://www.museum-bellerive.ch/typo3temp/pics/8bbad1cc6f.jpg");
		stmtInsertPoi.bindString(11, "H�schgasse 3\n8008 Z�rich");
		poiIds.put("Museum Bellerive", stmtInsertPoi.executeInsert());
		
		//--------------------
		
		// Bahnhofstrasse
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Bahnhofstrasse");
		stmtInsertPoi.bindString(4, "Die 1,4 km lange Bahnhofstrasse ist die schickste Einkaufsstrasse Z�richs und eine der sch�nsten Europas. Zahlreiche elegante Modegesch�fte, Warenh�user und Boutiquen sowie Luxushotels s�umen die ber�hmteste Bahnhofstrasse der Welt. Einst befand sich hier die Befestigungslinie der Z�rcher Stadtbefestigung. Ab 1864 wurde der Fr�schengraben zugesch�ttet und durch einen Boulevard ersetzt, der Paradeplatz und Hauptbahnhof verbindet. Sehenswert sind unter anderem der Hauptbahnhof, die Max-Bill-Skulptur und die Banken am Paradeplatz.");
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
		stmtInsertPoi.bindString(4, "Das Herzst�ck der Bahnhofstrasse ist der Paradeplatz. Es ist eine der teuersten Lagen der Stadt. Der Paradeplatz steht f�r Banken und Wohlstand. Credit Suisse und UBS, die beiden Schweizer Grossbanken, haben ihren Sitz in der Edelgegend. Einst war der Platz als Schweinemarkt bekannt. Sp�ter wurde er Neumarkt genannt, bis er 1865 in Paradeplatz umbenannt wurde. 1838 wurde hier das erste Fremdenhotel der Stadt, das heutige Savoy Baur en Ville, er�ffnet.");
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
		stmtInsertPoi.bindString(4, "Jelmoli - The House of Brands, in 5 Minuten zu Fuss vom Hauptbahnhof, ist das gr�sste Shop-in-Shop Warenhaus der Schweiz. Das Warenhaus wurde nach Vorbildern aus Paris und London 1899 erstellt und geh�rt heute zu den besten Adressen in Z�rich. Lebensmittel und Delikatessen findet man im Untergeschoss des Jelmoli-Warenhaus (Gourmet Factory). Die meisten der Produkte kann man auch an einer der zahlreichen kleineren Bars probieren.");
		stmtInsertPoi.bindDouble(5, 47.374243);
		stmtInsertPoi.bindDouble(6, 8.537661);
		stmtInsertPoi.bindString(7, "http://www.jelmoli.ch");
		stmtInsertPoi.bindString(8, "Montag bis Samstag: 09:00 - 20:00 Uhr");
		stmtInsertPoi.bindString(9, "http://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Jelmoli.JPG/800px-Jelmoli.JPG");
		stmtInsertPoi.bindString(10, "http://www.jelmoli.ch/media/17327/hdr_oeffnungszeiten.jpg");
		stmtInsertPoi.bindString(11, "Seidengasse 1\n8001 Z�rich");
		poiIds.put("Jelmoli", stmtInsertPoi.executeInsert());
		
		// Globus
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Globus");
		stmtInsertPoi.bindString(4, "Schickes Warenhaus, besonders die Delikatessenabteilung im Untergeschoss l�sst dem Gourmet das Wasser im Munde zusammenlaufen. Das im Jahr 1907 gegr�ndete Unternehmen geh�rt seit 1997 zum Migros-Konzern.");
		stmtInsertPoi.bindDouble(5, 47.375868);
		stmtInsertPoi.bindDouble(6, 8.537985);
		stmtInsertPoi.bindString(7, "http://www.globus.ch");
		stmtInsertPoi.bindString(8, "Montag bis Samstag: 09:00 - 20:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.globus.ch//locations/zuerich-bahnhofstrasse/large/large.jpg");
		stmtInsertPoi.bindString(10, "http://www.globus.ch//elements/0ce09873-2fb9-4233-8f48-a8f7fbf854db//image/image.jpg");
		stmtInsertPoi.bindString(11, "Schweizergasse 11\n8001 Z�rich");
		poiIds.put("Globus", stmtInsertPoi.executeInsert());
		
		// Schweizer Heimatwerk
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Schweizer Heimatwerk");
		stmtInsertPoi.bindString(4, "Das 1930 gegr�ndete Schweizer Heimatwerk hat sich zu einem bestandenen Label entwickelt. Es steht f�r Schweizer Kunsthandwerk von h�chster Qualit�t, Funktionalit�t und ausgezeichnetem Design. Die Vielfalt der Produkte bewegt sich zwischen helvetischer Tradition und heutigen Trends. Vom Holzkreiseln �ber Glasschalen bis zu Schmuck � die abwechslungsreichen und erlesenen Kunstwerke �Made in Switzerland� machen das Angebot des Heimatwerks einmalig auf der Welt. G�ste wie Einheimische sch�tzen diese auserlesenen Geschenkideen aus der Schweiz.");
		stmtInsertPoi.bindDouble(5, 47.374340);
		stmtInsertPoi.bindDouble(6, 8.541662);
		stmtInsertPoi.bindString(7, "http://www.heimatwerk.ch/");
		stmtInsertPoi.bindString(8, "Montag bis Freitag: 09:00 - 20:00 Uhr\nSamstag: 09:00 - 18:00 Uhr");
		stmtInsertPoi.bindString(9, "http://farm3.static.flickr.com/2773/4515594428_d52351a9b5.jpg");
		stmtInsertPoi.bindString(10, "http://images.gadmin.st.s3.amazonaws.com/n20506/images/zuerich/detail_breit/web-heimatwerk11-1.png");
		stmtInsertPoi.bindString(11, "Uraniastrasse 1\n8001 Z�rich");
		poiIds.put("Schweizer Heimatwerk", stmtInsertPoi.executeInsert());
		
		// Sihlcity
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Sihlcity");
		stmtInsertPoi.bindString(4, "Das Einkaufs- und Freizeitzentrum Sihlcity bietet Shopping in 80 Gesch�ften in einer einzigartigen architektonischen Kulisse von Tradition und Innovation, kombiniert mit diversen Restaurants, Bars und Caf�s, Kinos, Nachtklub, Wellnesszentrum Asia Spa sowie dem Hotel FourPoints by Sheraton Sihlcity.");
		stmtInsertPoi.bindDouble(5, 47.358026);
		stmtInsertPoi.bindDouble(6, 8.523268);
		stmtInsertPoi.bindString(7, "http://www.sihlcity.ch");
		stmtInsertPoi.bindString(8, "Gesch�fte: Montag bis Samstag: 09:00 - 20:00 Uhr\nMall: T�glich: 08:00 - 24:00 Uhr");
		stmtInsertPoi.bindString(9, "http://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Z%C3%BCrich_-_Sihlcity_IMG_0911.JPG/799px-Z%C3%BCrich_-_Sihlcity_IMG_0911.JPG");
		stmtInsertPoi.bindString(10, "http://upload.wikimedia.org/wikipedia/commons/thumb/7/7b/Sihlcity.jpg/435px-Sihlcity.jpg");
		stmtInsertPoi.bindString(11, "Kalanderplatz 1\n8045 Z�rich");
		poiIds.put("Sihlcity", stmtInsertPoi.executeInsert());
		
		// Spr�ngli
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Spr�ngli");
		stmtInsertPoi.bindString(4, "Die Schweiz geniesst weltweiten Ruhm f�r die beste Schokolade, ein Image, das sie nicht zuletzt auch der Confiserie Spr�ngli in Z�rich verdankt. Noch heute werden die weltbekannten Spr�ngli-Spezialit�ten wie zum Beispiel die Luxemburgerlis, Pralin�s und Truffes sowie die anderen beliebten Confiserie-Spezialit�ten, nach traditionellen Rezepten hergestellt.");
		stmtInsertPoi.bindDouble(5, 47.369390);
		stmtInsertPoi.bindDouble(6, 8.539136);
		stmtInsertPoi.bindString(7, "http://www.confiserie-spruengli.ch");
		stmtInsertPoi.bindString(8, "Montag bis Freitag: 07:30 - 18:30 Uhr\nSamstag: 08:00 - 17:00 Uhr");
		stmtInsertPoi.bindString(9, "http://photos.igougo.com/images/p171608-Zurich-Confiserie_Sprngli_Bahnhofstrasse_Zrich.jpg");
		stmtInsertPoi.bindString(10, "http://kontakt.spruengli.ch/diashow/bilder/005.jpg");
		stmtInsertPoi.bindString(11, "Bahnhofstrasse 21\n8001 Z�rich");
		poiIds.put("Spr�ngli", stmtInsertPoi.executeInsert());
		
		// Im Viadukt
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Im Viadukt");
		stmtInsertPoi.bindString(4, "Unter den Bogen des 1894 erbauten Eisenbahnviadukts in Z�rich West ist wieder Leben eingekehrt. Ein urbaner Treffpunkt von 500 Meter L�nge l�dt zum Flanieren, Einkaufen, Vergn�gen, Essen und Trinken ein. In 36 Viaduktb�gen ist ein Shopping-Paradies entstanden: Ein bunter Mix mit Delikatessenl�den, Ateliers Galerien, Sport- und Modelabels pr�sentieren sich dem Besucher. Das Herz der Anlage ist die Markthalle, in der zwanzig Bauern und Lebensmittelh�ndler aus der Umgebung ihre Ware anbieten.");
		stmtInsertPoi.bindDouble(5, 47.388342);
		stmtInsertPoi.bindDouble(6, 8.525974);
		stmtInsertPoi.bindString(7, "http://www.im-viadukt.ch");
		stmtInsertPoi.bindString(8, "Markthalle: Montag bis Samstag: 08:00 - 20:00 Uhr");
		stmtInsertPoi.bindString(9, "http://markthalle.im-viadukt.ch/uploads/assets/pola/88c11593e1f0080b1e0733f714ab9df34d12d5e0.jpg");
		stmtInsertPoi.bindString(10, "http://markthalle.im-viadukt.ch/uploads/assets/pola/43bbcf7012f3f2fb3b796ed21cba1b3151d841ac.jpg");
		stmtInsertPoi.bindString(11, "Limmatstrasse 259\n8005 Z�rich");
		poiIds.put("Im Viadukt", stmtInsertPoi.executeInsert());
		
		//--------------------
		
		// Hauptbahnhof
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehensw�rdigkeiten"));
		stmtInsertPoi.bindString(3, "Hauptbahnhof");
		stmtInsertPoi.bindString(4, "Wichtigstes Einfallstor f�r Reisende ist der Hauptbahnhof. Im prunkvollen Neorenaissance-Bau aus Sandstein befindet sich der heutige Bahnhof von Z�rich. Sein Haupteingang, ein Triumphbogen am Ende der Bahnhofstrasse, wirkt wie ein Tor zu einer damals neu erschlossenen Welt. Erbaut wurde der Bahnhof 1871. Unterirdisch werden heute zwei S-Bahnh�fe mit der Haupthalle verbunden. Hier befinden sich Ladenpassagen.");
		stmtInsertPoi.bindDouble(5, 47.378174);
		stmtInsertPoi.bindDouble(6, 8.540191);
		stmtInsertPoi.bindString(7, "http://www.railcity.ch/index_zuerich.htm");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/hauptbahnhof/ZH-HB-16.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/hauptbahnhof/ZH-HB-04.jpg");
		stmtInsertPoi.bindString(11, "Bahnhofplatz\n8001 Z�rich");
		poiIds.put("Hauptbahnhof", stmtInsertPoi.executeInsert());
		
		// ETH Z�rich
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehensw�rdigkeiten"));
		stmtInsertPoi.bindString(3, "ETH Z�rich");
		stmtInsertPoi.bindString(4, "Die kurz ETH genannte Eidgen�ssische Technische Hochschule z�hlt zu den renomiertesten Hochschulen der Welt mit naturwissenschaftlich-technischem Schwerpunkt. Hier forschten, lehrten und lernten kluge K�pfe wie Albert Einstein und Wolfgang Pauli.");
		stmtInsertPoi.bindDouble(5, 47.376441);
		stmtInsertPoi.bindDouble(6, 8.547991);
		stmtInsertPoi.bindString(7, "http://www.ethz.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/ETH_geradlinig_1.jpg/800px-ETH_geradlinig_1.jpg");
		stmtInsertPoi.bindString(10, "http://www.ethz.ch/media/pictures/zentrum/eth_zentrum_luftaufnahme1_thumb.jpg");
		stmtInsertPoi.bindString(11, "R�mistrasse 101\n8092 Z�rich");
		poiIds.put("ETH Z�rich", stmtInsertPoi.executeInsert());
		
		// Fraum�nster
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehensw�rdigkeiten"));
		stmtInsertPoi.bindString(3, "Fraum�nster");
		stmtInsertPoi.bindString(4, "Die Kirche mit Frauenkloster wurde 853 von K�nig Ludwig dem Deutschen gestifet und von Frauen des europ�ischen Hochadels bewohnt. Das Kloster genoss die Gunst von K�nigen und die �btissin hatte das M�nzrecht von Z�rich bis ins 13. Jh. Nach der Reformation kamen Kirche und Kloster in den Besitz der Stadt.\nBedeutende Bauteile sind der romanische Chor und das hochgew�lbte Querschiff. Das Langhaus wurde 1911 letztmals umgebaut, nachdem schon im 18.Jh. der Nordturm erh�ht und der S�dturm abgetragen worden war.\nBedeutendster Schmuck neben der gr�ssten Orgel im Kanton Z�rich mit 5793 Pfeifen sind seine Farbfenster: die Nordfenster im Querschiff (1945) sind gefertigt von Augusto Giacometti, Alberto Giacomettis Onkel. Der f�nfteilige Fensterzyklus im Chor (1970) und die Rosette im s�dlichen Querschiff (1978) sind Werke Marc Chagalls. Im Kreuzgang befindet sich ein Freskenzyklus von Paul Bodmer zur Gr�ndung des Fraum�nsters. Zu den Hauptattraktionen des M�nsters z�hlen heute die Glasfenster von Chagall und Giacometti.");
		stmtInsertPoi.bindDouble(5, 47.369790);
		stmtInsertPoi.bindDouble(6, 8.541532);
		stmtInsertPoi.bindString(7, "http://www.fraumuenster.ch");
		stmtInsertPoi.bindString(8, "November - M�rz 10:00 - 16:00 Uhr\nApril - Oktober 10:00 - 18:00 Uhr");
		stmtInsertPoi.bindString(9, "http://mw2.google.com/mw-panoramio/photos/medium/9725570.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/kirchen/fraumuenster3.jpg");
		stmtInsertPoi.bindString(11, "M�nsterhof\n8001 Z�rich");
		poiIds.put("Fraum�nster", stmtInsertPoi.executeInsert());
		
		// Grossm�nster
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehensw�rdigkeiten"));
		stmtInsertPoi.bindString(3, "Grossm�nster");
		stmtInsertPoi.bindString(4, "In der Altstadt befindet sich das Wahrzeichen Z�richs: das Grossm�nster mit seinen Doppelt�rmen. Der Name stammt erst aus dem 14. Jahrhundert. Zuvor wurde die Kirche in Urkunden lediglich als \"Z�rcher Kirche\" bezeichnet. Der Innenraum des Grossm�nsters ist sehr schlicht gehalten. Er enth�lt nur eine Kanzel und einen Taufstein. Im Chor sind drei farbige Fenster von Augusto Giacometti zu sehen, welche die Weihnachtsgeschichte erz�hlen. Weiterhin sehenswert ist die Krypta, der �lteste Teil der Kirche, wo stark verblasste Wandmalereien aus dem 14./15. Jahrhundert zu sehen sind.");
		stmtInsertPoi.bindDouble(5, 47.370000);
		stmtInsertPoi.bindDouble(6, 8.544167);
		stmtInsertPoi.bindString(7, "http://www.grossmuenster.ch");
		stmtInsertPoi.bindString(8, "M�rz � Oktober 10:00 - 18:00 Uhr\nNovember � Februar 10:00 - 17:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.grossmuenster.ch/typo3temp/pics/c512687fc9.jpg");
		stmtInsertPoi.bindString(10, "http://www.grossmuenster.ch/fileadmin/templates/images/tour/08_kln.jpg");
		stmtInsertPoi.bindString(11, "Zwingliplatz\n8000 Z�rich");
		poiIds.put("Grossm�nster", stmtInsertPoi.executeInsert());

		// Lindenhof
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehensw�rdigkeiten"));
		stmtInsertPoi.bindString(3, "Lindenhof");
		stmtInsertPoi.bindString(4, "Hier geniesst man eine herrliche Aussicht auf die Altstadt, das Grossm�nster, das Rathaus, die Limmat, die Universit�t und die Eidgen�ssische Technische Hochschule. Zudem ist der Lindenhof Schauplatz zahlreicher geschichtlicher Ereignisse.\nIm 4. Jahrhundert stand auf dem Lindenhof ein r�misches Kastell. Es diente der r�mischen Besatzung und der Bev�lkerung zum Schutz vor den Angriffen der Alemannen.\nIm 9. Jahrhundert baute der Enkel von Karl dem Grossen an dieser Stelle eine Pfalz, eine k�nigliche Residenz.\nIm 13. Jahrhundert zogen die Z�rcher gegen Winterthur in den Krieg. Dabei fielen so viele M�nner, dass Z�rich keine Krieger mehr hatte. Herzog, Stadtherr von Winterthur, versuchte Z�rich einzunehmen und stationierte ein Heer vor die Stadtmauern. Die Z�rcherinnen verkleideten sich daraufhin als Krieger und zogen mit langen Spiessen auf den erh�hten Lindenhof. Die Belagerer glaubten, ein starkes Heer sei zur Verst�rkung nach Z�rich gekommen und so gab Albrecht I. von Habsburg die Belagerung auf. Die Brunnenfigur auf dem Lindenhof zeigt die heldenhaften Z�rcherinnen.\nBis in die fr�he Neuzeit diente der Platz den Z�rchern zu Versammlungen. 1798 wurde auf dem Lindenhof zum Beispiel der Eid auf die helvetische Verfassung geschworen.\nHeute ist der Lindenhof eine Oase der Ruhe und Erholung inmitten der Stadt und Treffpunkt passionierter Schachspieler.");
		stmtInsertPoi.bindDouble(5, 47.373005);
		stmtInsertPoi.bindDouble(6, 8.541307);
		stmtInsertPoi.bindNull(7);
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/lindenhof/DSC_3258.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/lindenhof/lindenhof-limmatquai.jpg");
		stmtInsertPoi.bindNull(11);
		poiIds.put("Lindenhof", stmtInsertPoi.executeInsert());
		
		// Niederdorf
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehensw�rdigkeiten"));
		stmtInsertPoi.bindString(3, "Niederdorf");
		stmtInsertPoi.bindString(4, "Tags�ber laden die Fussg�ngerzone und viele, in kleinen G�sschen versteckte L�den zum \"shoppen\" ein. Abends verwandelt sich das Niederdorf mit seinen Bars, Beizen und Strassenk�nstlern zum Vergn�gungsviertel f�r ein bunt gemischtes Publikum.");
		stmtInsertPoi.bindDouble(5, 47.373241);
		stmtInsertPoi.bindDouble(6, 8.543742);
		stmtInsertPoi.bindString(7, "http://www.doerfli.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://images.gadmin.st.s3.amazonaws.com/n20505/images/zuerich/detail_breit/detailboard_niederdorf_7573-1.jpg");
		stmtInsertPoi.bindString(10, "http://upload.wikimedia.org/wikipedia/commons/thumb/1/13/Z%C3%BCrich_-_Niederdorf_-_St%C3%BCssihofstatt_-_zur_Schnidern_IMG_1375.jpg/658px-Z%C3%BCrich_-_Niederdorf_-_St%C3%BCssihofstatt_-_zur_Schnidern_IMG_1375.jpg");
		stmtInsertPoi.bindNull(11);
		poiIds.put("Niederdorf", stmtInsertPoi.executeInsert());
		
		// St. Peter
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehensw�rdigkeiten"));
		stmtInsertPoi.bindString(3, "St. Peter");
		stmtInsertPoi.bindString(4, "Die Kirche St. Peter ist mit dem gr�ssten Zifferblatt Europas best�ckt: Der Durchmesser betr�gt 8,7 Meter. Im Turm befinden sich f�nf Glocken von 1880 - die gr�sste wiegt ohne Kl�ppel �ber 6000 kg.\nVom Mittelalter an bis 1911 diente der Kirchturm der Brandwache.\n\nDie Kirche St. Peter ist die �lteste Pfarrkirche Z�richs und reicht in ihren Anf�ngen in die Zeit vor 900.\nDer erste B�rgermeister Rudolf Brun, 1360 im Chor der Kirche begraben, erwirbt 1345 die Kirche mit allen Pflichten und Rechten (Kirchensatz). Heute befindet sich sein Grab und Denkmal an der Aussenmauer des Turms. Der erste reformierter Pfarrer war Leo Jud (1523-1542), ein Freund Zwinglis und Mitarbeiter an der ersten Z�rcher Bibel�bersetzung. 1778-1801 wirkte der Pfarrer J.C. Lavater (Schriftsteller, Physiognomiker; Freund des jungen Goethe) in dieser Kirche. Sein Grabstein befindet sich an der Kirchenmauer, ein Denkmal im Chor der Kirche.\n\nBauelemente\nTurm: unterer Teil sp�tromanisch, oben gotisch\nChor: von etwa 1230\nFreskenfragmente 1300 bis 1500\nSchiff: barock");
		stmtInsertPoi.bindDouble(5, 47.371061);
		stmtInsertPoi.bindDouble(6, 8.540781);
		stmtInsertPoi.bindString(7, "http://www.st-peter-zh.ch");
		stmtInsertPoi.bindString(8, "Montag - Freitag: 8:00 - 18:00 Uhr\nSamstag: 10:00 - 16:00 Uhr\nSonntag: ca. 11:00 - 17:00 Uhr");
		stmtInsertPoi.bindString(9, "http://de.academic.ru/pictures/dewiki/115/st_peter_zuerich.jpg");
		stmtInsertPoi.bindString(10, "http://images.gadmin.st.s3.amazonaws.com/n20505/images/zuerich/detail_breit/detailboard_stpeterkirche_8086-1.jpg");
		stmtInsertPoi.bindString(11, "Schl�ssel-Gasse\n8000 Z�rich");
		poiIds.put("St. Peter", stmtInsertPoi.executeInsert());
		
		// Zoo Z�rich
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehensw�rdigkeiten"));
		stmtInsertPoi.bindString(3, "Zoo Z�rich");
		stmtInsertPoi.bindString(4, "Auf dem Z�richberg liegt der Zoologische Garten mit einer Regenwaldhalle, die in Europa einmalig ist. Er�ffnet wurde er im Jahre 1929. Bekannt ist der Zoo unter anderem f�r die artgerechte Haltung der Tiere. Auf 15 Hektar leben rund 2000 Tiere und 280 verschiedene Arten aus sechs Kontinenten. Zu den Hauptattraktionen des Zoos z�hlen unter anderem der s�damerikanische Bergnebelwald, in dem Brillen- und Nasenb�ren zu Hause sind, oder die Himalaya-Anlage, in der Sibirische Tiger, Mongolische W�lfe, Schneeleoparden und Kleine Pandas leben. Selbstverst�ndlich z�hlt auch die Masoala Regenwald-Halle zu einer der Hauptattraktion. Der madagassische Masoala-Regenwald wird auf gut einem Hektar abgebildet und bietet Lemuren, Makis, Cham�leons, Flughunden, Aldabra-Riesenschildkr�ten, Geckos, Fr�schen, Echsen und Insekten Unterschlupf. 2006 wurde eine L�wenanlage f�r Asiatische L�wen er�ffnet. Afrikahaus, Menschenaffenhaus und Schildkr�tenhaus sind nur ein paar der weiteren Attraktionen. Abgerundet wird das Angebot durch Zoolino, wo die kleinen Besucher auf dem Gel�nde eines alten Bauernhofes Kontakt zu Schweinen, G�nsen, H�hnern, Ziegen oder Meerschweinchen aufnehmen k�nnen, und die Naturwerkstatt, in der Stadtkinder die Tiere im Siedlungsraum n�her gebracht werden. Bis 2020 sollen weitere Projekte wie die asiatische W�ste, die Savanne und ein s�damerikanischer und afrikanischer Regenwald realisiert werden. Im ganzen Park stehen Besuchern B�nke und Picknick-Tische zur Verf�gung. Auch f�r die Kinder gibt es eine grosse Anzahl von Spielm�glichkeiten.");
		stmtInsertPoi.bindDouble(5, 47.384535);
		stmtInsertPoi.bindDouble(6, 8.574758);
		stmtInsertPoi.bindString(7, "http://www.zoo.ch");
		stmtInsertPoi.bindString(8, "M�rz � Oktober 09:00 � 18:00 Uhr\nNovember � Februar 09:00 � 17:00 Uhr");
		stmtInsertPoi.bindString(9, "http://upload.wikimedia.org/wikipedia/commons/thumb/9/94/ZooZ%C3%BCrich_Eingang.jpg/800px-ZooZ%C3%BCrich_Eingang.jpg");
		stmtInsertPoi.bindString(10, "http://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Masoalah-halle-aussen.jpg/800px-Masoalah-halle-aussen.jpg");
		stmtInsertPoi.bindString(11, "Z�richbergstrasse 221\n8044 Z�rich");
		poiIds.put("Zoo Z�rich", stmtInsertPoi.executeInsert());
		
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
