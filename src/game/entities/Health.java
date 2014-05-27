package game.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Health extends Item {

	public Health(float x, float y, int height, int width) {
		super(x, y, height, width);
		this.value = 200;
	}

	@Override
	public void caught(Player player) {
		if(this.colide(player)) {
			player.addHealthPoints(value);
		}
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		paint.setColor(Color.GREEN);
		canvas.drawRect(getX(),getY(),getX()+getWidth(), getY()+getHeight(),paint);
		
	}




}
