package com.gdx.game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class MonsterTest {

	@Test
	public void testGetHealth() {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.disableAudio(true);
		new Lwjgl3Application(new GdxFightingGame(), config).exit();
		Gdx.app.exit();
		
	Monster m1=new Monster();
	m1.SetHealth(300);
	assertEquals(0, m1.getHealth());
	

	
	}

	@Test
	public void testGetDamage() {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.disableAudio(true);
		new Lwjgl3Application(new GdxFightingGame(), config).exit();
		Gdx.app.exit();
		
	Monster m1=new Monster();
	boolean aux=false;
	if(m1.getDamage()<11)
		aux=true;
	assertTrue(aux);
	}

	
	@Test
	public void testSetArmour() {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.disableAudio(true);
		new Lwjgl3Application(new GdxFightingGame(), config).exit();
		Gdx.app.exit();
		
	Monster m1=new Monster();
	boolean aux=false;
	m1.setArmour(20);
	assertEquals(20,m1.armour);
	}

	@Test
	public void testSetHealth() {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.disableAudio(true);
		new Lwjgl3Application(new GdxFightingGame(), config).exit();
		Gdx.app.exit();
		
	Monster m1=new Monster();
	m1.SetHealth(300);
	assertEquals(0, m1.getHealth());
	}

}
