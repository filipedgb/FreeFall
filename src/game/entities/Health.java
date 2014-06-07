package game.entities;

import game.engine.PlayActivity;
import game.engine.Tools;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Esta classe representa o item de vida, o jogador ao apanhar este item ganha vida 
 * @author André Pires, Filipe Gama
 * @see Item
 */
public class Health extends Item {

	public Health(float x, float y) {
		super(x, y);
		this.value = 200;
		this.bmp = Tools.getHealth();
	}
	
	/**
	 * Criado para efeitos de teste
	 * @param x
	 * @param y
	 */
	public Health(float x, float y, boolean teste) {
		super(x, y);
		this.value = 200;
	}

	/**
	 * Esta função recebe o jogador e adiciona-lhe o valor de "value" ao atributo lifepoints através de addHealthPoints
	 * Chama a função disabler que inicia um contador para o tempo que o item vai estar desativado depois de apanhado 
	 */
	@Override
	public boolean caught(Player player) {
		if(this.colide(player)) {
			if(!Tools.isMute()) PlayActivity.getSingleInstance().playHealth();
			player.addHealthPoints(value);
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
			player.addHealthPoints(value);
			disabler(25);
		}
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(bmp, getX(), getY(), paint);
	}
}