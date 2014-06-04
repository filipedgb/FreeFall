package game.entities;

import game.engine.Tools;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Skymine extends Item {

	public Skymine(float x, float y) {
		super(x, y);
		this.bmp = Tools.getSkymine();
	}

	@Override
	public void caught(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(bmp, getX(), getY(), paint);
	}

}
