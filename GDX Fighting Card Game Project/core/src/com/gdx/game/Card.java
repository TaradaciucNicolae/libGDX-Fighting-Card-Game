package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

// TODO: Auto-generated Javadoc
/**
 * Clasa Card.
 */
public class Card extends Table {

  /**
   * Instantiates a new card.
   * 
   * 
   * @param attack      the attack.
   * @param health      the health
   * @param damage      the damage.
   * @param title       the title.
   * @param description the description
   * @param texture     the texture
   * @param skin        the skin
   */
  Card(int attack, int health, int damage, String title, String description, Texture texture, Skin skin) {

    Texture idlesheet = new Texture(Gdx.files.internal("cards//pixelCardAssest.png"));
    TextureRegion[][] tmp = TextureRegion.split(idlesheet, idlesheet.getWidth() / 5, idlesheet.getHeight());
    this.setBackground(new TextureRegionDrawable(tmp[0][2]));
    this.setFillParent(true);
    // this.add(image).width(Gdx.graphics.getWidth() /
    // 10).height(Gdx.graphics.getHeight() / 7).colspan(3);
    this.row();
    // Skin skin = new Skin(Gdx.files.internal("glassy//skin//glassy-ui.json"));
    Label cardName = new Label(title, skin);
    Label cardDescription = new Label(description, skin);
    Label cardAttack = new Label(attack + "", skin);
    Label cardLife = new Label(health + "", skin);
    this.add(cardName).colspan(3).padBottom(5);
    this.row();
    this.add(cardDescription).colspan(3).padBottom(5);
    this.row();
    this.add(cardLife);
    this.add(cardAttack);
  }
}