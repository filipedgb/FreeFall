package game.engine;

import game.config.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class GameMainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.main);

		//exit button
		Button buttonExit = (Button) findViewById(R.id.exit_button);
		buttonExit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
				System.exit(0);
			}
		});

		//highscore button
		Button buttonHighscore = (Button) findViewById(R.id.highscore_button);
		buttonHighscore.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GameMainActivity.this, HighscoreActivity.class);
				startActivity(intent);
			}
		});

		//start button
		Button buttonStart = (Button) findViewById(R.id.start_button);
		buttonStart.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GameMainActivity.this, PlayActivity.class);
				startActivity(intent);
			}
		});
	}
}