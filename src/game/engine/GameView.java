package game.engine;

import game.config.R;
import game.entities.GameObject;
import game.entities.Player;
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
	
	/**
	 * 0 - up 1 -down 2-left 3 -right
	 */
	private int direction;

	public GameView(Context context) {
		super(context);
		GameObject.setRes(this.getResources());
			
		paint = new Paint();
		game = new PlayState(this);
		new GameLoop(this,game);
		
	}

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
		canvas.drawRect(30, 30, 30+game.getPlayer().getLifepoints()*(30+getWidth()/4)/Player.getMax_life(), 40, paint);
		paint.setColor(Color.BLUE);
      	canvas.drawRect(30, 50, 30+game.getPlayer().getFuel()*(30+getWidth()/4)/Player.getMax_fuel(), 60, paint);

		game.getPlayer().draw(canvas,paint);
		
		if(game.getHealth_item().isActive()) game.getHealth_item().draw(canvas,paint);
//		game.getSlowmotion_item().draw(canvas,paint);
		if(game.getNodamage_item().isActive())game.getNodamage_item().draw(canvas,paint);
		if(game.getFuel_item().isActive())game.getFuel_item().draw(canvas,paint);

		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 92, 87, 8));
		paint.setTextSize(20);
		canvas.drawText("SCORE: " + (int) game.getPoints(), 2*canvas.getWidth()/3 , 30, paint);
//		paint.setTextSize(10);
//		canvas.drawText("Life: " + game.getPlayer().getLifepoints(), getWidth()/3, 50, paint);
	}
	
	/**
	 *  Se o jogador clica na metade direita do ecrã, o jogador move-se para a direita
	 *  A aceleração tem um pico inicial (event ACTION_DOWN) e depois aumenta consoante o tempo que o jogador mantiver o ecrã premido (event ACTION_MOVE)
	 *  	 
	 *  Se o jogador clicar na parte de cima do ecrã, pode contrariar a gravidade, abrandando até poder mesmo começar a subir
	 *  
	 *  Se o jogador clicar na parte de baixo do ecrã, acelera ainda mais ganhando pontos extra
	 *   
	 */

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			if(event.getX() > this.getWidth()/2) 
				game.setGlobalAccelaration(game.getGlobalAccelaration_x()-100,game.getGlobalAccelaration_y());
			else 
				game.setGlobalAccelaration(game.getGlobalAccelaration_x()+100,game.getGlobalAccelaration_y());

			return true;
		}
		
		if(event.getAction() == MotionEvent.ACTION_MOVE) { 
			if(event.getY() < this.getHeight()/6 && game.getPlayer().getFuel() > 0)  {
				game.getPlayer().setMotion(2);
				game.setGlobalAccelaration(game.getGlobalAccelaration_x(),game.getGlobalAccelaration_y()+10);
				game.getPlayer().addFuel(-0.05f*game.getGlobalAccelaration_y());
			}
						
			else if(event.getY() > 5*this.getHeight()/6 && game.getPlayer().getFuel() > 0 ) {
				game.getPlayer().setMotion(1);
				game.setGlobalAccelaration(game.getGlobalAccelaration_x(),game.getGlobalAccelaration_y()-10);
				game.getPlayer().addFuel(0.05f*game.getGlobalAccelaration_y());
			}
			
			else if(event.getX() > this.getWidth()/2) {
				game.getPlayer().setMotion(3);
				game.setGlobalAccelaration(game.getGlobalAccelaration_x()-10,game.getGlobalAccelaration_y());
			}		
			else if(event.getX() < this.getWidth()/2 ){
				game.getPlayer().setMotion(0);
				game.setGlobalAccelaration(game.getGlobalAccelaration_x()+10,game.getGlobalAccelaration_y());
			}
			
		}
		
		if(event.getAction() == MotionEvent.ACTION_UP) {
			Log.e("coiso", "Levantou");
			game.setGlobalAccelaration(0,0);
			game.getPlayer().setMotion(-1);
		}
		
		
		
		return super.onTouchEvent(event);
	}

	


}
