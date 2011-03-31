package ch.bfh.CityExplorer.Activities;

import ch.bfh.CityExplorer.R;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        
        VideoView vv = (VideoView)this.findViewById(R.id.vvVideo_Video);
        String fileName = "android.resource://" + getPackageName() + "/" + R.raw.cityexplorer;
        vv.setVideoURI(Uri.parse(fileName));
        MediaController mediaController = new MediaController(this);
        vv.setMediaController(mediaController);
        vv.start();
        vv.setOnCompletionListener(new OnCompletionListener(){
			@Override
			public void onCompletion(MediaPlayer arg0) {
				finish();
			}
        });
	}
	
}
