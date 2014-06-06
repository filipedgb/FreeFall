package game.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import game.entities.*;

public class ObjectTests {

	@Test
	public void HealthItem() {
		// verificar se o item fica inactivo depois de apanhado (durante x tempo)
		Player p = new Player(true);
		Health h = new Health(p.getX(),p.getY(), true);

		assertTrue(h.isActive());
		h.caught(p, true);

		assertFalse(h.isActive());
	}

	@Test
	public void FuelItem() {
		// verificar se o item fica inactivo depois de apanhado (durante x tempo)
		Player p = new Player(true);
		Fuel f = new Fuel(p.getX(),p.getY(), true);

		assertTrue(f.isActive());
		f.caught(p, true);

		assertFalse(f.isActive());
	}

	@Test
	public void slowDownItem() {
		// verificar se o item fica inactivo depois de apanhado (durante x tempo)
		Player p = new Player(true);
		SlowDown sm = new SlowDown(p.getX(),p.getY(), true);

		assertTrue(sm.isActive());
		sm.caught(p, true);

		assertFalse(sm.isActive());
	}

	@Test
	public void mineItem() {
		// verificar se o item fica inactivo depois de apanhado (durante x tempo)
		Player p = new Player(true);
		Skymine s = new Skymine(p.getX(),p.getY(), true);

		assertTrue(s.isActive());
		s.caught(p, true);

		assertFalse(s.isActive());
	}

	@Test
	public void InvulnerabilityItem() {
		// verificar se o item fica inactivo depois de apanhado (durante x tempo)
		Player p = new Player(true);
		Invulnerability i = new Invulnerability();

		assertTrue(i.isActive());
		i.caught(p, true);

		assertFalse(i.isActive());
	}
}
