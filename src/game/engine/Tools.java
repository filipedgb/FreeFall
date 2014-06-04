package game.engine;

import game.config.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tools {

	private static float screenWithd;
	private static float screenHeight;
	private static float drawUnity;
	private static float FPS = 75;
	private static Resources res;

	private static Bitmap cloud ;
	private static Bitmap health;
	private static Bitmap invulnerable;
	private static Bitmap fuel;
	private static Bitmap skymine;
	private static Bitmap slowmo;

	public static float getDrawUnity() {
		return drawUnity;
	}

	public static Resources getRes() {
		return res;
	}

	public static Bitmap getCloud() {
		return cloud;
	}

	public static Bitmap getHealth() {
		return health;
	}

	public static Bitmap getInvulnerable() {
		return invulnerable;
	}

	public static Bitmap getFuel() {
		return fuel;
	}

	public static Bitmap getSkymine() {
		return skymine;
	}

	public static Bitmap getSlowmo() {
		return slowmo;
	}

	public static void loadImages(Resources resources) {
		res = resources;

		cloud = BitmapFactory.decodeResource(res, R.drawable.nuvem);
		cloud = Bitmap.createScaledBitmap(cloud, (int) getDrawUnity(5), (int) getDrawUnity(5), true);

		invulnerable = BitmapFactory.decodeResource(res, R.drawable.invulnerable);
		invulnerable = Bitmap.createScaledBitmap(invulnerable, (int) getDrawUnity(2), (int) getDrawUnity(2), true);

		fuel = BitmapFactory.decodeResource(res, R.drawable.fuel);
		fuel = Bitmap.createScaledBitmap(fuel, (int) getDrawUnity(2), (int) getDrawUnity(2), true);

		skymine = BitmapFactory.decodeResource(res, R.drawable.mine);
		skymine = Bitmap.createScaledBitmap(skymine, (int) getDrawUnity(2), (int) getDrawUnity(2), true);

		slowmo = BitmapFactory.decodeResource(res, R.drawable.slowmo);
		slowmo = Bitmap.createScaledBitmap(slowmo, (int) getDrawUnity(2), (int) getDrawUnity(2), true);

		health = BitmapFactory.decodeResource(res, R.drawable.health);
		health = Bitmap.createScaledBitmap(health, (int) getDrawUnity(2), (int) getDrawUnity(2), true);
	}


	public static void init(int width, int height) {
		screenWithd = width;
		screenHeight = height;
		drawUnity = screenWithd/20;
	}

	public static float getScreenWithd() {
		return screenWithd;
	}

	public static float getScreenHeight() {
		return screenHeight;
	} 

	public static float getDrawUnity(float numberUnities) {
		return drawUnity*numberUnities;
	}

	public static float getFPS() {
		return FPS;
	}
}