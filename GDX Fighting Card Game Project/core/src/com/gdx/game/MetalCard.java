package com.gdx.game;

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
 * Clasa MetalCard reprezinta cartea de metal a jocului care poate fi folosita cu scopul de a oferi armura si viata jucatorului.
 *
 */

public class MetalCard extends Cards {
	
	/**
	 * 
	 * @param armour - nivelul de armura al cartii de metal
	 * @param health - nivelul de viata al cartii de metal
	 */
	
	MetalCard(int armour, int health) {
		this.table=new Table();
		Texture im=new Texture(Gdx.files.internal("cardselement//metal.png"));
		Texture idlesheet=new Texture(Gdx.files.internal("cards//pixelCardAssest.png"));
    	TextureRegion[][] tmp = TextureRegion.split(idlesheet,idlesheet.getWidth()/5,idlesheet.getHeight());
		table.setBackground(new TextureRegionDrawable(tmp[0][2]));
		Skin gameSkin = new Skin(Gdx.files.internal("glassy//skin//glassy-ui.json"));
		this.armour=armour;
		this.health=health;
		Label l1=new Label(this.armour + " arm",gameSkin);
		Label l2=new Label(this.health + " heal",gameSkin);
		table.add(new Image(im)).width(70).height(120).right().top();
    	table.row();
    	table.add(l1).width(60);
    	table.row();
    	table.add(l2).width(60);
    	table.debug();
    	//l1.setFontScale(0.7f);
    	//l2.setFontScale(0.7f);
	}

}
