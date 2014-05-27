package game.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import game.entities.*;

public class PlayerTests {

	//GAMEPLAY

	@Test
	public void moveLeft() { 
		// jogador move-se para a esquerda. Verificar posição, velocidade e aceleração
		Player p = new Player();

		float pX = p.getX();
		float pY = p.getY();
		float vX = p.getVelocity_x();
		float vY = p.getVelocity_y();
		float aX = p.getAccelaration_x();
		float aY = p.getAccelaration_y();

		//move left
		p.move(-20,0);

		assertTrue(pX > p.getX());
		assertTrue(pY == p.getY());

	}

	public void moveRight() { 
		// jogador move-se para a direita. Verificar posição, velocidade e aceleração
		Player p = new Player();

		float pX = p.getX();
		float pY = p.getY();
		float vX = p.getVelocity_x();
		float vY = p.getVelocity_y();
		float aX = p.getAccelaration_x();
		float aY = p.getAccelaration_y();

		//move right
		p.move(20,0);

		assertTrue(pX < p.getX());
		assertTrue(pY == p.getY());
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
		Player p = new Player();

		int life = p.getLifepoints();

		//cria obstaculo que tira a vida toda ao player
		Obstacle ob = new Obstacle(0,0, life);

		//verifica se colide
		assertTrue(ob.colide(p));

		ob.damage(p);

		//verifica se perde vida toda
		assertTrue(life > p.getLifepoints());
	}

	@Test
	public void movesOutOfBounds() {

	}


	// BONUS E OBSTACULOS

	@Test
	public void picksHealth() {
		// jogador apanha um bonus de vida (ganha vida)
		Player p = new Player();

		int life = p.getLifepoints();

		//cria obstaculo que da a vida ao player
		Health h = new Health(p.getX(),p.getY(), 5,5);

		//verifica se apanha
		assertTrue(h.colide(p));

		h.caught(p);

		//verifica se ganha vida
		assertTrue(life < p.getLifepoints());
	}

	@Test
	public void picksFuel() {
		// jogador apanha um bonus de combustivel
	}

	@Test
	public void picksInvulnerableBonus () {
		// jogador fica invulnerável durante x tempo
		Player p = new Player();

		int life = p.getLifepoints();

		Invulnerability ob = new Invulnerability();

		Obstacle obj = new Obstacle(0,0);

		//verifica se apanha
		assertTrue(ob.colide(p));

		//colide com objeto que danifica
		obj.damage(p);

		//verifica se nao perde vida
		assertTrue(life > p.getLifepoints());
	}

	@Test
	public void colidesWithObstacle() {
		// jogador colide com um obstaculo (perde vida)
		Player p = new Player();

		int life = p.getLifepoints();

		Obstacle ob = new Obstacle(0,0);

		//verifica se colide
		assertTrue(ob.colide(p));

		ob.damage(p);

		//verifica se perde vida
		assertTrue(life > p.getLifepoints());
	}

	@Test
	public void colidesWithMine() {
		// jogador fica com os controlos trocados 
	}



}
