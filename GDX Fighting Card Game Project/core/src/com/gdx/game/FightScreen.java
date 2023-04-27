package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class FightScreen implements Screen {
	private GdxFightingGame game;
	private Stage stage;
	
	//Player idle animation test
	Animation<TextureRegion> playeridle;
	Texture idlesheet;
	SpriteBatch spriteBatch;
	float stateTime;
	//
	
	
	public FightScreen(GdxFightingGame agame) {
		// TODO Auto-generated constructor stub
		game=agame;
		stage = new Stage();
		
		final Monster m1=new Monster();
		Player p=new Player();
		//animation
		idlesheet=new Texture(Gdx.files.internal("Player//KnightIdle_strip.png"));
		TextureRegion[][] tmp = TextureRegion.split(idlesheet,idlesheet.getWidth()/15,idlesheet.getHeight());
		//15 is the number of frames and colums we have
		TextureRegion[] Player_frames= new TextureRegion[15];
		int index=0;
		for(int i=0;i<15;++i) {
			Player_frames[index++]=tmp[0][i];
		}
		
		playeridle=new Animation<TextureRegion>(0.025f,Player_frames);
		spriteBatch=new SpriteBatch();
		stateTime=0f;
		 //
		
		Table table=new Table();
		TextButton button = new TextButton("attack", GdxFightingGame.gameSkin);
		table.add(button);
		table.left().bottom();
		
		stage.addActor(table);
		
		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("am atacat");
				m1.SetHealth(30);
				System.out.println(" monster hp:"+m1.getHealth());
				if(!m1.alive)
				game.setScreen(new MainScreen(game));
				

			}
		});
		
		
	}
	@Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }
 
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //animation
        stateTime += Gdx.graphics.getDeltaTime();
        //
        stage.act();
        stage.draw();
        //animation
        TextureRegion currentFrame = playeridle.getKeyFrame(stateTime, true);
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, 200, 100,192,128); // Draw current frame at (50, 50)
		spriteBatch.end();
        //
		
        
    }
 
    @Override
    public void resize(int width, int height) {
 
    }
 
    @Override
    public void pause() {
 
    }
 
    @Override
    public void resume() {
 
    }
 
    @Override
    public void hide() {
 
    }
 
    @Override
    public void dispose() {
    //animation
    // SpriteBatches and Textures must always be disposed
    spriteBatch.dispose();
    idlesheet.dispose();
    //
    stage.dispose();
    }

}
