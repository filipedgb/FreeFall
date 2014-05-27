package game.entities;

import android.content.res.Resources;
import android.graphics.*;
import android.util.Log;

public class Obstacle extends GameObject {
	
	private static Bitmap bmp;
	private boolean fast = false;
	private int damage;
	
	public Obstacle(int x, int y, Resources res) {
		super(x, y, 40, 40);
//		if (bmp==null) {
//			//instancio a imagem do resource
//			bmp = BitmapFactory.decodeResource(res, R.drawable.mosquito);
//			//redimensiona imagem
//			bmp = Bitmap.createScaledBitmap(bmp, 40, 40, true);
//		}
		
		damage = 10;
	}
	
	public Obstacle(int x, int y) {
		super(x, y, 40, 40);
		damage = 10;
	}
	
	public Obstacle(int x, int y, int damage) {
		super(x, y, 40, 40);
		this.damage = damage;
	}
	
	
	public boolean isFast() {
		return fast;
	}

	public void setFast(boolean fast) {
		this.fast = fast;
	}

	
	public void damage(Player player) {
		if(this.colide(player)) {
			Log.e("damage", "" + damage);
			player.addHealthPoints(-damage);
		}
	}
	

	public void draw(Canvas canvas, Paint paint) {
		paint.setColor(Color.CYAN);
		canvas.drawRect(getX(),getY(),getX()+getWidth(), getY()+getHeight(),paint);
		// canvas.drawBitmap(bmp, getX(), getY(), paint);
	}
}
