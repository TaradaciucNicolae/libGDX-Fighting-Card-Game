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
   * @param gameSkin     - skin-ul interfetei jocului
   * @param damage       - gradul de atac al cartii
   * @param health       - nivelul de viata al cartii
   * @param armour       - nivelul de armura al cartii
   * @param PozaCarte    - poza unei carti
   * @param table        - tabelul care contribuie la alcatuirea elementelor
   *                     cartilor
   * @param ListaCuCards - lista care contine toate cartile
   */

  private static Skin gameSkin = new Skin(Gdx.files.internal("glassy//skin//glassy-ui.json"));;
  int damage;
  int health;
  int armour;
  String PozaCarte;
  Table table;
  ArrayList<Cards> ListaCuCards = new ArrayList<>();

  /**
   * Constructorul Cards() fara argumente este folosit pentru a crea un nou pachet
   * de carti prin intermediul celor 5 tipuri de carti mostenite din aceasta
   * clasa, si anume: FireCard WaterCard EarthCard MetalCard WoodCard
   */

  Cards() {
  }

  /**
   * 
   * @param dmg     - gradul de atac dat de carte
   * @param viata   - viata primita de jucator
   * @param iconita - poza cartii din joc
   */

  Cards(int dmg, int viata, String iconita) {
    this.damage = dmg;
    this.health = viata;
    this.PozaCarte = iconita;
  }

  /**
   * 
   * @param card - cartea care se va incarca in pachet
   */

  public Cards(Card card) {
    LoadCards();
  }

  /**
   * Getter
   * 
   * @return Numar intreg, armura cartii
   */

  public int getArmour() {
    return armour;
  }

  /**
   * Getter
   * 
   * @return Numar intreg, nivelul de atac al cartii
   */

  public int getDamage() {
    return damage;
  }

  /**
   * Getter
   * 
   * @return Numar intreg, nivelul de viata al cartii
   */

  public int getHealth() {
    return health;
  }

  /**
   * Setter
   * 
   * @param armour - Numar intreg, nivelul de armura al cartii
   */
  public void setArmourCard(int armour) {
    this.armour = armour;
  }

  /**
   * Setter
   * 
   * @param damage - Numar intreg, gradul de atac al cartii
   */

  public void setDamageCard(int damage) {
    this.damage = damage;
  }

  /**
   * Setter
   * 
   * @param health - Numar intreg, nivelul de viata al cartii
   */

  public void setHealthCard(int health) {
    this.health = health;
  }

  /**
   * Functia LoadCards() are rolul de a crea un pachet cu toate cartile
   * disponibile din joc.
   */

  public void LoadCards() {

    ListaCuCards.add(new MetalCard(5, 10));
    ListaCuCards.add(new WaterCard(5));
    ListaCuCards.add(new FireCard(20));
    ListaCuCards.add(new WoodCard(15, 7));
    ListaCuCards.add(new EarthCard(20));
  }

  /**
   * Functia AfisarePachetComplet() are rolul de a afisa toate cartile disponibile
   * din joc.
   */

  public void AfisarePachetComplet() {
    for (int i = 0; i < ListaCuCards.size(); i++) {
      System.out.println(" Cartea nr " + i + " va da " + ListaCuCards.get(i).damage + " damage si va oferi "
          + ListaCuCards.get(i).health + " viata");
    }
  }
}
