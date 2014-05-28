package game.entities;

import android.content.res.Resources;

public abstract class Item extends GameObject {
	
	protected float value;
	
	public Item(float x, float y) {
		super(x, y, 25 , 25);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void caught(Player player) ;
	
}
