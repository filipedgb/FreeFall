package game.objects;

import android.content.res.Resources;
import android.graphics.*;

public class Clouds extends GameObject {
	
	private static Bitmap bmp;
	private int velocity = 5;
	
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
			setY(getY()-velocity);
		} else {
			int x = (int) (Math.random() * (width-25));
			int y = (int) (Math.random() *200);
			setX(x);
			setY(height+y);
		}
	}
	
	public void resetVelocity() {
		this.velocity = 5;
	}
	
	public void incVelocity() {
		this.velocity += 1;
	}
	
	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public void draw(Canvas canvas, Paint paint) {
		paint.setColor(Color.BLUE);
		canvas.drawRect(getX(),getY(),getX()+getWidth(), getY()+getHeight(),paint);
		// canvas.drawBitmap(bmp, getX(), getY(), paint);
	}
}
