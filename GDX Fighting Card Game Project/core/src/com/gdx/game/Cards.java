package com.gdx.game;

import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

/**
 * Class for cards.
 *
 */
public class Cards {

  /**
   * @param gameSkin     - skin of game interface
   * @param damage       - damage dealt by the card
   * @param health       - amount of health that a card gives
   * @param armour       - amount of armour that a card gives
   * @param PozaCarte    - image of a card
   * @param table        - table used for combining all the cards elements
   * @param ListaCuCards - a list that contains all the cards
   */

  private static Skin gameSkin = new Skin(Gdx.files.internal("glassy//skin//glassy-ui.json"));;
  int damage;
  int health;
  int armour;
  String PozaCarte;
  Table table;
  ArrayList<Cards> ListaCuCards = new ArrayList<>();

  /**
   * Cards constructor. Cards() constructor is used for creating a new deck using
   * the five types of cards extended from this class: FireCard WaterCard
   * EarthCard MetalCard WoodCard
   */

  Cards() {
  }

  /**
   * Cards constructor with arguments.
   * 
   * @param dmg     - damage parameter that we want the card to have
   * @param viata   - health parameter that we want the card to have
   * @param iconita - the icon we want our card to have
   */

  Cards(int dmg, int viata, String iconita) {
    this.damage = dmg;
    this.health = viata;
    this.PozaCarte = iconita;
  }

  /**
   * 
   * @param card - card that is going in the deck
   */

  public Cards(Card card) {
    LoadCards();
  }

  /**
   * Getter.
   * 
   * @return amount of armour that the card gives
   */

  public int getArmour() {
    return armour;
  }

  /**
   * Getter.
   * 
   * @return amount of damage that the card gives
   */

  public int getDamage() {
    return damage;
  }

  /**
   * Getter.
   * 
   * @return amount of heal that the card gives
   */

  public int getHealth() {
    return health;
  }

  /**
   * Setter.
   * 
   * @param armour - amount of armour that we want our card to have
   */
  public void setArmourCard(int armour) {
    this.armour = armour;
  }

  /**
   * Setter.
   * 
   * @param damage - amount of damage that we want our card to have
   */

  public void setDamageCard(int damage) {
    this.damage = damage;
  }

  /**
   * Setter.
   * 
   * @param health - amount of heal that we want our card to have
   */

  public void setHealthCard(int health) {
    this.health = health;
  }

  /**
   * LoadCards() method. LoadCards() method is responsible for creating a deck
   * using all the available cards in the game.
   */

  public void LoadCards() {

    ListaCuCards.add(new MetalCard(5, 10));
    ListaCuCards.add(new WaterCard(5));
    ListaCuCards.add(new FireCard(20));
    ListaCuCards.add(new WoodCard(15, 7));
    ListaCuCards.add(new EarthCard(20));
  }

  /**
   * AfisarePachetComplet method. AfisarePachetComplet is responsible for
   * displaying all the available cards.
   */

  public void AfisarePachetComplet() {
    for (int i = 0; i < ListaCuCards.size(); i++) {
      System.out.println(" Cartea nr " + i + " va da " + ListaCuCards.get(i).damage + " damage si va oferi "
          + ListaCuCards.get(i).health + " viata");
    }
  }
}
