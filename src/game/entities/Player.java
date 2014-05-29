package game.entities;

import game.config.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;


public class Player extends GameObject {

	private int lifepoints;
	private int fuel;
	boolean turbo_enabled;
	boolean invulnerable;
	private int invulnerable_ticks;


	public Player(int x, int y) {
		super(x, y, screen_width/6, screen_width/6);
		lifepoints = 1000;
		fuel = 100;
		turbo_enabled = false;
		accelaration_y = 0;
		invulnerable = false;
		invulnerable_ticks = 0;
		
		if (bmp==null) {
			//instancio a imagem do resource
			bmp = BitmapFactory.decodeResource(res, R.drawable.alien);
			//redimensiona imagem
			bmp = Bitmap.createScaledBitmap(bmp, 50, 50, true);
		}
	}

	public Player() {
		this(0,0);
		lifepoints = 1000;
		fuel = 100;
		turbo_enabled = false;
		accelaration_y = 0;
		invulnerable = false;
		invulnerable_ticks = 0;
	}

	public void draw(Canvas canvas, Paint paint) {
		//paint.setColor(Color.RED);
		//canvas.drawRect(getX(),getY(),getX()+getWidth(), getY()+getHeight(),paint);
		canvas.drawBitmap(bmp, getX(), getY(), paint);
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
		if(!invulnerable || value > 0) lifepoints += value;
	
	}
	
	public void setInvulnerable(boolean x) {
		invulnerable = x;
		invulnerable_ticks = 100;
	}
	
	public boolean isInvulnerable() {
		return invulnerable;
	}

	public int getInvulnerable_ticks() {
		// TODO Auto-generated method stub
		return invulnerable_ticks;
	}
	
	public void decrement_ticks() {
		invulnerable_ticks--;
	}
}
