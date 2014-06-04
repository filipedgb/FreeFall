package game.engine;

import android.os.Handler;
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

	private static Handler h;

	private static float points;
	/**
	 * @return the points
	 */
	public static float getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public static void setPoints(float points) {
		GameLoop.points = points;
	}

	public GameLoop(GameView gameview,PlayState gamestate) {
		stopThread(0);
		current_instance = this;
		current_gameview = gameview;
		current_gamestate = gamestate;
		this.setPriority(Thread.MIN_PRIORITY);
		h = new Handler();
		this.start();
	}

	public static void stopThread(float p) {
		if(current_instance != null) {
			current_instance.running = false;
			current_instance = null;
			points = p;

			Thread t = new Thread() {
				public void run() {
					try {
						h.post(askName);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			};

			if(PlayActivity.getSingleInstance().getHighscores().getScoreIndex((int) points) < 10) {
				t.run();
			}
		}
	}

	private final static Runnable askName = new Runnable() {
		public void run() {
			PlayActivity.getSingleInstance().askName();
		}
	};

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
					e.printStackTrace();
				}
			}
		}

	}

}
