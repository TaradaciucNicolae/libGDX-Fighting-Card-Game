package com.gdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Monster Class.
 *
 */
public class Monster {

  /**
   * Parameters.
   * 
   * @param damage - damage that the monster can inflict.
   * @param armour - armour of the monster.
   * 
   */
  private int damage;
  private int health;
  private int maxHealth;
  int armour;
  boolean alive;
  private ArrayList<Moves> moves = new ArrayList<>();
  MyAnimation animation;
  Animation<TextureRegion> playeridle;
  Texture idlesheet;
  SpriteBatch spriteBatch;
  float stateTime;

  /**
   * Monster constructor.
   * Monster health is randomly assigned between [80 120]
   * Monster damage is randomly assigned between [4 10]
   * Armour starts at 0
   */
  Monster() {
    maxHealth = (int) Math.floor(Math.random() * (120 - 80 + 1) + 80);
    health = maxHealth;
    System.out.println(health);
    damage = (int) Math.floor(Math.random() * (10 - 4 + 1) + 4);
    System.out.println(damage);
    armour = 0;
    alive = true;
    moves.add(new Moves(damage * 2, 0, 0));
    moves.add(new Moves(0, damage, 0));
    moves.add(new Moves(0, 0, damage * 2));
    idlesheet = new Texture(Gdx.files.internal("Without Outline//MiniDreadKnight.png"));
    TextureRegion[][] tmp = TextureRegion.split(idlesheet, idlesheet.getWidth() / 8, idlesheet.getHeight() / 9);

    // 15 is the number of frames and colums we have
    TextureRegion[] Player_frames = new TextureRegion[2];
    int index = 0;
    for (int i = 0; i < 2; ++i) {
      Player_frames[index++] = tmp[3][i];
    }
    playeridle = new Animation<TextureRegion>(0.1f, Player_frames);

    randomMonsterTexture();

  }
  
  /**
   * RandomMonsterTexture method. This method is responsible for randomly
   * assignating a texture to the monster.
   */
  public void randomMonsterTexture() {

    for (int i = 0; i < 3; ++i) {
      switch ((int) Math.floor(Math.random() * (6 - 1 + 1) + 1)) {
      case 1:
        idlesheet = new Texture(Gdx.files.internal("Without Outline//MiniDreadKnight.png"));
        TextureRegion[][] tmp = TextureRegion.split(idlesheet, idlesheet.getWidth() / 8, idlesheet.getHeight() / 9);
        TextureRegion[] Player_frames = new TextureRegion[2];
        int index = 0;
        for (int j = 0; j < 2; ++j) {
          Player_frames[index++] = tmp[3][j];
        }

        playeridle = new Animation<TextureRegion>(0.1f, Player_frames);
        animation = new MyAnimation(playeridle);
        break;

      case 2:
        idlesheet = new Texture(Gdx.files.internal("Without Outline//MiniGhost.png"));
        TextureRegion[][] tmp1 = TextureRegion.split(idlesheet, idlesheet.getWidth() / 8, idlesheet.getHeight() / 5);
        TextureRegion[] Player_frames1 = new TextureRegion[4];
        index = 0;
        for (int j = 0; j < 4; ++j) {
          Player_frames1[index++] = tmp1[0][j];
        }
        playeridle = new Animation<TextureRegion>(0.1f, Player_frames1);
        animation = new MyAnimation(playeridle);
        break;
      case 3:
        idlesheet = new Texture(Gdx.files.internal("Without Outline//MiniSkeleton.png"));
        TextureRegion[][] tmp2 = TextureRegion.split(idlesheet, idlesheet.getWidth() / 6, idlesheet.getHeight() / 6);
        TextureRegion[] Player_frames2 = new TextureRegion[4];
        index = 0;
        for (int j = 0; j < 4; ++j) {
          Player_frames2[index++] = tmp2[0][j];
        }
        playeridle = new Animation<TextureRegion>(0.1f, Player_frames2);
        animation = new MyAnimation(playeridle);
        break;
      case 4:
        idlesheet = new Texture(Gdx.files.internal("Without Outline//MiniLich.png"));
        TextureRegion[][] tmp3 = TextureRegion.split(idlesheet, idlesheet.getWidth() / 8, idlesheet.getHeight() / 7);
        TextureRegion[] Player_frames3 = new TextureRegion[4];
        index = 0;
        for (int j = 0; j < 4; ++j) {
          Player_frames3[index++] = tmp3[0][j];
        }
        playeridle = new Animation<TextureRegion>(0.1f, Player_frames3);
        animation = new MyAnimation(playeridle);
        break;
      case 5:
        idlesheet = new Texture(Gdx.files.internal("Without Outline//MiniReaper.png"));
        TextureRegion[][] tmp4 = TextureRegion.split(idlesheet, idlesheet.getWidth() / 8, idlesheet.getHeight() / 5);
        TextureRegion[] Player_frames4 = new TextureRegion[4];
        index = 0;
        for (int j = 0; j < 4; ++j) {
          Player_frames4[index++] = tmp4[0][j];
        }
        playeridle = new Animation<TextureRegion>(0.1f, Player_frames4);
        animation = new MyAnimation(playeridle);
        break;
      case 6:
        idlesheet = new Texture(Gdx.files.internal("Without Outline//MiniZombieButcher.png"));
        TextureRegion[][] tmp5 = TextureRegion.split(idlesheet, idlesheet.getWidth() / 12, idlesheet.getHeight() / 8);
        TextureRegion[] Player_frames5 = new TextureRegion[4];
        index = 0;
        for (int j = 0; j < 4; ++j) {
          Player_frames5[index++] = tmp5[1][j];
        }
        playeridle = new Animation<TextureRegion>(0.1f, Player_frames5);
        animation = new MyAnimation(playeridle);

      }
    }
  }

  /**
   * getHealth method.
   * 
   * @return monster health.
   * 
   */
  public int getHealth() {
    return health;

  }

  /**
   * getDamage method.
   * 
   * @return damage that the monster can inflict.
   */
  public int getDamage() {
    return damage;
  }

  /**
   * getMove method.
   * 
   * @return a random move that the monster can do.
   */
  public Moves getMove() {
    int i = (int) Math.floor(Math.random() * (2 - 0 + 1) + 0);

    return moves.get(i);

  }

  /**
   * Heal method.
   * 
   * @param heal - amount of heal the monster can regenerate.
   * 
   */
  public void heal(int heal) {
    if (this.maxHealth >= this.health + heal) {
      this.health = this.health + heal;
    } else {
      this.health = this.maxHealth;
    }
  }

  public void moves(int dmg, int heal, int armour) {

  }

  /**
   * SetArmour method.
   * 
   * @param armour - the amount of armour the monster gets.
   * 
   */
  public void setArmour(int armour) {
    this.armour = armour;
  }

  /**
   * Setter for Monster's Health.
   * 
   * @param damageReceived - the damage that the monster receives. 
   * @param aux - used for determining if the monster has ran out
   * of shield and he needs to receive damage to his health as well.
   *
   */
  public void SetHealth(int damageReceived) {
    int aux = armour - damageReceived;
    if (aux > 0) {
      this.armour = aux;
      return;
    }
    health = health + aux;
    if (health <= 0) {
      health = 0;
      System.out.println("mort");
      alive = false;
    }
  }
}
