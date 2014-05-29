package game.states;

import java.util.ArrayList;

import android.util.Log;
import game.engine.GameView;
import game.entities.Health;
import game.entities.Invulnerability;
import game.entities.Obstacle;
import game.entities.Player;
import game.entities.SlowDown;

public class PlayState implements GameState{

	private ArrayList<Obstacle> objects = new ArrayList<Obstacle>();
	private Player player;
	private Health health_item;
	private SlowDown slowmotion_item;
	private Invulnerability nodamage_item;
	private float points;

	private boolean movePlayer = false;
	private int moveCounter = 25;
	
	private boolean gameStarted = false;
	private GameView current_view;

	public PlayState(GameView gameView) {
		current_view = gameView;
	}

	public void init() {
		randomizeObstacles();	
		player = new Player(current_view.getWidth()/2-50, current_view.getHeight()/3);
		health_item = new Health((int)(Math.random()*(current_view.getWidth()-25)),(int) Math.random()*200 + current_view.getHeight());
		slowmotion_item = new SlowDown((int)(Math.random()*(current_view.getWidth()-25)),(int) Math.random()*100 + current_view.getHeight());
		nodamage_item = new Invulnerability((int)(Math.random()*(current_view.getWidth()-25)),(int) Math.random()*150 + current_view.getHeight());
		gameStarted = true;
	}


	private void randomizeObstacles() {
		int x;
		for (int i = 0; i < 5; i++) {
			int y = (int) (Math.random() *200);
			x = (int) (Math.random()*(6*current_view.getWidth())-3*current_view.getWidth());
			objects.add(new Obstacle(x, current_view.getHeight()+y));
		}		
	}
	
	

	public void update() {
		if (gameStarted==false) {
			return;
		}
		
		points += 0.25;
		
		if(movePlayer) {
			player.move();
			//moveCounter--;
		}
		
		health_item.caught(player);
		nodamage_item.caught(player);
		
		if (player.getInvulnerable_ticks() > 0 && player.isInvulnerable()) player.decrement_ticks(); 
		if(player.getInvulnerable_ticks() == 0) player.setInvulnerable(false);
		
		if(slowmotion_item.colide(player)) {
			decreaseVelocity();
		}

		for(int i = 0; i < objects.size(); i++){
			objects.get(i).move(current_view.getHeight(), current_view.getWidth());
			objects.get(i).damage(player);

		}
		
			
		health_item.move(current_view.getHeight(), current_view.getWidth());
		slowmotion_item.move(current_view.getHeight(), current_view.getWidth());
		nodamage_item.move(current_view.getHeight(),current_view.getWidth());
	
	
	}
	
	public float getGlobalAccelaration() {
		return health_item.getAccelaration_x();
	}
	
	public void setGlobalAccelaration(float ac) {
		Log.e("tag2", "" + ac); 
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).setAccelaration_x(ac);
		}
		
		health_item.setAccelaration_x(ac);
		slowmotion_item.setAccelaration_x(ac);
		nodamage_item.setAccelaration_x(ac);
	}
	

	private void decreaseVelocity() {
		for(int i = 0; i < objects.size(); i++){
			objects.get(i).setVelocity_y(-20);
		}
		
		health_item.setVelocity_y(-20);
		slowmotion_item.setVelocity_y(-20);
		nodamage_item.setVelocity_y(-20);
		
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


	public ArrayList<Obstacle> getObjects() {
		return objects;
	}

	void setObjects(ArrayList<Obstacle> objects) {
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

	public Invulnerability getNodamage_item() {
		return nodamage_item;
	}
}
