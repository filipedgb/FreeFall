package game.entities;

import game.engine.Tools;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Esta classe representa um objeto do jogo
 * @author André Pires, Filipe Gama
 *
 */
public abstract class GameObject {
	private int height;
	private int width;

	protected static float global_accelaration_x = 0;
	protected static float global_accelaration_y = 0;

	protected static float global_velocity_x = 0;
	protected static float global_velocity_y = 0;

	protected float x;
	protected float y;
	protected float terminal_velocity = 50;
	protected float accelaration_y;
	protected float gravity_constant = -9.8f;
	protected float velocity_y = 0;
	protected float accelaration_x = 0;
	protected float velocity_x = 0;
	protected float resistence ;
	
	protected Bitmap bmp;

	protected static Resources res;
	protected static int screen_height;
	protected static int screen_width;

	protected boolean active = true;

	/**
	 * Define as duas componentes da aceleração, que são comuns a todos os objectos
	 * 
	 * Os objectos igualam a sua aceleração (variável nao estatica) a esta , por defeito, 
	 * embora possam ter a sua propria aceleração se se quiser defini-la. 
	 * 
	 * @param x - aceleração do objecto em x (feita por ele proprio, atrito à parte)
	 * @param y - aceleração do objecto em y (feita por ele proprio, gravidade à parte)
	 */
	public static void setGlobalAccelaration(float x, float y) {
		global_accelaration_x = x;
		global_accelaration_y = y;
	}


	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean bool) {
		active = bool;
	}

	public static int getScreen_height() {
		return screen_height;
	}

	public static void setScreen_height(int screen_height) {
		GameObject.screen_height = screen_height;
	}

	public static int getScreen_width() {
		return screen_width;
	}

	public static void setScreen_width(int screen_width) {
		GameObject.screen_width = screen_width;
	}


	public static Resources getRes() {
		return res;
	}

	public static void setRes(Resources res) {
		GameObject.res = res;
	}

	public GameObject(float x, float y, int height, int width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	/**
	 * Função que verifica se o objecto colide com outro objecto 
	 * @param r - objecto com o qual se quer verificar se o atual colide
	 * @return
	 */
	public boolean colide(GameObject r) {
		if (r.getX() > x + width) return false;
		if (r.getY() > y + height) return false;
		if (r.getX() + r.getWidth() < x) return false;
		if (r.getY() + r.getHeight() < y) return false;
		return true;
	}

	/**
	 * Função que verifica se o objecto colide com um determinado espaço,
	 * passado como input 
	 * 
	 * @param x2
	 * @param y2
	 * @return
	 */
	public boolean colide(int x2, int y2) {
		if (x2 > x + width) return false;
		if (y2 > y + height) return false;
		if (x2 < x) return false;
		if (y2 < y) return false;
		return true;
	}

	/**
	 * Função move default para todos os objectos
	 * 
	 * Aceleração particular do objecto recebe o valor da aceleração global (pois todos têm a mesma, visto o movimento ser do jogador)
	 * 
	 * A velocidade em y é definida através da aceleração provocada pelos motadores da nave + aceleração gravítica
	 * A velocidade em x é definida pela aceleração horizontal da nave + aceleração oposta ao moviemtno (atrito)
	 * 
	 * A divisão por FPS nos cálculos é devida ao framerate. Esta função é chamada FPS vezes por segundo 
	 * logo o incremento deve ser 1/FPS para os valores terem unidades certas - m/s^2 e m/s
	 * 
	 * O jogador encontra-se no centro do referencial, sendo assim o movimento do mesmo é simulado através
	 * do movimento relativo de todos os outros objectos em cena. 
	 * Como o jogador está em queda livre, os objectos sobem. Quando ultrapassam o 2*(topo do ecrã), a sua posição
	 * é recalculada aleatoriamente numa posição abaixo do ecrã.   
	 */
	public void move() {
	
		accelaration_x = global_accelaration_x;
		accelaration_y = global_accelaration_y;
	

		resistence = (float) (-0.9*velocity_x);

		velocity_x = velocity_x + (accelaration_x + resistence)/Tools.getFPS();
		velocity_y = velocity_y + (accelaration_y + gravity_constant)/Tools.getFPS();

		if(velocity_y >= terminal_velocity) {
			accelaration_y = 0;
		}
		
		if(velocity_x >= terminal_velocity) {
			accelaration_x = 0;
		}

		if (getY()>-screen_height) {
			setY(getY() + velocity_y/Tools.getFPS());
			setX(getX() + velocity_x/Tools.getFPS());
	 
			
		} else {
			int x = (int) (Math.random()*3*screen_width);
			int y = (int) (Math.random()*screen_height);
			setX(x);
			setY(screen_height+y);
		}
	}

	public float getAccelaration_y() {
		return accelaration_y;
	}

	public void setAccelaration_y(float accelaration_y) {
		this.accelaration_y = accelaration_y;
	}

	public float getVelocity_y() {
		return velocity_y;
	}

	public void setVelocity_y(float velocity_y) {
		this.velocity_y = velocity_y;
	}

	public float getAccelaration_x() {
		return accelaration_x;
	}

	public void setAccelaration_x(float accelaration_x) {
		this.accelaration_x = accelaration_x;
	}

	public Bitmap getBmp() {
		return bmp;
	}


	public void setBmp(Bitmap bmp) {
		this.bmp = bmp;
	}


	public float getVelocity_x() {
		return velocity_x;
	}

	public void setVelocity_x(float velocity_x) {
		this.velocity_x = velocity_x;
	}

	abstract public void draw(Canvas canvas, Paint paint);

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float d) {
		this.y = d;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public static float getGlobalVelocity_x() {
		return global_velocity_x;
	}

	public static float getGlobalVelocity_y() {
		return global_velocity_y;
	}

	public static void setGlobalVelocity(float x, float y) {
		GameObject.global_accelaration_x = x;
		GameObject.global_accelaration_y = y;
	}

}
