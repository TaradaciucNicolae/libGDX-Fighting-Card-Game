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
 * WoodCard class.
 */

public class WoodCard extends Cards {
	
  /**
   * WoodCard constructor.
   * 
   * @param damage - amount of damage the WoodCard gives
   * @param health - amount of health the WoodCard gives
   */

	
	WoodCard(int damage, int health) {
		this.table=new Table();
		Texture im=new Texture(Gdx.files.internal("cardselement//wood.png"));
		Texture idlesheet=new Texture(Gdx.files.internal("cards//pixelCardAssest.png"));
    	TextureRegion[][] tmp = TextureRegion.split(idlesheet,idlesheet.getWidth()/5,idlesheet.getHeight());
		table.setBackground(new TextureRegionDrawable(tmp[0][4]));
		Skin gameSkin = new Skin(Gdx.files.internal("arcade//skin//arcade-ui.json"));
		this.damage=damage;
		this.health=health;
		Label l1=new Label(this.damage + " dmg",gameSkin);
		Label l2=new Label(this.health + " heal",gameSkin);
		table.add(new Image(im)).width(70).height(120).right().top();
    	table.row();
    	table.add(l1).width(70);
    	l1.setFontScale(0.7f);
    	table.row();
    	table.add(l2).width(70);
    	//table.debug();
    	l2.setFontScale(0.7f);
	}

}
