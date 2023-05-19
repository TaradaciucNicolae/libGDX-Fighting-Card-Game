package com.gdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MapScreen implements Screen {
	private GdxFightingGame game;
	private Stage stage;
	private int[][] Fields;
	private TextButton[][] Buttons;
	private Random randomNumber;
	private Skin skin;
	//private Skin skin2;
	private Table table;
	private Table background;
	private int currentrow=1;
	
	/**
	 * 
	 * game = GdxFightingGame instance
	 * Fields = Fields that need to be populated on the matrix of Buttons
	 * Buttons = A matrix which contains all of the buttons we have to display on screen
	 * randomNumber = A parameter that we use for generating random numbers
	 */
	public MapScreen(GdxFightingGame game) {
		this.game=game;
		stage = new Stage();
		Fields=new int[5][3];
		Buttons=new TextButton[5][3];
		randomNumber=new Random();
		skin = new Skin(Gdx.files.internal("glassy//skin//glassy-ui.json"));
		//skin2 = new Skin(Gdx.files.internal("neon//skin//neon-ui.json"));
		table=new Table();
		table.setFillParent(true);
		table.toFront();
		background=new Table();
		table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("Map//MapBackground.jpg"))));
		background.setFillParent(true);
		background.toBack();
		FieldPopulation();
		stage.addActor(table);
		table.debug();
		
		
	}
	/**
	 * 
	 * @return the current row where we are in the matrix of buttons
	 */
	public int CurrentRow() {
		return currentrow;
	}
	
	/**
	 * This method is used for choosing which fields to populate and then create buttons using TableButtons method.
	 */
	public void FieldPopulation() {
		int numberOfNodes;
		int previousColumn;
		int flag;
		int val;
		int position;
		for(int i=0;i<Fields.length;++i) {
			if(i%2==0) {
				position=randomNumber.nextInt(Fields[0].length);
				Fields[i][position]=1;
			}
			else
			{
				switch(numberOfNodes=randomNumber.nextInt((Fields[0].length-1)+1)+1) {
				case 1:
					position=randomNumber.nextInt(Fields[0].length);
					Fields[i][position]=1;
					break;
				default:
					flag=1;
					previousColumn=randomNumber.nextInt(Fields[0].length);
					Fields[i][previousColumn]=1;
					while(flag!=numberOfNodes){
						val=randomNumber.nextInt(Fields[0].length);
						if(val!=previousColumn) {
							Fields[i][val]=1;
							flag++;
						}
							
					}
					
					
				}
			}
		}
		TableButtons();
	}
	
	/**
	 * This method creates and adds buttons to the Buttons matrix.
	 */
	public void TableButtons() {
		for(int i=0;i<Fields.length;++i)
			for(int j=0;j<Fields[0].length;++j) {
				if(i%2==0) {
					if(Fields[i][j]==1) {
						Buttons[i][j]=new TextButton(""+(randomNumber.nextInt((600-100)+1)+100), skin);
						Buttons[i][j].addListener(change);
						Buttons[i][j].setTouchable(Touchable.disabled);
						table.add();
						table.add(Buttons[i][j]).width(100).height(100);
						table.add();
						table.row();
					}
				}
				else
				{
					if(Fields[i][j]==1) {
						Buttons[i][j]=new TextButton(""+(randomNumber.nextInt((600-100)+1)+100), skin);
						Buttons[i][j].addListener(change);
						Buttons[i][j].setTouchable(Touchable.disabled);
						table.add(Buttons[i][j]).width(100).height(100);
					}
					if(j==Fields[0].length-1)
						table.row();
				}
			}
		table.center();
	}
	
	/**
	 * This is a listener that sets the buttons to be touchable on the current row and then creates a new FightScreen.
	 * Also updates the score with the number on the buttons.
	 */
	ChangeListener change=new ChangeListener() {

		@Override
		public void changed(ChangeEvent event, Actor actor) {
			for(int i=0;i<Buttons[0].length;++i) {
	        	if(Buttons[currentrow][i]!=null)
	        		Buttons[currentrow][i].setTouchable(Touchable.disabled);
	        	if(event.getTarget().equals(Buttons[currentrow][i])) {
	        		game.currentRoomScore=Integer.parseInt(Buttons[currentrow][i].getText().toString());
	        		game.score=game.score+game.currentRoomScore;
	        		System.out.println("Asta e score-ul curent: "+game.score);
	        	}	
	        }
			currentrow++;
			switch(game.zone) {
			case 1:
				game.setScreen(new FightScreen(game));
				break;
			case 2:
				game.setScreen(new Zone2(game));
			}
		}
		
	};

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for(int i=0;i<Buttons[0].length;++i) {
        	if(Buttons[currentrow][i]!=null)
        		Buttons[currentrow][i].setTouchable(Touchable.enabled);
        }
        
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
