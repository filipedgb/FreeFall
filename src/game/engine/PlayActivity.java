package game.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import game.config.R;
import game.states.HighscoreState;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;



public class PlayActivity extends Activity {

	private SensorManager sensorManager;

	private MediaPlayer mediaPlayer;
	private HighscoreState highscores;
	
	private static PlayActivity singleInstance = null;

	public static PlayActivity getSingleInstance()  {
		return singleInstance; 
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		GameLoop.stopThread();
		super.onBackPressed();
		Log.e("entrou","backbutton");
	}

	public void playHitSound() {
		mediaPlayer.start();
	}

}
