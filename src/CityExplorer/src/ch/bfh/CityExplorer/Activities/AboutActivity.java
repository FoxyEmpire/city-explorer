package ch.bfh.CityExplorer.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import ch.bfh.CityExplorer.R;

public class AboutActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
	}
	
	public void playVideo(View v) {
		Intent intent = new Intent(this, VideoActivity.class);
		startActivity(intent);
	}
}
