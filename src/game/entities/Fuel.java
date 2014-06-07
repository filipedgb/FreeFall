package game.entities;

import game.engine.PlayActivity;
import game.engine.Tools;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Esta classe representa o item de combustivel, se o jogador apanhar este item ganha combustivel
 * @author André Pires, Filipe Gama
 * @see Item
 */
public class Fuel extends Item {

	public Fuel(float x, float y) {
		super(x, y);
		this.value = 100;
		this.bmp  = Tools.getFuel();
	}

	/**
	 * Criado para efeitos de teste
	 * @param x
	 * @param y
	 * @param teste
	 */
	public Fuel(float x, float y, boolean teste) {
		super(x,y);
		this.value = 100;
	}

	/**
	 * Esta função recebe o jogador e adiciona-lhe o valor de "value" ao atributo fuel, através da funçao addFuel.
	 * Chama a função disabler que inicia um contador para o tempo que o item vai estar desativado depois de apanhado 
	 */
	@Override
	public void caught(Player player) {
		if(this.colide(player)) {
			if(!Tools.isMute()) PlayActivity.getSingleInstance().playFuel();
			player.addFuel(value);
			disabler(25);			
		}		
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(bmp, getX(), getY(), paint);
	}

	@Override
	public void caught(Player player, boolean teste) {
		if(this.colide(player)) {
			player.addFuel(value);
			disabler(25);			
		}		
	}


}
