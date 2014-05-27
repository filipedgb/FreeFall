package game.states;

import java.util.ArrayList;

import game.engine.GameView;
import game.entities.GameObject;
import game.entities.Health;
import game.entities.Obstacle;
import game.entities.Player;
import game.entities.SlowDown;
import android.util.Log;

public class PlayState implements GameState{

	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private Player player;
	private Health health_item;
	private SlowDown slowmotion_item;
		
	private float points;


//	private boolean movePlayerLeft = false;
//	private int moveCounterLeft = 15;
//	private boolean movePlayerRight = false;
//	private int moveCounterRight = 15;
	
	private boolean movePlayer = false;
	private int moveCounter = 25;
	
	private boolean gameStarted = false;
	private GameView current_view;

	public PlayState(GameView gameView) {
		current_view = gameView;
	}

	public void init() {
		randomizeObstacles();	
		player = new Player(current_view.getWidth()/2, current_view.getHeight()/3);
		health_item = new Health((int)(Math.random()*(current_view.getWidth()-25)),(int) Math.random()*200,15,15);
		slowmotion_item = new SlowDown((int)(Math.random()*(current_view.getWidth()-25)),(int) Math.random()*200,15,15);
		gameStarted = true;
	}


	private void randomizeObstacles() {
		int x;
		for (int i = 0; i < 5; i++) {
			int y = (int) (Math.random() *200);
			x = (int) (Math.random()*(current_view.getWidth()-25));
			objects.add(new Obstacle(x, y, current_view.getResources()));
		}		
	}

	public void update() {
		if (gameStarted==false) {
			Log.e("update", "entrou no gamestartfals");
					return;
		}
		
		if(movePlayer) {
			player.move();
			//moveCounter--;
		}
		
		health_item.caught(player);
		
		if(slowmotion_item.colide(player)) {
			decreaseVelocity();
		}
	
		for(int i = 0; i < objects.size(); i++){
			objects.get(i).move(current_view.getHeight(), current_view.getWidth());
		}
		
		health_item.move(current_view.getHeight(), current_view.getWidth());
		slowmotion_item.move(current_view.getHeight(), current_view.getWidth());
		
		Log.e("POSICAO",health_item.getX() + " " + health_item.getY());
		
		if(player.getLifepoints() <= 0) {
			current_view.release();
		}
	}
	

	private void decreaseVelocity() {
		for(int i = 0; i < objects.size(); i++){
			objects.get(i).setVelocity_y(-20);
		}
		
		health_item.setVelocity_y(-20);
		slowmotion_item.setVelocity_y(-20);
		
	}

	public SlowDown getSlowmotion_item() {
		return slowmotion_item;
	}

	public void setSlowmotion_item(SlowDown slowmotion_item) {
		this.slowmotion_item = slowmotion_item;
	}

	public Health getHealth_item() {
		return health_item;
	}

	public void setHealth_item(Health health_item) {
		this.health_item = health_item;
	}

	public float getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}


	public ArrayList<GameObject> getObjects() {
		return objects;
	}

	void setObjects(ArrayList<GameObject> objects) {
		this.objects = objects;
	}

	public boolean isMovePlayer() {
		return movePlayer;
	}

	public void setMovePlayer(boolean movePlayer) {
		this.movePlayer = movePlayer;
	}

	public int getMoveCounter() {
		return moveCounter;
	}

	public void setMoveCounter(int moveCounter) {
		this.moveCounter = moveCounter;
	}


	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
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
