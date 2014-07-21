package game.engine;

import game.config.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/**
 * Classe com a funcao de guardar dados sobre o ecra do telemovel, e das imagens
 * do jogo
 * 
 * @author André Pires, Filipe Gama
 * 
 */
public class Tools {

	// General info
	private static float screenWidth;
	private static float screenHeight;
	private static float drawUnity;
	private static float FPS = 75;
	private static Resources res;

	private static boolean mute = false;
	private static int level;
	private static int orientation = 0; // 0 - portrait, 1 -landscape

	// Images and animations
	private static Bitmap cloud;
	private static Bitmap health;
	private static Bitmap invulnerable;
	private static Bitmap fuel;
	private static Bitmap skymine;
	private static Bitmap slowmo;
	private static Bitmap boost;
	private static Bitmap invulnerableword;
	private static Bitmap satelite;
	private static Bitmap asteroid;
	private static Bitmap levelone;
	private static Bitmap malfunction;
	private static Bitmap player;
	private static Bitmap player_spritesheet;
	private static Sprite player_animation;
	private static Sprite malfunction_anim;
	
	private static Bitmap coin;

	public static void setLevel(int level) {
		Tools.level = level;
	}

	public static int getLevel() {
		return level;
	}

	/**
	 * Esta função foi retirada da internet sem sofrer qualquer tipo de
	 * alteração. O seu objectivo é simplesmente o de redimensionar um bitmap
	 * (sem estragar a qualidade do mesmo)
	 * 
	 * @param bm
	 * @param newHeight
	 * @param newWidth
	 * @return
	 */
	public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the bit map
		matrix.postScale(scaleWidth, scaleHeight);
		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
				matrix, false);
		return resizedBitmap;
	}

	/**
	 * Função que carrega as imagens todas logo no início do jogo.
	 * 
	 * @param resources
	 */

	public static void loadImages(Resources resources) {
		res = resources;

		cloud = BitmapFactory.decodeResource(res, R.drawable.nuvem);
		cloud = Bitmap.createScaledBitmap(cloud, (int) getDrawUnity(4),
				(int) getDrawUnity(4), true);

		satelite = BitmapFactory.decodeResource(res, R.drawable.satelite);
		satelite = Bitmap.createScaledBitmap(satelite, (int) getDrawUnity(4),
				(int) getDrawUnity(4), true);

		asteroid = BitmapFactory.decodeResource(res, R.drawable.asteroid);
		asteroid = Bitmap.createScaledBitmap(asteroid, (int) getDrawUnity(4),
				(int) getDrawUnity(4), true);

		invulnerable = BitmapFactory.decodeResource(res,
				R.drawable.invulnerable);
		invulnerable = Bitmap.createScaledBitmap(invulnerable,
				(int) getDrawUnity(1.5f), (int) getDrawUnity(1.5f), true);

		fuel = BitmapFactory.decodeResource(res, R.drawable.fuel);
		fuel = Bitmap.createScaledBitmap(fuel, (int) getDrawUnity(1.5f),
				(int) getDrawUnity(1.5f), true);

		skymine = BitmapFactory.decodeResource(res, R.drawable.mine);
		skymine = Bitmap.createScaledBitmap(skymine, (int) getDrawUnity(1.5f),
				(int) getDrawUnity(1.5f), true);

		slowmo = BitmapFactory.decodeResource(res, R.drawable.slowmo);
		slowmo = Bitmap.createScaledBitmap(slowmo, (int) getDrawUnity(1.5f),
				(int) getDrawUnity(1.5f), true);

		health = BitmapFactory.decodeResource(res, R.drawable.health);
		health = Bitmap.createScaledBitmap(health, (int) getDrawUnity(1.5f),
				(int) getDrawUnity(1.5f), true);

		boost = BitmapFactory.decodeResource(res, R.drawable.boostbonus);

		invulnerableword = BitmapFactory.decodeResource(res,
				R.drawable.invulnerableword);
		invulnerableword = Bitmap.createScaledBitmap(invulnerableword,
				(int) getDrawUnity(15), (int) getDrawUnity(3), true);

		malfunction = BitmapFactory.decodeResource(res,
				R.drawable.malfunc_sprite);
		malfunction = Tools.getResizedBitmap(malfunction,
				(int) Tools.getDrawUnity((float) 10.2),
				(int) Tools.getDrawUnity(40));
		malfunction_anim = new Sprite((int) Tools.getDrawUnity(3),
				(int) Tools.getDrawUnity(15), 3, 2, malfunction);
		
		coin = BitmapFactory.decodeResource(res, R.drawable.coin);
		coin = Bitmap.createScaledBitmap(coin, (int) getDrawUnity(1.5f),
				(int) getDrawUnity(1.5f), true);

	}

	public static Bitmap getPlayer_spritesheet() {
		return player_spritesheet;
	}

	public static void setPlayer_spritesheet(Bitmap player_spritesheet) {
		Tools.player_spritesheet = player_spritesheet;
	}

	public static Sprite getPlayer_animation() {
		return player_animation;
	}

	public static void setPlayer_animation(Sprite player_animation) {
		Tools.player_animation = player_animation;
	}

	public static void setRes(Resources res) {
		Tools.res = res;
	}

	public static Sprite getMalfunction_anim() {
		return malfunction_anim;
	}

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

	public static Bitmap getSatelite() {
		return satelite;
	}

	public static void setSatelite(Bitmap satelite) {
		Tools.satelite = satelite;
	}

	public static Bitmap getBoost() {
		return boost;
	}

	public static void init(int width, int height) {
		screenWidth = width;
		screenHeight = height;
		drawUnity = screenWidth / 20;
	}

	public static float getScreenWidth() {
		return screenWidth;
	}

	public static Bitmap getLevelone() {
		return levelone;
	}

	public static void setLevelone(Bitmap levelone) {
		Tools.levelone = levelone;
	}

	public static float getScreenHeight() {
		return screenHeight;
	}

	public static float getDrawUnity(float numberUnities) {
		return drawUnity * numberUnities;
	}

	public static float getFPS() {
		return FPS;
	}

	public static Bitmap getInvulnerableword() {
		return invulnerableword;
	}

	public static Bitmap getAsteroid() {
		return asteroid;
	}

	public static Bitmap getPlayer() {
		return player;
	}

	public static boolean isMute() {
		return mute;
	}

	public static void setMute(boolean mute) {
		Tools.mute = mute;
	}

	public static int getOrientation() {
		return orientation;
	}

	public static void setOrientation(int orientation) {
		Tools.orientation = orientation;
	}

	public static Bitmap getCoin() {
		return coin;
	}
}