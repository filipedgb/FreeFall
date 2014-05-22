package game.tests;

import static org.junit.Assert.*;
import org.junit.Test;

public class PlayerTests {

	//GAMEPLAY
	
	@Test
	public void moveLeft() { 
		// jogador move-se para a esquerda. Verificar posição, velocidade e aceleração
	}
	
	public void moveRight() { 
		// jogador move-se para a direita. Verificar posição, velocidade e aceleração
	}
	
	@Test 
	public void movesUp() {
		// jogador gasta combustível para contrariar a gravidade, abrandando a queda
	}
	
	public void movesDown() {
		// jogador pode arriscar acelerar ainda mais a sua queda para ganhar mais pontos 
	}
	
	@Test
	public void gameOver() {
		// jogador fica sem vida 
	}
	
	@Test
	public void movesOutOfBounds() {
		
	}
	
	
	// BONUS E OBSTACULOS
	
	@Test
	public void picksHealth() {
		// jogador apanha um bonus de vida (ganha vida)
	}
	
	@Test
	public void picksFuel() {
		// jogador apanha um bonus de combustivel
	}
	
	@Test
	public void picksInvulnerableBonus () {
		// jogador fica invulnerável durante x tempo
	}

	@Test
	public void colidesWithObstacle() {
		// jogador colide com um obstaculo (perde vida)
	}
	
	@Test
	public void colidesWithMine() {
		// jogador fica com os controlos trocados 
	}

	
	
}
