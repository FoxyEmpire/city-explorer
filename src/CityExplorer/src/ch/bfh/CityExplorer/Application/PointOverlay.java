package ch.bfh.CityExplorer.Application;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class PointOverlay extends Overlay{
	private GeoPoint point;
	private Bitmap drawable;

	public PointOverlay(GeoPoint point, Bitmap drawable) {  
		this.point = point;
		this.drawable = drawable;
	}  

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {  
		Projection projection = mapView.getProjection();  
		Paint paint = new Paint();  
		Point p = new Point();  
		projection.toPixels(point, p);
		canvas.drawBitmap(drawable, p.x, p.y - drawable.getHeight(), paint);
		super.draw(canvas, mapView, shadow);  
	}  
}
