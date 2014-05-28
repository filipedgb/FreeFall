package game.entities;

import game.config.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Health extends Item {

	public Health(float x, float y) {
		super(x, y);
		this.value = 30;
				
		if (bmp==null) {
			//instancio a imagem do resource
			bmp = BitmapFactory.decodeResource(res, R.drawable.health);
			//redimensiona imagem
			bmp = Bitmap.createScaledBitmap(bmp, 25, 25, true);
		}
		
	}

	@Override
	public void caught(Player player) {
		if(this.colide(player)) {
			Log.e("coiso", "colidiu");
			player.addHealthPoints(value);
		}
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		//paint.setColor(Color.GREEN);
		//canvas.drawRect(getX(),getY(),getX()+getWidth(), getY()+getHeight(),paint);
		canvas.drawBitmap(bmp, getX(), getY(), paint);
	}




}
