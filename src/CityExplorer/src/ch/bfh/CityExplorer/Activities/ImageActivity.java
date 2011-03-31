package ch.bfh.CityExplorer.Activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import ch.bfh.CityExplorer.R;

public class ImageActivity extends Activity implements OnTouchListener {

	// These matrices will be used to move and zoom image
	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();

	private PointF start = new PointF();
	private PointF mid = new PointF();
	private float oldDist = 1f;

	private ImageView imageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image);

		Bundle extra = getIntent().getExtras();
		if (extra != null){
			byte[] data = extra.getByteArray("image");
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			imageView = (ImageView)findViewById(R.id.imageView);
			imageView.setImageBitmap(bitmap);
			imageView.setOnTouchListener(this);
		}
		else{
			finish();
		}

		matrix.setTranslate(1f, 1f);
		imageView.setImageMatrix(matrix);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		ImageView view = (ImageView) v;

		// Handle touch events here...
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = spacing(event);
			savedMatrix.set(matrix);
			midPoint(mid, event);
			break;
		case MotionEvent.ACTION_MOVE:
			float newDist = spacing(event);
			if (newDist > 10f) {
				matrix.set(savedMatrix);
				float scale = newDist / oldDist;
				matrix.postScale(scale, scale, mid.x, mid.y);
			}
			break;
		}

		// Perform the transformation
		view.setImageMatrix(matrix);
		view.invalidate();
		return true; // indicate event was handled
	}

	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}
	
	private void midPoint(PointF point, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		point.set(x / 2, y / 2);
	}
	
}
