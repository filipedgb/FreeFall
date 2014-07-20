package game.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import game.config.R;
import game.engine.rules.HelpActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Esta classe representa o menu do jogo, que, consoante o que o jogador
 * escolher, pode jogar o jogo em si, pode verificar o highscore, ir para as
 * opções, ou sair
 * 
 * @author André Pires, Filipe Gama
 * @see Activity
 */
public class GameMainActivity extends Activity {
	private static int coins;
	public static final String filenameCoins = "FreeFallCoins";


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadCoins();

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.main);

		// exit button
		Button buttonExit = (Button) findViewById(R.id.exit_button);
		buttonExit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
				System.exit(0);
			}
		});

		// highscore button
		Button buttonHighscore = (Button) findViewById(R.id.highscore_button);
		buttonHighscore.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GameMainActivity.this,
						HighscoreActivity.class);
				startActivity(intent);
			}
		});

		// start button
		Button buttonStart = (Button) findViewById(R.id.start_button);

		buttonStart.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GameMainActivity.this,
						PlayActivity.class);
				Tools.setLevel(1);
				startActivity(intent);
			}
		});
		
		// options button
		Button buttonOptions = (Button) findViewById(R.id.options_button);

		buttonOptions.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GameMainActivity.this,
						OptionsActivity.class);
				startActivity(intent);
			}
		});

		// rules button
		Button rules = (Button) findViewById(R.id.controls);

		rules.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GameMainActivity.this,
						HelpActivity.class);
				startActivity(intent);
			}
		});


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
		GameMainActivity.coins = coins;
	}

	public void updateCoins() {
		TextView coinsTV = (TextView) findViewById(R.id.coinsTV);
		coinsTV.setText("COINS: " + GameMainActivity.getCoins());
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		updateCoins();
		super.onResume();
	}
}