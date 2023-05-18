package com.gdx.game;

import java.util.ArrayList;

import javax.xml.transform.Source;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SettingsScreen implements Screen {
	 private Stage stage;

	 
	 
	 public SettingsScreen(GdxFightingGame game) {
	        stage = new Stage();

	 
	        TextButton backButton = new TextButton("Back", new Skin(Gdx.files.internal("neon//skin//neon-ui.json")));
	        backButton.addListener(new ChangeListener() {
	            @Override
	            public void changed(ChangeEvent event, Actor actor) {
	      
	               game.setScreen(new MainScreen(game));
	            }
	        });
	        
	  
	        
	       
	        Slider volumeSlider = new Slider(0f, 1f, 0.1f, false, new Skin(Gdx.files.internal("neon//skin//neon-ui.json")));
	        volumeSlider.setValue(1f);
	      
	        volumeSlider.addListener(new ChangeListener() {
	        
	            @Override
	            public void changed(ChangeEvent event, Actor actor) {
	                float volume = volumeSlider.getValue();
	     
	            //    game.adjustMusicVolume(volume);
	               
	            }
	        });
	        
	        volumeSlider.setBounds(100, 100, 100, 220);
	        
	    
	        stage.addActor(volumeSlider);
	        stage.setKeyboardFocus(volumeSlider); 


	        stage.addActor(backButton);
	        Gdx.input.setInputProcessor(stage);
	    }
	 
	 
	 
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		   Gdx.input.setInputProcessor(stage);
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
		
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
		
		 stage.dispose();
		
	}
	
	
	
}
