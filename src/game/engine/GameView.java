package game.engine;

import game.entities.GameObject;
import game.states.PlayState;
import android.content.Context;
import android.graphics.*;
import android.util.Log;
import android.view.*;

public class GameView extends View{
	private Paint paint;
//	private int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
//	private long next_game_tick = System.currentTimeMillis();
//	private int sleep_time = 0;
//	private final static int INTERVAL = 200;
//	private int FRAMES_PER_SECOND = 25;
//	private Clouds[] inimigos;
//	private Alien principal;
//	private boolean jogoIniciado = false;

	private PlayState game;



	public GameView(Context context) {
		super(context);
		GameObject.setRes(this.getResources());
			
		paint = new Paint();
		game = new PlayState(this);
		new GameLoop(this,game);
		/*
		Thread minhaThread = new Thread(this);
		minhaThread.setPriority(Thread.MIN_PRIORITY);
		minhaThread.start();
		 */
	}


	/*
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

	 */

	public void draw(Canvas canvas) {
		super.draw(canvas);
		if (game.isGameStarted() ==false) {
			GameObject.setScreen_height(this.getHeight());
			Log.e("gameview", "" + this.getHeight());
			GameObject.setScreen_width(this.getWidth());			
			game.init();
		}

		//desenha cor de fundo
		canvas.drawColor(Color.argb(255, 135, 206, 235));

		//desenha objectos
		for (int i = 0; i < game.getObjects().size(); i++) {
			game.getObjects().get(i).draw(canvas, paint);
		}
		
		/**
		 * Desenha barras de vida e combustível
		 */
		paint.setColor(Color.GREEN);
        canvas.drawRect(30, 30, 30+game.getPlayer().getLifepoints()*(30+getWidth()/4)/game.getPlayer().getMax_life(), 40, paint);
		paint.setColor(Color.BLUE);
        canvas.drawRect(30, 50, 30+game.getPlayer().getFuel()*(30+getWidth()/4)/game.getPlayer().getMax_fuel(), 60, paint);



		game.getPlayer().draw(canvas,paint);
		if(game.getHealth_item().isActive()) game.getHealth_item().draw(canvas,paint);
//		game.getSlowmotion_item().draw(canvas,paint);
		if(game.getNodamage_item().isActive())game.getNodamage_item().draw(canvas,paint);
		if(game.getFuel_item().isActive())game.getFuel_item().draw(canvas,paint);


		//defino a cor do texto
		
		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 92, 87, 8));
		paint.setTextSize(20);
		canvas.drawText("SCORE: " + (int) game.getPoints(), 2*canvas.getWidth()/3 , 30, paint);
//		paint.setTextSize(10);
//		canvas.drawText("Life: " + game.getPlayer().getLifepoints(), getWidth()/3, 50, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			return true;
		}
		
		if(event.getAction() == MotionEvent.ACTION_MOVE) { 
			if(event.getY() < this.getHeight()/6 && game.getPlayer().getFuel() > 0)  {
				game.setGlobalAccelaration(game.getGlobalAccelaration_x(),game.getGlobalAccelaration_y()+10);
				game.getPlayer().addFuel(-0.05f*game.getGlobalAccelaration_y());
			}
						
			else if(event.getY() > 5*this.getHeight()/6 && game.getPlayer().getFuel() > 0 ) {
				game.setGlobalAccelaration(game.getGlobalAccelaration_x(),game.getGlobalAccelaration_y()-10);
				game.getPlayer().addFuel(0.05f*game.getGlobalAccelaration_y());
			}

			if(event.getX() > this.getWidth()/2) 
				game.setGlobalAccelaration(game.getGlobalAccelaration_x()-10,game.getGlobalAccelaration_y());
						
			else
				game.setGlobalAccelaration(game.getGlobalAccelaration_x()+10,game.getGlobalAccelaration_y());
			
		}
		
		if(event.getAction() == MotionEvent.ACTION_UP) {
			Log.e("coiso", "Levantou");
			game.setGlobalAccelaration(0,0);
		}
		
		
		
		return super.onTouchEvent(event);
	}

	


}
