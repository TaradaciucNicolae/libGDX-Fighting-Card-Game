package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class Card extends Table {

	Card(int attack, int health, int damage, String title, String description, Texture texture) {
		
		// Table table = new Table();
		
		Image image = new Image(texture);
		this.add(image).width(Gdx.graphics.getWidth() / 10).height(Gdx.graphics.getHeight() / 7).colspan(3);
		
		// table.add(image).width(Gdx.graphics.getWidth() / 10).height(Gdx.graphics.getHeight() / 7).colspan(3);
		
		this.row();
		
		// table.row();
		
		Skin skin = new Skin(Gdx.files.internal("glassy//skin//glassy-ui.json"));
		Label cardName = new Label(title, skin);
		
		Pixmap labelColor = new Pixmap(1, 1, Pixmap.Format.RGB888);
		Color color = new Color(Color.GRAY);
		color.a = 0.75f;
		labelColor.setColor(color);
		labelColor.fill();
		
		Label cardDescription = new Label(description, skin);
		cardDescription.getStyle().background = new Image(new Texture(labelColor)).getDrawable();
		Label cardAttack = new Label(attack + "", skin);
		cardAttack.getStyle().background = new Image(new Texture(labelColor)).getDrawable();
		Label cardHealth = new Label(health + "", skin);
		cardHealth.getStyle().background = new Image(new Texture(labelColor)).getDrawable();
		Label cardDamage = new Label(damage + "", skin);
		cardDamage.getStyle().background = new Image(new Texture(labelColor)).getDrawable();
		
		this.add(cardName).colspan(3).padBottom(5);
		this.row();
		this.add(cardDescription).colspan(3).padBottom(5);
		this.row();
		this.add(cardAttack);
		this.add(cardHealth);
		this.add(cardDamage);
		
		/*
		
		table.add(cardName).colspan(3).padBottom(5);
		table.row();
		table.add(cardDescription).colspan(3).padBottom(5);
		table.row();
		table.add(cardAttack);
		table.add(cardHealth);
		table.add(cardDamage);
		
		table.setFillParent(true);
	    
	    */ // nu a mers cu table
	}	
}