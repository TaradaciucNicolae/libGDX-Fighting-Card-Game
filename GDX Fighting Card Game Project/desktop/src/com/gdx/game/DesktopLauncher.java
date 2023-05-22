package com.gdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.gdx.game.GdxFightingGame;

/**
 * Game launcher.
 */
public class DesktopLauncher {
  /**
   * Main.
   */
  public static void main(String[] arg) {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.setForegroundFPS(60);
    config.setTitle("My GDX Game");
    config.setWindowedMode(1280, 720);

    new Lwjgl3Application(new GdxFightingGame(), config);
  }
}
