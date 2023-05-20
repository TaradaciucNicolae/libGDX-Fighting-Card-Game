package com.gdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

/**
 * Clasa Player.
 *
 */
public class Player {

  /**
   * Parametri.
   * @param idlesheet -
   * @param armour    - Armura curenta a jucatorului
   * @param alive     - Acest parametru este conditia principala in a determina
   *                  daca jocutorul a murit sau nu
   * @param health    - Viata curenta a jucatorului (Initializata cu 100)
   * @param nrCards   - Numarul de Cards pe care jucatorul le poate utiliza
   *
   */
  public Texture idlesheet;
  private int armour;
  public boolean alive = true;
  private int maxHealth = 100;
  private int health;
  private int nrCards = -1;

  /**
   * Parametri.
   * @param listaCardsInMana - Toate Cartile pe care jucatorul de tine in mana in
   *                         momentul respectiv
   * @param listaCardsTotal  - Toate Cartile detinute de jucator in momentul
   *                         respectiv
   * @param listaCardsInMana - Toate Cartile ce au fost utilizate in combat
   */
  ArrayList<Cards> listaCardsInMana = new ArrayList<>();
  ArrayList<Cards> listaCardsTotal = new ArrayList<>();
  ArrayList<Cards> listaDiscarded = new ArrayList<>();

  /**
   * Constructorul Player() va crea Jucatorul ca obiect.
   */
  Player() {
    idlesheet = new Texture(Gdx.files.internal("Player//AnimationSheet_Character.png"));
    health = maxHealth;
    crearePachet();
  }

  /**
   * Extragerea cartilor din pachet DE RESCRIS.
   */
  void draw() {
    if (!this.listaCardsTotal.isEmpty()) {
      Cards c = listaCardsTotal.get(0);
      this.nrCards = this.nrCards + 1;
      listaCardsTotal.remove(0);
      listaCardsInMana.add(c);
    } else {
      listaDiscarded.forEach((n) -> listaCardsTotal.add(n));
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
   * Functie de creare a pachetului initial al jucatorului (Intre 5 si 13 Cards din ListaCuCards).
   */

  public void heal(int heal) {
    if (this.maxHealth >= this.health + heal) {
      this.health = this.health + heal;
    } else {
      this.health = this.maxHealth;
    }
  }

  /**
   * Functie de creare a pachetului initial al jucatorului (Intre 5 si 13 Cards din ListaCuCards).
   */
  public void crearePachet() {
    listaCardsInMana.clear();
    listaCardsTotal.clear();
    listaDiscarded.clear();
    nrCards = -1;
    ArrayList<Cards> c = new ArrayList<>();
    int min = 10;
    int max = 10;

    listaCardsTotal.add(new MetalCard(5, 10));
    listaCardsTotal.add(new WaterCard(5));
    listaCardsTotal.add(new FireCard(20));
    listaCardsTotal.add(new WoodCard(15, 7));
    listaCardsTotal.add(new EarthCard(20));
    listaCardsTotal.add(new FireCard(20));

    // 5 carti random vor fi atribuite jucatorului in mana si restul 5 vor ramane in
    // pachet
    for (int i = listaCardsTotal.size() / 2; i > 0; i--) { // AICI TREBUIE RAFACUTA INEXAREA
      nrCards++;
      listaCardsInMana.add(listaCardsTotal.get(i));
      listaCardsTotal.remove(listaCardsTotal.get(i));

    }
  }

  /**
   * Getter for Player's health.
   * 
   * @return Returneaza viata actuala a jucatorului
   */
  public int getHealth() {
    return health;

  }

  /**
   * Setter for Player's Health.
   * 
   * @param dmg -Damageul primit de player, in cadrul unui atac
   */
  public void setHealth(int dmg) {

    this.health = this.health - dmg;
    if (this.health <= 0) {
      this.alive = false;
    }
  }

  /**
   * Getter for Player's Armour.
   * 
   * @return Returneaza nivelul de armura al jucatorului
   */
  public int getArmour() {
    return this.armour;
  }

  /**
   * Setter for Player's armour.
   * 
   * @param dmg - Damageul primit de jucator.
   * @return Seteaza nou nivel de armura al jucatoruli in functie de damageul primit
   */
  public void setArmour(int dmg) {
    this.armour = this.armour - dmg;
  }

  /**
   * Getter for Player's number of cards that can be used.
   * 
   * @return Returneaza numarul de Cards pe care jucatorul le pote utiliza
   */
  public int getNrCards() {
    return nrCards;
  }

  /**
   * Functie de Receives Damages for Player.
   * 
   * @param damage = Daunele provocate jucatorului
   */
  public void receivesDamages(int damage) {
    if (health - damage < 0) {
      health = 0;
      System.out.println("Ai murit");
      return;

    }
    health = health - damage;
  }

  /**
   * Function to use  a certain card.
   * 
   * @param c - este cartea care a fost folosita in timpul luptei.
   */
  public void folosesteCarte(Cards c) {
    listaDiscarded.add(c);
    listaCardsInMana.remove(c);
    nrCards = nrCards - 1;
    System.out.println("nr carti" + nrCards);

  }

  /**
   * Functie de afisare a Cartilor ce se afla in posesia jucatorului.
   */
  public void afisarePachetPlayer() {
    for (int i = 0; i < listaCardsInMana.size(); i++) {
      System.out.println(" Cartea nr " + i + " va da " + listaCardsInMana.get(i).damage + 
          " damage si va oferi " + listaCardsInMana.get(i).health + " viata");

    }
  }

}
