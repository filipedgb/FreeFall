package game.main;

import game.logic.GameState;
import game.objects.Alien;
import game.objects.Clouds;
import android.content.Context;
import android.graphics.*;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.*;

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
	private int moveLeft_counter= 0;

	
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
//		canvas.drawBitmap(bmpFundo, 0, 0, paint);
		
		//define a cor do desenho
		paint.setColor(Color.BLUE);
		for (int i = 0; i < game.getClouds().length; i++) {
			game.getClouds()[i].draw(canvas, paint);
		}
		
	
		game.getPlayer().draw(canvas,paint);
		
		//defino a cor do texto
		paint.setColor(Color.WHITE);
		paint.setTextSize(15);
	//	canvas.drawText("Pontos: " + pontos, 0, 30, paint);
		
		canvas.drawText("Life points: " + game.getPlayer().getLifepoints(), getWidth()/3, 30, paint);
		canvas.drawText("Turbo points: " + (int) game.getPlayer().getTurbopoints(), getWidth()/3, 50, paint);

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
			
			if(event.getX() < getWidth()/2) {
				//game.getPlayer().moveLeft();
				game.setMoveCounterLeft(15);
				game.setMovePlayerLeft(true);
			}
						
			if(event.getX() > getWidth()/2) {
				game.setMoveCounterRight(15);
				game.setMovePlayerRight(true);
			}
			
			if(event.getY() > (4*getHeight()/5) && game.getPlayer().getTurbopoints() > 0) {
				game.getPlayer().setTurbo(true);
//				FRAMES_PER_SECOND = 65;
//				SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
//				Log.e("entrou", "" + FRAMES_PER_SECOND);
			}
			
			if(event.getY() < (getHeight()/5)) {
				game.getPlayer().setTurbo(false);
//				FRAMES_PER_SECOND = 25;
//				SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
//				Log.e("entrou", "" + FRAMES_PER_SECOND);

			}
			
			
		
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
