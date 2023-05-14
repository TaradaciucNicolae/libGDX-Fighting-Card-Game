package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MyAnimation extends Actor{
		Animation animation;
	    TextureRegion currentRegion;

	    float time = 0f;

	    public MyAnimation (Animation animation) {
	        this.animation = animation;
	    }

	    @Override
	    public void act(float delta){
	        super.act(delta);
	        time += delta;

	        currentRegion = (TextureRegion) animation.getKeyFrame(time, true);
	    }

	    @Override
	    public void draw(Batch batch, float parentAlpha) {
	        super.draw(batch, parentAlpha);
	        batch.draw(currentRegion, 800, 200,-150,150);
	    }

}
