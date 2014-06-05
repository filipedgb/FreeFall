package game.entities;

import game.engine.Tools;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Esta classe representa um objeto (representado por nuvens) que causa dano ao jogador 
 * @author André Pires, Filipe Gama
 * @see GameObject
 */
public class Obstacle extends GameObject {

	private boolean fast = false;
	private int damage;
	private static Bitmap current_bmp;
	
	public Obstacle(int x, int y) {
		super(x, y , (int) Tools.getDrawUnity(7), (int) Tools.getDrawUnity(7));
		this.bmp = Tools.getSatelite();		
		damage = 3;
		current_bmp = bmp;
	}

	public static Bitmap getCurrent_bmp() {
		return current_bmp;
	}

	public static void setCurrent_bmp(Bitmap current_bmp) {
		Obstacle.current_bmp = current_bmp;
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
	 * Função que recebe um jogador e lhe tira vida se houver colisão desse jogador com o obstaculo(this).
	 * @param player - objecto do jogador actual
	 * @return - true se houver colisão, falso se não
	 */
	public boolean damage(Player player) {
		if(this.colide(player)) {
			player.addHealthPoints(-damage);
			return true;
		}
		return false;
	}

	public void draw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(current_bmp, getX(), getY(), paint);
	}
}
