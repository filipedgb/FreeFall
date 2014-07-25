package game.engine;

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
import android.widget.ImageButton;

/**
 * Esta classe representa o menu do jogo, que, consoante o que o jogador
 * escolher, pode jogar o jogo em si, pode verificar o highscore, ir para as
 * opções, ou sair
 * 
 * @author André Pires, Filipe Gama
 * @see Activity
 */
public class GameMainActivity extends Activity {
	//	private static int coins;
	//	public static final String filenameCoins = "FreeFallCoins";


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//	loadCoins();

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.main);

		// exit button
		ImageButton  buttonExit = (ImageButton ) findViewById(R.id.exit_btn);
		buttonExit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
				System.exit(0);
			}
		});
		
	

		// highscore button
		ImageButton  buttonHighscore = (ImageButton ) findViewById(R.id.scores_btn);
		buttonHighscore.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GameMainActivity.this,
						HighscoreActivity.class);
				startActivity(intent);
			}
		});

		// start button
		ImageButton  buttonStart = (ImageButton ) findViewById(R.id.start_btn);
		buttonStart.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GameMainActivity.this,
						PlayActivity.class);
				Tools.setLevel(1);
				startActivity(intent);
			}
		});

		// options button
		ImageButton  buttonOptions = (ImageButton ) findViewById(R.id.options_btn);

		buttonOptions.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GameMainActivity.this,
						OptionsActivity.class);
				startActivity(intent);
			}
		});

		// rules button
		ImageButton  rules = (ImageButton ) findViewById(R.id.rules_btn);

		rules.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GameMainActivity.this,
						HelpActivity.class);
				startActivity(intent);
			}
		});

		// market button
		ImageButton  market = (ImageButton ) findViewById(R.id.market_btn);

		market.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GameMainActivity.this,
						MarketActivity.class);
				startActivity(intent);
			}
		});


		//	updateCoins();
	}

	//	private void loadCoins() {
	//		File file = getFileStreamPath(filenameCoins);
	//
	//		if (!file.exists()) {
	//			setCoins(0);
	//		}
	//
	//		try {
	//			FileInputStream fis = openFileInput(filenameCoins);
	//			ObjectInputStream ois = new ObjectInputStream(fis);
	//			setCoins((Integer) ois.readObject());
	//			ois.close();
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//	}
	//
	//	public static void addCoins() {
	//		setCoins(getCoins() + 1);
	//	}
	//
	//	/**
	//	 * @return the coins
	//	 */
	//	public static int getCoins() {
	//		return coins;
	//	}
	//
	//	/**
	//	 * @param coins the coins to set
	//	 */
	//	public static void setCoins(int coins) {
	//		GameMainActivity.coins = coins;
	//	}
	//
	//	public void updateCoins() {
	//		TextView coinsTV = (TextView) findViewById(R.id.coinsTV);
	//		coinsTV.setText("COINS: " + GameMainActivity.getCoins());
	//	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		//	updateCoins();
		super.onResume();
	}
}