package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Card extends Table {

    Card(int attack, int health, int damage, String title, String description, Texture texture, Skin skin) {
        //Texture texture = new Texture(Gdx.files.internal("Cards//Card1.png"));
        Image image = new Image(texture);
        this.add(image).width(Gdx.graphics.getWidth() / 10).height(Gdx.graphics.getHeight() / 7).colspan(3);
        this.row();
        //Skin skin = new Skin(Gdx.files.internal("glassy//skin//glassy-ui.json"));
        Label cardName = new Label(title, skin);
        Label cardDescription = new Label(description, skin);
        Label cardAttack = new Label(attack + "", skin);
        Label cardLife = new Label(health + "", skin);
        this.add(cardName).colspan(3).padBottom(5);
        this.row();
        this.add(cardDescription).colspan(3).padBottom(5);
        this.row();
        this.add(cardLife);
        this.add(cardAttack);
    }
}