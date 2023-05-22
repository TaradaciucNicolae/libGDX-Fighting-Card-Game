package com.gdx.game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyAnimationTest {

  @Test
  public void testMyAnimation() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    
    MyAnimation animation=new MyAnimation(new Animation<TextureRegion>(0.1f,new TextureRegion[2]));
    assertNotNull("animation was not instantiated correctly",animation.animation);
  }

  @Test
  public void testSetCoord() {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.disableAudio(true);
    new Lwjgl3Application(new GdxFightingGame(), config).exit();
    
    MyAnimation animation=new MyAnimation(new Animation<TextureRegion>(0.1f,new TextureRegion[2]));
    animation.setCoord(100);
    assertEquals(100,animation.xcoord);
  }

}
