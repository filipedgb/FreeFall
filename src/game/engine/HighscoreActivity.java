package game.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import game.config.R;
import game.states.HighscoreState;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Representa o menu de consulta dos highscores
 * @author André Pires, Filipe Gama
 * @see Activity
 */
public class HighscoreActivity extends Activity {
	private HighscoreState highscores;
	public static final String filename = "FreeFallHighscore";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.highscore);

		loadHighscores();
		updateHighscore();
	}

	/**
	 * Faz load dos highscores atraves do ficheiro guardado na memoria interna do telemovel
	 */
	public void loadHighscores() {
		File file = getFileStreamPath(filename);

		if(!file.exists()) {
			highscores = new HighscoreState();
		}

		try {
			FileInputStream fis = openFileInput(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			highscores = (HighscoreState) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HighscoreState getHighscores() {
		return highscores;
	}

	public void setHighscores(HighscoreState highscores) {
		this.highscores = highscores;
	}

	/**
	 * Atualiza os highscores a mostrar
	 */
	public void updateHighscore() {
		ArrayList<ArrayList<String>> h = highscores.getHighscores();

		TextView tv;

		/** update names */
		tv = (TextView) findViewById(R.id.name1);
		tv.setText(h.get(0).get(0));

		tv = (TextView) findViewById(R.id.name2);
		tv.setText(h.get(1).get(0));

		tv = (TextView) findViewById(R.id.name3);
		tv.setText(h.get(2).get(0));

		tv = (TextView) findViewById(R.id.name4);
		tv.setText(h.get(3).get(0));

		tv = (TextView) findViewById(R.id.name5);
		tv.setText(h.get(4).get(0));

		tv = (TextView) findViewById(R.id.name6);
		tv.setText(h.get(5).get(0));

		tv = (TextView) findViewById(R.id.name7);
		tv.setText(h.get(6).get(0));

		tv = (TextView) findViewById(R.id.name8);
		tv.setText(h.get(7).get(0));

		tv = (TextView) findViewById(R.id.name9);
		tv.setText(h.get(8).get(0));

		tv = (TextView) findViewById(R.id.name10);
		tv.setText(h.get(9).get(0));

		/** update scores */
		tv = (TextView) findViewById(R.id.score1);
		tv.setText(h.get(0).get(1));

		tv = (TextView) findViewById(R.id.score2);
		tv.setText(h.get(1).get(1));

		tv = (TextView) findViewById(R.id.score3);
		tv.setText(h.get(2).get(1));

		tv = (TextView) findViewById(R.id.score4);
		tv.setText(h.get(3).get(1));

		tv = (TextView) findViewById(R.id.score5);
		tv.setText(h.get(4).get(1));

		tv = (TextView) findViewById(R.id.score6);
		tv.setText(h.get(5).get(1));

		tv = (TextView) findViewById(R.id.score7);
		tv.setText(h.get(6).get(1));

		tv = (TextView) findViewById(R.id.score8);
		tv.setText(h.get(7).get(1));

		tv = (TextView) findViewById(R.id.score9);
		tv.setText(h.get(8).get(1));

		tv = (TextView) findViewById(R.id.score10);
		tv.setText(h.get(9).get(1));

		/** update dates */
		tv = (TextView) findViewById(R.id.date1);
		tv.setText(h.get(0).get(2));

		tv = (TextView) findViewById(R.id.date2);
		tv.setText(h.get(1).get(2));

		tv = (TextView) findViewById(R.id.date3);
		tv.setText(h.get(2).get(2));

		tv = (TextView) findViewById(R.id.date4);
		tv.setText(h.get(3).get(2));

		tv = (TextView) findViewById(R.id.date5);
		tv.setText(h.get(4).get(2));

		tv = (TextView) findViewById(R.id.date6);
		tv.setText(h.get(5).get(2));

		tv = (TextView) findViewById(R.id.date7);
		tv.setText(h.get(6).get(2));

		tv = (TextView) findViewById(R.id.date8);
		tv.setText(h.get(7).get(2));

		tv = (TextView) findViewById(R.id.date9);
		tv.setText(h.get(8).get(2));

		tv = (TextView) findViewById(R.id.date10);
		tv.setText(h.get(9).get(2));
	}
}
