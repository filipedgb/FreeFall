package game.entities;

import game.engine.MarketActivity;
import game.engine.PlayActivity;
import game.engine.Tools;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Coin extends Item {

	public Coin(float x, float y) {
		super(x, y);
		this.bmp = Tools.getCoin();
	}

	@Override
	public boolean caught(Player player) {
		if(this.colide(player)) {
			if(this.isActive()) {
				MarketActivity.addCoins();
				PlayActivity.getSingleInstance().playCoin();
			}
			disabler(25);
			return true;
		}
		return false;
	}

	@Override
	public void caught(Player player, boolean teste) {
		if(this.colide(player)) {
			MarketActivity.addCoins();
			disabler(25);
		}
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(bmp, getX(), getY(), paint);
	}

}
