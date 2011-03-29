package ch.bfh.CityExplorer.Activities;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ImageDisplayer implements Runnable {
	
	public ImageView view;
	public Bitmap bmp;
	public ProgressBar pbar;
	
	public ImageDisplayer(ImageView imageView, Bitmap bmp, ProgressBar pbar) {
		this.view = imageView;
		this.bmp = bmp;
		this.pbar = pbar;
	}
	
	public void run() {
		view.setImageBitmap(bmp);
		view.setVisibility(View.VISIBLE);
		pbar.setVisibility(View.GONE);
	}
 
}
