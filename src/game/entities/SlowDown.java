package game.entities;

import game.engine.Tools;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class SlowDown extends Item  {

	public SlowDown(float x, float y) {
		super(x, y);
		this.bmp = Tools.getSlowmo();
	}


	@Override
	public void draw(Canvas canvas, Paint paint) {
		paint.setColor(Color.BLUE);
		canvas.drawRect(getX(),getY(),getX()+getWidth(), getY()+getHeight(),paint);
		
	}


	@Override
	public void caught(Player player) {
		// TODO Auto-generated method stub
		
	}




	
	
}
