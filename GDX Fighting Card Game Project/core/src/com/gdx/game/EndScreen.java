package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class is for the final screen.
 */

public class EndScreen implements Screen {
  /**
   * Parameters of EndScreen. game-instance of the game batch-parameter used for
   * drawing the background backgrounds-an array of textures used to store the
   * moving backgrounds sky-background that is going to be static on the screen
   * backgroundOffsets-an array of float values that is telling the speed at which
   * the backgrounds are moving backgroundMaxScrollingSpeed-a float value that
   * indicates the maximum value at which a background can move font-stores the
   * font which is going to be used for drawing the text on screen sound-stores
   * the music which is played when the screen comes on
   */
  private GdxFightingGame game;
  private SpriteBatch batch;
  private Texture[] backgrounds;
  private Texture sky;
  private float[] backgroundOffsets = { 0, 0, 0, 0, 0 };
  private float backgroundMaxScrollingSpeed;
  private BitmapFont font;
  private Music sound;

  /**
   * EndScreen constructor which instantiates all of the variables.
   */
  public EndScreen(GdxFightingGame game) {
    this.game = game;
    backgrounds = new Texture[5];
    backgrounds[0] = new Texture("background//EndScreen//Layers//far-clouds.png");
    backgrounds[1] = new Texture("background//EndScreen//Layers//near-clouds.png");
    backgrounds[2] = new Texture("background//EndScreen//Layers//far-mountains.png");
    backgrounds[3] = new Texture("background//EndScreen//Layers//mountains.png");
    backgrounds[4] = new Texture("background//EndScreen//Layers//trees.png");
    sky = new Texture("background//EndScreen//Layers//sky.png");
    backgroundMaxScrollingSpeed = Gdx.graphics.getWidth() / 6;
    batch = new SpriteBatch();
    font = new BitmapFont(Gdx.files.internal("EndScreen//font//endscreenfont.fnt"));
    sound = Gdx.audio.newMusic(Gdx.files.internal("ogg//Night Ambient 1.ogg"));
    sound.setVolume(game.soundVolume);
    sound.setLooping(true);
    sound.play();
  }

  /**
   * This method is responsible for drawing the layers by drawing one background
   * after the other.
   * 
   * @param deltaTime-the time at which the game is progressing
   */
  private void drawBackground(float deltaTime) {
    backgroundOffsets[0] += deltaTime * backgroundMaxScrollingSpeed / 16;
    backgroundOffsets[1] += deltaTime * backgroundMaxScrollingSpeed / 8;
    backgroundOffsets[2] += deltaTime * backgroundMaxScrollingSpeed / 4;
    backgroundOffsets[3] += deltaTime * backgroundMaxScrollingSpeed / 2;
    backgroundOffsets[4] += deltaTime * backgroundMaxScrollingSpeed;
    for (int layer = 0; layer < backgrounds.length; ++layer) {
      // when a layer offset becomes greater than the width of the screen
      // it get set back to 0
      if (backgroundOffsets[layer] > Gdx.graphics.getWidth()) {
        backgroundOffsets[layer] = 0;
      }
      // this line draws the layer from right to left
      batch.draw(backgrounds[layer], -backgroundOffsets[layer], 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      // this line draws the second layer which comes after the first
      batch.draw(backgrounds[layer], -backgroundOffsets[layer] + Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(),
          Gdx.graphics.getHeight());
    }
  }

  @Override
  public void show() {
    // TODO Auto-generated method stub
  }

  @Override
  public void render(float delta) {
    // TODO Auto-generated method stub
    batch.begin();
    batch.draw(sky, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    drawBackground(Gdx.graphics.getDeltaTime());
    font.draw(batch, "Thus the necromancer has been slain... At least for NOW...", 200, 200);
    font.draw(batch, "Score " + game.score, 100, 100);
    batch.end();
    if (Gdx.input.isTouched()) {
      game.zone = 1;
      game.score = 0;
      game.setScreen(new MainScreen(game));
    }
  }

  @Override
  public void resize(int width, int height) {
    // TODO Auto-generated method stub

  }

  @Override
  public void pause() {
    // TODO Auto-generated method stub

  }

  @Override
  public void resume() {
    // TODO Auto-generated method stub

  }

  @Override
  public void hide() {
    // TODO Auto-generated method stub
    sound.stop();
  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub
    sky.dispose();
    for (int i = 0; i < backgrounds.length; ++i) {
      backgrounds[i].dispose();
    }
  }

}
