package ch.bfh.CityExplorer.Activities;

import ch.bfh.CityExplorer.R;
import ch.bfh.CityExplorer.Activities.MapsActivity;
import android.app.Activity;
import android.content.Intent;
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
    }
    
    protected void OnClickMap(View view){
    	Intent intent = new Intent(this, MapsActivity.class);
		startActivity(intent);
    }
}