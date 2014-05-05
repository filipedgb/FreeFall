package game.objects;

import android.content.res.Resources;
import android.graphics.*;

public class Clouds extends GameObject {
	
	private static Bitmap bmp;
	private float velocity = 5;
	private boolean fast = false;
	
	public Clouds(int x, int y, Resources res) {
		super(x, y, 40, 40);
//		if (bmp==null) {
//			//instancio a imagem do resource
//			bmp = BitmapFactory.decodeResource(res, R.drawable.mosquito);
//			//redimensiona imagem
//			bmp = Bitmap.createScaledBitmap(bmp, 40, 40, true);
//		}
	}
	
	public boolean isFast() {
		return fast;
	}

	public void setFast(boolean fast) {
		this.fast = fast;
	}

	public void move(int height, int width) {
		if (getY()>0) {
			if(fast)setY(getY()-(velocity+10));
			else setY(getY()-velocity);
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
		this.velocity += 0.01;
	}
	
	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float a) {
		this.velocity = a;
	}

	public void draw(Canvas canvas, Paint paint) {
		paint.setColor(Color.BLUE);
		canvas.drawRect(getX(),getY(),getX()+getWidth(), getY()+getHeight(),paint);
		// canvas.drawBitmap(bmp, getX(), getY(), paint);
	}
}
