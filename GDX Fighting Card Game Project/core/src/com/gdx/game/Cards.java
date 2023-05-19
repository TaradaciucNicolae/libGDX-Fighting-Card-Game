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
 * Clasa Cards.
 *
 */
public class Cards {

  private static Skin gameSkin = new Skin(Gdx.files.internal("glassy//skin//glassy-ui.json"));;
  int damage;
  int health; // am schimbat in public ca sa le pot accesa din player
  int armour;
  String pozaCarte;
  Table table;

  ArrayList<Cards> listaCuCarti = new ArrayList<>();

  /*
   * Prin intermediul acestui constructor se va crea un nou pachet de Cards.
   */

  Cards() { // e ca un fel de " creare pachet"

    // damage = 5 + (int)(Math.random() * ((10 - 5) + 1));
    // health = 5 +(int)(Math.random() * ((10 - 5) + 1));
    // System.out.println("Damage card = "+ damage);
    // System.out.println("Health card = " + health);
    Texture im = new Texture(Gdx.files.internal("badlogic.jpg"));
    Texture idlesheet = new Texture(Gdx.files.internal("cards//pixelCardAssest.png"));
    TextureRegion[][] tmp = TextureRegion.split(idlesheet,idlesheet.getWidth()/5,idlesheet.getHeight());
    table = new Table();
    table.setBackground(new TextureRegionDrawable(tmp[0][1]));

    this.damage = 15;
    this.health = 5;
    this.armour = 10;
    Label l1 = new Label("5 dmg ", gameSkin);
    table.setWidth(120);
    table.setHeight(200);
    table.add().height(10);
    table.row().height(20);
    table.add(new Image(im)).width(80).height(70);
    table.row().height(80);
    table.add(l1).width(60);

    table.debug();
    l1.setFontScale(0.7f);
    table.setTouchable(Touchable.enabled);

    // loadCards(); // adaugat de nicu
  }

  /**
   * Parametri pentru constructorul Cards.
   * 
   * @param dmg     este damage-ul dat de carte
   * @param viata   este viata primita de jucator
   * @param iconita este poza Cardsi de joc
   */
  Cards(int dmg, int viata, String iconita){
    this.damage = dmg;
    this.health = viata;
    this.pozaCarte = iconita;
  }

  public Cards(Card card) {
    // TODO Auto-generated constructor stub
    loadCards(); // adaugat de nicu
  }

  public int getArmour() {
    return armour;
  }

  public int getDamage() {
    return damage;
  }

  public int getHealth() {
    return health;
  }

  public void setDamageCard(int damage) {
    this.damage = damage;
  }

  public void setHealthCard(int health) {
    this.health = health;
  }

  /**
   * Aceasta functie va introduce toate Cardsle din joc, in interiorul unui pachet.
   */

  public void loadCards() { // adaugat de nicu


    listaCuCarti.add(new MetalCard(5, 10));
    listaCuCarti.add(new WaterCard(5));
    listaCuCarti.add(new FireCard(20));
    listaCuCarti.add(new WoodCard(15, 7));
    listaCuCarti.add(new EarthCard(20));
  }

  /**
   * Aceasta functie va afisa toate Cardsle existente in acest joc.
   */
  public void afisarePachetComplet() { // adaugat de nicu
    for (int i = 0; i < listaCuCarti.size(); i++) {
      System.out.println(" Cartea nr " + i + " va da " + listaCuCarti.get(i).damage + " damage si va oferi "
          + listaCuCarti.get(i).health + " viata");
    }
  }



}
