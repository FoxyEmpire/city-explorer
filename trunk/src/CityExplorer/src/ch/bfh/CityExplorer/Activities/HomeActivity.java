package ch.bfh.CityExplorer.Activities;

import java.util.Calendar;

import ch.bfh.CityExplorer.R;
import ch.bfh.CityExplorer.Activities.MapsActivity;
import ch.bfh.CityExplorer.Domain.PointOfInterest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;

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
    }
    
    protected void OnClickMap(View view){
    	Intent intent = new Intent(this, MapsActivity.class);
		startActivity(intent);
    }
    
    protected void OnClickListOfPointsOfInterests(View view){
    	Intent intent = new Intent(this,GroupActivity.class);
		startActivity(intent);
    }
    
    protected void OnClickFavorit(View view){
    	Intent intent = new Intent(this,FavoritActivity.class);
		startActivity(intent);
    }
}