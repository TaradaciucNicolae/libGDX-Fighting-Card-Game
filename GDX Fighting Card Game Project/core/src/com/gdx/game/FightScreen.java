package com.gdx.game;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class FightScreen implements Screen {
	private GdxFightingGame game;
	private Stage stage;
	private Table TableForCards = new Table();
	private Table TableForPackAndDiscarded = new Table();
	Music sound;
	
	//Player idle animation test
	Animation<TextureRegion> playeridle;
	Texture idlesheet;
	SpriteBatch spriteBatch;
	float stateTime;
	ArrayList<ImageButton> ListaButoaneCarti = new ArrayList<>();

	
	
	public FightScreen(GdxFightingGame agame) {
		// TODO Auto-generated constructor stub
		game=agame;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage); // 
		sound = Gdx.audio.newMusic(Gdx.files.internal("ogg//Action 5 (Loop).ogg"));
		sound.setLooping(true);
		sound.play();
		stage = new Stage();
		
		final Monster m1=new Monster();
		final Player p=new Player(); // crearea playerului, impreauna cu cea a pachetului trebuie sa fie mutate in ecranul / butonul de play
		p.CrearePachet(); // dar le-am lasat aici pt ca aici era creat playerul initial si pt a nu crea confuzii
		
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
		table.add(button).row();
		table.center().top();
		table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background//back2.png"))));
		table.setFillParent(true);
		stage.addActor(table);
		
		TextButton buttonCamera = new TextButton("Selectare camera(in ecranul cu harta...)", GdxFightingGame.gameSkin);
		table.add(buttonCamera);
		
		TextButton ButtonPack = new TextButton("Pachet",GdxFightingGame.gameSkin);
		TableForPackAndDiscarded.center();
		TableForCards.setFillParent(true);
		ButtonPack.setBounds(30, 200, 200, 100);
		TableForPackAndDiscarded.add(ButtonPack);
		stage.addActor(ButtonPack);
		
		TextButton ButtonDiscarded= new TextButton("Discarded cards",GdxFightingGame.gameSkin);
		TableForPackAndDiscarded.center();
		TableForCards.setFillParent(true);
		ButtonDiscarded.setBounds(950, 200, 200, 100);
		TableForPackAndDiscarded.add(ButtonDiscarded);
		stage.addActor(ButtonDiscarded);
		
		TextButton ButtonEndTurn= new TextButton("End turn",GdxFightingGame.gameSkin);
		TableForPackAndDiscarded.center();
		TableForCards.setFillParent(true);
		ButtonEndTurn.setBounds(950, 350, 200, 100);
		TableForPackAndDiscarded.add(ButtonEndTurn);
		stage.addActor(ButtonEndTurn);
		
		TableForCards.center().bottom();
		TableForCards.setFillParent(true);
		stage.addActor(TableForCards);

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
		
		buttonCamera.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("Camera a fost slectata");
				p.AfisarePachetPlayer();
				AfisareCartiPeEcran(p);
			}
		});
		
		ButtonDiscarded.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("Lista Discarded Cards: ");
				for (int i = 0; i < p.ListaDiscarded.size(); i++) {
					System.out.println(" Cartea nr " + i + " va da " + p.ListaDiscarded.get(i).damage+ " damage si va oferi "
							+ p.ListaDiscarded.get(i).health + " viata");
				}
			}
		});
		
		ButtonPack.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("Carti ramase in pachet: ");
				for (int i = 0; i < p.ListaCardsTotal.size(); i++) {
					System.out.println(" Cartea nr " + i + " va da " + p.ListaCardsTotal.get(i).damage+ " damage si va oferi "
							+ p.ListaCardsTotal.get(i).health + " viata");
				}
			}
		});
		
		ButtonEndTurn.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("tura s-a terminat");
				
				for (int i = 0; i < p.ListaDiscarded.size(); i++) {
					p.ListaCardsTotal.add(p.ListaDiscarded.get(i));
					p.ListaDiscarded.remove(p.ListaDiscarded.get(i));
				}
			}
		});
		
	}
	
	public void AfisareCartiPeEcran(final Player p) {
		
		for(int i=0;i<p.ListaCardsInMana.size();i++)
		{
			System.out.println("a intrat in for");
			final ImageButton ButonAdaugareCarte;
			System.out.println("a creat textbutton");
			
			Texture tex = new Texture(Gdx.files.internal(p.ListaCardsInMana.get(i).PozaCarte));
			TextureRegion TexReg= new TextureRegion(tex);
			TextureRegionDrawable TexRegDraw =new TextureRegionDrawable(TexReg);
			
			
			//Drawable drawable =new TextureRegionDrawable(new TextureRegion((Texture) p.ListaCardsInMana.get(i).PozaCarte));
		//	ButonAdaugareCarte = new TextButton("",new Skin(Gdx.files.internal(p.ListaCardsInMana.get(i).PozaCarte)));
			ButonAdaugareCarte = new ImageButton(TexRegDraw);
			//ButonAdaugareCarte.setSize(9, 13);
		//	TextButton.TextButtonStyle style = ButonAdaugareCarte.getStyle();
			//style.m
			System.out.println("a pus parametri in textbuton");

			//TableForCards.add(ButonAdaugareCarte).pad(20).size(20,30);
		//	Cell<ImageButton> cell = TableForCards.add(ButonAdaugareCarte).uniform().pad(10).size(80,120);
			
			//ButonAdaugareCarte.setBackground(p.ListaCardsInMana.get(i).PozaCarte);
			ListaButoaneCarti.add(ButonAdaugareCarte);
			stage.addActor(ButonAdaugareCarte); //
			//TableForCards.invalidate();
			//ButonAdaugareCarte.add
			
			ButonAdaugareCarte.addListener(new InputListener(){
				public void enter(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, Actor actor) {
					super.enter(event, x, y, pointer, actor);
					
				
					ButonAdaugareCarte.setSize(90, 150);
			
			
					stage.draw();
				}
				public void exit(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, Actor actor) {
					super.exit(event, x, y, pointer, actor);
			
					ButonAdaugareCarte.setSize(80, 150);
		
				}
				
			});
	
			TableForCards.add(ButonAdaugareCarte).uniform().pad(10).size(80,150);
			
			
			
					

			ButonAdaugareCarte.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
			
					System.out.println("buton clicked");
	
					
					for (int j = 0; j < ListaButoaneCarti.size(); j++)
					{
						if(event.getTarget()==ListaButoaneCarti.get(j)) {
							p.ListaDiscarded.add(p.ListaCardsInMana.get(j));
							ButonAdaugareCarte.remove();
							ListaButoaneCarti.remove(ButonAdaugareCarte);
							p.FolosesteCarte(p.ListaCardsInMana.get(j)); // trebuie facut sistemul ca sa stearga cartea corecta
							p.AfisarePachetPlayer();
						}
					
				
					//p.AfisarePachetPlayer();
				}
					
					TableForCards.clearChildren(); // remove all buttons from the table

					// add the remaining buttons back to the table
					for (int j = 0; j < ListaButoaneCarti.size(); j++) {
						TableForCards.add(ListaButoaneCarti.get(j)).uniform().pad(10).size(80,150);
					}
					
				}
			});
			
	//	*/	
			
		/*
			ButonAdaugareCarte.addListener(new ClickListener() {
	      //      public void clicked(InputEvent event, float x, float y) {
	            	public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent e, float x, float y) {
	            		super.clicked(e, x, y);
	                for (int i = 0; i < ListaButoaneCarti.size(); i++) {
	                    if (e.getTarget() == ListaButoaneCarti.get(i)) {
	                    	System.out.println("sth");
	                    }
	                }
	            }







	        });
	        */
		//	stage.addActor(ButonAdaugareCarte);
			
			Gdx.input.setInputProcessor(stage);
			}
	

		//	TableForCards.invalidate();
			Gdx.input.setInputProcessor(stage);
		//	InputListener listener = new InputListener() {
	
		//	};
		
			
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
    	sound.stop();
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
