package game.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Alien extends GameObject {

	private int lifepoints;
	private double turbopoints;
	boolean turbo_enabled;


	public Alien(int x, int y) {
		super(x, y, 25, 25);
		lifepoints = 1000;
		turbopoints = 100;
		turbo_enabled = false;
	}
	
	public boolean isTurbo() {
		return turbo_enabled;
	}
	
	public void setTurbo(boolean a) { 
		turbo_enabled = a; 
	}
	
	public double getTurbopoints() {
		return turbopoints;
	}

	public void setTurbopoints(double d) {
		this.turbopoints = d;
	}

	public void moveLeft() {
		x -= 3;
	}
	
	public void moveRight() {
		x += 3;
	}
	
	public void moveDown() {
		y += 5;
	}
	
	public void moveUp() {
		y -= 5;
	}

	public void getsHit() {
		lifepoints -= 25;
		Log.e("vida", "-25 vida");
		Log.e("vida", "" + lifepoints);

	}
	
	public int getLifepoints() {
		return lifepoints;
	}

	void setLifepoints(int lifepoints) {
		this.lifepoints = lifepoints;
	}

	public void draw(Canvas canvas, Paint paint) {
		paint.setColor(Color.RED);
		canvas.drawRect(getX(),getY(),getX()+getWidth(), getY()+getHeight(),paint);
		// canvas.drawBitmap(bmp, getX(), getY(), paint);
	}
	

}
