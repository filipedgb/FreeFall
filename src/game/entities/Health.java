package game.entities;

import game.engine.Tools;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Health extends Item {

	public Health(float x, float y) {
		super(x, y);
		this.value = 200;
		this.bmp = Tools.getHealth();
	}

	/**
	 * Esta fun��o recebe o jogador e adiciona-lhe o valor de "value" ao atributo lifepoints atrav�s de addHealthPoints
	 * Chama a fun��o disabler que inicia um contador para o tempo que o item vai estar desativado depois de apanhado 
	 */
	@Override
	public void caught(Player player) {
		if(this.colide(player)) {
			player.addHealthPoints(value);
			disabler(25);
		}
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(bmp, getX(), getY(), paint);
	}
}