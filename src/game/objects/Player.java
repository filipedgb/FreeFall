package game.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Player extends GameObject {

	private int lifepoints;
	private int fuel;
	boolean turbo_enabled;
	boolean invulnerable;

	public Player(int x, int y) {
		super(x, y, 25, 25);
		lifepoints = 1000;
		fuel = 100;
		turbo_enabled = false;
		accelaration_y = 0;
		invulnerable = false;
	}

	public Player() {
		super(0, 0, 25, 25);
		lifepoints = 1000;
		fuel = 100;
		turbo_enabled = false;
		accelaration_y = 0;
		invulnerable = false;
	}

	public void draw(Canvas canvas, Paint paint) {
		paint.setColor(Color.RED);
		canvas.drawRect(getX(),getY(),getX()+getWidth(), getY()+getHeight(),paint);
		// canvas.drawBitmap(bmp, getX(), getY(), paint);
	}

	public void move() {
		move(0,0);
	}

	@Override
	public void move(int a, int b) {
		resistence = (float) (-0.9*velocity_x);

		velocity_x = velocity_x + (accelaration_x + resistence)/25;
		velocity_y = velocity_y + accelaration_y/25;

		setY(getY() + velocity_y/25);
		setX(getX() + velocity_x/25);
	}

	public int getLifepoints() {
		return lifepoints;
	}

	public int getFuel() {
		return fuel;
	}

	public void addHealthPoints(float value) {
		if(!invulnerable || value>0)
			lifepoints += value;
	}
	
	public void setInvulnerable(boolean x) {
		invulnerable = x;
	}
}
