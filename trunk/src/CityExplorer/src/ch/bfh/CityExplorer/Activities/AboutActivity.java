package ch.bfh.CityExplorer.Activities;

import java.io.IOException;

import ch.bfh.CityExplorer.R;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

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
