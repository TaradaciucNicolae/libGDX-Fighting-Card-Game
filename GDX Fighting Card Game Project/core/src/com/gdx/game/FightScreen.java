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
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
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

/**
 * Class for the Fight Screen.
 */
public class FightScreen implements Screen {

  /**
   * @param game      - refrence to our game
   * @param stage     - used for drawing all the elements
   * @param sound     - music
   * @param soundLose - music for the moment when you lose
   * @param soundwin  - music for the moment when you win
   * @param i         - counter needed for drag and drop
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
  Moves m;
  ProgressBar playerHpBar;
  ProgressBar monster1HpBar = null;
  ProgressBar monster2HpBar = null;
  ProgressBar monster3HpBar = null;
  ArmourVisual armour;

  Animation<TextureRegion> playeridle;
  Texture idlesheet;
  SpriteBatch spriteBatch;
  float stateTime;
  int numberOfMonsters;
  final Monster m1;
  final Monster m2;
  final Monster m3;

  /**
   * FightScreen constructor.
   */
  public FightScreen(GdxFightingGame agame) {
    // TODO Auto-generated constructor stub
    game = agame;
    stage = new Stage();
    if (game.currentRoomScore >= 0 && game.currentRoomScore <= 200)
      numberOfMonsters = 1;
    else if (game.currentRoomScore >= 201 && game.currentRoomScore <= 500)
      numberOfMonsters = 2;
    else
      numberOfMonsters = 3;

    playerHpBar = new ProgressBar(1, 100, 1, false, game.gameSkin);
    playerHpBar.setValue(game.p1.getHealth());

    armour=new ArmourVisual();
    tableTop.add().width(125).height(200);
    tableTop.row();
    tableTop.add();
    
    tableTop.add(playerHpBar);
    tableTop.add(armour.table).height(50).width(50).fill();
    tableTop.add();

    

    if (numberOfMonsters == 1) {

      m1 = new Monster();
      monster1HpBar = new ProgressBar(1, m1.getHealth(), 1, false, game.gameSkin);
      tableTop.add(monster1HpBar);
      monster1HpBar.setValue(m1.getHealth());
      m1.animation.setCoord(700);
      m2 = null;
      m3 = null;
    } else if (numberOfMonsters == 2) {

      m1 = new Monster();
      monster1HpBar = new ProgressBar(1, m1.getHealth(), 1, false, game.gameSkin);
      tableTop.add(monster1HpBar);
      monster1HpBar.setValue(m1.getHealth());
      m1.animation.setCoord(700);
      m2 = new Monster();
      monster2HpBar = new ProgressBar(1, m2.getHealth(), 1, false, game.gameSkin);
      tableTop.add(monster2HpBar);
      monster2HpBar.setValue(m2.getHealth());
      m2.animation.setCoord(900);
      m3 = null;
    } else {

      m1 = new Monster();
      monster1HpBar = new ProgressBar(1, m1.getHealth(), 1, false, game.gameSkin);
      tableTop.add(monster1HpBar);
      monster1HpBar.setValue(m1.getHealth());
      m1.animation.setCoord(700);
      m2 = new Monster();
      monster2HpBar = new ProgressBar(1, m2.getHealth(), 1, false, game.gameSkin);
      tableTop.add(monster2HpBar);
      monster2HpBar.setValue(m2.getHealth());
      m2.animation.setCoord(900);
      m3 = new Monster();
      monster3HpBar = new ProgressBar(1, m3.getHealth(), 1, false, game.gameSkin);
      tableTop.add(monster3HpBar);
      monster3HpBar.setValue(m3.getHealth());
      m3.animation.setCoord(1100);
    }

    soundWin = Gdx.audio.newMusic(Gdx.files.internal("ogg//Victory.ogg"));
    soundWin.setVolume(game.soundVolume);
    soundLose = Gdx.audio.newMusic(Gdx.files.internal("ogg//Death.ogg"));
    soundLose.setVolume(game.soundVolume);
    sound = Gdx.audio.newMusic(Gdx.files.internal("ogg//Action 5 (Loop).ogg"));
    sound.setVolume(game.soundVolume);
    sound.setLooping(true);
    sound.play();

    stage = new Stage();

    game.p1.CrearePachet();
    idlesheet = game.p1.idlesheet;
    TextureRegion[][] tmp = TextureRegion.split(idlesheet, idlesheet.getWidth() / 8, idlesheet.getHeight() / 9);

    // 15 is the number of frames and colums we have
    TextureRegion[] Player_frames = new TextureRegion[2];
    int index = 0;
    for (int i = 0; i < 2; ++i) {
      Player_frames[index++] = tmp[3][i];
    }

    playeridle = new Animation<TextureRegion>(0.1f, Player_frames);
    tableTop.add().width(100).height(100);

    tableTop.row();
    tableTop.add().width(100);
    tableTop.add().width(200);
    tableTop.add().width(100);
    tableTop.add().width(100);
    if (numberOfMonsters == 1) {
    
      tableTop.add(m1.animation).width(200).height(100);
      tableTop.add().width(200).height(100);
      tableTop.add().width(200).height(100);

    } else if (numberOfMonsters == 2) {
    
      tableTop.add(m1.animation).width(200).height(100);
      tableTop.add(m2.animation).width(200).height(100);
      tableTop.add().width(200).height(100);

    } else {
 
      tableTop.add(m1.animation).width(200).height(100);
      tableTop.add(m2.animation).width(200).height(100);
      tableTop.add(m3.animation).width(200).height(100);

    }
    
    tableTop.debug();
    tableTop.bottom().left();
    spriteBatch = new SpriteBatch();
    stateTime = 0f;
    //

    final TextButton button = new TextButton("End Turn", GdxFightingGame.gameSkin);
    tableTop.add(button).width(150);
    button.getLabel().setFontScale(0.5f);

    table.setBackground(new TextureRegionDrawable(
        new TextureRegion(new Texture("background//oak_woods_v1.0//background//background_layer_1.png"))));
    table.setFillParent(true);

    tableTop.setBackground(new TextureRegionDrawable(
        new TextureRegion(new Texture("background//oak_woods_v1.0//background//background_layer_2.png"))));
    table.add(tableTop).grow();
    table.row().height(200);

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
    tableBot.add(hand).height(200);

    for (i = 0; i <= 2; ++i)
      game.p1.ListaCardsInMana.get(i).table.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
          System.out.println("I got clicked!");
          System.out.println(game.p1.ListaCardsInMana);

        }
      });


    table.add(tableBot);

    table.top().left();
    table.debug();

    tableBot.debug();

    stage.addActor(table);

    System.out.println(i);

    for (i = 0; i <= game.p1.getNrCards(); ++i)
      dragAndDrop.addTarget(new DragAndDrop.Target(m1.animation) {

        @Override
        public void drop(com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source source, 
            Payload payload, float x, float y, int pointer) {
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
              if (!m1.alive) {
                m1.animation.setVisible(false);
                monster1HpBar.setVisible(false);
              }
              playerHpBar.setValue(game.p1.getHealth());
              armour.setArmour(game.p1.getArmour());
              monster1HpBar.setValue(m1.getHealth());
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
                float newWidth = 600;
                float newHeight = 500;
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
        public boolean drag(com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source source, 
            Payload payload, float x, float y, int pointer) {
          return true;
        }
      });
    if (!(m2 == null))
      for (i = 0; i <= game.p1.getNrCards(); ++i)
        dragAndDrop.addTarget(new DragAndDrop.Target(m2.animation) {

          @Override
          public void drop(com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source source, 
              Payload payload, float x, float y, int pointer) {
            System.out.println("s-a activat babuinul" + i);
            source.getActor().setVisible(false);
            for (i = 0; i <= game.p1.getNrCards(); ++i) {

              if (!game.p1.ListaCardsInMana.get(i).table.isVisible()) {
                m2.SetHealth(game.p1.ListaCardsInMana.get(i).getDamage());
                game.p1.setArmour(game.p1.ListaCardsInMana.get(i).getArmour());
                game.p1.heal(game.p1.ListaCardsInMana.get(i).getHealth());
                game.p1.FolosesteCarte(game.p1.ListaCardsInMana.get(i));
                if (!m2.alive) {
                  m2.animation.setVisible(false);
                  monster2HpBar.setVisible(false);
                }
                playerHpBar.setValue(game.p1.getHealth());
                armour.setArmour(game.p1.getArmour());
                monster2HpBar.setValue(m2.getHealth());
                // if all enemies die create a victory screen
                if ((!m1.alive && m2 == null && m3 == null) 
                    || (!m1.alive && !m2.alive && m3 == null)
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
                  float newWidth = 600;
                  float newHeight = 500;
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
          public boolean drag(com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source source, 
              Payload payload, float x, float y, int pointer) {
            return true;
          }
        });
    if (!(m3 == null))
      for (i = 0; i <= game.p1.getNrCards(); ++i)
        dragAndDrop.addTarget(new DragAndDrop.Target(m3.animation) {

          @Override
          public void drop(com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source source, 
              Payload payload, float x, float y, int pointer) {
            System.out.println("s-a activat babuinul" + i);
            source.getActor().setVisible(false);
            for (i = 0; i <= game.p1.getNrCards(); ++i) {

              if (!game.p1.ListaCardsInMana.get(i).table.isVisible()) {
                m3.SetHealth(game.p1.ListaCardsInMana.get(i).getDamage());
                game.p1.setArmour(game.p1.ListaCardsInMana.get(i).getArmour());
                game.p1.heal(game.p1.ListaCardsInMana.get(i).getHealth());
                game.p1.FolosesteCarte(game.p1.ListaCardsInMana.get(i));
                if (!m3.alive) {
                  m3.animation.setVisible(false);
                  monster3HpBar.setVisible(false);
                }
                playerHpBar.setValue(game.p1.getHealth());
                monster3HpBar.setValue(m3.getHealth());
                armour.setArmour(game.p1.getArmour());
                // if all enemies die create a victory screen
                if ((!m1.alive && m2 == null && m3 == null)
                    || (!m1.alive && !m2.alive && m3 == null)
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
                  float newWidth = 600;
                  float newHeight = 500;
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
          public boolean drag(com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source source, 
              Payload payload, float x, float y, int pointer) {
            // TODO Auto-generated method stub
            return true;
          }
        });

    button.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {

        System.out.println("cate carti intra la end" + game.p1.getNrCards());
        if (numberOfMonsters == 1) {
          m = m1.getMove();
          game.p1.setHealth(m.getDmg());
          System.out.println(m.getDmg());
          System.out.println(m.getArmour());
          System.out.println(m.getHeal());
          m1.setArmour(m.getArmour());
          m1.heal(m.getHeal());
          monster1HpBar.setValue(m1.getHealth());

        } else if (numberOfMonsters == 2) {

          if (m1.alive) {
            m = m1.getMove();
            game.p1.setHealth(m.getDmg());
            m1.setArmour(m.getArmour());
            m1.heal(m.getHeal());
          }

          if (m2.alive) {
            m = m2.getMove();
            game.p1.setHealth(m.getDmg());
            m2.setArmour(m.getArmour());
            m2.heal(m.getHeal());
          }
          monster1HpBar.setValue(m1.getHealth());
          monster2HpBar.setValue(m2.getHealth());

        } else {
          if (m1.alive) {
            m = m1.getMove();
            game.p1.setHealth(m.getDmg());
            m1.setArmour(m.getArmour());
            m1.heal(m.getHeal());
          }

          if (m2.alive) {
            m = m2.getMove();
            game.p1.setHealth(m.getDmg());
            m2.setArmour(m.getArmour());
            m2.heal(m.getHeal());
          }

          if (m3.alive) {
            m = m3.getMove();
            game.p1.setHealth(m.getDmg());
            m3.setArmour(m.getArmour());
            m3.heal(m.getHeal());
          }
          monster1HpBar.setValue(m1.getHealth());
          monster2HpBar.setValue(m2.getHealth());
          monster3HpBar.setValue(m3.getHealth());

        }
        armour.setArmour(game.p1.getArmour());
        playerHpBar.setValue(game.p1.getHealth());
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
        //if the player dies create a pop-up screen that returns you to main menu
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
          float newWidth = 600;
          float newHeight = 500;
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
    spriteBatch.draw(currentFrame, 165, 200, 150, 150); // Draw current frame at (50, 50)
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
    stage.dispose();
  }

}
