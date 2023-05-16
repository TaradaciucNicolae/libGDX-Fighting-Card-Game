package com.gdx.game;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * 
 * @author Ovidiu-Florin Berea
 * Unit test pentru clasa Cards.
 */
class CardsTest {
	
	/**
	 * Testarea functiei getArmour().
	 */
	
	@Test
	public void testGetArmour() {
		WaterCard card = new WaterCard(7);
		assertEquals("Nu este corect", 7, card.getArmour());
	}
	
	/**
	 * Testarea functiei getDamage().
	 */
	
	@Test
	public void testGetDamage() {
		FireCard card = new FireCard(8);
		assertEquals("Nu este corect", 8, card.getDamage());
	}
	
	/**
	 * Testarea functiei getHealth().
	 */
	
	@Test
	public void testGetHealth() {
		EarthCard card = new EarthCard(9);
		assertEquals("Nu este corect", 9, card.getHealth());
	}
	
	/**
	 * Testarea functiei setArmourCard().
	 */
	
	@Test
	public void testSetArmourCard() {
		WaterCard card = new WaterCard(7);
		card.setArmourCard(10);
		assertTrue(card.getArmour() == 10);
	}
	
	/**
	 * Testarea functiei setDamageCard().
	 */
	
	@Test
	public void testSetDamageCard() {
		FireCard card = new FireCard(8);
		card.setDamageCard(10);
		assertTrue(card.getDamage() == 10);
	}
	
	/**
	 * Testarea functiei setHealthCard().
	 */
	
	@Test
	public void testSetHealthCard() {
		EarthCard card = new EarthCard(9);
		card.setHealthCard(10);
		assertTrue(card.getHealth() == 10);
	}

}
