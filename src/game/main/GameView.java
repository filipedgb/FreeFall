package game.main;

import game.objects.Alien;
import game.objects.Clouds;
import android.content.Context;
import android.graphics.*;
import android.util.Log;
import android.view.*;

public class GameView extends View implements Runnable {
	private final static int INTERVAL = 25;
	private boolean running = true;
	private Paint paint;
	private Clouds[] inimigos;
	private Alien principal;
	private int pontos = 0;
	private long tempo = System.currentTimeMillis();
	
	private boolean jogoIniciado = false;
	
	private Bitmap bmpFundo;
	
	public GameView(Context context) {
		super(context);
		paint = new Paint();
		Thread minhaThread = new Thread(this);
		minhaThread.setPriority(Thread.MIN_PRIORITY);
		minhaThread.start();
	}
	
	public void iniciaJogo() {
		inimigos = new Clouds[5];
		for (int i = 0; i < inimigos.length; i++) {
			int y = i*+50;
			int x = (int) (Math.random()*(getWidth()-25));
			inimigos[i] = new Clouds(x, y, getResources());
		}
		
		principal = new Alien(getWidth()/2, getHeight()/2);
		
		
		
		
//		bmpFundo = BitmapFactory.decodeResource(getResources(), R.drawable.fundo);
//		bmpFundo = Bitmap.createScaledBitmap(bmpFundo, getWidth(), getHeight(), true);
		
		jogoIniciado = true;
	}

	public void run() {
		while (running) {
			try {
				Thread.sleep(INTERVAL);
			} catch (Exception e) {
				Log.e("Jogo", "Sleep da Thread");
			}
			update();
		}
	}
			
	private void update() {
		if (jogoIniciado==false) {
			return;
		}
		for (int i = 0; i < inimigos.length; i++) {
			inimigos[i].move(getHeight(), getWidth());
		}

		//invoca o método draw
		postInvalidate();
		
	}
	
	public void draw(Canvas canvas) {
		super.draw(canvas);
		if (jogoIniciado==false) {
			iniciaJogo();
		}
		
		//desenha cor de fundo
		canvas.drawColor(Color.BLACK);
//		canvas.drawBitmap(bmpFundo, 0, 0, paint);
		
		//define a cor do desenho
		paint.setColor(Color.BLUE);
		for (int i = 0; i < inimigos.length; i++) {
			inimigos[i].draw(canvas, paint);
		}
		
		principal.draw(canvas,paint);
		
//		//defino a cor do texto
//		paint.setColor(Color.BLACK);
//		paint.setTextSize(30);
//		canvas.drawText("Pontos: " + pontos, 0, 30, paint);
//		
//		int segundos = (int) (System.currentTimeMillis() - tempo)/1000;
//		canvas.drawText("Tempo: " + segundos, 200, 30, paint);
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		//pega o evento
		boolean hold = true;
		int action = event.getAction();
		//pega a posicao do dedo
		int x = (int) event.getX();
		int y = (int) event.getY();
		if (action == MotionEvent.ACTION_DOWN) {
			//afundou o dedo
			hold = true;
			
			if(event.getX() < getWidth()/2) {
				principal.moveLeft();
			}
						
			if(event.getX() > getWidth()/2) {
				principal.moveRight();
			}
			
			
		
		} else if (action==MotionEvent.ACTION_UP) {
			//soltou o dedo
			
		//	hold = false;

		}
		else if (action==MotionEvent.ACTION_MOVE) {
			//movimentou o dedo
			
			
		}
		
		return super.onTouchEvent(event);
	}
	
	//termina o jogo
	public void release() {
		running = false;
	}
	
}
