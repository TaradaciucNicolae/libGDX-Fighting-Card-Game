package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * MyAnimation Class.
 *
 */
public class MyAnimation extends Actor {
  Animation animation;
  TextureRegion currentRegion;
  int xcoord = 800;
  float time = 0f;
  int faceDirection=-150;

  /**
   * MyAnimation constructor.
   * 
   * @param animation - the animation we want to have.
   */
  public MyAnimation(Animation animation) {
    this.animation = animation;
  }

  @Override
  public void act(float delta) {
    super.act(delta);
    time += delta;

    currentRegion = (TextureRegion) animation.getKeyFrame(time, true);
  }
  
  void setfaceDirection(int x) {
    this.faceDirection = x;
  }

  /**
   * SetCoord method.
   * 
   * @param x - the x coordinate where we want our animation to be drawn.
   */
  void setCoord(int x) {
    this.xcoord = x;
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
    batch.draw(currentRegion, xcoord, 200, faceDirection, 150);
  }

}
