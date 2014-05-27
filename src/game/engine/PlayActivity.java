package game.engine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class PlayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(new GameView(getBaseContext()));
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent intent = new Intent(PlayActivity.this, GameMainActivity.class);
        startActivity(intent);
        finish();
	}
	
	

	
	

}
