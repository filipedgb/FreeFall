package game.entities;

import game.engine.Tools;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Skymine extends Item {

	public Skymine(float x, float y) {
		super(x, y);
		this.bmp = Tools.getSkymine();
	}
	
	
	/**
	 * Fun��o que caso o jogador n�o esteja invulner�vel activa o malfunctioning (controlos trocados).
	 * Recebe um player e �-lhe mudado o booleano de malfunctioning, que ser� usado posteriomente
	 */
	@Override
	public void caught(Player player) {
		if(!player.isInvulnerable()) {
			if(this.colide(player)) {
				player.setMalfunctioning(true);
				disabler(25);	
			}
		}
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(bmp, getX(), getY(), paint);
	}
}