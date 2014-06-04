package game.entities;

import android.util.Log;
import game.engine.Tools;

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
		super(x, y, screen_width/12 , screen_width/12);
	}

	public abstract void caught(Player player);
	
	public void updateItem() {
		if(ticks > 0) ticks--;
		else if (ticks == 0) {
			ticks = number_ticks;
		}
	}


	public void disabler(int numSeconds) {
		number_ticks =  (int) (numSeconds*Tools.getFPS());
		ticks  = (int) (numSeconds*Tools.getFPS()); 
		this.setActive(false);
		Log.e("DESATIVOU", "DESATIVOU");
	
	}
}
