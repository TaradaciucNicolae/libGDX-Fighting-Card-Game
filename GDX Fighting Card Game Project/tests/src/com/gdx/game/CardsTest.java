package com.gdx.game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class CardsTest {
  
  /**
   * Testarea functiei getArmour().
   */

  @Test
  public void testGetArmour() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    Gdx.app.exit();

    WaterCard card = new WaterCard(7);
    assertEquals("Nu este corect", 7, card.getArmour());
  }

  /**
   * Testarea functiei getDamage().
   */

  @Test
  public void testGetDamage() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    Gdx.app.exit();
    
    FireCard card = new FireCard(8);
    assertEquals("Nu este corect", 8, card.getDamage());
  }

  /**
   * Testarea functiei getHealth().
   */

  @Test
  public void testGetHealth() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    Gdx.app.exit();
    
    EarthCard card = new EarthCard(9);
    assertEquals("Nu este corect", 9, card.getHealth());
  }

  /**
   * Testarea functiei setArmourCard().
   */

  @Test
  public void testSetArmourCard() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    Gdx.app.exit();
    
    WaterCard card = new WaterCard(7);
    card.setArmourCard(10);
    assertTrue(card.getArmour() == 10);
  }

  /**
   * Testarea functiei setDamageCard().
   */

  @Test
  public void testSetDamageCard() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    Gdx.app.exit();
    
    FireCard card = new FireCard(8);
    card.setDamageCard(10);
    assertTrue(card.getDamage() == 10);
  }

  /**
   * Testarea functiei setHealthCard().
   */

  @Test
  public void testSetHealthCard() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    Gdx.app.exit();
    
    EarthCard card = new EarthCard(9);
    card.setHealthCard(10);
    assertTrue(card.getHealth() == 10);
  }
}
