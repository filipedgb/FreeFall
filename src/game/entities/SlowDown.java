package game.entities;

import game.engine.Tools;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Esta classe representa o item "slowmotion", que, quando "apanhado" causa um decrescimo de velocidade do jogo 
 * @author André Pires, Filipe Gama
 * @see Item
 */
public class SlowDown extends Item  {

	public SlowDown(float x, float y) {
		super(x, y);
		this.bmp = Tools.getSlowmo();
	}
	/**
	 * Criado para efeitos de teste
	 * @param x
	 * @param y
	 * @param teste
	 */
	public SlowDown(float x, float y, boolean teste) {
		super(x, y);
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(bmp, getX(), getY(), paint);	
	}

	/**
	 * Caso o jogador apanhe (colida) com este item, a velocidade global é reduzida. 
	 */
	@Override
	public boolean caught(Player player) {
		if(this.colide(player)) {
			disabler(25);	
			return true;
		}
		return false;
	}

	/**
	 * Funcao criada para efeitos de teste
	 */
	@Override
	public void caught(Player player, boolean teste) {
		if(this.colide(player)) {
			GameObject.setGlobalVelocity(GameObject.getGlobalVelocity_x(), GameObject.getGlobalVelocity_y()/2);
			disabler(25);	
		}
	}	
}