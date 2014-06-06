package game.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import game.entities.*;
import game.states.*;

public class GeneralTests {

	@Test
	public void init() {
		// verifica inicializações do jogo
		HighscoreState h = new HighscoreState();
		Player p = new Player(true);

		assertEquals(h.numHighscores(),10);
		assertEquals(p.getLifepoints(), Player.getMax_life());
	}

	@Test
	public void newZone() {
		// Ao longo da queda livre o jogador vai passando por várias zonas da atmosfera 
	}

	@Test
	public void testHighscore() {
		HighscoreState h = new HighscoreState();
		
		assertEquals(h.getHighscores().size(),10);
		
		//verifica se adiciona um novo highscore		
		h.addHighscore("name", 10);
		
		assertEquals(h.getHighscores().get(0).get(0),"name");
		
		assertEquals(h.getHighscores().get(0).get(1), Integer.toString(10));
		
		//verifica se continua com 10 highscores
		assertEquals(h.getHighscores().size(),10);
	}
	
	@Test
	public void picksSlowDownBonus() {
		// jogador apanha um bonus slow motion (a velocidade de queda abranda bruscamente)
		// Testa se os objectos (cenário excepto jogador) ficaram mais lentos
		Player p = new Player(true);
		SlowDown s = new SlowDown(p.getX(), p.getY(), true);

		assertTrue(s.colide(p));

		s.caught(p);
	}
}
