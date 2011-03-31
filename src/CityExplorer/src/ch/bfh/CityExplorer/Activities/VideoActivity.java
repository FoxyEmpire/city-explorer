package ch.bfh.CityExplorer.Activities;

import ch.bfh.CityExplorer.R;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class VideoActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        
        VideoView vv = (VideoView)this.findViewById(R.id.vvVideo_Video);
        String fileName = "android.resource://" + getPackageName() + "/" + R.raw.cityexplorer;
        vv.setVideoURI(Uri.parse(fileName));
        vv.start();
	}
	
}
