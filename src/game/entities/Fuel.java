package game.entities;

import game.config.R;
import game.engine.Tools;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
		// TODO Auto-generated method stub
		canvas.drawBitmap(bmp, getX(), getY(), paint);

	}

}
