package game.entities;

import game.config.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Fuel extends Item {

	public Fuel(float x, float y) {
		super(x, y);
		if (bmp==null) {
			//instancio a imagem do resource
			bmp = BitmapFactory.decodeResource(res, R.drawable.fuel);
			//redimensiona imagem
			bmp = Bitmap.createScaledBitmap(bmp, this.getWidth(), this.getHeight(), true);
		}
	}


	@Override
	public void caught(Player player) {
		if(this.colide(player)) {
			player.addFuel(value);
			active = false;
		}		
	}


	@Override
	public void draw(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(bmp, getX(), getY(), paint);

	}

}
