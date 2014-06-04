package game.entities;

import android.util.Log;
import game.engine.Tools;

/**
 * Esta classe serve de classe pai para todos os itens do jogo, ou seja, todos os power ups que o jogador pode "apanhar"
 * @author André Pires, Filipe Gama
 * @see GameObject
 */
public abstract class Item extends GameObject {

	protected float value;
	protected int ticks;
	protected int number_ticks = 0;

	@Override
	public void move() {
		if(!this.isActive() && ticks == number_ticks) {
			this.setActive(true);
			Log.e("ACTIVOU", "ACTIVOU");
		}
		super.move();
	}

	public Item(float x, float y) {
		super(x,y,(int)Tools.getDrawUnity(1),(int) Tools.getDrawUnity(1));
	}

	/**
	 * Recebe um jogador e é suposto definir aquilo que lhe acontece quando o item(this) é apanhado
	 * @param player
	 */
	public abstract void caught(Player player);

	/**
	 * Função que tem como unico objectivo actualizar o contador
	 */
	public void updateItem() {
		if(ticks > 0) ticks--;
		else if (ticks == 0) {
			ticks = number_ticks;
		}
	}

	/**
	 * Função que tem como objectivo inicializar o contador 
	 * @param numSeconds - numero de segundos que se pretende que o item esteja desactivado apos ser apanhado
	 */
	public void disabler(int numSeconds) {
		number_ticks =  (int) (numSeconds*Tools.getFPS());
		ticks  = (int) (numSeconds*Tools.getFPS()); 
		this.setActive(false);

	}
}
