package game.entities;

public abstract class Item extends GameObject {
	
	protected float value;
	
	public Item(float x, float y, int height, int width) {
		super(x, y, height, width);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void caught(Player player) ;
	
}
