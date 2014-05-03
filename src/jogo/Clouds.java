package jogo;

import android.content.res.Resources;
import android.graphics.*;

public class Clouds extends GameObject {
	private static Bitmap bmp;
	
	public Clouds(int x, int y, Resources res) {
		super(x, y, 40, 40);
//		if (bmp==null) {
//			//instancio a imagem do resource
//			bmp = BitmapFactory.decodeResource(res, R.drawable.mosquito);
//			//redimensiona imagem
//			bmp = Bitmap.createScaledBitmap(bmp, 40, 40, true);
//		}
	}
	public void move(int height, int width) {
		if (getY()>0) {
			setY(getY()-5);
		} else {
			int x = (int) (Math.random() * (width-25));
			setX(x);
			setY(height+25);
		}
	}
	public void draw(Canvas canvas, Paint paint) {
		paint.setColor(Color.CYAN);
		canvas.drawRect(getX(),getY(),getX()+getWidth(), getY()+getHeight(),paint);
		// canvas.drawBitmap(bmp, getX(), getY(), paint);
	}
}
