package com.gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * GdxFightingGame Class.
 */
public class GdxFightingGame extends Game {

  public Player p1;
  public MapScreen map;
  public int score = 0;
  public int currentRoomScore;
  public int zone = 3;
  public float soundVolume = 1;
  public static Skin gameSkin;

  /**
   * Create() method.
   * This method is used for creating the menu screen.
   */
  public void create() {
    gameSkin = new Skin(Gdx.files.internal("glassy//skin//glassy-ui.json"));
    this.setScreen(new MainScreen(this));

  }

  @Override
  public void render() {
    // clear the screen
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    super.render();
  }

  public void dispose() {
  }

}