package com.gdx.game;

/**
 * Clasa Moves.
 *
 */
public class Moves {

  private int dmg;
  private int heal;
  private int armour;

  /**
   * Constructorul cu parametri, Moves.
   * 
   * @param dmg
   * @param heal
   * @param armour
   */
  public Moves(int dmg, int heal, int armour) {
    // TODO Auto-generated constructor stub
    this.dmg = dmg;
    this.heal = heal;
    this.armour = armour;
  }

  public void setDmg(int dmg) {
    this.dmg = dmg;
  }

  public void setHeal(int heal) {
    this.heal = heal;
  }

  public void setArmour(int armour) {
    this.armour = armour;
  }

  public int getDmg() {
    return this.dmg;
  }

  public int getHeal() {
    return this.heal;
  }

  public int getArmour() {
    return this.armour;
  }

}
