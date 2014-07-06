package game.states;

import java.util.ArrayList;
import java.util.Random;

import game.engine.*;
import game.entities.*;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

/**
 * Estado que representa o jogo em si
 * 
 * @author André Pires, Filipe Gama
 */
public class PlayState {

	private ArrayList<Obstacle> objects = new ArrayList<Obstacle>();
	private Player player;
	private ArrayList<Item> itens = new ArrayList<Item>();
	private float points = 1;
	private int level = 1;
	private boolean changelevel = false;
	private int direction = -1;
	private boolean gameStarted = false;
	private GameView current_view;
	private Vibrator v;
	private int max_velocity_x = 150;
	private int max_velocity_y = 700;

	public PlayState(GameView gameView) {
		current_view = gameView;
		v = (Vibrator) current_view.getContext().getSystemService(
				Context.VIBRATOR_SERVICE);
	}

	/**
	 * Função que inicializa os objectos todos da cena, com posições aleatórias
	 */
	public void init() {
		randomizeObstacles();
		Random rand = new Random();

		int w = current_view.getWidth();
		int h = current_view.getHeight();

		player = new Player(w / 2 - (int) Tools.getDrawUnity(4) / 2, h / 3);
		itens.add(new Health(rand.nextInt(w - 25), rand.nextInt(h) + h));
		itens.add(new SlowDown(rand.nextInt(w - 25), rand.nextInt(h) + h));
		itens.add(new Invulnerability(rand.nextInt(w - 25), rand.nextInt(h) + h));
		itens.add(new Fuel(rand.nextInt(w - 25), rand.nextInt(h) + h));
		itens.add(new Skymine(rand.nextInt(w - 25), rand.nextInt(h) + h));

		gameStarted = true;
		changeLevel(Tools.getLevel());
	}

	/**
	 * Função que determina posições iniciais dos obstáculos, chamada no init
	 */
	private void randomizeObstacles() {
		int x, y;
		for (int i = 0; i < 10; i++) {
			y = (int) (Math.random() * 200);
			x = (int) (Math.random() * (6 * current_view.getWidth()) - 3 * current_view
					.getWidth());
			objects.add(new Obstacle(x, current_view.getHeight() + y));
		}
	}

	/**
	 * Atualiza todos os objetos do jogo, quando esta a decorrer o jogo em si
	 */
	public void update() {
		if (gameStarted == false) {
			return;
		}

		// verifica nivel
		if (points >= 400 && level < 3) {
			changeLevel(3);
		} else if (points >= 200 && level < 2) {
			changeLevel(2);
		}

		if (isChangelevel())
			switch (level) {
			case (2):
				Obstacle.setCurrent_bmp(Tools.getAsteroid());
				changelevel = false;
				break;
			case (3):
				Obstacle.setCurrent_bmp(Tools.getCloud());
				changelevel = false;
				break;
			default:
				break;
			}

		// Verifica se jogador perdeu
		if (player.getLifepoints() > 0) {
			if (player.isBoost())
				points += 0.5;
			else
				points += 0.1;
		} else {
			GameLoop.stopThread(points);
		}

		// Verifica velocidade máxima horizontal
		if (GameObject.getGlobalVelocity_x() > 10)
			player.addHealthPoints(-1);

		// Verifica movimentos do player
		checkPlayerMovements();

		// Verifica se foi apanhado o slow down
		if (itens.get(1).isActive()) {
			if (itens.get(1).caught(player)) {
				for (Item x : itens)
					x.setVelocity_y(x.getVelocity_y() / 2);
				for (Obstacle x : objects)
					x.setVelocity_y(x.getVelocity_y() / 2);
			}
		}

		// Verifica se foi apanhado algum item
		for (Item x : itens) {
			if (x.isActive())
				x.caught(player);
			// atualiza os itens
			x.updateItem();
		}

		player.update();

		// Counter para o bonus de invulnerabilidade
		if (player.isInvulnerable()) {
			if (player.getInvulnerable_ticks() > 0)
				player.decrement_ticks();
			if (player.getInvulnerable_ticks() == 0)
				player.setInvulnerable(false);
		}

		// Move todos os obstáculos e verifica e o jogador colide com algum
		// deles
		for (Obstacle x : objects) {
			x.move();
			x.updateItem();
			if (x.damage(player) && !player.isInvulnerable())
				v.vibrate(250);
		}

		// move os itens
		for (Item x : itens)
			x.move();
	}

	/**
	 * Verifica movimentos do jogador
	 */
	public void checkPlayerMovements() {
		switch (direction) {
		case (0): // Left
			if (Math.abs(objects.get(0).getVelocity_x()) < max_velocity_x) {
				getPlayer().setMotion(2);
				GameObject.setGlobalAccelaration(getGlobalAccelaration_x()
						+ Tools.getDrawUnity(0.5f), getGlobalAccelaration_y());
			}
			break;
		case (1): // Down
			if (Math.abs(objects.get(0).getVelocity_y()) < max_velocity_y) {
				getPlayer().setMotion(1);
				GameObject.setGlobalAccelaration(getGlobalAccelaration_x(),
						getGlobalAccelaration_y() - Tools.getDrawUnity(0.5f));
				getPlayer().addFuel(0.01f * getGlobalAccelaration_y());
			}
			break;

		case (2):// Up
			if (Math.abs(objects.get(0).getVelocity_y()) < max_velocity_y) {
				getPlayer().setMotion(0);
				GameObject.setGlobalAccelaration(getGlobalAccelaration_x(),
						getGlobalAccelaration_y() + Tools.getDrawUnity(0.5f));
			}
			getPlayer().addFuel(-0.01f * getGlobalAccelaration_y());
			break;
		case (3): // Right
			if (Math.abs(objects.get(0).getVelocity_x()) < max_velocity_x) {
				getPlayer().setMotion(3);
				GameObject.setGlobalAccelaration(getGlobalAccelaration_x()
						- Tools.getDrawUnity(0.5f), getGlobalAccelaration_y());
			}
			break;
		case (-1): // No acceleration
			GameObject.setGlobalAccelaration(0, 0);
			getPlayer().setMotion(-1);
			break;
		}
	}

	/**
	 * Modifica o nivel
	 * 
	 * @param lvl
	 */
	public void changeLevel(int lvl) {
		Intent intent = new Intent(PlayActivity.getSingleInstance()
				.getBaseContext(), LevelActivity.class);
		Tools.setLevel(lvl);
		PlayActivity.getSingleInstance().startActivity(intent);
		GameLoop.getCurrent_instance().setRunning(false);
		level = lvl;

		changelevel = true;
	}

	// GETTERS AND SETTERS

	public boolean isChangelevel() {
		return changelevel;
	}

	public void setChangelevel(boolean changelevel) {
		this.changelevel = changelevel;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public float getGlobalAccelaration_x() {
		return GameObject.getGlobal_accelaration_x();
	}

	public float getGlobalAccelaration_y() {
		return GameObject.getGlobal_accelaration_y();
	}

	public Skymine getSky_mine() {
		return (Skymine) itens.get(4);
	}

	public SlowDown getSlowmotion_item() {
		return (SlowDown) itens.get(1);
	}

	public void setSlowmotion_item(SlowDown slowmotion_item) {
		itens.set(1, slowmotion_item);
	}

	public Health getHealth_item() {
		return (Health) itens.get(0);
	}

	public void setHealth_item(Health health_item) {
		itens.set(0, health_item);
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
		return (Invulnerability) itens.get(2);
	}

	public Fuel getFuel_item() {
		return (Fuel) itens.get(3);
	}

	/**
	 * @return the itens
	 */
	public ArrayList<Item> getItens() {
		return itens;
	}

	/**
	 * @param itens
	 *            the itens to set
	 */
	public void setItens(ArrayList<Item> itens) {
		this.itens = itens;
	}
}
