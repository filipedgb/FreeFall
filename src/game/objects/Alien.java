package game.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Alien extends GameObject {

	private int lifepoints;
	
	
	public Alien(int x, int y) {
		super(x, y, 15, 15);
		lifepoints = 100;
	}
	
	public void moveLeft() {
		x -= 15;
	}
	
	public void moveRight() {
		x += 15;
	}
	
	public void moveDown() {
		y += 5;
	}
	
	public void moveUp() {
		y -= 5;
	}

	public void draw(Canvas canvas, Paint paint) {
		paint.setColor(Color.GREEN);
		canvas.drawRect(getX(),getY(),getX()+getWidth(), getY()+getHeight(),paint);
		// canvas.drawBitmap(bmp, getX(), getY(), paint);
	}
	

}
