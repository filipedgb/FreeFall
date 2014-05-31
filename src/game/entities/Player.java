package game.entities;

import game.config.R;
import game.engine.Sprite;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;


public class Player extends GameObject {

	private int lifepoints;
	private static int max_life = 1000;
	private int fuel;
	private static int max_fuel = 500;
	boolean turbo_enabled;
	boolean invulnerable;
	private int invulnerable_ticks;
	private Sprite player_animation;
	private Bitmap player_spritesheet;
	private int motion;


	public Player(int x, int y) {
		super(x, y, screen_width/6, screen_width/6);
		lifepoints = max_life;
		fuel = max_fuel;
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
		
		player_spritesheet = BitmapFactory.decodeResource(res, R.drawable.alien_anim);
		player_animation = new Sprite(x,y,screen_height,screen_width,player_spritesheet);
			
	}
	

	public void setMotion(int motion) {
		player_animation.setDirection(motion);
		this.motion = motion;
	}


	public Player() {
		this(0,0);
		lifepoints = 1000;
		fuel = 100;
		turbo_enabled = false;
		accelaration_y = 0;
		invulnerable = false;
		invulnerable_ticks = 0;
		motion = -1;
	}

	public void draw(Canvas canvas, Paint paint) {
		//paint.setColor(Color.RED);
		//canvas.drawRect(getX(),getY(),getX()+getWidth(), getY()+getHeight(),paint);
		Log.e("PLAYER","" + motion);
		
		if(motion == -1) canvas.drawBitmap(bmp, getX(), getY(), paint);
		else player_animation.draw(canvas);

	}
	
	


	@Override
	public void move() {
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
		if(value > 0 && lifepoints <= max_life) { 
			if(lifepoints+value > 1000) lifepoints = max_life;
			else lifepoints += value;
		}
		
		else if(value < 0 && lifepoints > 0 && !invulnerable) {
			if(lifepoints+value < 0) lifepoints = 0;
			else lifepoints += value;
		}
	}
	
	public void addFuel(float value) {
		if(value > 0 && fuel <= max_fuel) { 
			if(fuel+value > 1000) fuel = max_fuel;
			else fuel += value;
		}
		
		else if(value < 0 && fuel > 0) {
			if(fuel+value < 0) fuel = 0;
			else fuel += value;
		}	
	}
	
	
	
	public static int getMax_life() {
		return max_life;
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

	public static int getMax_fuel() {
		return max_fuel;
	}


}
