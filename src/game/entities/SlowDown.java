package game.entities;

import game.engine.Tools;
import android.graphics.Canvas;
import android.graphics.Paint;

public class SlowDown extends Item  {

	public SlowDown(float x, float y) {
		super(x, y);
		this.bmp = Tools.getSlowmo();
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(bmp, getX(), getY(), paint);	
	}
	
	/**
	 * Caso o jogador apanhe (colida) com este item, a velocidade global é reduzida. 
	 */
	@Override
	public void caught(Player player) {
		if(this.colide(player)) {
			GameObject.setGlobalVelocity(GameObject.getGlobalVelocity_x(), GameObject.getGlobalVelocity_y()/2);
			disabler(25);	
		}
		
	}	
}