package ch.bfh.CityExplorer.Activities;

import java.io.ByteArrayOutputStream;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Bitmap.CompressFormat;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ImageDisplayer implements Runnable {

	public ImageView view;
	public Bitmap bmp;
	public ProgressBar pbar;
	private Display display;

	public ImageDisplayer(ImageView imageView, Bitmap bmp, ProgressBar pbar, Display display) {
		this.view = imageView;
		this.bmp = bmp;
		this.pbar = pbar;
		this.display = display;
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
			Bitmap temp = bmp;
			if (bmp.getWidth() > display.getWidth()) {
				int newWidth = display.getWidth();
				int newHeight = bmp.getHeight() * newWidth / bmp.getWidth();

				float scaleWidth = ((float) newWidth) / bmp.getWidth();
				float scaleHeight = ((float) newHeight) / bmp.getHeight();

				Matrix matrix = new Matrix();
				matrix.postScale(scaleWidth, scaleHeight);

				temp = Bitmap.createBitmap(bmp, 0, 0,
						bmp.getWidth(), bmp.getHeight(), matrix, true);

			}

			view.setImageBitmap(temp);
			view.setVisibility(View.VISIBLE);
			pbar.setVisibility(View.GONE);

			view.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(view.getContext(), ImageActivity.class);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();     
					bmp.compress(CompressFormat.PNG,100,bos);  
					intent.putExtra("image", bos.toByteArray());
					view.getContext().startActivity(intent);
				}
			});
		}
	}

}
