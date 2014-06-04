package game.entities;

public abstract class Item extends GameObject {

	protected float value;

	public Item(float x, float y) {
		super(x, y, screen_width/12 , screen_width/12);
	}

	public abstract void caught(Player player);
}
