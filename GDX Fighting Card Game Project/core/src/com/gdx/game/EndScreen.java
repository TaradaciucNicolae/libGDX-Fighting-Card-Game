package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndScreen implements Screen {
	
	private GdxFightingGame game;
	private SpriteBatch batch;
	private Texture[] backgrounds;
	private Texture sky;
	private float backgroundOffsets[]= {0,0,0,0,0};
	private float backgroundMaxScrollingSpeed;
	private BitmapFont font;
	
	public EndScreen(GdxFightingGame game){
		this.game=game;
		backgrounds=new Texture[5];
		backgrounds[0] = new Texture("background//EndScreen//Layers//far-clouds.png");
		backgrounds[1] = new Texture("background//EndScreen//Layers//near-clouds.png");
		backgrounds[2] = new Texture("background//EndScreen//Layers//far-mountains.png");
		backgrounds[3] = new Texture("background//EndScreen//Layers//mountains.png");
		backgrounds[4] = new Texture("background//EndScreen//Layers//trees.png");
		sky=new Texture("background//EndScreen//Layers//sky.png");
		backgroundMaxScrollingSpeed=Gdx.graphics.getWidth()/6;
		batch=new SpriteBatch();
		font=new BitmapFont(Gdx.files.internal("EndScreen//font//endscreenfont.fnt"));
	}
	
	private void drawBackground(float deltaTime) {
		backgroundOffsets[0]+=deltaTime*backgroundMaxScrollingSpeed/16;
		backgroundOffsets[1]+=deltaTime*backgroundMaxScrollingSpeed/8;
		backgroundOffsets[2]+=deltaTime*backgroundMaxScrollingSpeed/4;
		backgroundOffsets[3]+=deltaTime*backgroundMaxScrollingSpeed/2;
		backgroundOffsets[4]+=deltaTime*backgroundMaxScrollingSpeed;
		
		for(int layer=0;layer<backgrounds.length;++layer) {
			if(backgroundOffsets[layer]>Gdx.graphics.getWidth()) {
				backgroundOffsets[layer]=0;
			}
			batch.draw(backgrounds[layer],-backgroundOffsets[layer],0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			batch.draw(backgrounds[layer],-backgroundOffsets[layer]+Gdx.graphics.getWidth(),0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
		batch.draw(sky,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		drawBackground(Gdx.graphics.getDeltaTime());
		font.draw(batch,"Thus the necromancer has been slain... At least for NOW...",200,200);
		font.draw(batch,"Score "+game.score,100,100);
		batch.end();
		if(Gdx.input.isTouched()) {
			game.zone=1;
			game.score=0;
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
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		sky.dispose();
		for(int i=0;i<backgrounds.length;++i)
			backgrounds[i].dispose();
	}

}
