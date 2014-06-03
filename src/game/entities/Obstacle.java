package game.entities;

import game.config.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;


public class Obstacle extends GameObject {
	
	private boolean fast = false;
	private int damage;
	
	public Obstacle(int x, int y) {
		super(x, y , screen_height/3,  screen_height/3);
		if (bmp==null) {
			//instancio a imagem do resource
			bmp = BitmapFactory.decodeResource(res, R.drawable.nuvem);
			//redimensiona imagem
			bmp = Bitmap.createScaledBitmap(bmp, this.getHeight(), this.getWidth(), true);
		}
		
		damage = 3;
	}
	

	public Obstacle(int x, int y, int damage) {
		this(x, y);
		this.damage = damage;
		
	}
	
	
	public boolean isFast() {
		return fast;
	}

	public void setFast(boolean fast) {
		this.fast = fast;
	}

	
	public boolean damage(Player player) {
		if(this.colide(player)) {
			player.addHealthPoints(-damage);
			return true;
		}
		return false;
	}
	

	public void draw(Canvas canvas, Paint paint) {
		//paint.setColor(Color.CYAN);
		//canvas.drawRect(getX(),getY(),getX()+getWidth(), getY()+getHeight(),paint);
		canvas.drawBitmap(bmp, getX(), getY(), paint);
	}

	
}
