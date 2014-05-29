package game.entities;


public abstract class Item extends GameObject {
	
	protected float value;
	
	public Item(float x, float y) {
		super(x, y, screen_width/10 , screen_width/10);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void caught(Player player) ;
	
}
