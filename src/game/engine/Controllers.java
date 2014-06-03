package game.engine;

import game.entities.GameObject;
import game.states.PlayState;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


public class Controllers implements OnTouchListener, SensorEventListener {

	private GameView current_gameview;
	private PlayState current_game;
	
	private boolean controllerSensor = false;
		
	private final int LEFT_DIRECTION = 0;
	private final int DOWN_DIRECTION = 1;
	private final int UP_DIRECTION = 2;
	private final int RIGHT_DIRECTION = 3;
	private final int NO_ACCEL = -1;


	public Controllers(GameView view, PlayState game) {
		current_gameview = view;
		current_game = game;
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
	public boolean onTouch(View arg0, MotionEvent event) {

		try {
			Thread.sleep(70);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			if(event.getX() > current_gameview.getWidth()/2) 
				GameObject.setGlobalAccelaration(current_game.getGlobalAccelaration_x()-100,current_game.getGlobalAccelaration_y());
			else 
				GameObject.setGlobalAccelaration(current_game.getGlobalAccelaration_x()+100,current_game.getGlobalAccelaration_y());

			return true;
		}

		if(event.getAction() == MotionEvent.ACTION_MOVE) { 
			if(event.getY() < current_gameview.getHeight()/6 && current_game.getPlayer().getFuel() > 0)  {
				current_game.getPlayer().setMotion(UP_DIRECTION);
				GameObject.setGlobalAccelaration(current_game.getGlobalAccelaration_x(),current_game.getGlobalAccelaration_y()+10);
				current_game.getPlayer().addFuel(-0.05f*current_game.getGlobalAccelaration_y());
			}

			else if(event.getY() > 5*current_gameview.getHeight()/6 && current_game.getPlayer().getFuel() > 0 ) {
				current_game.getPlayer().setMotion(DOWN_DIRECTION);
				GameObject.setGlobalAccelaration(current_game.getGlobalAccelaration_x(),current_game.getGlobalAccelaration_y()-10);
				current_game.getPlayer().addFuel(0.05f*current_game.getGlobalAccelaration_y());
			}

			else if(event.getX() > current_gameview.getWidth()/2) {
				current_game.getPlayer().setMotion(RIGHT_DIRECTION);
				GameObject.setGlobalAccelaration(current_game.getGlobalAccelaration_x()-10,current_game.getGlobalAccelaration_y());
			}		
			else if(event.getX() < current_gameview.getWidth()/2 ){
				current_game.getPlayer().setMotion(LEFT_DIRECTION);
				GameObject.setGlobalAccelaration(current_game.getGlobalAccelaration_x()+10,current_game.getGlobalAccelaration_y());
			}

		}

		if(event.getAction() == MotionEvent.ACTION_UP) {
			Log.e("coiso", "Levantou");
			GameObject.setGlobalAccelaration(0,0);
			current_game.getPlayer().setMotion(NO_ACCEL);
		}



		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {

	}
	
	
	/**
	 * Acelerómetro 
	 */

	@Override
	public void onSensorChanged(SensorEvent event) {
		/**
		 * se o jogador tiver escolhido para usar acelerómetro controllerSensor = true
		 */
		if(controllerSensor) {

			// check sensor type
			if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){

				float x=event.values[0];
			
				if(x > 0) {
					if (current_game.isGameStarted()) { 
						current_game.getPlayer().setMotion(LEFT_DIRECTION);
						GameObject.setGlobalAccelaration(current_game.getGlobalAccelaration_x()-10,current_game.getGlobalAccelaration_y());
					}	
				}
				else if(x < 0){
					if(current_game.isGameStarted()) {
						current_game.getPlayer().setMotion(RIGHT_DIRECTION);
						GameObject.setGlobalAccelaration(current_game.getGlobalAccelaration_x()+10,current_game.getGlobalAccelaration_y());
					}
				}

			}

		}
	}
}
