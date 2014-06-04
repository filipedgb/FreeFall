package game.states;

import java.util.ArrayList;
import java.util.Random;

import game.engine.GameLoop;
import game.engine.GameView;
import game.entities.*;
import android.content.Context;
import android.os.Vibrator;

public class PlayState implements GameState {

	private ArrayList<Obstacle> objects = new ArrayList<Obstacle>();
	private Player player;
	private Health health_item;
	private SlowDown slowmotion_item;
	private Invulnerability nodamage_item;
	private Fuel fuel_item;
	private Skymine sky_mine;
	private float points;
	
	private int direction = -1;
	
	private boolean gameStarted = false;
	private GameView current_view;
	Vibrator v;

	public PlayState(GameView gameView) {
		current_view = gameView;
		v = (Vibrator) current_view.getContext().getSystemService(Context.VIBRATOR_SERVICE);
	}

	/**
	 * Função que inicializa os objectos todos da cena, com posições aleatórias
	 */
	public void init() {
		randomizeObstacles();	
		Random rand = new Random();

		player = new Player(current_view.getWidth()/2-50, current_view.getHeight()/3);
		health_item = new Health((int) rand.nextInt(current_view.getWidth()-25),rand.nextInt(current_view.getHeight())+current_view.getHeight());
		slowmotion_item = new SlowDown((int)(Math.random()*(current_view.getWidth()-25)),(int) Math.random()*100 + current_view.getHeight());
		nodamage_item = new Invulnerability((int)rand.nextInt(current_view.getWidth()-25),rand.nextInt(current_view.getHeight())+current_view.getHeight());
		fuel_item = new Fuel((int) rand.nextInt(current_view.getWidth()-25),rand.nextInt(current_view.getHeight())+current_view.getHeight());
		sky_mine = new Skymine((int) rand.nextInt(current_view.getWidth()-25),rand.nextInt(current_view.getHeight())+current_view.getHeight());
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
		
		// Verifica se jogador perdeu
		if(player.getLifepoints() > 0) {
			if(player.isBoost()) points += 0.5;
			else points += 0.1;
		}
		else {
			GameLoop.stopThread(points);
		}
		
		//Verifica velocidade máxima horizontal 
		if(GameObject.getGlobalVelocity_x() > 10) player.addHealthPoints(-1);
		
		//Verifica movimentos do player
		switch(direction) {
			case (0): //Left 
				getPlayer().setMotion(0);
				GameObject.setGlobalAccelaration(getGlobalAccelaration_x()+10,getGlobalAccelaration_y());
				break;
			case (1): //Down
				getPlayer().setMotion(1);
				GameObject.setGlobalAccelaration(getGlobalAccelaration_x(),getGlobalAccelaration_y()-10);
				getPlayer().addFuel(0.01f*getGlobalAccelaration_y());
				break;
			case (2)://Up
				getPlayer().setMotion(2);
				GameObject.setGlobalAccelaration(getGlobalAccelaration_x(),getGlobalAccelaration_y()+10);
				getPlayer().addFuel(-0.01f*getGlobalAccelaration_y());
				break;
			case (3): //Right
				getPlayer().setMotion(3);
				GameObject.setGlobalAccelaration(getGlobalAccelaration_x()-10,getGlobalAccelaration_y());
				break;
			case (-1): //No acceleration
				GameObject.setGlobalAccelaration(0,0);
				getPlayer().setMotion(-1);
				break;
		
		}

		// Verifica se foi apanhado algum item
		if(health_item.isActive()) health_item.caught(player);
		if(nodamage_item.isActive()) nodamage_item.caught(player);
		if(fuel_item.isActive()) fuel_item.caught(player);
		if(sky_mine.isActive()) sky_mine.caught(player);
		if(slowmotion_item.isActive()) slowmotion_item.caught(player);

		
		//actualiza os items
		health_item.updateItem();
		fuel_item.updateItem();
		nodamage_item.updateItem();
		slowmotion_item.updateItem();
		sky_mine.updateItem();
		slowmotion_item.updateItem();
		player.update();



		// Counter para o bonus de invulnerabilidade
		if (player.getInvulnerable_ticks() > 0 && player.isInvulnerable()) player.decrement_ticks(); 
		if(player.getInvulnerable_ticks() == 0) player.setInvulnerable(false);

		if(slowmotion_item.colide(player)) {
			decreaseVelocity();
		}

		// Move todos os obstáculos e verifica e o jogador colide com algum deles
		for(int i = 0; i < objects.size(); i++){
			objects.get(i).move();
			if(objects.get(i).damage(player)) {
				v.vibrate(20);
			};
		}

		// Move os restantes objectos do jogo
		health_item.move();
		slowmotion_item.move();
		nodamage_item.move();
		fuel_item.move();
		sky_mine.move();
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public float getGlobalAccelaration_x() {
		return health_item.getAccelaration_x();
	}
	public float getGlobalAccelaration_y() {
		return health_item.getAccelaration_y();
	}

	public Skymine getSky_mine() {
		return sky_mine;
	}

	private void decreaseVelocity() {
		GameObject.setGlobalVelocity(GameObject.getGlobalVelocity_x() , GameObject.getGlobalVelocity_y()/5);	
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
		return fuel_item;
	}
}
