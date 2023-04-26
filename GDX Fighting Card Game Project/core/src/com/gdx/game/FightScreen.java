package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

public class FightScreen implements Screen {
	private GdxFightingGame game;
	private Stage stage;
	
	public FightScreen(GdxFightingGame agame) {
		// TODO Auto-generated constructor stub
		game=agame;
		stage = new Stage();
		
		final Monster m1=new Monster();
		Player p=new Player();
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
        stage.act();
        stage.draw();
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
   stage.dispose();
  }

}
