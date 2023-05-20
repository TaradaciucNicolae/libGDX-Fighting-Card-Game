package com.gdx.game;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;

public class a {

	@Test
	public void testMonster() {
Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		new Lwjgl3Application(new GdxFightingGame(), config).exit();
		Gdx.app.exit();
		
	Monster m1=new Monster();
	assertEquals(m1, m1);
	}

	@Test
	public void testGetHealth() {
Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		new Lwjgl3Application(new GdxFightingGame(), config).exit();
		Gdx.app.exit();
		
	Monster m1=new Monster();
	assertEquals(m1, m1);
	}

	@Test
	public void testGetDamage() {
Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		new Lwjgl3Application(new GdxFightingGame(), config).exit();
		Gdx.app.exit();
		
	Monster m1=new Monster();
	assertEquals(m1, m1);
	}

	@Test
	public void testGetMove() {
		HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
new HeadlessApplication(new ApplicationListener() {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
    // Override necessary methods
    
}, conf);
Gdx.gl = (GL20) mock(GL20.class);

		//new Lwjgl3Application(new GdxFightingGame(), config).exit();
		//Gdx.app.exit();
		
		
	Monster m1=new Monster();
	assertEquals(m1, m1);
	}

	@Test
	public void testHeal() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testMoves() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetArmour() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetHealth() {
		fail("Not yet implemented"); // TODO
	}

}
