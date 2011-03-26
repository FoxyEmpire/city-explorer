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
		
		stmtInsertCat.bindString(1, "Sehensw�rdigkeiten");
		categoryIds.put("Sehensw�rdigkeiten", stmtInsertCat.executeInsert());
		
		stmtInsertCat.bindString(1, "Kinos");
		categoryIds.put("Kinos", stmtInsertCat.executeInsert());
		
		stmtInsertCat.bindString(1, "Restaurants");
		categoryIds.put("Restaurants", stmtInsertCat.executeInsert());
		
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

		
		// Zeughauskeller
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Zeughauskeller");
		stmtInsertPoi.bindString(4, "Schweizer K�che im Grossformat: W�rste, R�sti u.a. Das Traditionslokal wird von Einheimischen wir von Touristen besucht.");
		stmtInsertPoi.bindDouble(5, 47.370328);
		stmtInsertPoi.bindDouble(6, 8.539818);
		stmtInsertPoi.bindString(7, "http://www.zeughauskeller.ch");
		stmtInsertPoi.bindString(8, "T�glich 11:30 � 23:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/zeughauskeller/DSC_6569.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/zeughauskeller/DSC_6577.jpg");
		poiIds.put("Zeughauskeller", stmtInsertPoi.executeInsert());

		// Landesmuseum Z�rich
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Landesmuseum Z�rich");
		stmtInsertPoi.bindString(4, "Gleich neben dem Hauptbahnhof scheint ein trutziges Schloss zu stehen - tats�chlich handelt es sich bei der Geb�udegruppe um das Landesmuseum, die bedeutendste Sammlung zur Schweizer Kultur und Geschichte.");
		stmtInsertPoi.bindDouble(5, 47.379024);
		stmtInsertPoi.bindDouble(6, 8.541004);
		stmtInsertPoi.bindString(7, "http://www.landesmuseum.ch");
		stmtInsertPoi.bindString(8, "Di - So 10:00 - 17:00 Uhr\nDo 10:00 - 19:00 Uhr");
		stmtInsertPoi.bindString(9, "");
		stmtInsertPoi.bindString(10, "");
		poiIds.put("Landesmuseum Z�rich", stmtInsertPoi.executeInsert());
		
		//Kunsthaus
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Kunsthaus");
		stmtInsertPoi.bindString(4, "F�r Kulturfreunde ist allein der Besuch des Kunsthaues eine Reise nach Z�rich wert. Die Wechselausstellungen sind ausgezeichnet, und das Haus verf�gt �ber eine der bedeutendsten Gem�lde- und Skulpturensammlungen Europas, darunter die umfangreichste Giacometti-Sammlung weltweit sowie die gr�sste Munch-Sammlung ausserhalb Norwegens.");
		stmtInsertPoi.bindDouble(5, 47.370113);
		stmtInsertPoi.bindDouble(6, 8.548463);
		stmtInsertPoi.bindString(7, "http://www.kunsthaus.ch");
		stmtInsertPoi.bindString(8, "");
		stmtInsertPoi.bindString(9, "");
		stmtInsertPoi.bindString(10, "");
		poiIds.put("Kunsthaus", stmtInsertPoi.executeInsert());
		
		//Bahnhofstrasse
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Bahnhofstrasse");
		stmtInsertPoi.bindString(4, "Die 1,4 km lange Bahnhofstrasse ist die schickste Einkaufsstrasse Z�richs und eine der sch�nsten Europas. Zahlreiche elegante Modegesch�fte, Warenh�user und Boutiquen sowie Luxushotels s�umen die ber�hmteste Bahnhofstrasse der Welt. Einst befand sich hier die Befestigungslinie der Z�rcher Stadtbefestigung. Ab 1864 wurde der Fr�schengraben zugesch�ttet und durch einen Boulevard ersetzt, der Paradeplatz und Hauptbahnhof verbindet. Sehenswert sind unter anderem der Hauptbahnhof, die Max-Bill-Skulptur und die Banken am Paradeplatz.");
		stmtInsertPoi.bindDouble(5, 47.376271);
		stmtInsertPoi.bindDouble(6, 8.539534);
		stmtInsertPoi.bindString(7, "http://www.bahnhofstrasse-zuerich.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "");
		stmtInsertPoi.bindString(10, "");
		poiIds.put("Bahnhofstrasse", stmtInsertPoi.executeInsert());
		
		//Paradeplatz
		stmtInsertPoi.bindLong(2, categoryIds.get("Einkaufen"));
		stmtInsertPoi.bindString(3, "Paradeplatz");
		stmtInsertPoi.bindString(4, "Das Herzst�ck der Bahnhofstrasse ist der Paradeplatz. Es ist eine der teuersten Lagen der Stadt. Der Paradeplatz steht f�r Banken und Wohlstand. Credit Suisse und UBS, die beiden Schweizer Grossbanken, haben ihren Sitz in der Edelgegend. Einst war der Platz als Schweinemarkt bekannt. Sp�ter wurde er Neumarkt genannt, bis er 1865 in Paradeplatz umbenannt wurde. 1838 wurde hier das erste Fremdenhotel der Stadt, das heutige Savoy Baur en Ville, er�ffnet.");
		stmtInsertPoi.bindDouble(5, 47.369688);
		stmtInsertPoi.bindDouble(6, 8.538885);
		stmtInsertPoi.bindString(7, "http://www.paradeplatz.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.scheuble.ch/images/zuerich-sehenswert/zuerich-paradeplatz.jpg");
		stmtInsertPoi.bindString(10, "http://www.nzz.ch/images/paradeplatz_fullSize_1.2613447.1243149950.jpg");
		poiIds.put("Paradeplatz", stmtInsertPoi.executeInsert());
		
		//Hauptbahnhof
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehensw�rdigkeiten"));
		stmtInsertPoi.bindString(3, "Hauptbahnhof");
		stmtInsertPoi.bindString(4, "Wichtigstes Einfallstor f�r Reisende ist der Hauptbahnhof. Im prunkvollen Neorenaissance-Bau aus Sandstein befindet sich der heutige Bahnhof von Z�rich. Sein Haupteingang, ein Triumphbogen am Ende der Bahnhofstrasse, wirkt wie ein Tor zu einer damals neu erschlossenen Welt. Erbaut wurde der Bahnhof 1871. Unterirdisch werden heute zwei S-Bahnh�fe mit der Haupthalle verbunden. Hier befinden sich Ladenpassagen.");
		stmtInsertPoi.bindDouble(5, 47.378174);
		stmtInsertPoi.bindDouble(6, 8.540191);
		stmtInsertPoi.bindString(7, "http://www.railcity.ch");
		stmtInsertPoi.bindString(8, "");
		stmtInsertPoi.bindString(9, "");
		stmtInsertPoi.bindString(10, "");
		poiIds.put("Hauptbahnhof", stmtInsertPoi.executeInsert());
		
		//ETH Z�rich
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehensw�rdigkeiten"));
		stmtInsertPoi.bindString(3, "ETH Z�rich");
		stmtInsertPoi.bindString(4, "Die kurz ETH genannte Eidgen�ssische Technische Hochschule z�hlt zu den renomiertesten Hochschulen der Welt mit naturwissenschaftlich-technischem Schwerpunkt. Hier forschten, lehrten und lernten kluge K�pfe wie Albert Einstein und Wolfgang Pauli.");
		stmtInsertPoi.bindDouble(5, 47.376441);
		stmtInsertPoi.bindDouble(6, 8.547991);
		stmtInsertPoi.bindString(7, "http://www.ethz.ch");
		stmtInsertPoi.bindString(8, "");
		stmtInsertPoi.bindString(9, "");
		stmtInsertPoi.bindString(10, "");
		poiIds.put("ETH Z�rich", stmtInsertPoi.executeInsert());
		
		//Fraum�nster
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehensw�rdigkeiten"));
		stmtInsertPoi.bindString(3, "Fraum�nster");
		stmtInsertPoi.bindString(4, "Die Kirche mit Frauenkloster wurde 853 von K�nig Ludwig dem Deutschen gestifet und von Frauen des europ�ischen Hochadels bewohnt. Das Kloster genoss die Gunst von K�nigen und die �btissin hatte das M�nzrecht von Z�rich bis ins 13. Jh. Nach der Reformation kamen Kirche und Kloster in den Besitz der Stadt.\nBedeutende Bauteile sind der romanische Chor und das hochgew�lbte Querschiff. Das Langhaus wurde 1911 letztmals umgebaut, nachdem schon im 18.Jh. der Nordturm erh�ht und der S�dturm abgetragen worden war.\nBedeutendster Schmuck neben der gr�ssten Orgel im Kanton Z�rich mit 5793 Pfeifen sind seine Farbfenster: die Nordfenster im Querschiff (1945) sind gefertigt von Augusto Giacometti, Alberto Giacomettis Onkel. Der f�nfteilige Fensterzyklus im Chor (1970) und die Rosette im s�dlichen Querschiff (1978) sind Werke Marc Chagalls. Im Kreuzgang befindet sich ein Freskenzyklus von Paul Bodmer zur Gr�ndung des Fraum�nsters. Zu den Hauptattraktionen des M�nsters z�hlen heute die Glasfenster von Chagall und Giacometti.");
		stmtInsertPoi.bindDouble(5, 47.372084);
		stmtInsertPoi.bindDouble(6, 8.543691);
		stmtInsertPoi.bindString(7, "http://www.fraumuenster.ch");
		stmtInsertPoi.bindString(8, "November - M�rz 10:00 - 16:00 Uhr\nApril - Oktober 10:00 - 18:00 Uhr");
		stmtInsertPoi.bindString(9, "");
		stmtInsertPoi.bindString(10, "");
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
		poiIds.put("Grossm�nster", stmtInsertPoi.executeInsert());

		// Lindenhof
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehensw�rdigkeiten"));
		stmtInsertPoi.bindString(3, "Lindenhof");
		stmtInsertPoi.bindString(4, "Hier geniesst man eine herrliche Aussicht auf die Altstadt, das Grossm�nster, das Rathaus, die Limmat, die Universit�t und die Eidgen�ssische Technische Hochschule. Zudem ist der Lindenhof Schauplatz zahlreicher geschichtlicher Ereignisse.\nIm 4. Jahrhundert stand auf dem Lindenhof ein r�misches Kastell. Es diente der r�mischen Besatzung und der Bev�lkerung zum Schutz vor den Angriffen der Alemannen.\nIm 9. Jahrhundert baute der Enkel von Karl dem Grossen an dieser Stelle eine Pfalz, eine k�nigliche Residenz.\nIm 13. Jahrhundert zogen die Z�rcher gegen Winterthur in den Krieg. Dabei fielen so viele M�nner, dass Z�rich keine Krieger mehr hatte. Herzog, Stadtherr von Winterthur, versuchte Z�rich einzunehmen und stationierte ein Heer vor die Stadtmauern. Die Z�rcherinnen verkleideten sich daraufhin als Krieger und zogen mit langen Spiessen auf den erh�hten Lindenhof. Die Belagerer glaubten, ein starkes Heer sei zur Verst�rkung nach Z�rich gekommen und so gab Albrecht I. von Habsburg die Belagerung auf. Die Brunnenfigur auf dem Lindenhof zeigt die heldenhaften Z�rcherinnen.\nBis in die fr�he Neuzeit diente der Platz den Z�rchern zu Versammlungen. 1798 wurde auf dem Lindenhof zum Beispiel der Eid auf die helvetische Verfassung geschworen.\nHeute ist der Lindenhof eine Oase der Ruhe und Erholung inmitten der Stadt und Treffpunkt passionierter Schachspieler.");
		stmtInsertPoi.bindDouble(5, 47.373005);
		stmtInsertPoi.bindDouble(6, 8.541307);
		stmtInsertPoi.bindNull(7);
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/lindenhof/lindenhof-limmatquai.jpg");
		stmtInsertPoi.bindString(10, "http://images.gadmin.st.s3.amazonaws.com/n20505/images/zuerich/detail_breit/detailboard_lindenhof_7436-1.jpg");
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
		stmtInsertPoi.bindString(10, "");
		poiIds.put("Niederdorf", stmtInsertPoi.executeInsert());
		
		//St. Peter
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehensw�rdigkeiten"));
		stmtInsertPoi.bindString(3, "St. Peter");
		stmtInsertPoi.bindString(4, "Die Kirche St. Peter ist mit dem gr�ssten Zifferblatt Europas best�ckt: Der Durchmesser betr�gt 8,7 Meter. Im Turm befinden sich f�nf Glocken von 1880 - die gr�sste wiegt ohne Kl�ppel �ber 6000 kg.\nVom Mittelalter an bis 1911 diente der Kirchturm der Brandwache.\n\nDie Kirche St. Peter ist die �lteste Pfarrkirche Z�richs und reicht in ihren Anf�ngen in die Zeit vor 900.\nDer erste B�rgermeister Rudolf Brun, 1360 im Chor der Kirche begraben, erwirbt 1345 die Kirche mit allen Pflichten und Rechten (Kirchensatz). Heute befindet sich sein Grab und Denkmal an der Aussenmauer des Turms. Der erste reformierter Pfarrer war Leo Jud (1523-1542), ein Freund Zwinglis und Mitarbeiter an der ersten Z�rcher Bibel�bersetzung. 1778-1801 wirkte der Pfarrer J.C. Lavater (Schriftsteller, Physiognomiker; Freund des jungen Goethe) in dieser Kirche. Sein Grabstein befindet sich an der Kirchenmauer, ein Denkmal im Chor der Kirche.\n\nBauelemente\nTurm: unterer Teil sp�tromanisch, oben gotisch\nChor: von etwa 1230\nFreskenfragmente 1300 bis 1500\nSchiff: barock");
		stmtInsertPoi.bindDouble(5, 47.371061);
		stmtInsertPoi.bindDouble(6, 8.540781);
		stmtInsertPoi.bindString(7, "http://www.st-peter-zh.ch");
		stmtInsertPoi.bindString(8, "Montag bis Freitag: 8.00 bis 18.00 Uhr\nSamstag: 10.00 bis 16.00 Uhr\nSonntag: Nach dem Gottesdienst um ca.11.00 Uhr bis 17.00 Uhr");
		stmtInsertPoi.bindString(9, "http://images.gadmin.st.s3.amazonaws.com/n20505/images/zuerich/detail_breit/detailboard_stpeterkirche_8086-1.jpg");
		stmtInsertPoi.bindString(10, "");
		poiIds.put("St. Peter", stmtInsertPoi.executeInsert());
		
		//Zoo Z�rich
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehensw�rdigkeiten"));
		stmtInsertPoi.bindString(3, "Zoo Z�rich");
		stmtInsertPoi.bindString(4, "Auf dem Z�richberg liegt der Zoologische Garten mit einer Regenwaldhalle, die in Europa einmalig ist. Er�ffnet wurde er im Jahre 1929. Bekannt ist der Zoo unter anderem f�r die artgerechte Haltung der Tiere. Auf 15 Hektar leben rund 2000 Tiere und 280 verschiedene Arten aus sechs Kontinenten. Zu den Hauptattraktionen des Zoos z�hlen unter anderem der s�damerikanische Bergnebelwald, in dem Brillen- und Nasenb�ren zu Hause sind, oder die Himalaya-Anlage, in der Sibirische Tiger, Mongolische W�lfe, Schneeleoparden und Kleine Pandas leben. Selbstverst�ndlich z�hlt auch die Masoala Regenwald-Halle zu einer der Hauptattraktion. Der madagassische Masoala-Regenwald wird auf gut einem Hektar abgebildet und bietet Lemuren, Makis, Cham�leons, Flughunden, Aldabra-Riesenschildkr�ten, Geckos, Fr�schen, Echsen und Insekten Unterschlupf. 2006 wurde eine L�wenanlage f�r Asiatische L�wen er�ffnet. Afrikahaus, Menschenaffenhaus und Schildkr�tenhaus sind nur ein paar der weiteren Attraktionen. Abgerundet wird das Angebot durch Zoolino, wo die kleinen Besucher auf dem Gel�nde eines alten Bauernhofes Kontakt zu Schweinen, G�nsen, H�hnern, Ziegen oder Meerschweinchen aufnehmen k�nnen, und die Naturwerkstatt, in der Stadtkinder die Tiere im Siedlungsraum n�her gebracht werden. Bis 2020 sollen weitere Projekte wie die asiatische W�ste, die Savanne und ein s�damerikanischer und afrikanischer Regenwald realisiert werden. Im ganzen Park stehen Besuchern B�nke und Picknick-Tische zur Verf�gung. Auch f�r die Kinder gibt es eine grosse Anzahl von Spielm�glichkeiten.");
		stmtInsertPoi.bindDouble(5, 47.384462);
		stmtInsertPoi.bindDouble(6, 8.574792);
		stmtInsertPoi.bindString(7, "http://www.zoo.ch");
		stmtInsertPoi.bindString(8, "M�rz � Oktober 09.00 � 18.00 Uhr\nNovember � Februar 09.00 � 17.00 Uhr");
		stmtInsertPoi.bindString(9, "");
		stmtInsertPoi.bindString(10, "");
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
