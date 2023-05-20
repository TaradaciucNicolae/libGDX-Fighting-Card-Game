package com.gdx.game;

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
import java.util.Random;

/**
 * Crearea MapScreen-ului.
 *
 */
public class MapScreen implements Screen {
  private GdxFightingGame game;
  private Stage stage;
  private int[][] fields;
  private TextButton[][] buttons;
  private Random randomNumber;
  private Skin skin;
  private Table table;
  private int currentRow = 1;

  /**
   * Crearea ecranului destinat hartii.
   * 
   * @param game         - GdxFightingGame instance
   * @param fields       - fields that need to be populated on the matrix of buttons
   * @param buttons      - A matrix which contains all of the buttons we have to display on screen
   * @param randomNumber - A parameter that we use for generating random numbers
   */
  public MapScreen(GdxFightingGame game) {
    this.game = game;
    stage = new Stage();
    fields = new int[5][3];
    buttons = new TextButton[5][3];
    randomNumber = new Random();
    skin = new Skin(Gdx.files.internal("craftacular//skin//craftacular-ui.json"));
    table = new Table();
    table.setFillParent(true);
    fieldPopulation();
    stage.addActor(table);
    table.debug();

  }

  /**
   * Functie ce returneaza randul curent al matricii de butoane.
   * 
   * @return the current row where we are in the matrix of buttons
   */
  public int currentRow() {
    return currentRow;
  }

  /**
   * This method is used for choosing which fields to populate and then create
   * buttons with another method.
   */
  public void fieldPopulation() {
    int numberOfNodes;
    int previousColumn;
    int flag;
    int val;
    int position;
    for (int i = 0; i < fields.length; ++i) {
      if (i % 2 == 0) {
        position = randomNumber.nextInt(fields[0].length);
        fields[i][position] = 1;
      } else {
        switch (numberOfNodes = randomNumber.nextInt((fields[0].length - 1) + 1) + 1) {
          case 1:
            position = randomNumber.nextInt(fields[0].length);
            fields[i][position] = 1;
            break;
          default:
            flag = 1;
            previousColumn = randomNumber.nextInt(fields[0].length);
            fields[i][previousColumn] = 1;
            while (flag != numberOfNodes) {
              val = randomNumber.nextInt(fields[0].length);
              if (val != previousColumn) {
                fields[i][val] = 1;
                flag++;
              }

            }

        }
      }
    }
    tableButtons();
  }

  /**
   * This method creates and adds buttons to the buttons matrix.
   */
  public void tableButtons() {
    for (int i = 0; i < fields.length; ++i) {
      for (int j = 0; j < fields[0].length; ++j) {
        if (i % 2 == 0) {
          if (fields[i][j] == 1) {
            buttons[i][j] = new TextButton("" + i + j, skin);
            buttons[i][j].addListener(change);
            buttons[i][j].setTouchable(Touchable.disabled);
            // buttons[i][j].setDisabled(true);
            table.add();
            table.add(buttons[i][j]).width(100).height(100);
            table.add();
            table.row();
          }
        } else {
          if (fields[i][j] == 1) {
            buttons[i][j] = new TextButton("" + i + j, skin);
            buttons[i][j].addListener(change);
            buttons[i][j].setTouchable(Touchable.disabled);
            table.add(buttons[i][j]).width(100).height(100);
          }
          if (j == fields[0].length - 1) {
            table.row();
          }
        }
      }
    }
    table.center();
  }

  ChangeListener change = new ChangeListener() {

    @Override
    public void changed(ChangeEvent event, Actor actor) {
      for (int i = 0; i < buttons[0].length; ++i) {
        if (buttons[currentRow][i] != null) {
          buttons[currentRow][i].setTouchable(Touchable.disabled);
        }
      }
      currentRow++;
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

    for (int i = 0; i < buttons[0].length; ++i) {
      if (buttons[currentRow][i] != null) {
        buttons[currentRow][i].setTouchable(Touchable.enabled);
      }
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
