package game.entities;

import game.engine.Tools;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Esta classe representa um objeto (representado por nuvens) que causa dano ao
 * jogador
 * 
 * @author André Pires, Filipe Gama
 * @see GameObject
 */
public class Obstacle extends GameObject {

	private boolean fast = false;
	private int damage;
	private static Bitmap current_bmp;
	private boolean canColide = true;

	protected int ticks;
	protected int number_ticks = 0;

	public Obstacle(int x, int y) {
		super(x, y, (int) Tools.getDrawUnity(4), (int) Tools.getDrawUnity(4));
		this.bmp = Tools.getSatelite();
		damage = 50;
		current_bmp = bmp;
	}

	public Obstacle(int x, int y, int damage) {
		this(x, y);
		this.damage = damage;
	}

	/**
	 * Criado para efeitos de teste
	 * 
	 * @param x
	 *            posicao
	 * @param y
	 *            posicao
	 * @param damage
	 *            dano que tira ao jogador
	 * @param teste
	 */
	public Obstacle(int x, int y, int damage, boolean teste) {
		super(x, y, (int) Tools.getDrawUnity(7), (int) Tools.getDrawUnity(7));
		this.damage = damage;
	}

	public static Bitmap getCurrent_bmp() {
		return current_bmp;
	}

	public static void setCurrent_bmp(Bitmap current_bmp) {
		Obstacle.current_bmp = current_bmp;
	}

	public boolean isFast() {
		return fast;
	}

	public void setFast(boolean fast) {
		this.fast = fast;
	}

	/**
	 * Função que recebe um jogador e lhe tira vida se houver colisão desse
	 * jogador com o obstaculo(this).
	 * 
	 * @param player
	 *            - objecto do jogador actual
	 * @return - true se houver colisão, falso se não
	 */
	public boolean damage(Player player) {
		if (this.colide(player) && canColide) {
			disableColide(0.5f);
			player.addHealthPoints(-damage);
			return true;
		}
		return false;
	}

	public void disableColide(float numSeconds) {
		number_ticks = (int) (numSeconds * Tools.getFPS());
		ticks = (int) (numSeconds * Tools.getFPS());
		canColide = false;
	}

	public void updateItem() {
		if (ticks > 0)
			ticks--;
		else if (ticks == 0) {
			ticks = number_ticks;
			canColide = true;
		}
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(current_bmp, getX(), getY(), paint);
	}
}
