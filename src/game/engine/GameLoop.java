package game.engine;

import android.os.Handler;
import game.states.PlayState;

/**
 * Esta classe representa uma thread que esta sempre a correr enquanto o jogo esta a decorrer
 * @author André Pires, Filipe Gama
 * @see Thread
 */
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
	private static boolean askingName = true;

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

	/**
	 * Termina a thread e verifica se é necessario guardar os scores
	 * @param p score a verificar
	 */
	public static void stopThread(float p) {
		if(current_instance != null) {
			current_instance.running = false;
			current_instance = null;
			points = p;

			if(PlayActivity.getSingleInstance().getHighscores().getScoreIndex((int) points) < 10) {
				askingName=true;
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
				t.run();
			}
		}
	}

	public static GameLoop getCurrent_instance() {
		return current_instance;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	/**
	 * Serve para perguntar o nome ao jogador caso entre no highscore
	 */
	private final static Runnable askName = new Runnable() {
		public void run() {
			if(askingName) {
				PlayActivity.getSingleInstance().askName();
				askingName = false;
			}
		}
	};

	/**
	 * Funcao que esta a decorrer durante o jogo, que atualiza o display
	 */
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
	
	public void refresh() {
		current_instance = new GameLoop(current_gameview,current_gamestate);
	}

}
