package game.entities;

import game.engine.Tools;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Fuel extends Item {

	public Fuel(float x, float y) {
		super(x, y);
		this.value = 100;
		this.bmp  = Tools.getFuel();
	}

	@Override
	public void caught(Player player) {
		if(this.colide(player)) {
			player.addFuel(value);
			active = false;
		}		
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(bmp, getX(), getY(), paint);
	}
}
