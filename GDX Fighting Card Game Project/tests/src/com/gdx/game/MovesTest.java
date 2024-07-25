package com.gdx.game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class MovesTest {

  @Test
  public void testGetDmg() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    
    Moves m1=new Moves(30,10,10);
    assertEquals(30,m1.getDmg());
  }

  @Test
  public void testGetHeal() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    
    Moves m1=new Moves(30,10,10);
    assertEquals(10,m1.getHeal());
  }

  @Test
  public void testGetArmour() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    
    Moves m1=new Moves(30,10,10);
    assertEquals(10,m1.getArmour());
  }

}
