package game.engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;

public class Sprite {
	
	private int x, y, height , width, currentframe =0, direction;
	

	Bitmap spritesheet;
	
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth)
	{
	    int width = bm.getWidth();
	    int height = bm.getHeight();
	    float scaleWidth = ((float) newWidth) / width;
	    float scaleHeight = ((float) newHeight) / height;
	    // create a matrix for the manipulation
	    Matrix matrix = new Matrix();
	    // resize the bit map
	    matrix.postScale(scaleWidth, scaleHeight);
	    // recreate the new Bitmap
	    Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
	    return resizedBitmap;
	}
	
	
	public Sprite (int x, int y , int view_height, int view_width, Bitmap spritesheet) {
		this.spritesheet = spritesheet;
		//	spritesheet = getResizedBitmap(spritesheet,50,50);
		height = spritesheet.getHeight()/4;
		width = spritesheet.getWidth()/5;
		this.x = x;
		this.y = y;
			
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void draw(Canvas canvas) {
		update();
		Log.e("direcao", "" + direction);

		int srcX = currentframe*width;
		int srcY = direction*height;
					
		Rect src = new Rect(srcX,srcY,srcX+width,srcY+height);
		Rect dst = new Rect(x,y, x+width, y+height);
		
		canvas.drawBitmap(spritesheet, src, dst,null);
	}

	private void update() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.e("tintas", "" + currentframe);
		currentframe = ++currentframe%4;
	}

}
