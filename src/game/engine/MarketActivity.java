package game.engine;

import game.config.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MarketActivity extends Activity {

	private static int coins;
	public static final String filenameCoins = "FreeFallCoins";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.market);
		
		loadCoins();
		updateCoins();

	}
	
	private void loadCoins() {
		File file = getFileStreamPath(filenameCoins);

		if (!file.exists()) {
			setCoins(0);
		}

		try {
			FileInputStream fis = openFileInput(filenameCoins);
			ObjectInputStream ois = new ObjectInputStream(fis);
			setCoins((Integer) ois.readObject());
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addCoins() {
		setCoins(getCoins() + 1);
	}

	/**
	 * @return the coins
	 */
	public static int getCoins() {
		return coins;
	}

	/**
	 * @param coins the coins to set
	 */
	public static void setCoins(int coins) {
		MarketActivity.coins = coins;
	}

	public void updateCoins() {
		TextView coinsTV = (TextView) findViewById(R.id.coinsTV);
		coinsTV.setText("COINS: " + MarketActivity.getCoins());
	}
	
	@Override
	protected void onResume() {
		updateCoins();
		super.onResume();
	}


}
