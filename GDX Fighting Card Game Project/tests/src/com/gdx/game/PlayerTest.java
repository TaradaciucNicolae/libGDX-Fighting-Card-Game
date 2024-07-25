package com.gdx.game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class PlayerTest {


  @Test
  public void testGetHealth() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    Gdx.app.exit();
    
    Player p=new Player();
    p.setHealth(200);
    assertEquals(0,p.getHealth());
  }

  @Test
  public void testSetHealth() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    Gdx.app.exit();
    
    Player p=new Player();
    p.setHealth(20);
    assertEquals(80,p.getHealth());
  }

  @Test
  public void testGetArmour() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    Gdx.app.exit();
    
    Player p=new Player();
    p.setArmour(200);
    assertEquals(0,p.getArmour());
  }

  @Test
  public void testSetArmour() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    Gdx.app.exit();
    
    Player p=new Player();
    p.setHealth(20);
    assertEquals(80,p.getHealth());
  }
  

}
