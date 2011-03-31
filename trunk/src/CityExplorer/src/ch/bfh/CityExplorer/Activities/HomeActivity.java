package ch.bfh.CityExplorer.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import ch.bfh.CityExplorer.R;

public class HomeActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        findViewById(R.id.btnMap).setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		OnClickMap(v);
        	}
    	});
        
        findViewById(R.id.btnListOfPointsOfInterests).setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		OnClickListOfPointsOfInterests(v);
        	}
    	});
        
        findViewById(R.id.btnFavorit).setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		OnClickFavorit(v);
        	}
    	});
        
        findViewById(R.id.btnAbout).setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		OnClickAbout(v);
        	}
    	});
    }
    
    protected void OnClickMap(View view) {
    	Intent intent = new Intent(this, PoiMapActivity.class);
		startActivity(intent);
    }
    
    protected void OnClickListOfPointsOfInterests(View view){
    	Intent intent = new Intent(this, GroupActivity.class);
		startActivity(intent);
    }
    
    protected void OnClickFavorit(View view){
    	Intent intent = new Intent(this, FavoritActivity.class);
		startActivity(intent);
    }
    
    protected void OnClickAbout(View view){
    	Intent intent = new Intent(this, AboutActivity.class);
		startActivity(intent);
    }
}