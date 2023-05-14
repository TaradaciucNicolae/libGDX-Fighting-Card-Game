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

public class FightScreen implements Screen {
	private GdxFightingGame game;
	private Stage stage;
	Music sound;
	
	Music soundLose;
	Music soundWin;
	
	HorizontalGroup h= new HorizontalGroup();
	DragAndDrop dragAndDrop =new DragAndDrop(); 
	//ArrayList<DragAndDrop> dragAndDrop=new ArrayList<DragAndDrop>();
	
	private Table TableForCards = new Table();
	private Table TableForPackAndDiscarded = new Table();
	ArrayList<ImageButton> ListaButoaneCarti = new ArrayList<>();
	
	Table table2=new Table();
	Table table3=new Table();
	Table table=new Table();
	MyAnimation a;
	int i;
	
	
	//Player idle animation test
	Animation<TextureRegion> playeridle;
	Texture idlesheet;
	SpriteBatch spriteBatch;
	float stateTime;
	//
	
	int numberOfMonsters;
	final Monster m1;
	final Monster m2;
	final Monster m3;
	
	public FightScreen(GdxFightingGame agame) {
		// TODO Auto-generated constructor stub
		game=agame;
		stage = new Stage();
		
		 numberOfMonsters=(int)Math.floor(Math.random() * (3 - 1 + 1) + 1);
		 System.out.println("number of monster= "+numberOfMonsters);
		
		if(numberOfMonsters==1)
		{
			m1=new Monster();
			m2=null;
			m3=null;
		}
		else if(numberOfMonsters==2)
		{
			m1=new Monster();
			m2=new Monster();
			m3=null;
		}
		else
		{
			m1=new Monster();
			m2=new Monster();
			m3=new Monster();
		}
			
		
		soundWin = Gdx.audio.newMusic(Gdx.files.internal("ogg//Victory.ogg"));
		soundLose = Gdx.audio.newMusic(Gdx.files.internal("ogg//Death.ogg"));
		sound = Gdx.audio.newMusic(Gdx.files.internal("ogg//Action 5 (Loop).ogg"));
		sound.setLooping(true);
		sound.play();
		stage = new Stage();
		
		
		Player p=new Player();
		//animation
		//idlesheet=game.p1.idlesheet;
		idlesheet=new Texture(Gdx.files.internal("Without Outline//MiniDreadKnight.png"));
		TextureRegion[][] tmp = TextureRegion.split(idlesheet,idlesheet.getWidth()/8,idlesheet.getHeight()/9);

		//15 is the number of frames and colums we have
		TextureRegion[] Player_frames= new TextureRegion[2];
		int index=0;
		for(int i=0;i<2;++i) {
			Player_frames[index++]=tmp[3][i];
		}
		
		playeridle=new Animation<TextureRegion>(0.1f,Player_frames);
		
		a=new MyAnimation(playeridle);
		a.setHeight(100);
		a.setWidth(100);
		table2.add().width(150).height(300);
		table2.row();
		table2.add().width(100);
		table2.add(a).width(200).height(200);
		table2.debug();
		table2.bottom().center();
		spriteBatch=new SpriteBatch();
		stateTime=0f;
		 //

		
		final TextButton button = new TextButton("End Turn", GdxFightingGame.gameSkin);
		
		table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background//oak_woods_v1.0//background//background_layer_1.png"))));
		table.setFillParent(true);
		
		table2.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background//oak_woods_v1.0//background//background_layer_2.png"))));		
		table.add(table2).grow();
		table.row().height(200);

		
		//FireCard fc1=new FireCard(10);
		//table3.add(fc1.table).grow();
		//table3.add(wc1.table).grow();

		
		
		
		for( i=0;i<=game.p1.getNrCards();++i)
		{
		h.addActor(game.p1.ListaCardsInMana.get(i).table);
		System.out.println(i);
		game.p1.ListaCardsInMana.get(i).table.setUserObject(h);
		//dragAndDrop.add(new DragAndDrop());
		dragAndDrop.addSource(new DragAndDrop.Source(game.p1.ListaCardsInMana.get(i).table) {
			
			@Override
			public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
				// TODO Auto-generated method stub
				
				DragAndDrop.Payload payload=new DragAndDrop.Payload();
				payload.setDragActor(getActor());
				stage.addActor(getActor());
				System.out.println(i);
				dragAndDrop.setDragActorPosition(getActor().getWidth()/2,- getActor().getHeight()/2);
				
				return payload;
				
				
				
			}
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer,DragAndDrop.Payload payload, DragAndDrop.Target target)
			{
				for(i=0;i<=game.p1.getNrCards();++i)
				if (target==null)
				{
					
					System.out.println("I got clicked! bababbababauin"+i);
					((HorizontalGroup)game.p1.ListaCardsInMana.get(i).table.getUserObject()).addActor(game.p1.ListaCardsInMana.get(i).table);
				}
			}
			
			
		} );
		
		h.expand();
		h.fill();
		}
		table3.add(h);
		
		for( i=0;i<=2;++i)
		game.p1.ListaCardsInMana.get(i).table.addListener(new ClickListener(){
		        @Override
		        public void clicked(InputEvent event, float x, float y) {
		            System.out.println("I got clicked!");
		            System.out.println(game.p1.ListaCardsInMana);
		            game.p1.draw();
		            h.addActor(game.p1.ListaCardsInMana.get(game.p1.getNrCards()).table);
		            game.p1.ListaCardsInMana.get(game.p1.getNrCards()).table.setVisible(true);
		            System.out.println(game.p1.getNrCards());
		            game.p1.ListaCardsInMana.get(game.p1.getNrCards()).table.setUserObject(h);
		            dragAndDrop.addSource(new DragAndDrop.Source(game.p1.ListaCardsInMana.get(game.p1.getNrCards()).table) {
		    			
		    			@Override
		    			public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
		    				// TODO Auto-generated method stub
		    				
		    				DragAndDrop.Payload payload=new DragAndDrop.Payload();
		    				payload.setDragActor(getActor());
		    				stage.addActor(getActor());
		    				System.out.println(i);
		    				dragAndDrop.setDragActorPosition(getActor().getWidth()/2,- getActor().getHeight()/2);
		    				
		    				return payload;
		    				
		    				
		    				
		    			}
		    			@Override
		    			public void dragStop(InputEvent event, float x, float y, int pointer,DragAndDrop.Payload payload, DragAndDrop.Target target)
		    			{
		    				for(i=0;i<=game.p1.getNrCards();++i)
		    				if (target==null)
		    				{
		    					
		    					System.out.println("I got clicked! bababbababauin"+i);
		    					((HorizontalGroup)game.p1.ListaCardsInMana.get(game.p1.getNrCards()).table.getUserObject()).addActor(game.p1.ListaCardsInMana.get(game.p1.getNrCards()).table);
		    				}
		    			}
		    			
		    			
		    		} );
		        }
		    });
		table3.add(button).height(200).width(200);
		
		
		table.add(table3);
		
		table.top().left();
		table.debug();
		table2.debug();
		table3.debug();
		
		stage.addActor(table);

		//for(i=0;i<=game.p1.getNrCards();++i)
		//{
			System.out.println(i);
			
		//}
		
		

		for( i=0;i<=game.p1.getNrCards();++i)
		dragAndDrop.addTarget(new DragAndDrop.Target(a) {
			
			@Override
			public void drop(com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source source, Payload payload, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				System.out.println("s-a activat babuinul"+i);
				
		
				source.getActor().setVisible(false);
				//game.p1.FolosesteCarte((Cards) payload.getObject());
				
				
				//game.p1.FolosesteCarte(game.p1.ListaCardsInMana.get(i-1));
				
				
			}
			
			@Override
			public boolean drag(com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source source, Payload payload, float x,
					float y, int pointer) {
				// TODO Auto-generated method stub
				return true;
			}
		});
		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				System.out.println("cate carti intra la end"+game.p1.getNrCards());
				
			
				for(i=0;i<=game.p1.getNrCards();++i)
				{
					
					if (!game.p1.ListaCardsInMana.get(i).table.isVisible())
					{
						System.out.println("cred ca s-a sters");
					
						game.p1.FolosesteCarte( game.p1.ListaCardsInMana.get(i));
					}
					
				}
				for(i=0;i<=game.p1.getNrCards();++i)
				{
					
					if (!game.p1.ListaCardsInMana.get(i).table.isVisible())
					{
						System.out.println("cred ca s-a sters");
					
						game.p1.FolosesteCarte( game.p1.ListaCardsInMana.get(i));
					}
					
				}
				System.out.println(game.p1.ListaCardsInMana);
				System.out.println(game.p1.ListaCardsTotal);
				System.out.println(game.p1.ListaDiscarded);
				System.out.println("am atacat");
				m1.SetHealth(30);
				System.out.println(" monster hp:"+m1.getHealth());
				if(!m1.alive)
				{
					sound.stop();
					
					soundWin.setLooping(true);
					soundWin.play();
					TextButton winB= new TextButton("Return to Main Menu", GdxFightingGame.gameSkin);
					Window win = new Window("Vicory", GdxFightingGame.gameSkin);
					button.setVisible(false);
					win.toFront();
					
					
					win.add(winB); //Add a new text button that unpauses the game.
					win.pack(); //Important! Correctly scales the window after adding new elements.
					float newWidth = 600, newHeight = 500;
					win.setBounds((Gdx.graphics.getWidth() - newWidth ) / 2,
					(Gdx.graphics.getHeight() - newHeight ) / 2, newWidth , newHeight ); //Center on screen.
					stage.addActor(win);
					
					winB.addListener(new ChangeListener() {
						@Override
						public void changed(ChangeEvent event, Actor actor) {
							game.getScreen().dispose();
							//If we want to use the number of rows of the matrix Buttons the we need a method for it 
							if(game.map.CurrentRow()==5)
								game.setScreen(new MainScreen(game));
							game.setScreen(game.map);

						}
					});
					
					
					
				}
				
				
				int move=(int)Math.floor(Math.random() * (0 - 12 + 1) + 12);
				System.out.println("random number"+move);
				if(move<5)
				{
					game.p1.setHealth(15);
					System.out.println("a atacat"+game.p1.getHealth());
					if(!game.p1.alive)
					{
						sound.stop();
						
						soundLose.setLooping(true);
						soundLose.play();
						TextButton loseB= new TextButton("Return to Main Menu", GdxFightingGame.gameSkin);
						Window lose = new Window("Defeat", GdxFightingGame.gameSkin);
						button.setVisible(false);
						lose.toFront();
						
						
						
						lose.add(loseB); //Add a new text button that unpauses the game.
						lose.pack(); //Important! Correctly scales the window after adding new elements.
						float newWidth = 600, newHeight = 500;
						lose.setBounds((Gdx.graphics.getWidth() - newWidth ) / 2,
						(Gdx.graphics.getHeight() - newHeight ) / 2, newWidth , newHeight ); //Center on screen.
						stage.addActor(lose);
						
						loseB.addListener(new ChangeListener() {
							@Override
							public void changed(ChangeEvent event, Actor actor) {
								game.getScreen().dispose();
								game.setScreen(new MainScreen(game));

							}
						});
						
						
						
					}
				}
				else if(move<8)
				{
					m1.setArmour(15);
					System.out.println("armour up");
				}
				else
				{
					m1.heal(40);
					System.out.println("heal");
				}
				

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
		spriteBatch.draw(currentFrame, 200, 200,150,150); // Draw current frame at (50, 50)
		/*if(numberOfMonsters==1)
		{
			spriteBatch.draw(currentFrame, 800, 200,-150,150); // Draw current frame at (50, 50)
		}
		else if(numberOfMonsters==2)
		{
			spriteBatch.draw(currentFrame, 800, 200,-150,150); // Draw current frame at (50, 50)
			spriteBatch.draw(currentFrame, 1000, 200,-150,150); // Draw current frame at (50, 50)
		}
		else
		{
			spriteBatch.draw(currentFrame, 800, 200,-150,150); // Draw current frame at (50, 50)
			spriteBatch.draw(currentFrame, 1000, 200,-150,150); // Draw current frame at (50, 50)
			spriteBatch.draw(currentFrame, 1200, 200,-150,150); // Draw current frame at (50, 50)
		}
		*/ 
		spriteBatch.end();
        
		
        
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
    	soundWin.stop();
    	soundLose.stop();
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
