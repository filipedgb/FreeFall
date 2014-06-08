package game.engine;

import game.entities.Item;
import game.states.PlayState;
import android.content.Context;
import android.graphics.*;
import android.view.*;

/**
 * Esta classe representa a view do jogo
 * 
 * @author André Pires, Filipe Gama
 * @see View
 */
public class GameView extends View {
	private Paint paint;
	private PlayState game = null;

	public GameView(Context context) {
		super(context);
		Tools.setRes(this.getResources());
		// GameObject.setRes(this.getResources());
		paint = new Paint();
	}

	/**
	 * Inicializa tudo o que é preciso na view. Um estado do jogo (onde se
	 * encontra a lógica) Carrega as imagens todas através da classe Tools. O
	 * gameloop, que é uma thread responsável por chamar a função de atualização
	 * do estado do jogo e desenhá-lo no ecrã Inicializa a classe controller ,
	 * que contem os listeners tanto de acelerometro como touch.
	 */
	public void init() {
		Tools.loadImages(this.getResources());
		game = new PlayState(this);
		new GameLoop(this, game);
		Controllers.getControllerInstance().controllerInit(this, game);
		this.setOnTouchListener(Controllers.getControllerInstance());
	}

	public PlayState getGame() {
		return game;
	}

	/**
	 * Desenha tudo o que se encontra na view do jogo. (Comentários dentro da
	 * função )
	 * 
	 * Verifica o que deve ser a altura e a largura (landscape/portrait)
	 * 
	 */
	public void draw(Canvas canvas) {
		super.draw(canvas);

		if (game == null) {
			if (this.getHeight() > this.getWidth())
				Tools.init(this.getWidth(), this.getHeight());
			else {
				Tools.init(this.getHeight(), this.getWidth());
				Tools.setOrientation(1);
			}
			// GameObject.setScreen_height(this.getHeight());
			// GameObject.setScreen_width(this.getWidth());
			init();
		}

		if (!game.isGameStarted()) {
			game.init();
		}

		// Desenha o fundo
		if (game.getLevel() == 1)
			canvas.drawColor(Color.argb(255, 0, 0, 0));
		else if (game.getLevel() == 2)
			canvas.drawColor(Color.argb(255, 0, 23, 78));
		else if (game.getLevel() == 3)
			canvas.drawColor(Color.argb(255, 135, 206, 235));

		// Desenha invulnerável bonus
		if (game.getPlayer().isInvulnerable())
			canvas.drawBitmap(Tools.getInvulnerableword(),
					Tools.getDrawUnity(3), Tools.getDrawUnity(4), paint);

		// Desenha bonus points
		if (game.getPlayer().isBoost())
			canvas.drawBitmap(Tools.getBoost(), Tools.getDrawUnity(8),
					Tools.getDrawUnity(3), paint);

		// Desenha malfunction sprite
		if (game.getPlayer().isMalfunctioning())
			Tools.getMalfunction_anim().draw(canvas,
					(int) Tools.getDrawUnity(3), (int) Tools.getDrawUnity(3));

		// Desenha os obstáculos
		for (int i = 0; i < game.getObjects().size(); i++) {
			game.getObjects().get(i).draw(canvas, paint);
		}

		// desenha barras
		drawBarras(canvas);

		// Desenha jogador
		game.getPlayer().draw(canvas, paint);

		// Desenha items que podem ser apanhados -
		// Vida/Invulnerabilidade/Combustível
		for (Item x : game.getItens())
			if (x.isActive())
				x.draw(canvas, paint);

		// Desenha a pontuação
		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 92, 87, 8));
		paint.setTextSize(Tools.getDrawUnity((float) 1.5));
		canvas.drawText("SCORE: " + (int) game.getPoints(),
				Tools.getDrawUnity(12),
				paint.getTextSize() + Tools.getDrawUnity(1), paint);
	}

	/**
	 * Desenha as barras de vida e de combustivel
	 * 
	 * @param canvas
	 */
	public void drawBarras(Canvas canvas) {
		// Desenha barras por baixo
		paint.setColor(Color.MAGENTA);

		canvas.drawRect(
				Tools.getDrawUnity(2),
				Tools.getDrawUnity((float) 1.5),
				Tools.getDrawUnity(2) + Tools.getDrawUnity(7),
				Tools.getDrawUnity((float) 1.5)
						+ Tools.getDrawUnity((float) 0.5), paint);

		// Desenha barra de vida
		paint.setColor(Color.GREEN);

		canvas.drawRect(
				Tools.getDrawUnity(2),
				Tools.getDrawUnity((float) 1.5),
				Tools.getDrawUnity(2)
						+ (game.getPlayer().getHealthFrac() * Tools
								.getDrawUnity(7)),
				Tools.getDrawUnity((float) 1.5)
						+ Tools.getDrawUnity((float) 0.5), paint);

		// Desenha barras por baixo
		paint.setColor(Color.GRAY);

		canvas.drawRect(
				Tools.getDrawUnity(2),
				Tools.getDrawUnity((float) 2.25),
				Tools.getDrawUnity(2) + Tools.getDrawUnity(7),
				Tools.getDrawUnity((float) 2.25)
						+ Tools.getDrawUnity((float) 0.5), paint);

		paint.setColor(Color.BLUE);

		// Desenha barra de fuel
		canvas.drawRect(
				Tools.getDrawUnity(2),
				Tools.getDrawUnity((float) 2.25),
				Tools.getDrawUnity(2)
						+ (game.getPlayer().getFuelFrac() * Tools
								.getDrawUnity(7)),
				Tools.getDrawUnity((float) 2.25)
						+ Tools.getDrawUnity((float) 0.5), paint);
	}
}
