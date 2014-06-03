package game.engine;

public class Tools {
	
	private static float screenWithd;
	private static float screenHeight;
	private static float drawUnity;
	private static float FPS = 75;
		
	public static void init(int width, int height) {
		screenWithd = width;
		screenHeight = height;
		drawUnity = screenWithd/20;
	}
	
	public static float getScreenWithd() {
		return screenWithd;
	}

	public static float getScreenHeight() {
		return screenHeight;
	} 
		
	public static float getDrawUnity(float numberUnities) {
		return drawUnity*numberUnities;
	}

	public static float getFPS() {
		return FPS;
	}


	
	
	

}
