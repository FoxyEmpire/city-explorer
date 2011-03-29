package ch.bfh.CityExplorer.Activities;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.CityExplorer.R;
import ch.bfh.CityExplorer.Data.CategoryTbl;
import ch.bfh.CityExplorer.Data.CityExplorerDatabase;
import ch.bfh.CityExplorer.Data.ICategoryColumn;
import ch.bfh.CityExplorer.Data.PointOfInterestTbl;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GroupActivity  extends ListActivity {

	private ListAdapter mAdapter;
	private SQLiteDatabase db;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        db = new CityExplorerDatabase(this).getReadableDatabase();
        
        Cursor cursor = db.query(CategoryTbl.TABLE_NAME,
        		CategoryTbl.ALL_COLUMNS, null, null,
        		ICategoryColumn.NAME, null, null);
        cursor.moveToFirst();
        ArrayList<ListItem> items = new ArrayList<ListItem>();
        while (cursor.isAfterLast() == false) {
        	int id = cursor.getInt(cursor.getColumnIndex(PointOfInterestTbl.ID));
        	String name = cursor.getString(cursor.getColumnIndex(PointOfInterestTbl.NAME));
        	ListItem item = new ListItem(id, name);
        	items.add(item);
        	cursor.moveToNext();
        }        
        cursor.close();
       
        mAdapter = new ListAdapter(this, items);
        this.setListAdapter(mAdapter);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	ListItem item = (ListItem)getListView().getItemAtPosition(position);
    	Intent intent = new Intent(this, PointOfInterests.class);
    	intent.putExtra("categoryId", item.getId());
		startActivity(intent);
    }
	
	private class ListItem{
		private int id;
		private String name;
		
		public ListItem(int id, String name){
			this.id = id;
			this.name = name;
		}
		
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
	}
	
	private class ListAdapter extends BaseAdapter {
    	
    	private final LayoutInflater mLayoutInflater;
    	private List<ListItem> items;

    	public ListAdapter(Context pContext, List<ListItem> items){
    			mLayoutInflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    			this.items = items;
    	}
    	
    	@Override
    	public int getCount() {return items.size();}

    	@Override
    	public Object getItem(int pPosition) {
    		return items.get(pPosition);
    	}

    	@Override
    	public long getItemId(int arg0) {return 0;}

    	@Override
    	public View getView(int pPosition, View convertView, ViewGroup parent) {
    		if (convertView == null) {
    			convertView = mLayoutInflater.inflate(R.layout.listgroup, parent, false);
    		}

			((TextView) convertView.findViewById(R.id.tvGroup_Name)).setText(items.get(pPosition).getName());
    			
    		return convertView;
    	}
    }
    
}
