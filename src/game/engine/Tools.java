package game.engine;

import game.config.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/**
 * Classe com a funcao de guardar dados sobre o ecra do telemovel, e das imagens do jogo
 * @author André Pires, Filipe Gama
 *
 */
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
	private static Bitmap boost;
	private static Bitmap invulnerableword;

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

	/**
	 *  Esta função foi retirada da internet sem sofrer qualquer tipo de alteração.
	 *  O seu objectivo é simplesmente o de redimensionar um bitmap (sem estragar a qualidade do mesmo)
	 *  
	 * @param bm
	 * @param newHeight
	 * @param newWidth
	 * @return
	 */
	public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth)
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

	/**
	 * Função que carrega as imagens todas logo no início do jogo.
	 * @param resources
	 */

	public static void loadImages(Resources resources) {
		res = resources;

		cloud = BitmapFactory.decodeResource(res, R.drawable.nuvem);
		cloud = Bitmap.createScaledBitmap(cloud, (int) getDrawUnity(7), (int) getDrawUnity(7), true);

		invulnerable = BitmapFactory.decodeResource(res, R.drawable.invulnerable);
		invulnerable = Bitmap.createScaledBitmap(invulnerable, (int) getDrawUnity(1.5f), (int) getDrawUnity(1.5f), true);

		fuel = BitmapFactory.decodeResource(res, R.drawable.fuel);
		fuel = Bitmap.createScaledBitmap(fuel, (int) getDrawUnity(1.5f), (int) getDrawUnity(1.5f), true);

		skymine = BitmapFactory.decodeResource(res, R.drawable.mine);
		skymine = Bitmap.createScaledBitmap(skymine, (int) getDrawUnity(1.5f), (int) getDrawUnity(1.5f), true);

		slowmo = BitmapFactory.decodeResource(res, R.drawable.slowmo);
		slowmo = Bitmap.createScaledBitmap(slowmo, (int) getDrawUnity(1.5f), (int) getDrawUnity(1.5f), true);

		health = BitmapFactory.decodeResource(res, R.drawable.health);
		health = Bitmap.createScaledBitmap(health, (int) getDrawUnity(1.5f), (int) getDrawUnity(1.5f), true);

		boost = BitmapFactory.decodeResource(res, R.drawable.boostbonus);

		invulnerableword = BitmapFactory.decodeResource(res, R.drawable.invulnerableword);
		invulnerableword = Bitmap.createScaledBitmap(invulnerableword, (int) getDrawUnity(15), (int) getDrawUnity(3), true);
	}


	public static Bitmap getBoost() {
		return boost;
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

	public static Bitmap getInvulnerableword() {
		return invulnerableword;
	}
}