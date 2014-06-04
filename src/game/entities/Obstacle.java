package game.entities;

import game.engine.Tools;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Esta classe representa um objeto (representado por nuvens) que causa dano ao jogador 
 * @author Andr� Pires, Filipe Gama
 * @see GameObject
 */
public class Obstacle extends GameObject {

	private boolean fast = false;
	private int damage;

	public Obstacle(int x, int y) {
		super(x, y , (int) Tools.getDrawUnity(7), (int) Tools.getDrawUnity(7));
		this.bmp = Tools.getCloud();		
		damage = 3;
	}

	public Obstacle(int x, int y, int damage) {
		this(x, y);
		this.damage = damage;	
	}

	public boolean isFast() {
		return fast;
	}

	public void setFast(boolean fast) {
		this.fast = fast;
	}

	/**
	 * Fun��o que recebe um jogador e lhe tira vida se houver colis�o desse jogador com o obstaculo(this).
	 * @param player - objecto do jogador actual
	 * @return - true se houver colis�o, falso se n�o
	 */
	public boolean damage(Player player) {
		if(this.colide(player)) {
			player.addHealthPoints(-damage);
			return true;
		}
		return false;
	}

	public void draw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(bmp, getX(), getY(), paint);
	}
}
