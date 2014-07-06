package game.engine;

import game.config.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ChooseLevelActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.chooselevel);

		//Level 1
		ImageButton lvl1 = (ImageButton) findViewById(R.id.level1Button);
		lvl1.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ChooseLevelActivity.this,
						PlayActivity.class);
				Tools.setLevel(1);
				startActivity(intent);
			}
		});

		//Level 2
		ImageButton lvl2 = (ImageButton) findViewById(R.id.level2Button);
		lvl2.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ChooseLevelActivity.this,
						PlayActivity.class);
				Tools.setLevel(2);
				startActivity(intent);
			}
		});

		//Level 3
		ImageButton lvl3 = (ImageButton) findViewById(R.id.level3Button);
		lvl3.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ChooseLevelActivity.this,
						PlayActivity.class);
				Tools.setLevel(3);
				startActivity(intent);
			}
		});
	}
}
