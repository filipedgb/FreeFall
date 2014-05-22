package game.main;

import game.states.GameState;
import android.content.Context;
import android.graphics.*;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameView extends View implements Runnable {
	private final static int INTERVAL = 200;
	private Paint paint;
//	private Clouds[] inimigos;
//	private Alien principal;
//	private boolean jogoIniciado = false;
	
	private GameState game;
	private int FRAMES_PER_SECOND = 25;
    private int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
	private long next_game_tick = System.currentTimeMillis();
    private int sleep_time = 0;
	private boolean running = true;

	
	public GameView(Context context) {
		super(context);
		paint = new Paint();
		game = new GameState(this);
		Thread minhaThread = new Thread(this);
		minhaThread.setPriority(Thread.MIN_PRIORITY);
		minhaThread.start();
	}
	
	

	public void run() {
		while (running) {
			game.update();
			postInvalidate();
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
			

	
	public void draw(Canvas canvas) {
		super.draw(canvas);
		if (game.isGameStarted() ==false) {
			game.init();
		}
		
		
		//desenha cor de fundo
		canvas.drawColor(Color.BLACK);

		
		//define a cor do desenho
		paint.setColor(Color.BLUE);
		for (int i = 0; i < game.getObjects().size(); i++) {
			game.getObjects().get(i).draw(canvas, paint);
		}
		
	
		game.getPlayer().draw(canvas,paint);
		game.getHealth_item().draw(canvas,paint);
		game.getHealth_item().draw(canvas,paint);
		game.getSlowmotion_item().draw(canvas,paint);
		
		//defino a cor do texto
		paint.setColor(Color.WHITE);
		paint.setTextSize(20);
		canvas.drawText("Score: " + (int) game.getPoints(), getWidth()/3, 70, paint);
		paint.setTextSize(10);
		canvas.drawText("Life: " + game.getPlayer().getLifepoints(), getWidth()/3, 50, paint);
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		//pega o evento
		boolean hold = true;
		int action = event.getAction();
		//pega a posicao do dedo
		int x = (int) event.getX();
		int y = (int) event.getY();
		if (action == MotionEvent.ACTION_DOWN) {
			//afundou o dedo
			//hold = true;
//			if(event.getX() < getWidth()/2) {
//				game.setMoveCounterLeft(15);
//				game.setMovePlayerLeft(true);
//			}
//						
//			if(event.getX() > getWidth()/2) {
//				game.setMoveCounterRight(15);
//				game.setMovePlayerRight(true);
//			}
//			
//			
//			
//			if(event.getY() > (4*getHeight()/5) && game.getPlayer().getTurbopoints() > 0) {
//				game.getPlayer().setTurbo(true);
//
//			}
//			
//			if(event.getY() < (getHeight()/5)) {
//				game.getPlayer().setTurbo(false);
//			}
			
			game.setMovePlayer(true);
			game.setMoveCounter(25);
			game.getPlayer().setVelocity_x(event.getX() - game.getPlayer().getX());
	
			
		} else if (action==MotionEvent.ACTION_UP) {
			//soltou o dedo
			
			
		//	hold = false;

		}
		else if (action==MotionEvent.ACTION_MOVE) {
			//movimentou o dedo
			
			
		}
		
		return super.onTouchEvent(event);
	}
	
	//termina o jogo
	public void release() {
		running = false;
	}

	
}
