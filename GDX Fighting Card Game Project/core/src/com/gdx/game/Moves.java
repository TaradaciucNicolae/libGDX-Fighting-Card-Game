package com.gdx.game;

/**
 * Moves Class.
 *
 */
public class Moves {

  private int dmg;
  private int heal;
  private int armour;

  /**
   * Moves constructor.
   * 
   * @param dmg - amount of damage the move can do.
   * @param heal - amount of heal the move can do.
   * @param armour - amount of armour the move can do.
   */
  public Moves(int dmg, int heal, int armour) {
    this.dmg = dmg;
    this.heal = heal;
    this.armour = armour;
  }

  /**
   * setDmg method.
   * 
   * @param dmg - amount of damage the move will get.
   */
  public void setDmg(int dmg) {
    this.dmg = dmg;
  }

  /**
   * setHeal method.
   * 
   * @param heal - amount of heal the move will get.
   */
  public void setHeal(int heal) {
    this.heal = heal;
  }

  /**
   * setArmour method.
   * 
   * @param armour - amount of armour the move will get.
   */
  public void setArmour(int armour) {
    this.armour = armour;
  }

  /**
   * getDmg method.
   * 
   * @return damage of the move.
   */
  public int getDmg() {
    return this.dmg;
  }

  /**
   * getHeal method.
   * 
   * @return heal of the move.
   */
  public int getHeal() {
    return this.heal;
  }

  /**
   * getArmour method.
   * 
   * @return armour of the move.
   */
  public int getArmour() {
    return this.armour;
  }

}
