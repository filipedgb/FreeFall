package game.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import game.config.R;
import game.states.HighscoreState;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class PlayActivity extends Activity {

	private SensorManager sensorManager;

	private MediaPlayer mediaPlayer;
	private HighscoreState highscores;
	
	private static boolean clicked = false; 

	/**
	 * @return the highscores
	 */
	public HighscoreState getHighscores() {
		return highscores;
	}

	/**
	 * @param highscores the highscores to set
	 */
	public void setHighscores(HighscoreState highscores) {
		this.highscores = highscores;
	}

	private static PlayActivity singleInstance = null;
	
	private static String currentPlayerName = null;

	public static PlayActivity getSingleInstance()  {
		return singleInstance; 
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		GameView game_view = new GameView(getBaseContext());

		super.onCreate(savedInstanceState);
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);

		mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.hithurt);

		singleInstance = this;

		sensorManager.registerListener(new Controllers(game_view,game_view.getGame()),
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(game_view);

		loadHighscores();
	}

	public void saveHighscores() {
		try {
			FileOutputStream fos = openFileOutput(HighscoreActivity.filename, MODE_WORLD_READABLE); 
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(highscores);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void askName() {
		setClicked(false);
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Name");
		alert.setMessage("Insert your name: ");

		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);
		
		Log.e("ask name", "here");

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String value = input.getText().toString();
				setCurrentPlayerName(value);
				addH(value, (int) GameLoop.getPoints());
				saveHighscores();
			}
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				setCurrentPlayerName(null);
			}
		});
		
		alert.show();
	}

	public void addH (String name, int score) {
		highscores.addHighscore(name, score);
	}

	public void loadHighscores() {
		File file = getFileStreamPath(HighscoreActivity.filename);

		if(!file.exists()) {
			highscores = new HighscoreState();
		}

		else {
			try {
				FileInputStream fis = openFileInput(HighscoreActivity.filename);
				ObjectInputStream ois = new ObjectInputStream(fis);
				highscores = (HighscoreState) ois.readObject();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onBackPressed() {
		GameLoop.stopThread(0);
		super.onBackPressed();
	}

	public void playHitSound() {
		mediaPlayer.start();
	}

	public static String getCurrentPlayerName() {
		return currentPlayerName;
	}

	public static void setCurrentPlayerName(String currentPlayerName) {
		PlayActivity.currentPlayerName = currentPlayerName;
	}

	public static boolean isClicked() {
		return clicked;
	}

	public static void setClicked(boolean clicked) {
		PlayActivity.clicked = clicked;
	}
}
