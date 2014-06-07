package game.entities;


import game.engine.Tools;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Esta classe representa o jogador
 * @author André Pires, Filipe Gama
 * @see GameObject
 */
public class Player extends GameObject {

	private int lifepoints;
	private static int max_life = 1000;
	private int fuel;
	private static int max_fuel = 500;
	boolean turbo_enabled;
	boolean invulnerable;
	private int invulnerable_ticks;
	private int motion;
	private boolean malfunctioning = false;
	private int malfunc_ticks = 0;
	
	private boolean boost = false;

	public boolean isBoost() {
		return boost;
	}

	public void setBoost(boolean boost) {
		this.boost = boost;
	}

	public Player(int x, int y) {
		super(x, y, (int) Tools.getScreenHeight()/6, (int) Tools.getScreenWidth());
		lifepoints = max_life;
		fuel = max_fuel;
		turbo_enabled = false;
		accelaration_y = 0;
		invulnerable = false;
		invulnerable_ticks = 0;
		motion = -1;
		bmp = Tools.getPlayer();

	}
	
	/**
	 * Criado para efeitos de teste
	 */
	public Player(boolean teste) {
		super(0, 0, (int) Tools.getScreenHeight()/6, (int) Tools.getScreenWidth());
		lifepoints = max_life;
		fuel = max_fuel;
		turbo_enabled = false;
		accelaration_y = 0;
		invulnerable = false;
		invulnerable_ticks = 0;
		motion = -1;	
	}
	
	public boolean isMalfunctioning() {
		return malfunctioning;
	}

	public void setMalfunctioning(boolean malfunctioning) {
		this.malfunctioning = malfunctioning;
		malfunc_ticks = (int) (5*Tools.getFPS());
	}
	
	public void update() {
		if(malfunc_ticks > 0) malfunc_ticks--;
		else malfunctioning = false;
	}

	public float getHealthFrac() {
		return (float) lifepoints/max_life;
	}

	public float getFuelFrac() {
		return  (float) fuel/max_fuel;	
	}

	public void setMotion(int motion) {
		Tools.getPlayer_animation().setDirection(motion);
		this.motion = motion;
	}

	public Player() {
		this(0,0);
		lifepoints = 1000;
		fuel = 100;
		turbo_enabled = false;
		accelaration_y = 0;
		invulnerable = false;
		invulnerable_ticks = 0;
		motion = -1;
	}

	public void draw(Canvas canvas, Paint paint) {
		if(motion == -1) canvas.drawBitmap(bmp, getX(), getY(), paint);
		else Tools.getPlayer_animation().draw(canvas, (int) x, (int) y);
	}

	/**
	 * Movimenta o jogador
	 */
	@Override
	public void move() {
		resistence = (float) (-0.9*velocity_x);

		velocity_x = velocity_x + (accelaration_x + resistence)/Tools.getFPS();
		velocity_y = velocity_y + accelaration_y/Tools.getFPS();

		setY(getY() + velocity_y/Tools.getFPS());
		setX(getX() + velocity_x/Tools.getFPS());
	}

	public int getLifepoints() {
		return lifepoints;
	}

	public int getFuel() {
		return fuel;
	}

	/**
	 * Adiciona vida ao jogador
	 * @param value vida a ganhar (pode ser positivo ou negativo)
	 */
	public void addHealthPoints(float value) {
		if(value > 0 && lifepoints <= max_life) { 
			if(lifepoints+value > max_life) lifepoints = max_life;
			else lifepoints += value;
		}

		else if(value < 0 && lifepoints > 0 && !invulnerable) {
			if(lifepoints+value < 0) lifepoints = 0;
			else lifepoints += value;
		}
	}

	/**
	 * Adiciona combustivel ao jogador
	 * @param value combustivel a ganhar (pode ser positivo ou negativo)
	 */
	public void addFuel(float value) {
		if(value > 0 && fuel <= max_fuel) { 
			if(fuel+value > max_fuel) fuel = max_fuel;
			else fuel += value;
		}

		else if(value < 0 && fuel > 0) {
			if(fuel+value < 0) fuel = 0;
			else fuel += value;
		}	
	}

	public static int getMax_life() {
		return max_life;
	}

	public void setInvulnerable(boolean x) {
		invulnerable = x;
		invulnerable_ticks = (int) (4*Tools.getFPS());
	}

	public boolean isInvulnerable() {
		return invulnerable;
	}

	public int getInvulnerable_ticks() {
		return invulnerable_ticks;
	}

	public void decrement_ticks() {
		invulnerable_ticks--;
	}

	public static int getMax_fuel() {
		return max_fuel;
	}
}