package game.engine;

import game.config.R;
import game.entities.GameObject;
import game.states.PlayState;
import android.content.Context;
import android.graphics.*;
import android.view.*;

/**
 * Esta classe representa a view do jogo
 * @author André Pires, Filipe Gama
 * @see View
 */
public class GameView extends View {
	private Paint paint;
	private PlayState game = null;
	private Bitmap malfunction;
	private Sprite malfunction_anim;

	public GameView(Context context) {
		super(context);
		GameObject.setRes(this.getResources());
		paint = new Paint();
	}

	/**
	 * Inicializa tudo o que é preciso na view. Um estado do jogo (onde se encontra a lógica)
	 * O gameloop, que é uma thread responsável por actualizar e desenhar o jogo
	 * O listener, tanto de acelerometro como touch.
	 * Inicializa também uma sprite -> a animação de quando a nave entra em "malfunctioning"
	 */
	public void init() {
		Tools.loadImages(this.getResources());
		game = new PlayState(this);
		new GameLoop(this,game);
		this.setOnTouchListener(new Controllers(this,game));

		malfunction = BitmapFactory.decodeResource(getResources(), R.drawable.malfunc_sprite);
		malfunction = Tools.getResizedBitmap(malfunction, (int)Tools.getDrawUnity((float) 10.2),(int)Tools.getDrawUnity(40));
		malfunction_anim = new Sprite((int) Tools.getDrawUnity(3),(int) Tools.getDrawUnity(3),(int) Tools.getScreenHeight(),(int) Tools.getScreenWithd(),3,2,malfunction);	
	}

	public PlayState getGame() {
		return game;
	}


	/**
	 *  Desenha tudo o que se encontra na view do jogo. (Comentários dentro da função )
	 */
	public void draw(Canvas canvas) {
		super.draw(canvas);

		if(game == null) {
			Tools.init(this.getWidth(),this.getHeight());
			GameObject.setScreen_height(this.getHeight());
			GameObject.setScreen_width(this.getWidth());
			init();
		}

		if (!game.isGameStarted()) {
			game.init();
		}

		// Desenha o fundo
		canvas.drawColor(Color.argb(255, 135, 206, 235));

		// Desenha invulnerável bonus
		if(game.getPlayer().isInvulnerable()) canvas.drawBitmap(Tools.getInvulnerableword(), Tools.getDrawUnity(3), Tools.getDrawUnity(4), paint);

		// Desenha bonus points
		if(game.getPlayer().isBoost()) canvas.drawBitmap(Tools.getBoost(), Tools.getDrawUnity(8), Tools.getDrawUnity(3), paint);

		// Desenha malfunction sprite

		if(game.getPlayer().isMalfunctioning())	malfunction_anim.draw(canvas);

		// Desenha os obstáculos
		for (int i = 0; i < game.getObjects().size(); i++) {
			game.getObjects().get(i).draw(canvas, paint);
		}

		//Desenha barras por baixo
		paint.setColor(Color.MAGENTA);

		canvas.drawRect(Tools.getDrawUnity(2),
				Tools.getDrawUnity((float)1.5),
				Tools.getDrawUnity(2)+Tools.getDrawUnity(7),
				Tools.getDrawUnity((float)1.5)+Tools.getDrawUnity((float) 0.5),
				paint);

		// Desenha barra de vida 
		paint.setColor(Color.GREEN);

		canvas.drawRect(Tools.getDrawUnity(2),
				Tools.getDrawUnity((float)1.5),
				Tools.getDrawUnity(2)+(game.getPlayer().getHealthFrac()*Tools.getDrawUnity(7)),
				Tools.getDrawUnity((float)1.5)+Tools.getDrawUnity((float) 0.5),
				paint);

		//Desenha barras por baixo
		paint.setColor(Color.GRAY);

		canvas.drawRect(Tools.getDrawUnity(2),
				Tools.getDrawUnity((float)2.25),
				Tools.getDrawUnity(2)+Tools.getDrawUnity(7),
				Tools.getDrawUnity((float)2.25)+Tools.getDrawUnity((float) 0.5),
				paint);

		paint.setColor(Color.BLUE);

		// Desenha barra de fuel
		canvas.drawRect(Tools.getDrawUnity(2),
				Tools.getDrawUnity((float)2.25),
				Tools.getDrawUnity(2)+(game.getPlayer().getFuelFrac()*Tools.getDrawUnity(7)),
				Tools.getDrawUnity((float)2.25)+Tools.getDrawUnity((float) 0.5),
				paint);

		// Desenha jogador
		game.getPlayer().draw(canvas,paint);

		// Desenha items que podem ser apanhados - Vida/Invulnerabilidade/Combustível
		if(game.getHealth_item().isActive()) game.getHealth_item().draw(canvas,paint);
		if(game.getSlowmotion_item().isActive()) game.getSlowmotion_item().draw(canvas,paint);
		if(game.getNodamage_item().isActive())game.getNodamage_item().draw(canvas,paint);
		if(game.getFuel_item().isActive())game.getFuel_item().draw(canvas,paint);
		if(game.getSky_mine().isActive())game.getSky_mine().draw(canvas,paint);

		// Desenha a pontuação
		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 92, 87, 8));
		paint.setTextSize(Tools.getDrawUnity((float)1.5));
		canvas.drawText("SCORE: " + (int) game.getPoints(),Tools.getDrawUnity(12) ,paint.getTextSize()+Tools.getDrawUnity(1), paint);
	}

}
