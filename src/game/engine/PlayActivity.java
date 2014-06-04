package game.engine;

import game.config.R;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;



public class PlayActivity extends Activity {

	private SensorManager sensorManager;

	private MediaPlayer mediaPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		GameView game_view = new GameView(getBaseContext());
		
		super.onCreate(savedInstanceState);
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		
		mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.hithurt);
		
		
		sensorManager.registerListener(new Controllers(game_view,game_view.getGame()),
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(game_view);
	}
	
	public void playHitSound() {
		mediaPlayer.start();
	}

}
