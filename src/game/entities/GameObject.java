package game.entities;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class GameObject {
	private int height;
	private int width;

	protected float x;
	protected float y;
	protected float terminal_velocity = 50;
	protected float accelaration_y = -9.8f;
	protected float velocity_y = 0;
	protected float accelaration_x = 0;
	protected float velocity_x = 0;
	protected float resistence ;
	
	protected static Resources res;
	protected static int screen_height;
	protected static int screen_width;
	
	public static int getScreen_height() {
		return screen_height;
	}

	public static void setScreen_height(int screen_height) {
		GameObject.screen_height = screen_height;
	}

	public static int getScreen_width() {
		return screen_width;
	}

	public static void setScreen_width(int screen_width) {
		GameObject.screen_width = screen_width;
	}
	protected Bitmap bmp;


	public static Resources getRes() {
		return res;
	}

	public static void setRes(Resources res) {
		GameObject.res = res;
	}

	public GameObject(float x, float y, int height, int width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	public boolean colide(GameObject r) {
		if (r.getX()>x+width) return false;
		if (r.getY() > y +height) return false;
		if (r.getX()+r.getWidth()<x) return false;
		if (r.getY()+r.getHeight()<y) return false;
		return true;
	}

	public boolean colide(int x2, int y2) {
		if (x2>x+width) return false;
		if (y2>y+height) return false;
		if (x2<x) return false;
		if (y2<y) return false;
		return true;
	}


	public void move(int width ,int height) {

		resistence = (float) (-0.9*velocity_x);

		velocity_x = velocity_x + (accelaration_x + resistence)/25;
		velocity_y = velocity_y + (accelaration_y )/25;

		if(velocity_y >= terminal_velocity) {
			accelaration_y = 0;
		}

		if (getY()>-50) {
			setY(getY() + velocity_y/25);
			setX(getX() + velocity_x/25);
		} else {
			int x = (int) (Math.random()*(width-25));
			int y = (int) (Math.random()*200);
			setX(x);
			setY(height+50+y);
		}
	}

	public float getAccelaration_y() {
		return accelaration_y;
	}


	public void setAccelaration_y(float accelaration_y) {
		this.accelaration_y = accelaration_y;
	}


	public float getVelocity_y() {
		return velocity_y;
	}


	public void setVelocity_y(float velocity_y) {
		this.velocity_y = velocity_y;
	}


	public float getAccelaration_x() {
		return accelaration_x;
	}


	public void setAccelaration_x(float accelaration_x) {
		this.accelaration_x = accelaration_x;
	}


	public float getVelocity_x() {
		return velocity_x;
	}


	public void setVelocity_x(float velocity_x) {
		this.velocity_x = velocity_x;
	}


	abstract public void draw(Canvas canvas, Paint paint);

	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float d) {
		this.y = d;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
}
