package game.main;

import game.objects.Alien;
import game.objects.Clouds;
import android.content.Context;
import android.graphics.*;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.*;

public class GameView extends View implements Runnable {
	private final static int INTERVAL = 200;
	private Paint paint;
	private Clouds[] inimigos;
	private Alien principal;
	private boolean jogoIniciado = false;
	
	private int FRAMES_PER_SECOND = 25;
    private int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
	private long next_game_tick = System.currentTimeMillis();
    private int sleep_time = 0;
	private boolean running = true;

	
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
			update();
			postInvalidate();
			next_game_tick += SKIP_TICKS;
			sleep_time = (int) (next_game_tick - System.currentTimeMillis());
			if(sleep_time >= 0) {
				try {
					java.lang.Thread.sleep(sleep_time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
			
	private void update() {
		if (jogoIniciado==false) {
			return;
		}
		for (int i = 0; i < inimigos.length; i++) {
			inimigos[i].move(getHeight(), getWidth());
		}
		
		for(int i = 0; i < inimigos.length; i++) {
			if(principal.colide(inimigos[i])) {
				principal.getsHit();
			}
		}
		
		if(principal.getLifepoints() <= 0) {
			release();
		}
		
		if(principal.isTurbo() && principal.getTurbopoints() > 0) {
			principal.setTurbopoints(principal.getTurbopoints()-1);
		}
		else if (principal.getTurbopoints() < 100 && !principal.isTurbo()){
			principal.setTurbopoints(principal.getTurbopoints()+0.1);

		}
		
		
		if(principal.getTurbopoints() <= 0) {
			principal.setTurbo(false);
			FRAMES_PER_SECOND = 25;
			SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
		}
		
		
		
		

		
		
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
		
		//defino a cor do texto
		paint.setColor(Color.WHITE);
		paint.setTextSize(15);
	//	canvas.drawText("Pontos: " + pontos, 0, 30, paint);
		
		canvas.drawText("Life points: " + principal.getLifepoints(), getWidth()/3, 30, paint);
		canvas.drawText("Turbo points: " + (int) principal.getTurbopoints(), getWidth()/3, 50, paint);

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
			//hold = true;
			
			if(event.getX() < getWidth()/2) {
				principal.moveLeft();
			}
						
			if(event.getX() > getWidth()/2) {
				principal.moveRight();
			}
			
			if(event.getY() > (4*getHeight()/5) && principal.getTurbopoints() > 0) {
				principal.setTurbo(true);
				FRAMES_PER_SECOND = 65;
				SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
				Log.e("entrou", "" + FRAMES_PER_SECOND);
			}
			
			if(event.getY() < (getHeight()/5)) {
				principal.setTurbo(false);
				FRAMES_PER_SECOND = 25;
				SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
				Log.e("entrou", "" + FRAMES_PER_SECOND);

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
