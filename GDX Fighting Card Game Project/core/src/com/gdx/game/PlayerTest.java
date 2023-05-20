package com.gdx.game;

import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;


class PlayerTest {

  @Test
  void testPlayer() {
    fail("Not yet implemented");
  }

  @Test
  void testCrearePachet() {
    fail("Not yet implemented");
  }

  @Test
  void testGetHealth() {
    Player p = new Player();

    p.setHealth(0);
    if (p.getHealth() != 100) {
      fail("Funmctia GetHealth nu functioneaza corespunzator");
    }

  }

  @Test
  void testSetHealth() {
    fail("Not yet implemented");
  }

  @Test
  void testGetArmour() {
    fail("Not yet implemented");
  }

  @Test
  void testSetArmour() {
    fail("Not yet implemented");
  }

  @Test
  void testGetNrCards() {
    fail("Not yet implemented");
  }

  @Test
  void testReceivesDamages() {
    fail("Not yet implemented");
  }

  @Test
  void testFolosesteCarte() {
    fail("Not yet implemented");
  }

  @Test
  void testAfisarePachetPlayer() {
    fail("Not yet implemented");
  }

}
