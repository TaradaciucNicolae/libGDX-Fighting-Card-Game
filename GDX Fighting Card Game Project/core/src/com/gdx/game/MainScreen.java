package com.gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainScreen implements Screen{
	private Skin skin;
	private Skin skin2;
	private Skin skin3;
	private Skin skin4;
	private TextButton button;
	private TextButton button2;
	private TextButton button3;
	
	private Label nameLabel;
	private TextField nameText;
	private Label addressLabel;
	private TextField addressText;
	private Table table;
	
	private GdxFightingGame game;
	private Stage stage;
	
	Music sound;
	
	TextureRegion e;
	Texture back;
	private SpriteBatch batch;
	
	public MainScreen(GdxFightingGame agame) {
		game=agame;
		game.map=new MapScreen(game);
		// TODO Auto-generated constructor stub
	 
		sound = Gdx.audio.newMusic(Gdx.files.internal("ogg//Action1(Loop).ogg"));
		sound.setLooping(true);
		
		sound.play();
		stage = new Stage();
		// make a stage for your button to go on
		
		
		back=new Texture(Gdx.files.internal("background//back1.png"));
		e=new TextureRegion(back, 0, 0, Gdx.graphics.getWidth()*2, Gdx.graphics.getHeight()*2);
		batch=new SpriteBatch();
	
		

		// load a skin(a collection of styles for objects)
		// skin is from gdx-skins (https://github.com/czyzby/gdx-skins)
		skin = new Skin(Gdx.files.internal("glassy//skin//glassy-ui.json"));
		skin2 = new Skin(Gdx.files.internal("neon//skin//neon-ui.json"));
		skin3 = new Skin(Gdx.files.internal("terra-mother//skin//terra-mother-ui.json"));
		skin4 = new Skin(Gdx.files.internal("rainbow//skin//rainbow-ui.json"));
		// create your button
		button = new TextButton("play", skin4);
		button3 = new TextButton("settings", skin);
		button2 = new TextButton("exit", skin2);
		Label nameLabel = new Label("Aventura unui babuin", skin4);
		TextField nameText = new TextField(" ", skin);
		Label addressLabel = new Label("Address:", skin);
		TextField addressText = new TextField("", skin);

		// add it to your stage

		table = new Table(skin);
		button.toFront();
		button3.getLabel().setFontScale(0.5f);
		nameLabel.setFontScale(2);
		table.add(nameLabel).height(200).fill();
		table.row().height(50);
		table.add();
		table.row().height(100);// Row 0, column 0.
		table.add(button).fill();
		table.row().height(50);
		table.add();
		table.row().height(100);
		// Move to next row.
		table.add(button3).fill();
		table.row().height(50);
		table.add();
		table.row().height(100);
		table.add(button2).fill();
		// Row 1, column 0.
		table.setBackground(new TextureRegionDrawable(e));
		table.center().top();
		table.setFillParent(true);
		// adaugare linii ajutatoare pentru a vedea layout-ul
		table.debug();
		 

		stage.addActor(table);

		
		// add a listener to your buttons so it does something when clicked
		button3.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("I was clicked");
				
				 int numberOfMonsters=(int)Math.floor(Math.random() * (3 - 1 + 1) + 1);
				System.out.println("number of monster "+numberOfMonsters);
			}
		});

		button2.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("babuin");

			}
		});
		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("babuin se joaca");
				game.p1=new Player();
				game.map=new MapScreen(game);
				game.getScreen().dispose();
				game.setScreen(game.map);
			}
		});

		// set the sgae as the input processor so it will respond to clicks etc
		Gdx.input.setInputProcessor(stage);

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
        batch.begin();
      //  batch.draw(e, 0,0 ,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
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
    
    	sound.stop();
 
    }
 
    @Override
    public void dispose() {
    	
   stage.dispose();
   sound.dispose();
  }
}
