package com.gdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MapScreen implements Screen {
	private GdxFightingGame game;
	private Stage stage;
	private int[][] Fields;
	private TextButton[][] Buttons;
	private Random randomNumber;
	private Skin skin;
	private Table table;
	private int currentrow=1;
	
	/**
	 * 
	 * @param game = GdxFightingGame instance
	 * @param Fields = Fields that need to be populated on the matrix of Buttons
	 * @param Buttons = A matrix which contains all of the buttons we have to display on screen
	 * @param randomNumber = A parameter that we use for generating random numbers
	 */
	public MapScreen(GdxFightingGame game) {
		this.game=game;
		stage = new Stage();
		Fields=new int[5][3];
		Buttons=new TextButton[5][3];
		randomNumber=new Random();
		skin = new Skin(Gdx.files.internal("glassy//skin//glassy-ui.json"));
		table=new Table();
		table.setFillParent(true);
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
	 * This method is used for choosing which fields to populate and then create buttons with another method.
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
						Buttons[i][j]=new TextButton(""+i+j, skin);
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
						Buttons[i][j]=new TextButton(""+i+j, skin);
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
	
	ChangeListener change=new ChangeListener() {

		@Override
		public void changed(ChangeEvent event, Actor actor) {
			for(int i=0;i<Buttons[0].length;++i) {
	        	if(Buttons[currentrow][i]!=null)
	        		Buttons[currentrow][i].setTouchable(Touchable.disabled);
	        }
			currentrow++;
			game.setScreen(new FightScreen(game));
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
