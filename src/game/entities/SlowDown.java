package game.entities;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class SlowDown extends Item  {

	public SlowDown(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
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
