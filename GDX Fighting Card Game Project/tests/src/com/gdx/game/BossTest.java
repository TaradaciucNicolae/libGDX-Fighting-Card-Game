package com.gdx.game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class BossTest {

  @Test
  public void testGetHealth() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    
    Boss b1=new Boss();
    assertEquals(300,b1.getHealth());
  }

  @Test
  public void testGetDamage() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    
    Boss b1=new Boss();
    assertEquals(15,b1.getDamage());
  }

  @Test
  public void testHeal() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    
    Boss b1=new Boss();
    b1.heal(15);
    assertEquals(315,b1.getHealth());
  }

  @Test
  public void testSetHealth() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    
    Boss b1=new Boss();
    b1.SetHealth(10);
    assertEquals(290,b1.getHealth());
  }

}
