package com.gdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Player Class.
 *
 */

public class Player {
  /**
   * Parameters.
   * 
   * @param idlesheet - idle animation texture.
   * @param armour    - amount of armour the player has.
   * @param alive     - it tells if the player is alive or not
   * @param health    - amount of health the player has.
   * @param nrCards   - amount of cards the player can use.
   * @param listaCardsInMana - the hand of cards that the player has.
   * @param listaCardsTotal  - all the cards that the player has in deck.
   * @param listaDiscarded - all the cards that have been used.
   *
   */
  public Texture idlesheet;
  private int armour;
  public boolean alive = true;
  private int maxHealth = 100;
  private int health;
  private int nrCards = -1;
  int mana=3;
  ArrayList<Cards> listaCardsInMana = new ArrayList<>();
  ArrayList<Cards> listaCardsTotal = new ArrayList<>();
  ArrayList<Cards> listaDiscarded = new ArrayList<>();

  Player() {
    idlesheet = new Texture(Gdx.files.internal("Player//AnimationSheet_Character.png"));
    health = maxHealth;
    CrearePachet();
  }

  /**
   * draw method.
   * This method is responsible for drawing cards.
   */
  void draw() {
    if (!this.listaCardsTotal.isEmpty()) {
      Cards c = listaCardsTotal.get(0);
      this.nrCards = this.nrCards + 1;
      listaCardsTotal.remove(0);
      listaCardsInMana.add(c);
    } else {
      for (ListIterator<Cards> iter = listaDiscarded.listIterator(); iter.hasNext();) {
        Cards element = iter.next();
        listaCardsTotal.add(element);
      }
      listaDiscarded.clear();
      if (!this.listaCardsTotal.isEmpty()) {
        Cards c = listaCardsTotal.get(0);
        this.nrCards = this.nrCards + 1;
        listaCardsTotal.remove(0);
        listaCardsInMana.add(c);
      }
    }
  }

  /**
   * Heal method.
   * This method is responsible for healing the player.
   */
  public void heal(int heal) {
    if (this.maxHealth >= this.health + heal) {
      this.health = this.health + heal;
    } else {
      this.health = this.maxHealth;
    }
  }

  /**
   * CrearePachet method.
   * This method is used for creating the deck of cards that 
   * the player is using throughout the game.
   */
  public void CrearePachet()

  {
    listaCardsInMana.clear();
    listaCardsTotal.clear();
    listaDiscarded.clear();
    nrCards = -1;

    listaCardsTotal.add(new MetalCard(5, 10));
    listaCardsTotal.add(new WaterCard(5));
    listaCardsTotal.add(new FireCard(30));
    listaCardsTotal.add(new WoodCard(15, 7));
    listaCardsTotal.add(new EarthCard(20));
    listaCardsTotal.add(new FireCard(30));
   
    for (int i = listaCardsTotal.size() / 2; i > 0; i--) { // AICI TREBUIE RAFACUTA INEXAREA
      nrCards++;
      listaCardsInMana.add(listaCardsTotal.get(i));
      listaCardsTotal.remove(listaCardsTotal.get(i));

    }

  }

  /**
   * Getter for Player's health.
   * 
   * @return amount of health that the player has.
   */
  public int getHealth() {
    return health;

  }

  /**
   * Setter for Player's Health.
   * 
   * @param dmg - amount of damage the player receives.
   */
  public void setHealth(int dmg)
  {
      int armourAfterDmg;
      armourAfterDmg=this.armour-dmg;
      if(armourAfterDmg>=0)
      {
        this.armour=armourAfterDmg;
        System.out.println("dmg armura"+armourAfterDmg);
      }
      else
      {
        this.armour=0;
      this.health=this.health+armourAfterDmg;
      if(this.health<=0)
          this.alive=false;
      }
  }
  
  
  public void setArmour(int dmg)
  {
      this.armour=this.armour+dmg;
  }

  /**
   * Getter for Player's Armour.
   * 
   * @return amount of armour that the player has.
   */
  public int getArmour() {
    return this.armour;
  }


  /**
   * Getter for Player's number of cards that can be used.
   * 
   * @return the number of cards that the player can use.
   */
  public int getNrCards() {
    return nrCards;
  }

  /**
   * ReceivesDamages method.
   * @param damage - damage that the player receives.
   */
  public void ReceivesDamages(int damage) {
    if (health - damage < 0) {
      health = 0;
      System.out.println("Ai murit");
      return;

    }
    health = health - damage;
  }

  /**
   * FolosesteCarte method.
   * 
   * @param c - the card used in battle.
   * 
   *          Function to use a certain card.
   */
  public void FolosesteCarte(Cards c) {
    listaDiscarded.add(c);
    listaCardsInMana.remove(c);
    nrCards = nrCards - 1;
    System.out.println("nr carti" + nrCards);

  }

  /**
   * AfisarePachetPlayer method.
   * This method is used for displaying all of the cards
   * that are in the player's hands.
   */
  public void AfisarePachetPlayer() {
    for (int i = 0; i < listaCardsInMana.size(); i++) {
      System.out.println(" Cartea nr " + i + " va da " + listaCardsInMana.get(i).damage 
          + " damage si va oferi "
          + listaCardsInMana.get(i).health + " viata");

    }
  }

}
