package game.logic;

import game.main.GameView;
import game.objects.Alien;
import game.objects.Clouds;
import android.graphics.Paint;

public class GameState {

	private Clouds[] clouds;
	private Alien player;

	
	private boolean movePlayerLeft = false;
	private int moveCounterLeft = 15;
	private boolean movePlayerRight = false;
	private int moveCounterRight = 15;
	
	private boolean gameStarted = false;
	private GameView current_view;

	public GameState(GameView gameView) {
		current_view = gameView;
	}

	public void init() {

		clouds = new Clouds[5];
		
		randomizeClouds();	

		player = new Alien(current_view.getWidth()/2, current_view.getHeight()/2);

		gameStarted = true;
	}


	private void randomizeClouds() {
		int x;
		for (int i = 0; i < clouds.length; i++) {
			int y = i*+50;
			x = (int) (Math.random()*(current_view.getWidth()-25));
			clouds[i] = new Clouds(x, y, current_view.getResources());
		}		
	}

	public void update() {
		if (gameStarted==false) {
			return;
		}
		for (int i = 0; i < clouds.length; i++) {
			clouds[i].move(current_view.getHeight(), current_view.getWidth());
		}
		
		if(movePlayerLeft) {
			player.moveLeft();
			moveCounterLeft--; 
			if(moveCounterLeft == 0) movePlayerLeft = false;
		}
		
		if(movePlayerRight) {
			player.moveRight();
			moveCounterRight--; 
			if(moveCounterRight== 0) movePlayerRight = false;
		}
		
	
		for(int i = 0; i < clouds.length; i++) {
			if(player.colide(clouds[i])) {
				player.getsHit();
			}
		}

		if(player.getLifepoints() <= 0) {
			current_view.release();
		}

		if(player.isTurbo() && player.getTurbopoints() > 0) {
			incVelocity();
			player.setTurbopoints(player.getTurbopoints()-1);
		}
		else if (player.getTurbopoints() < 100 && !player.isTurbo()){
			resetVelocity();
			player.setTurbopoints(player.getTurbopoints()+0.1);

		}


		if(player.getTurbopoints() <= 0) {
			player.setTurbo(false);

		}

	}
	


	boolean isMovePlayerLeft() {
		return movePlayerLeft;
	}

	public void setMovePlayerLeft(boolean movePlayerLeft) {
		this.movePlayerLeft = movePlayerLeft;
	}

	int getMoveCounterLeft() {
		return moveCounterLeft;
	}

	public void setMoveCounterLeft(int moveCounterLeft) {
		this.moveCounterLeft = moveCounterLeft;
	}

	boolean isMovePlayerRight() {
		return movePlayerRight;
	}

	public void setMovePlayerRight(boolean movePlayerRight) {
		this.movePlayerRight = movePlayerRight;
	}

	int getMoveCounterRight() {
		return moveCounterRight;
	}

	public void setMoveCounterRight(int moveCounterRight) {
		this.moveCounterRight = moveCounterRight;
	}

	private void resetVelocity() {
		for(int i = 0; i < clouds.length; i++) {
			clouds[i].resetVelocity();
			//randomizeClouds();
		}
	}

	private void incVelocity() {
		for(int i = 0; i < clouds.length; i++) {
			clouds[i].setVelocity(10);
			//randomizeClouds();
		}
		
	}

	public Clouds[] getClouds() {
		return clouds;
	}

	
	public void setClouds(Clouds[] clouds) {
		this.clouds = clouds;
	}

	public Alien getPlayer() {
		return player;
	}

	public void setPlayer(Alien player) {
		this.player = player;
	}

	public boolean isGameStarted() {
		return gameStarted;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	public GameView getCurrent_view() {
		return current_view;
	}

	public void setCurrent_view(GameView current_view) {
		this.current_view = current_view;
	}
}
