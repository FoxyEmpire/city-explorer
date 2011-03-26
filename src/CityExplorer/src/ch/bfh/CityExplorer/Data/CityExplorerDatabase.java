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
		
		stmtInsertCat.bindString(1, "Sehenswürdigkeiten");
		categoryIds.put("Sehenswürdigkeiten", stmtInsertCat.executeInsert());
		
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
		//		cityIds.get("Zürich"),
		//		categoryIds.get("Bauwerke"),
		//		"Grossmünster",
		//		"Das Wahrzeichen Zürichs beherrscht mit der mächtigen Doppelturmfassade den oberen Limmatraum",
		//		47.370000,
		//		8.544167,
		//		"http://www.grossmuenster.ch");
		
		
		// QUELLE: http://www.zuerich-reisefuehrer.de/grossmuenster.html

		
		
		stmtInsertPoi.bindLong(1, cityIds.get("Zürich"));

		
		// Zeughauskeller
		stmtInsertPoi.bindLong(2, categoryIds.get("Restaurants"));
		stmtInsertPoi.bindString(3, "Zeughauskeller");
		stmtInsertPoi.bindString(4, "Schweizer Küche im Grossformat: Würste, Rösti u.a. Das Traditionslokal wird von Einheimischen wir von Touristen besucht.");
		stmtInsertPoi.bindDouble(5, 47.370328);
		stmtInsertPoi.bindDouble(6, 8.539818);
		stmtInsertPoi.bindString(7, "http://www.zeughauskeller.ch");
		stmtInsertPoi.bindString(8, "Täglich 11:30 – 23:00 Uhr");
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/restaurant8/zeughauskeller/DSC_6569.jpg");
		stmtInsertPoi.bindString(10, "http://www.sengers.ch/izueri/restaurant8/zeughauskeller/DSC_6577.jpg");
		poiIds.put("Zeughauskeller", stmtInsertPoi.executeInsert());

		// Landesmuseum Zürich
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Landesmuseum Zürich");
		stmtInsertPoi.bindString(4, "Gleich neben dem Hauptbahnhof scheint ein trutziges Schloss zu stehen - tatsächlich handelt es sich bei der Gebäudegruppe um das Landesmuseum, die bedeutendste Sammlung zur Schweizer Kultur und Geschichte.");
		stmtInsertPoi.bindDouble(5, 47.379024);
		stmtInsertPoi.bindDouble(6, 8.541004);
		stmtInsertPoi.bindString(7, "http://www.landesmuseum.ch");
		stmtInsertPoi.bindString(8, "Di - So 10:00 - 17:00 Uhr\nDo 10:00 - 19:00 Uhr");
		stmtInsertPoi.bindString(9, "");
		stmtInsertPoi.bindString(10, "");
		poiIds.put("Landesmuseum Zürich", stmtInsertPoi.executeInsert());
		
		//Kunsthaus
		stmtInsertPoi.bindLong(2, categoryIds.get("Kultur"));
		stmtInsertPoi.bindString(3, "Kunsthaus");
		stmtInsertPoi.bindString(4, "Für Kulturfreunde ist allein der Besuch des Kunsthaues eine Reise nach Zürich wert. Die Wechselausstellungen sind ausgezeichnet, und das Haus verfügt über eine der bedeutendsten Gemälde- und Skulpturensammlungen Europas, darunter die umfangreichste Giacometti-Sammlung weltweit sowie die grösste Munch-Sammlung ausserhalb Norwegens.");
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
		stmtInsertPoi.bindString(4, "Die 1,4 km lange Bahnhofstrasse ist die schickste Einkaufsstrasse Zürichs und eine der schönsten Europas. Zahlreiche elegante Modegeschäfte, Warenhäuser und Boutiquen sowie Luxushotels säumen die berühmteste Bahnhofstrasse der Welt. Einst befand sich hier die Befestigungslinie der Zürcher Stadtbefestigung. Ab 1864 wurde der Fröschengraben zugeschüttet und durch einen Boulevard ersetzt, der Paradeplatz und Hauptbahnhof verbindet. Sehenswert sind unter anderem der Hauptbahnhof, die Max-Bill-Skulptur und die Banken am Paradeplatz.");
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
		stmtInsertPoi.bindString(4, "Das Herzstück der Bahnhofstrasse ist der Paradeplatz. Es ist eine der teuersten Lagen der Stadt. Der Paradeplatz steht für Banken und Wohlstand. Credit Suisse und UBS, die beiden Schweizer Grossbanken, haben ihren Sitz in der Edelgegend. Einst war der Platz als Schweinemarkt bekannt. Später wurde er Neumarkt genannt, bis er 1865 in Paradeplatz umbenannt wurde. 1838 wurde hier das erste Fremdenhotel der Stadt, das heutige Savoy Baur en Ville, eröffnet.");
		stmtInsertPoi.bindDouble(5, 47.369688);
		stmtInsertPoi.bindDouble(6, 8.538885);
		stmtInsertPoi.bindString(7, "http://www.paradeplatz.ch");
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.scheuble.ch/images/zuerich-sehenswert/zuerich-paradeplatz.jpg");
		stmtInsertPoi.bindString(10, "http://www.nzz.ch/images/paradeplatz_fullSize_1.2613447.1243149950.jpg");
		poiIds.put("Paradeplatz", stmtInsertPoi.executeInsert());
		
		//Hauptbahnhof
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehenswürdigkeiten"));
		stmtInsertPoi.bindString(3, "Hauptbahnhof");
		stmtInsertPoi.bindString(4, "Wichtigstes Einfallstor für Reisende ist der Hauptbahnhof. Im prunkvollen Neorenaissance-Bau aus Sandstein befindet sich der heutige Bahnhof von Zürich. Sein Haupteingang, ein Triumphbogen am Ende der Bahnhofstrasse, wirkt wie ein Tor zu einer damals neu erschlossenen Welt. Erbaut wurde der Bahnhof 1871. Unterirdisch werden heute zwei S-Bahnhöfe mit der Haupthalle verbunden. Hier befinden sich Ladenpassagen.");
		stmtInsertPoi.bindDouble(5, 47.378174);
		stmtInsertPoi.bindDouble(6, 8.540191);
		stmtInsertPoi.bindString(7, "http://www.railcity.ch");
		stmtInsertPoi.bindString(8, "");
		stmtInsertPoi.bindString(9, "");
		stmtInsertPoi.bindString(10, "");
		poiIds.put("Hauptbahnhof", stmtInsertPoi.executeInsert());
		
		//ETH Zürich
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehenswürdigkeiten"));
		stmtInsertPoi.bindString(3, "ETH Zürich");
		stmtInsertPoi.bindString(4, "Die kurz ETH genannte Eidgenössische Technische Hochschule zählt zu den renomiertesten Hochschulen der Welt mit naturwissenschaftlich-technischem Schwerpunkt. Hier forschten, lehrten und lernten kluge Köpfe wie Albert Einstein und Wolfgang Pauli.");
		stmtInsertPoi.bindDouble(5, 47.376441);
		stmtInsertPoi.bindDouble(6, 8.547991);
		stmtInsertPoi.bindString(7, "http://www.ethz.ch");
		stmtInsertPoi.bindString(8, "");
		stmtInsertPoi.bindString(9, "");
		stmtInsertPoi.bindString(10, "");
		poiIds.put("ETH Zürich", stmtInsertPoi.executeInsert());
		
		//Fraumünster
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehenswürdigkeiten"));
		stmtInsertPoi.bindString(3, "Fraumünster");
		stmtInsertPoi.bindString(4, "Die Kirche mit Frauenkloster wurde 853 von König Ludwig dem Deutschen gestifet und von Frauen des europäischen Hochadels bewohnt. Das Kloster genoss die Gunst von Königen und die Äbtissin hatte das Münzrecht von Zürich bis ins 13. Jh. Nach der Reformation kamen Kirche und Kloster in den Besitz der Stadt.\nBedeutende Bauteile sind der romanische Chor und das hochgewölbte Querschiff. Das Langhaus wurde 1911 letztmals umgebaut, nachdem schon im 18.Jh. der Nordturm erhöht und der Südturm abgetragen worden war.\nBedeutendster Schmuck neben der grössten Orgel im Kanton Zürich mit 5793 Pfeifen sind seine Farbfenster: die Nordfenster im Querschiff (1945) sind gefertigt von Augusto Giacometti, Alberto Giacomettis Onkel. Der fünfteilige Fensterzyklus im Chor (1970) und die Rosette im südlichen Querschiff (1978) sind Werke Marc Chagalls. Im Kreuzgang befindet sich ein Freskenzyklus von Paul Bodmer zur Gründung des Fraumünsters. Zu den Hauptattraktionen des Münsters zählen heute die Glasfenster von Chagall und Giacometti.");
		stmtInsertPoi.bindDouble(5, 47.372084);
		stmtInsertPoi.bindDouble(6, 8.543691);
		stmtInsertPoi.bindString(7, "http://www.fraumuenster.ch");
		stmtInsertPoi.bindString(8, "November - März 10:00 - 16:00 Uhr\nApril - Oktober 10:00 - 18:00 Uhr");
		stmtInsertPoi.bindString(9, "");
		stmtInsertPoi.bindString(10, "");
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
		poiIds.put("Grossmünster", stmtInsertPoi.executeInsert());

		// Lindenhof
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehenswürdigkeiten"));
		stmtInsertPoi.bindString(3, "Lindenhof");
		stmtInsertPoi.bindString(4, "Hier geniesst man eine herrliche Aussicht auf die Altstadt, das Grossmünster, das Rathaus, die Limmat, die Universität und die Eidgenössische Technische Hochschule. Zudem ist der Lindenhof Schauplatz zahlreicher geschichtlicher Ereignisse.\nIm 4. Jahrhundert stand auf dem Lindenhof ein römisches Kastell. Es diente der römischen Besatzung und der Bevölkerung zum Schutz vor den Angriffen der Alemannen.\nIm 9. Jahrhundert baute der Enkel von Karl dem Grossen an dieser Stelle eine Pfalz, eine königliche Residenz.\nIm 13. Jahrhundert zogen die Zürcher gegen Winterthur in den Krieg. Dabei fielen so viele Männer, dass Zürich keine Krieger mehr hatte. Herzog, Stadtherr von Winterthur, versuchte Zürich einzunehmen und stationierte ein Heer vor die Stadtmauern. Die Zürcherinnen verkleideten sich daraufhin als Krieger und zogen mit langen Spiessen auf den erhöhten Lindenhof. Die Belagerer glaubten, ein starkes Heer sei zur Verstärkung nach Zürich gekommen und so gab Albrecht I. von Habsburg die Belagerung auf. Die Brunnenfigur auf dem Lindenhof zeigt die heldenhaften Zürcherinnen.\nBis in die frühe Neuzeit diente der Platz den Zürchern zu Versammlungen. 1798 wurde auf dem Lindenhof zum Beispiel der Eid auf die helvetische Verfassung geschworen.\nHeute ist der Lindenhof eine Oase der Ruhe und Erholung inmitten der Stadt und Treffpunkt passionierter Schachspieler.");
		stmtInsertPoi.bindDouble(5, 47.373005);
		stmtInsertPoi.bindDouble(6, 8.541307);
		stmtInsertPoi.bindNull(7);
		stmtInsertPoi.bindNull(8);
		stmtInsertPoi.bindString(9, "http://www.sengers.ch/izueri/lindenhof/lindenhof-limmatquai.jpg");
		stmtInsertPoi.bindString(10, "http://images.gadmin.st.s3.amazonaws.com/n20505/images/zuerich/detail_breit/detailboard_lindenhof_7436-1.jpg");
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
		stmtInsertPoi.bindString(10, "");
		poiIds.put("Niederdorf", stmtInsertPoi.executeInsert());
		
		//St. Peter
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehenswürdigkeiten"));
		stmtInsertPoi.bindString(3, "St. Peter");
		stmtInsertPoi.bindString(4, "Die Kirche St. Peter ist mit dem grössten Zifferblatt Europas bestückt: Der Durchmesser beträgt 8,7 Meter. Im Turm befinden sich fünf Glocken von 1880 - die grösste wiegt ohne Klöppel über 6000 kg.\nVom Mittelalter an bis 1911 diente der Kirchturm der Brandwache.\n\nDie Kirche St. Peter ist die älteste Pfarrkirche Zürichs und reicht in ihren Anfängen in die Zeit vor 900.\nDer erste Bürgermeister Rudolf Brun, 1360 im Chor der Kirche begraben, erwirbt 1345 die Kirche mit allen Pflichten und Rechten (Kirchensatz). Heute befindet sich sein Grab und Denkmal an der Aussenmauer des Turms. Der erste reformierter Pfarrer war Leo Jud (1523-1542), ein Freund Zwinglis und Mitarbeiter an der ersten Zürcher Bibelübersetzung. 1778-1801 wirkte der Pfarrer J.C. Lavater (Schriftsteller, Physiognomiker; Freund des jungen Goethe) in dieser Kirche. Sein Grabstein befindet sich an der Kirchenmauer, ein Denkmal im Chor der Kirche.\n\nBauelemente\nTurm: unterer Teil spätromanisch, oben gotisch\nChor: von etwa 1230\nFreskenfragmente 1300 bis 1500\nSchiff: barock");
		stmtInsertPoi.bindDouble(5, 47.371061);
		stmtInsertPoi.bindDouble(6, 8.540781);
		stmtInsertPoi.bindString(7, "http://www.st-peter-zh.ch");
		stmtInsertPoi.bindString(8, "Montag bis Freitag: 8.00 bis 18.00 Uhr\nSamstag: 10.00 bis 16.00 Uhr\nSonntag: Nach dem Gottesdienst um ca.11.00 Uhr bis 17.00 Uhr");
		stmtInsertPoi.bindString(9, "http://images.gadmin.st.s3.amazonaws.com/n20505/images/zuerich/detail_breit/detailboard_stpeterkirche_8086-1.jpg");
		stmtInsertPoi.bindString(10, "");
		poiIds.put("St. Peter", stmtInsertPoi.executeInsert());
		
		//Zoo Zürich
		stmtInsertPoi.bindLong(2, categoryIds.get("Sehenswürdigkeiten"));
		stmtInsertPoi.bindString(3, "Zoo Zürich");
		stmtInsertPoi.bindString(4, "Auf dem Zürichberg liegt der Zoologische Garten mit einer Regenwaldhalle, die in Europa einmalig ist. Eröffnet wurde er im Jahre 1929. Bekannt ist der Zoo unter anderem für die artgerechte Haltung der Tiere. Auf 15 Hektar leben rund 2000 Tiere und 280 verschiedene Arten aus sechs Kontinenten. Zu den Hauptattraktionen des Zoos zählen unter anderem der südamerikanische Bergnebelwald, in dem Brillen- und Nasenbären zu Hause sind, oder die Himalaya-Anlage, in der Sibirische Tiger, Mongolische Wölfe, Schneeleoparden und Kleine Pandas leben. Selbstverständlich zählt auch die Masoala Regenwald-Halle zu einer der Hauptattraktion. Der madagassische Masoala-Regenwald wird auf gut einem Hektar abgebildet und bietet Lemuren, Makis, Chamäleons, Flughunden, Aldabra-Riesenschildkröten, Geckos, Fröschen, Echsen und Insekten Unterschlupf. 2006 wurde eine Löwenanlage für Asiatische Löwen eröffnet. Afrikahaus, Menschenaffenhaus und Schildkrötenhaus sind nur ein paar der weiteren Attraktionen. Abgerundet wird das Angebot durch Zoolino, wo die kleinen Besucher auf dem Gelände eines alten Bauernhofes Kontakt zu Schweinen, Gänsen, Hühnern, Ziegen oder Meerschweinchen aufnehmen können, und die Naturwerkstatt, in der Stadtkinder die Tiere im Siedlungsraum näher gebracht werden. Bis 2020 sollen weitere Projekte wie die asiatische Wüste, die Savanne und ein südamerikanischer und afrikanischer Regenwald realisiert werden. Im ganzen Park stehen Besuchern Bänke und Picknick-Tische zur Verfügung. Auch für die Kinder gibt es eine grosse Anzahl von Spielmöglichkeiten.");
		stmtInsertPoi.bindDouble(5, 47.384462);
		stmtInsertPoi.bindDouble(6, 8.574792);
		stmtInsertPoi.bindString(7, "http://www.zoo.ch");
		stmtInsertPoi.bindString(8, "März – Oktober 09.00 – 18.00 Uhr\nNovember – Februar 09.00 – 17.00 Uhr");
		stmtInsertPoi.bindString(9, "");
		stmtInsertPoi.bindString(10, "");
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
