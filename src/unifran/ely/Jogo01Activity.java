package unifran.ely;

import android.app.Activity;
import android.os.Bundle;

public class Jogo01Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        GameView game = new GameView(this);
        setContentView(game);
    }
}