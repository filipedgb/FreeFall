package game.engine;

import game.config.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

/**
 * Representa o menu de opcoes
 * 
 * @author André Pires, Filipe Gama
 * @see Activity
 */
public class OptionsActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.options);

		ToggleButton toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
		toggleButton1
		.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					Tools.setMute(!Tools.isMute());
				} else {
					// The toggle is disabled
				}
			}
		});

		ToggleButton toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
		toggleButton2
		.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					Controllers.getControllerInstance()
					.setControllerSensor(
							!Controllers
							.getControllerInstance()
							.isControllerSensor());
				} else {

				}
			}
		});


		Button buttonChooseLevel = (Button) findViewById(R.id.chooseLevel_button);

		buttonChooseLevel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OptionsActivity.this,
						ChooseLevelActivity.class);
				startActivity(intent);
			}
		});
	}
}
