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
	
	public void end() {
		// Jogador consegue chegar ao solo 
	}
	
	@Test
	public void picksSlowDownBonus() {
		// jogador apanha um bonus slow motion (a velocidade de queda abranda bruscamente)
		// Testa se os objectos (cenário excepto jogador) ficaram mais lentos
	}
	
	@Test 
	public void velocity() {
		// verifica se velocidade funciona como esperados
	}
}
