package game.states;

import java.util.ArrayList;
import java.util.Random;

import android.util.Log;
import game.engine.GameView;
import game.entities.Fuel;
import game.entities.GameObject;
import game.entities.Health;
import game.entities.Invulnerability;
import game.entities.Obstacle;
import game.entities.Player;
import game.entities.SlowDown;
import android.os.Vibrator;

public class PlayState implements GameState{

	private ArrayList<Obstacle> objects = new ArrayList<Obstacle>();
	private Player player;
	private Health health_item;
//	private SlowDown //slowmotion_item;
	private Invulnerability nodamage_item;
	private Fuel fuel_item;
	private float points;
	
	private boolean gameStarted = false;
	private GameView current_view;

	public PlayState(GameView gameView) {
		current_view = gameView;
	}

	
	/**
	 * Função que inicializa os objectos todos da cena, com posições aleatórias
	 */
	
	public void init() {
		randomizeObstacles();	
		Random rand = new Random();
	
		player = new Player(current_view.getWidth()/2-50, current_view.getHeight()/3);
		health_item = new Health((int) rand.nextInt(current_view.getWidth()-25),rand.nextInt(current_view.getHeight())+current_view.getHeight());
		//slowmotion_item = new SlowDown((int)(Math.random()*(current_view.getWidth()-25)),(int) Math.random()*100 + current_view.getHeight());
		nodamage_item = new Invulnerability((int)rand.nextInt(current_view.getWidth()-25),rand.nextInt(current_view.getHeight())+current_view.getHeight());
		fuel_item = new Fuel((int) rand.nextInt(current_view.getWidth()-25),rand.nextInt(current_view.getHeight())+current_view.getHeight());
		gameStarted = true;
	}

	/**
	 * Função que determina posições iniciais dos obstáculos, chamada no init
	 */

	private void randomizeObstacles() {
		int x;
		for (int i = 0; i < 10; i++) {
			int y = (int) (Math.random() *200);
			x = (int) (Math.random()*(6*current_view.getWidth())-3*current_view.getWidth());
			objects.add(new Obstacle(x, current_view.getHeight()+y));
		}		
	}
	
	

	public void update() {
		if (gameStarted==false) {
			return;
		}
		
		/**
		 * Verifica se jogador perdeu
		 */
		
		if(player.getLifepoints() > 0) points += 0.1;
					
		/**
		 * Verifica se foi apanhado algum item
		 */
		if(health_item.isActive()) health_item.caught(player);
		if(nodamage_item.isActive()) nodamage_item.caught(player);
		if(fuel_item.isActive()) fuel_item.caught(player);

		/**
		 * Counter para o bonus de invulnerabilidade
		 */
		
		if (player.getInvulnerable_ticks() > 0 && player.isInvulnerable()) player.decrement_ticks(); 
		if(player.getInvulnerable_ticks() == 0) player.setInvulnerable(false);
		
//		if(//slowmotion_item.colide(player)) {
//			decreaseVelocity();
//		}
		
		/**
		 *  Move todos os obstáculos e verifica e o jogador colide com algum deles
		 */

		for(int i = 0; i < objects.size(); i++){
			objects.get(i).move();
			objects.get(i).damage(player);

		}
		
		/**
		 * Move os restantes objectos do jogo
		 */
					
		health_item.move();
		//slowmotion_item.move(current_view.getHeight(), current_view.getWidth());
		nodamage_item.move();
		fuel_item.move();

	
	
	}
	
	public float getGlobalAccelaration_x() {
		return health_item.getAccelaration_x();
	}
	public float getGlobalAccelaration_y() {
		return health_item.getAccelaration_y();
	}
	
	
	public void setGlobalAccelaration(float a_x, float a_y) {
		
		
		Log.e("tag2", "" + a_x); 
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).setAccelaration_x(a_x);
			objects.get(i).setAccelaration_y(a_y);

		}
		
		health_item.setAccelaration_x(a_x);
		health_item.setAccelaration_y(a_y);

		//slowmotion_item.setAccelaration_x(a_x);
		//slowmotion_item.setAccelaration_y(a_y);

		nodamage_item.setAccelaration_x(a_x);
		nodamage_item.setAccelaration_y(a_y);
		
		fuel_item.setAccelaration_x(a_x);
		fuel_item.setAccelaration_y(a_y);


	}
	

	private void decreaseVelocity() {
		for(int i = 0; i < objects.size(); i++){
			objects.get(i).setVelocity_y(-20);
		}
		
		health_item.setVelocity_y(-20);
		//slowmotion_item.setVelocity_y(-20);
		nodamage_item.setVelocity_y(-20);
		
	}
/*
	public SlowDown getSlowmotion_item() {
		return //slowmotion_item;
	}

	public void setSlowmotion_item(SlowDown //slowmotion_item) {
		this.//slowmotion_item = //slowmotion_item;
	}*/

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

	public Fuel getFuel_item() {
		// TODO Auto-generated method stub
		return fuel_item;
	}
}
