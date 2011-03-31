package ch.bfh.CityExplorer.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CityExplorerStorage {

	private static final String TAG = "CityExplorerStorage";

	private CityExplorerDatabase mDb;

	public CityExplorerStorage(Context context) {
		mDb = new CityExplorerDatabase(context);
		mDb.getReadableDatabase();
		Log.d(TAG, "Kontaktspeicher angelegt.");
	}

	public long InsertFavourite(int poiId) {
		final ContentValues daten = new ContentValues();
		daten.put(FavouriteTbl.POI_ID, poiId);

		final SQLiteDatabase dbCon = mDb.getWritableDatabase();

		try {
			final long id = dbCon.insertOrThrow(FavouriteTbl.TABLE_NAME, null, daten);
			Log.i(TAG, "Favourite mit id=" + id + " erzeugt.");
			return id;
		} finally {
			dbCon.close();
		}
	}

	public void DeleteFavourite(int poiId) {
		final SQLiteDatabase dbCon = mDb.getWritableDatabase();

		try {
			dbCon.delete(
					FavouriteTbl.TABLE_NAME,
					IFavouriteColumn.POI_ID + "=?",
					new String[] { String.valueOf(poiId) });
		} catch (Exception ex) {
		} finally {
			dbCon.close();
		}
		Log.i(TAG, "Favourite mit poiId=" + poiId + " gelöscht.");
	}


}
