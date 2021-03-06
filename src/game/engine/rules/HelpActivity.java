package game.engine.rules;

import game.config.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class HelpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.help);


		ImageButton controls = (ImageButton) findViewById(R.id.controls);
		controls.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(HelpActivity.this,
						ControlsActivity.class);
				startActivity(intent);
			}
		});

		ImageButton items = (ImageButton) findViewById(R.id.items);
		items.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(HelpActivity.this,
						ItemsActivity.class);
				startActivity(intent);
			}
		});



	}

}
