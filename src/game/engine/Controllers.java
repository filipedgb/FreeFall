package game.engine;

import game.entities.GameObject;
import game.states.PlayState;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * Esta classe representa os controlos do jogo, ou seja, verifica a interação que o utilizador fez com o telemovel
 * e faz as alterações necessárias ao jogo 
 * @author André Pires, Filipe Gama
 * @see OnTouchListener, SensorEventListener
 */
public class Controllers implements OnTouchListener, SensorEventListener {

	private GameView current_gameview;
	private PlayState current_game;

	private boolean controllerSensor = false;

	private final int LEFT_DIRECTION = 0;
	private final int DOWN_DIRECTION = 1;
	private final int UP_DIRECTION = 2;
	private final int RIGHT_DIRECTION = 3;
	private final int NO_ACCEL = -1;

	private static Controllers controllerInstance = new Controllers();

	private Controllers() {
		// prevenir que não são instanciados mais controllers
	}



	public boolean isControllerSensor() {
		return controllerSensor;
	}



	public void setControllerSensor(boolean controllerSensor) {
		this.controllerSensor = controllerSensor;
	}



	public static Controllers getControllerInstance() {
		return controllerInstance;
	}


	public void controllerInit(GameView view, PlayState game) {
		current_gameview = view;
		current_game = game;
	}




	/**
	 *  Se o jogador clica na metade direita do ecrã, o jogador move-se para a direita
	 *  A aceleração tem um pico inicial (event ACTION_DOWN) e depois aumenta consoante o tempo que o jogador mantiver o ecrã premido
	 *  
	 *  Quando o jogador levanta o dedo é desativada a flag hold e o jogador para de se mover.
	 *  	 
	 *  Se o jogador clicar na parte de cima do ecrã, pode contrariar a gravidade, abrandando até poder mesmo começar a subir
	 *  
	 *  Se o jogador clicar na parte de baixo do ecrã, acelera ainda mais ganhando pontos extra
	 *   
	 */

	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
		if(current_game != null) {

			if(event.getAction() == MotionEvent.ACTION_DOWN) {
				if(event.getY() < current_gameview.getHeight()/6 && current_game.getPlayer().getFuel() > 0) {
					if(current_game.getPlayer().isMalfunctioning()) current_game.setDirection(DOWN_DIRECTION);
					else current_game.setDirection(UP_DIRECTION);
				}

				else if(event.getY() > 5*current_gameview.getHeight()/6 && current_game.getPlayer().getFuel() > 0 ) {
					if(current_game.getPlayer().isMalfunctioning()) current_game.setDirection(UP_DIRECTION);
					else { current_game.setDirection(DOWN_DIRECTION);
					current_game.getPlayer().setBoost(true);
					}

				}

				else if(event.getX() > current_gameview.getWidth()/2 && !controllerSensor){
					if(current_game.getPlayer().isMalfunctioning()) current_game.setDirection(LEFT_DIRECTION);
					else current_game.setDirection(RIGHT_DIRECTION);
				}

				else if(event.getX() < current_gameview.getWidth()/2 && !controllerSensor){
					if(current_game.getPlayer().isMalfunctioning())current_game.setDirection(RIGHT_DIRECTION);
					else current_game.setDirection(LEFT_DIRECTION);
				}
			}

			if(event.getAction() == MotionEvent.ACTION_MOVE) { 

			}

			if(event.getAction() == MotionEvent.ACTION_UP) {
				current_game.setDirection(NO_ACCEL);
				current_game.getPlayer().setBoost(false);

			}

		}
		return true;

	}



	/**
	 * Acelerómetro 
	 */

	@Override
	public void onSensorChanged(SensorEvent event) {
		/**
		 * se o jogador tiver escolhido para usar acelerómetro controllerSensor = true
		 */
		if(controllerSensor && current_game != null) {
			if(current_game.isGameStarted()) {

				// check sensor type
				if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
					float x=event.values[0];

					if(x > 0.5) {
						if (current_game.isGameStarted()) { 
							if(current_game.getPlayer().isMalfunctioning())current_game.setDirection(RIGHT_DIRECTION);
							else current_game.setDirection(LEFT_DIRECTION);
						}	
					}
					else if(x < -0.5){
						if(current_game.isGameStarted()) {
							if(current_game.getPlayer().isMalfunctioning())current_game.setDirection(LEFT_DIRECTION);
							else current_game.setDirection(RIGHT_DIRECTION);
						}
					}
					
					else {
						current_game.setDirection(NO_ACCEL);
					}
				}
			}
		}
	}



	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
}
