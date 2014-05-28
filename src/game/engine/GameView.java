package game.engine;

import game.entities.GameObject;
import game.states.PlayState;
import android.content.Context;
import android.graphics.*;
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
			game.init();
		}

		//desenha cor de fundo
		canvas.drawColor(Color.argb(255, 204, 255, 255));

		//define a cor do desenho
		for (int i = 0; i < game.getObjects().size(); i++) {
			game.getObjects().get(i).draw(canvas, paint);
		}

		game.getPlayer().draw(canvas,paint);
		game.getHealth_item().draw(canvas,paint);
		game.getHealth_item().draw(canvas,paint);
		game.getSlowmotion_item().draw(canvas,paint);
		game.getNodamage_item().draw(canvas,paint);

		//defino a cor do texto
		paint.setColor(Color.WHITE);
		paint.setTextSize(20);
		canvas.drawText("Score: " + (int) game.getPoints(), getWidth()/3, 70, paint);
		paint.setTextSize(10);
		canvas.drawText("Life: " + game.getPlayer().getLifepoints(), getWidth()/3, 50, paint);
	}

	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		float temp;
		
		if (action == MotionEvent.ACTION_DOWN) {
			game.setMovePlayer(true);
			game.setMoveCounter(25);

			if(event.getX() < getWidth()/2) temp = this.getWidth()/2 ;
			else temp = - this.getWidth()/2;
			//	game.getPlayer().setVelocity_x((event.getX()-getWidth()/2));
			for(int i = 0; i < game.getObjects().size(); i++){
				game.getObjects().get(i).setVelocity_x(temp);
			}

			game.getHealth_item().setVelocity_x(temp);
			game.getSlowmotion_item().setVelocity_x(temp);
			game.getNodamage_item().setVelocity_x(temp);

		}

		return super.onTouchEvent(event);
	}

	//termina o jogo


}
