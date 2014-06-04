package game.engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

public class Sprite {

	private int x, y, height , width, currentframe =0, direction;
	private int rows, columns;

	Bitmap spritesheet;



	public Sprite (int x, int y , int view_height, int view_width, int columns, int rows, Bitmap spritesheet) {
		this.spritesheet = spritesheet;
		height = spritesheet.getHeight()/rows;
		width = spritesheet.getWidth()/columns;
		this.x = x;
		this.y = y;
		this.rows = rows;
		this.columns = this.columns;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void draw(Canvas canvas) {
		update();

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
			e.printStackTrace();
		}
		currentframe = ++currentframe%rows;
	}
}