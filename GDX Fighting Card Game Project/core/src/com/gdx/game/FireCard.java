package com.gdx.game;

import java.awt.Font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * 
 * @author Ovidiu-Florin Berea
 * Clasa FireCard reprezinta cartea de foc a jocului care poate fi folosita cu scopul de a ataca monstrii.
 * 
 */

public class FireCard extends Cards {

	/**
	 * 
	 * @param damage - gradul de atac al cartii de foc
	 */
	
	FireCard(int damage) {
		this.table=new Table();
		Texture im=new Texture(Gdx.files.internal("cardselement//fire.png"));
		Texture idlesheet=new Texture(Gdx.files.internal("cards//pixelCardAssest.png"));
    	TextureRegion[][] tmp = TextureRegion.split(idlesheet,idlesheet.getWidth()/5,idlesheet.getHeight());
		table.setBackground(new TextureRegionDrawable(tmp[0][1]));
		Skin gameSkin = new Skin(Gdx.files.internal("arcade//skin//arcade-ui.json"));
		this.damage=damage;
		Label l1=new Label(this.damage + " dmg",gameSkin);
		table.add(new Image(im)).width(50).height(120).right().top();
    	table.row();
    	table.add(l1).width(60);
    	//table.debug();
    	l1.setFontScale(0.7f);
	}
}

