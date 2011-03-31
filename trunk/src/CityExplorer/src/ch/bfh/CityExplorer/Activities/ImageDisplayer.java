package ch.bfh.CityExplorer.Activities;

import android.R;
import android.app.AlertDialog;
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
		if (bmp == null){
			pbar.setVisibility(View.GONE);
			AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
			/* dialog.setTitle(R.string.titelLoadImageError);
			   dialog.setMessage(R.string.textLoadImageError);*/
			 dialog.setTitle("Fehler");
			   dialog.setMessage("Ein Bild konnte nicht geladen werden.");
		   dialog.show();
		}
		else{
			view.setImageBitmap(bmp);
			view.setVisibility(View.VISIBLE);
			pbar.setVisibility(View.GONE);
		}
	}
 
}
