package game.engine;

import game.config.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

/**
 * Representa o menu de opcoes
 * @author André Pires, Filipe Gama
 * @see Activity
 */
public class OptionsActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.options);
		
		ToggleButton toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
		toggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		            // The toggle is enabled
		        } else {
		            // The toggle is disabled
		        }
		    }
		});
		
		ToggleButton toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
		toggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		        	Controllers.getControllerInstance().setControllerSensor(!Controllers.getControllerInstance().isControllerSensor());
		        } else {
		        	
		        }
		    }
		});
	}
}
