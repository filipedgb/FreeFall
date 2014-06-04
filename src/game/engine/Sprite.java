package game.engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {

	private int x, y, height , width, currentframe =0, direction;
	private int rows;

	Bitmap spritesheet;


	/**
	 * Construtor da sprite.
	 * @param x - posição x 
	 * @param y - posição y
	 * @param view_height - altura do ecrã
	 * @param view_width - largura do ecrã
	 * @param columns - colunas da spritesheet
	 * @param rows - linhas da spritesheet
	 * @param spritesheet - spritesheet (imagem, bitmap)
	 */
	public Sprite (int x, int y , int view_height, int view_width, int columns, int rows, Bitmap spritesheet) {
		this.spritesheet = spritesheet;
		height = spritesheet.getHeight()/rows;
		width = spritesheet.getWidth()/columns;
		this.x = x;
		this.y = y;
		this.rows = rows;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	/**
	 * Função que automaticamente recorta a spritesheet de forma a ficarmos só com a parte que interessa
	 * @param canvas - onde vai ser desenhada a animação
	 */

	public void draw(Canvas canvas) {
		update();

		int srcX = currentframe*width;
		int srcY = direction*height;

		Rect src = new Rect(srcX,srcY,srcX+width,srcY+height);
		Rect dst = new Rect(x,y, x+width, y+height);

		canvas.drawBitmap(spritesheet, src, dst,null);
	}
	
	
	/**
	 * Função que actualiza a imgem (de forma a ficar animada)
	 */
	private void update() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		currentframe = ++currentframe%rows;
	}
}