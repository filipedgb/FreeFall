package game.entities;

import game.engine.Tools;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Obstacle extends GameObject {

	private boolean fast = false;
	private int damage;

	public Obstacle(int x, int y) {
		super(x, y , screen_height/3,  screen_height/3);
		this.bmp = Tools.getCloud();		
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
		canvas.drawBitmap(bmp, getX(), getY(), paint);
	}
}
