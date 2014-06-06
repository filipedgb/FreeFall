package game.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import game.entities.*;

public class PlayerTests {

	//GAMEPLAY
	@Test
	public void gameOver() {
		// jogador fica sem vida
		Player p = new Player(true);

		int life = p.getLifepoints();

		//cria obstaculo que tira a vida toda ao player
		Obstacle ob = new Obstacle((int) p.getX(), (int) p.getY(), life,true);

		//verifica se colide
		assertTrue(ob.colide(p));

		ob.damage(p);

		//verifica se perde vida toda
		assertTrue(life > p.getLifepoints());
		assertEquals(p.getLifepoints(),0);
	}

	// BONUS E OBSTACULOS

	@Test
	public void picksHealth() {
		// jogador apanha um bonus de vida (ganha vida)
		Player p = new Player(true);

		int life = p.getLifepoints();

		//cria objeto que da a vida ao player
		Health h = new Health(p.getX(),p.getY(), true);

		Obstacle ob = new Obstacle((int) p.getX(), (int) p.getY(), 20,true);

		//verifica se apanha
		assertTrue(h.colide(p));
		h.caught(p, true);

		//verifica se nao ganha vida, pois tem a vida cheia
		assertEquals(life, p.getLifepoints());

		//perde vida
		ob.damage(p);
		int life1 = p.getLifepoints();
		assertTrue(life > life1);

		//ganha vida
		h.caught(p, true);
		assertTrue(life1 < p.getLifepoints());
	}

	@Test
	public void picksFuel() {
		// jogador apanha um bonus de combustivel
		Player p = new Player(true);

		int fuel = p.getFuel();

		//cria objeto que da a vida ao player
		Fuel f = new Fuel(p.getX(),p.getY(), true);

		//verifica se apanha
		assertTrue(f.colide(p));
		f.caught(p, true);

		//nao ganha fuel, pois esta cheio
		assertFalse(fuel < p.getFuel());

		//retirar fuel
		p.addFuel(-100);
		assertTrue(fuel > p.getFuel());

		//ganha fuel
		int fuel1 = p.getFuel();
		f.caught(p, true);
		assertTrue(fuel1 < p.getFuel());
	}

	@Test
	public void picksInvulnerableBonus () {
		// jogador fica invulnerável durante x tempo
		Player p = new Player(true);

		int life = p.getLifepoints();

		Invulnerability ob = new Invulnerability();

		Obstacle obj = new Obstacle((int) p.getX(), (int) p.getY(), life,true);

		//verifica se apanha
		assertTrue(ob.colide(p));
		ob.caught(p, true);

		//colide com objeto que danifica
		obj.damage(p);

		//verifica se nao perde vida
		assertEquals(life, p.getLifepoints());
	}

	@Test
	public void colidesWithObstacle() {
		// jogador colide com um obstaculo (perde vida)
		Player p = new Player(true);

		int life = p.getLifepoints();

		Obstacle ob = new Obstacle((int) p.getX(), (int) p.getY(), life,true);

		//verifica se colide
		assertTrue(ob.colide(p));

		ob.damage(p);

		//verifica se perde vida
		assertTrue(life > p.getLifepoints());
	}

	@Test
	public void colidesWithMine() {
		// jogador fica com os controlos trocados 
		Player p = new Player(true);
		Skymine s = new Skymine(p.getX(),p.getY(), true);

		assertFalse(p.isMalfunctioning());
		assertTrue(s.colide(p));
		s.caught(p,true);

		assertTrue(p.isMalfunctioning());
	}
}
