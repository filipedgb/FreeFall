package game.engine;

import game.entities.GameObject;
import game.states.PlayState;
import android.content.Context;
import android.graphics.*;
import android.util.Log;
import android.view.*;
import android.view.View.OnTouchListener;


public class GameView extends View {
	private Paint paint;
	private PlayState game;
	private final int LEFT_DIRECTION = 0;
	private final int DOWN_DIRECTION = 1;
	private final int UP_DIRECTION = 2;
	private final int RIGHT_DIRECTION = 3;
	private final int NO_ACCEL = -1;

	public GameView(Context context) {
		super(context);
		GameObject.setRes(this.getResources());

		paint = new Paint();
		game = new PlayState(this);
		new GameLoop(this,game);
		this.setOnTouchListener(new Controllers(this,game));

	}

	public PlayState getGame() {
		return game;
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);
		if (game.isGameStarted() ==false) {
			GameObject.setScreen_height(this.getHeight());
			Log.e("gameview", "" + this.getHeight());
			GameObject.setScreen_width(this.getWidth());
			Tools.init(this.getWidth(),this.getHeight());
			game.init();
		}

		// Desenha o fundo

		canvas.drawColor(Color.argb(255, 135, 206, 235));

		// Desenha os obstáculos

		for (int i = 0; i < game.getObjects().size(); i++) {
			game.getObjects().get(i).draw(canvas, paint);
		}

		// Desenha barra de vida 

		paint.setColor(Color.GREEN);

		canvas.drawRect(Tools.getDrawUnity(4),
				Tools.getDrawUnity(1),
				Tools.getDrawUnity(6)+(game.getPlayer().getHealthFrac()*Tools.getDrawUnity(4)),
				Tools.getDrawUnity(1)+Tools.getDrawUnity((float) 0.5),
				paint);

		paint.setColor(Color.BLUE);

		// Desenha barra de fuel

		canvas.drawRect(Tools.getDrawUnity(4),
				Tools.getDrawUnity((float)1.75),
				Tools.getDrawUnity(6)+(game.getPlayer().getFuelFrac()*Tools.getDrawUnity(4)),
				Tools.getDrawUnity((float)1.75)+Tools.getDrawUnity((float) 0.5),
				paint);

		// Desenha jogador
		game.getPlayer().draw(canvas,paint);

		// Desenha items que podem ser apanhados - Vida/Invulnerabilidade/Combustível
		if(game.getHealth_item().isActive()) game.getHealth_item().draw(canvas,paint);
		//		game.getSlowmotion_item().draw(canvas,paint);
		if(game.getNodamage_item().isActive())game.getNodamage_item().draw(canvas,paint);
		if(game.getFuel_item().isActive())game.getFuel_item().draw(canvas,paint);

		// Desenha a pontuação
		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 92, 87, 8));
		paint.setTextSize(Tools.getDrawUnity((float)1.5));
		canvas.drawText("SCORE: " + (int) game.getPoints(),Tools.getDrawUnity(12) ,paint.getTextSize()+Tools.getDrawUnity(1), paint);

	}

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//
//
//		if(event.getAction() == MotionEvent.ACTION_DOWN) {
//			if(event.getX() > this.getWidth()/2) 
//				game.setGlobalAccelaration(game.getGlobalAccelaration_x()-100,game.getGlobalAccelaration_y());
//			else 
//				game.setGlobalAccelaration(game.getGlobalAccelaration_x()+100,game.getGlobalAccelaration_y());
//
//			return true;
//		}
//
//		if(event.getAction() == MotionEvent.ACTION_MOVE) { 
//			if(event.getY() < this.getHeight()/6 && game.getPlayer().getFuel() > 0)  {
//				game.getPlayer().setMotion(UP_DIRECTION);
//				game.setGlobalAccelaration(game.getGlobalAccelaration_x(),game.getGlobalAccelaration_y()+10);
//				game.getPlayer().addFuel(-0.05f*game.getGlobalAccelaration_y());
//			}
//
//			else if(event.getY() > 5*this.getHeight()/6 && game.getPlayer().getFuel() > 0 ) {
//				game.getPlayer().setMotion(DOWN_DIRECTION);
//				game.setGlobalAccelaration(game.getGlobalAccelaration_x(),game.getGlobalAccelaration_y()-10);
//				game.getPlayer().addFuel(0.05f*game.getGlobalAccelaration_y());
//			}
//
//			else if(event.getX() > this.getWidth()/2) {
//				game.getPlayer().setMotion(RIGHT_DIRECTION);
//				game.setGlobalAccelaration(game.getGlobalAccelaration_x()-10,game.getGlobalAccelaration_y());
//			}		
//			else if(event.getX() < this.getWidth()/2 ){
//				game.getPlayer().setMotion(LEFT_DIRECTION);
//				game.setGlobalAccelaration(game.getGlobalAccelaration_x()+10,game.getGlobalAccelaration_y());
//			}
//			
//			
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//
//		if(event.getAction() == MotionEvent.ACTION_UP) {
//			Log.e("coiso", "Levantou");
//			game.setGlobalAccelaration(0,0);
//			game.getPlayer().setMotion(NO_ACCEL);
//		}
//
//		return true;
//	}
//
//	







}
