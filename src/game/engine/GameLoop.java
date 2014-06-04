package game.engine;

import android.util.Log;
import game.states.PlayState;

public class GameLoop extends Thread {

	private int FRAMES_PER_SECOND = (int) Tools.getFPS();
	private int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
	private long next_game_tick = System.currentTimeMillis();
	private int sleep_time = 0;
	private boolean running = true;
	
	private GameView current_gameview;
	private PlayState current_gamestate;
	private static GameLoop current_instance = null;

	
	public GameLoop(GameView gameview,PlayState gamestate) {
		stopThread();
		current_instance = this;
		current_gameview = gameview;
		current_gamestate = gamestate;
		this.setPriority(Thread.MIN_PRIORITY);
		this.start();
	}
	
	public static void stopThread() {
		if(current_instance != null) {
			current_instance.running = false;
			current_instance = null;
			
		}
	}

	public void run() {
		while (running) {
			current_gamestate.update();
			current_gameview.postInvalidate();
			next_game_tick += SKIP_TICKS;
			sleep_time = (int) (next_game_tick - System.currentTimeMillis());
			if(sleep_time >= 0) {
				try {
					java.lang.Thread.sleep(sleep_time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

}
