package game.engine;

import game.config.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

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
	}
}
