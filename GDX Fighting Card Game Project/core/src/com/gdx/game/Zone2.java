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

public class Zone2 implements Screen {

  /**
   * GdxFightingGame=refrence to our game Stage = the stage on which we draw our
   * objects
   * 
   * sound=music bgm soundLose= sound bgm when you lose game soundWin=sound bgm
   * when you win the fight
   * 
   * hand=special elements that handles the hand of cards dragAndDrop=drag and
   * drop animation+ implementation
   * 
   * table=main table that fits the entire screen tableTop= table for the actors
   * in the fight tableBot= table for actors that deal with the player
   * 
   * i=counter needed for drag and drop
   * 
   *
   * 
   * 
   * 
   * 
   * 
   */
  private GdxFightingGame game;
  private Stage stage;

  Music sound;
  Music soundLose;
  Music soundWin;

  HorizontalGroup hand = new HorizontalGroup();
  DragAndDrop dragAndDrop = new DragAndDrop();

  Table tableTop = new Table();
  Table tableBot = new Table();
  Table table = new Table();

  int i;

  /**
   * object that help with animations
   */
  // Player idle animation test
  Animation<TextureRegion> playeridle;
  Texture idlesheet;
  SpriteBatch spriteBatch;
  float stateTime;
  //
  /**
   * enemies
   */
  int numberOfMonsters;
  final Monster m1;
  final Monster m2;
  final Monster m3;

  public Zone2(GdxFightingGame agame) {
    // TODO Auto-generated constructor stub
    game = agame;
    stage = new Stage();
    /**
     * generate a random number of monsters between 1 and 3
     */
    numberOfMonsters = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
    System.out.println("number of monster= " + numberOfMonsters);

    if (numberOfMonsters == 1) {
      m1 = new Monster();
      m2 = null;
      m3 = null;
    } else if (numberOfMonsters == 2) {
      m1 = new Monster();
      m2 = new Monster();
      m2.animation.setCoord(1000);
      m3 = null;
    } else {
      m1 = new Monster();
      m2 = new Monster();
      m2.animation.setCoord(1000);
      m3 = new Monster();
      m3.animation.setCoord(1200);
    }

    /**
     * setup for sounds , animation and tables
     */
    soundWin = Gdx.audio.newMusic(Gdx.files.internal("ogg//Victory.ogg"));
    soundLose = Gdx.audio.newMusic(Gdx.files.internal("ogg//Death.ogg"));
    sound = Gdx.audio.newMusic(Gdx.files.internal("ogg//Action 5 (Loop).ogg"));
    sound.setLooping(true);
    sound.play();

    stage = new Stage();

    game.p1.CrearePachet();
    // animation
    // idlesheet=game.p1.idlesheet;
    idlesheet = game.p1.idlesheet;
    TextureRegion[][] tmp = TextureRegion.split(idlesheet, idlesheet.getWidth() / 8, idlesheet.getHeight() / 9);

    // 15 is the number of frames and colums we have
    TextureRegion[] Player_frames = new TextureRegion[2];
    int index = 0;
    for (int i = 0; i < 2; ++i) {
      Player_frames[index++] = tmp[3][i];
    }

    playeridle = new Animation<TextureRegion>(0.1f, Player_frames);
    tableTop.add().width(150).height(300);
    tableTop.row();
    tableTop.add().width(100);
    if (numberOfMonsters == 1) {
      tableTop.add().width(500);
      tableTop.add(m1.animation).width(200).height(200);

    } else if (numberOfMonsters == 2) {
      tableTop.add().width(500);
      tableTop.add(m1.animation).width(200).height(200);
      tableTop.add(m2.animation).width(200).height(200);

    } else {
      tableTop.add().width(500);
      tableTop.add(m1.animation).width(200).height(200);
      tableTop.add(m2.animation).width(200).height(200);

      tableTop.add(m3.animation).width(200).height(200);

    }

    tableTop.debug();
    tableTop.bottom().left();
    spriteBatch = new SpriteBatch();
    stateTime = 0f;
    //

    final TextButton button = new TextButton("End Turn", GdxFightingGame.gameSkin);

    table.setBackground(new TextureRegionDrawable(
        new TextureRegion(new Texture("background//Zone2//5.png"))));
    table.setFillParent(true);

    tableTop.setBackground(new TextureRegionDrawable(
        new TextureRegion(new Texture("background//Zone2//0.png"))));
    table.add(tableTop).grow();
    table.row().height(200);

    // FireCard fc1=new FireCard(10);
    // table3.add(fc1.table).grow();
    // table3.add(wc1.table).grow();

    /**
     * create paylods for the cards to enable them to be draged and make the cards
     * return to the hand if they are droped outside a valid target
     */

    for (i = 0; i <= game.p1.getNrCards(); ++i) {
      hand.addActor(game.p1.ListaCardsInMana.get(i).table);
      System.out.println(i);
      game.p1.ListaCardsInMana.get(i).table.setUserObject(hand);

      dragAndDrop.addSource(new DragAndDrop.Source(game.p1.ListaCardsInMana.get(i).table) {

        @Override
        public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
          // TODO Auto-generated method stub

          DragAndDrop.Payload payload = new DragAndDrop.Payload();
          payload.setDragActor(getActor());
          stage.addActor(getActor());
          System.out.println(i);
          dragAndDrop.setDragActorPosition(getActor().getWidth() / 2, -getActor().getHeight() / 2);

          return payload;

        }

        @Override
        public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload,
            DragAndDrop.Target target) {
          for (i = 0; i <= game.p1.getNrCards(); ++i)
            if (target == null) {

              System.out.println("I got clicked! bababbababauin" + i);
              ((HorizontalGroup) game.p1.ListaCardsInMana.get(i).table.getUserObject())
                  .addActor(game.p1.ListaCardsInMana.get(i).table);
            }
        }

      });

      hand.expand();
      hand.fill();
    }
    tableBot.add(hand);

    for (i = 0; i <= 2; ++i)
      game.p1.ListaCardsInMana.get(i).table.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
          System.out.println("I got clicked!");
          System.out.println(game.p1.ListaCardsInMana);

        }
      });
    tableBot.add(button).height(200).width(200);

    table.add(tableBot);

    table.top().left();
    table.debug();

    tableBot.debug();

    stage.addActor(table);

    // for(i=0;i<=game.p1.getNrCards();++i)
    // {
    System.out.println(i);

    // }

    /**
     * creater targets for the cards to be dropped in
     */

    for (i = 0; i <= game.p1.getNrCards(); ++i)
      dragAndDrop.addTarget(new DragAndDrop.Target(m1.animation) {

        @Override
        public void drop(com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source source, Payload payload, float x,
            float y, int pointer) {
          // TODO Auto-generated method stub
          System.out.println("s-a activat babuinul" + i);
          source.getActor().setVisible(false);
          for (i = 0; i <= game.p1.getNrCards(); ++i) {

            if (!game.p1.ListaCardsInMana.get(i).table.isVisible()) {
              m1.SetHealth(game.p1.ListaCardsInMana.get(i).getDamage());
              game.p1.setArmour(game.p1.ListaCardsInMana.get(i).getArmour());
              game.p1.heal(game.p1.ListaCardsInMana.get(i).getHealth());
              game.p1.FolosesteCarte(game.p1.ListaCardsInMana.get(i));
              System.out.println(m1.getHealth());
              if (!m1.alive)
                m1.animation.setVisible(false);
              /**
               * if all enemies die create a victory screen
               */
              if ((!m1.alive && m2 == null && m3 == null) || (!m1.alive && !m2.alive && m3 == null)
                  || (!m1.alive && !m2.alive && !m3.alive)) {
                sound.stop();
                soundWin.setLooping(true);
                soundWin.play();
                TextButton winB = new TextButton("Return to Main Menu", GdxFightingGame.gameSkin);
                Window win = new Window("Vicory", GdxFightingGame.gameSkin);
                button.setVisible(false);
                win.toFront();

                win.add(winB); // Add a new text button that unpauses the game.
                win.pack(); // Important! Correctly scales the window after adding new elements.
                float newWidth = 600, newHeight = 500;
                win.setBounds((Gdx.graphics.getWidth() - newWidth) / 2, (Gdx.graphics.getHeight() - newHeight) / 2,
                    newWidth, newHeight); // Center on screen.
                stage.addActor(win);

                winB.addListener(new ChangeListener() {
                  @Override
                  public void changed(ChangeEvent event, Actor actor) {
                    game.getScreen().dispose();
                    // If we want to use the number of rows of the matrix Buttons the we need a
                    // method for it
                    if (game.map.CurrentRow() == 5) {
                      game.zone++;
                      game.map = new MapScreen(game);
                    }
                    game.setScreen(game.map);

                  }
                });

              }
            }

          }

        }

        @Override
        public boolean drag(com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source source, Payload payload, float x,
            float y, int pointer) {
          // TODO Auto-generated method stub
          return true;
        }
      });
    if (!(m2 == null))
      for (i = 0; i <= game.p1.getNrCards(); ++i)
        dragAndDrop.addTarget(new DragAndDrop.Target(m2.animation) {

          @Override
          public void drop(com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source source, Payload payload, float x,
              float y, int pointer) {
            // TODO Auto-generated method stub
            System.out.println("s-a activat babuinul" + i);
            source.getActor().setVisible(false);
            for (i = 0; i <= game.p1.getNrCards(); ++i) {

              if (!game.p1.ListaCardsInMana.get(i).table.isVisible()) {
                m2.SetHealth(game.p1.ListaCardsInMana.get(i).getDamage());
                game.p1.setArmour(game.p1.ListaCardsInMana.get(i).getArmour());
                game.p1.heal(game.p1.ListaCardsInMana.get(i).getHealth());
                game.p1.FolosesteCarte(game.p1.ListaCardsInMana.get(i));
                if (!m2.alive)
                  m2.animation.setVisible(false);
                /**
                 * if all enemies die create a victory screen
                 */
                if ((!m1.alive && m2 == null && m3 == null) || (!m1.alive && !m2.alive && m3 == null)
                    || (!m1.alive && !m2.alive && !m3.alive)) {
                  sound.stop();
                  soundWin.setLooping(true);
                  soundWin.play();
                  TextButton winB = new TextButton("Return to Main Menu", GdxFightingGame.gameSkin);
                  Window win = new Window("Vicory", GdxFightingGame.gameSkin);
                  button.setVisible(false);
                  win.toFront();

                  win.add(winB); // Add a new text button that unpauses the game.
                  win.pack(); // Important! Correctly scales the window after adding new elements.
                  float newWidth = 600, newHeight = 500;
                  win.setBounds((Gdx.graphics.getWidth() - newWidth) / 2, (Gdx.graphics.getHeight() - newHeight) / 2,
                      newWidth, newHeight); // Center on screen.
                  stage.addActor(win);

                  winB.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                      game.getScreen().dispose();
                      // If we want to use the number of rows of the matrix Buttons the we need a
                      // method for it
                      if (game.map.CurrentRow() == 5) {
                        game.zone++;
                        game.map = new MapScreen(game);
                      }
                      game.setScreen(game.map);

                    }
                  });

                }
              }

            }

          }

          @Override
          public boolean drag(com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source source, Payload payload, float x,
              float y, int pointer) {
            // TODO Auto-generated method stub
            return true;
          }
        });
    if (!(m3 == null))
      for (i = 0; i <= game.p1.getNrCards(); ++i)
        dragAndDrop.addTarget(new DragAndDrop.Target(m3.animation) {

          @Override
          public void drop(com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source source, Payload payload, float x,
              float y, int pointer) {
            // TODO Auto-generated method stub
            System.out.println("s-a activat babuinul" + i);
            source.getActor().setVisible(false);
            for (i = 0; i <= game.p1.getNrCards(); ++i) {

              if (!game.p1.ListaCardsInMana.get(i).table.isVisible()) {
                m3.SetHealth(game.p1.ListaCardsInMana.get(i).getDamage());
                game.p1.setArmour(game.p1.ListaCardsInMana.get(i).getArmour());
                game.p1.heal(game.p1.ListaCardsInMana.get(i).getHealth());
                game.p1.FolosesteCarte(game.p1.ListaCardsInMana.get(i));
                if (!m3.alive)
                  m3.animation.setVisible(false);
                /**
                 * if all enemies die create a victory screen
                 */
                if ((!m1.alive && m2 == null && m3 == null) || (!m1.alive && !m2.alive && m3 == null)
                    || (!m1.alive && !m2.alive && !m3.alive)) {
                  sound.stop();
                  soundWin.setLooping(true);
                  soundWin.play();
                  TextButton winB = new TextButton("Return to Main Menu", GdxFightingGame.gameSkin);
                  Window win = new Window("Vicory", GdxFightingGame.gameSkin);
                  button.setVisible(false);
                  win.toFront();

                  win.add(winB); // Add a new text button that unpauses the game.
                  win.pack(); // Important! Correctly scales the window after adding new elements.
                  float newWidth = 600, newHeight = 500;
                  win.setBounds((Gdx.graphics.getWidth() - newWidth) / 2, (Gdx.graphics.getHeight() - newHeight) / 2,
                      newWidth, newHeight); // Center on screen.
                  stage.addActor(win);

                  winB.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                      game.getScreen().dispose();
                      // If we want to use the number of rows of the matrix Buttons the we need a
                      // method for it
                      if (game.map.CurrentRow() == 5) {
                        game.zone++;
                        game.map = new MapScreen(game);
                      }
                      game.setScreen(game.map);

                    }
                  });

                }
              }

            }

          }

          @Override
          public boolean drag(com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source source, Payload payload, float x,
              float y, int pointer) {
            // TODO Auto-generated method stub
            return true;
          }
        });

    /**
     * end turn button , that puts used cards in the discard pile and makes enemys
     * act
     */
    button.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {

        System.out.println("cate carti intra la end" + game.p1.getNrCards());

        Moves m = m1.getMove();
        game.p1.setHealth(m.getDmg());
        System.out.println(m.getDmg());
        System.out.println(m.getArmour());
        System.out.println(m.getHeal());
        m1.setArmour(m.getArmour());
        m1.heal(m.getHeal());
        for (i = 0; i <= game.p1.getNrCards(); ++i) {

          if (!game.p1.ListaCardsInMana.get(i).table.isVisible()) {
            System.out.println("cred ca s-a sters");

            game.p1.FolosesteCarte(game.p1.ListaCardsInMana.get(i));
          }

        }
        for (i = 0; i <= game.p1.getNrCards(); ++i) {

          if (!game.p1.ListaCardsInMana.get(i).table.isVisible()) {
            System.out.println("cred ca s-a sters");

            game.p1.FolosesteCarte(game.p1.ListaCardsInMana.get(i));
          }

        }
        /**
         * if the player dies create a pop-up screen that raturns you to main menu
         */

        if (!game.p1.alive) {
          sound.stop();

          soundLose.setLooping(true);
          soundLose.play();
          TextButton loseB = new TextButton("Return to Main Menu", GdxFightingGame.gameSkin);
          Window lose = new Window("Defeat", GdxFightingGame.gameSkin);
          button.setVisible(false);
          lose.toFront();

          lose.add(loseB); // Add a new text button that unpauses the game.
          lose.pack(); // Important! Correctly scales the window after adding new elements.
          float newWidth = 600, newHeight = 500;
          lose.setBounds((Gdx.graphics.getWidth() - newWidth) / 2, (Gdx.graphics.getHeight() - newHeight) / 2, newWidth,
              newHeight); // Center on screen.
          stage.addActor(lose);

          loseB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
              game.getScreen().dispose();
              game.zone = 1;
              game.score = 0;
              game.setScreen(new MainScreen(game));

            }
          });

        }

        game.p1.draw();
        hand.addActor(game.p1.ListaCardsInMana.get(game.p1.getNrCards()).table);
        game.p1.ListaCardsInMana.get(game.p1.getNrCards()).table.setVisible(true);
        System.out.println(game.p1.getNrCards());
        game.p1.ListaCardsInMana.get(game.p1.getNrCards()).table.setUserObject(hand);
        dragAndDrop.addSource(new DragAndDrop.Source(game.p1.ListaCardsInMana.get(game.p1.getNrCards()).table) {

          @Override
          public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
            // TODO Auto-generated method stub

            DragAndDrop.Payload payload = new DragAndDrop.Payload();
            payload.setDragActor(getActor());
            stage.addActor(getActor());
            System.out.println(i);
            dragAndDrop.setDragActorPosition(getActor().getWidth() / 2, -getActor().getHeight() / 2);

            return payload;

          }

          @Override
          public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload,
              DragAndDrop.Target target) {
            for (i = 0; i <= game.p1.getNrCards(); ++i)
              if (target == null) {

                System.out.println("I got clicked! bababbababauin" + i);
                ((HorizontalGroup) game.p1.ListaCardsInMana.get(game.p1.getNrCards()).table.getUserObject())
                    .addActor(game.p1.ListaCardsInMana.get(game.p1.getNrCards()).table);
              }
          }

        });

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
    // animation
    stateTime += Gdx.graphics.getDeltaTime();
    //
    stage.act();
    stage.draw();
    // animation
    TextureRegion currentFrame = playeridle.getKeyFrame(stateTime, true);
    spriteBatch.begin();
    spriteBatch.draw(currentFrame, 200, 200, 150, 150); // Draw current frame at (50, 50)
    /*
     * if(numberOfMonsters==1) { spriteBatch.draw(currentFrame, 800, 200,-150,150);
     * // Draw current frame at (50, 50) } else if(numberOfMonsters==2) {
     * spriteBatch.draw(currentFrame, 800, 200,-150,150); // Draw current frame at
     * (50, 50) spriteBatch.draw(currentFrame, 1000, 200,-150,150); // Draw current
     * frame at (50, 50) } else { spriteBatch.draw(currentFrame, 800, 200,-150,150);
     * // Draw current frame at (50, 50) spriteBatch.draw(currentFrame, 1000,
     * 200,-150,150); // Draw current frame at (50, 50)
     * spriteBatch.draw(currentFrame, 1200, 200,-150,150); // Draw current frame at
     * (50, 50) }
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
    // animation
    // SpriteBatches and Textures must always be disposed

    //
    stage.dispose();
  }

}
