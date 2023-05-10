package com.gdx.game;

import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Cards {

	int damage;
	int health; // am schimbat in public ca sa le pot accesa din player
	int armour;
	String PozaCarte;
	Table table;
	
	

	ArrayList<Cards> ListaCuCards = new ArrayList<>();


	/*
	 * Prin intermediul acestui constructor se va crea un nou pachet de Cards.
	 */

	Cards(int damage,int heal,int armour) { // e ca un fel de " creare pachet"

		// damage = 5 + (int)(Math.random() * ((10 - 5) + 1));
		// health = 5 +(int)(Math.random() * ((10 - 5) + 1));
		// System.out.println("Damage card = "+ damage);
		// System.out.println("Health card = " + health);
		Texture im=new Texture(Gdx.files.internal("badlogic.jpg"));
		Texture idlesheet=new Texture(Gdx.files.internal("cards//pixelCardAssest.png"));
    	TextureRegion[][] tmp = TextureRegion.split(idlesheet,idlesheet.getWidth()/5,idlesheet.getHeight());
    	table=new Table();
        table.setBackground(new TextureRegionDrawable(tmp[0][1]));
        Skin gameSkin = new Skin(Gdx.files.internal("glassy//skin//glassy-ui.json"));;
        this.damage=damage;
        this.health=heal;
        this.armour=armour;
        Label l1=new Label("5 dmg ",gameSkin);
    	table.add(new Image(im)).width(70).height(120).right().top();
    	table.row();
    	table.add(l1).width(60);
    	table.debug();
    	l1.setFontScale(0.7f);
    	
    	
        
		LoadCards(); // adaugat de nicu
	}

	/**
	 * 
	 * @param dmg     este samage-ul dat de carte
	 * @param viata   este viata primita de jucator
	 * @param iconita este poza Cardsi de joc
	 */

	Cards(int dmg, int viata, String iconita) // adaugat de nicu
	{
		this.damage = dmg;
		this.health = viata;
		this.PozaCarte = iconita;

	}

	public Cards() {
		// TODO Auto-generated constructor stub
		LoadCards(); // adaugat de nicu
	}

	public int getDamage() {
		return damage;
	}

	public int getHealth() {
		return health;
	}

	public void setDamageCard(int damage) {
		this.damage = damage;
	}

	public void setHealthCard(int health) {
		this.health = health;
	}

	/**
	 * Aceasta functie va introduce toate Cardsle din joc, in interiorul unui pachet
	 */

	public void LoadCards() { // adaugat de nicu

		ListaCuCards.add(new Cards(10, 5, "cards/card1.PNG"));
		ListaCuCards.add(new Cards(20, 0, "cards/card2.PNG"));
		ListaCuCards.add(new Cards(30, 0, "cards/card3.PNG"));
		ListaCuCards.add(new Cards(40, 3, "cards/card4.PNG"));
		ListaCuCards.add(new Cards(50, 15, "cards/card5.PNG"));

	}
	/**
	 * Aceasta functie va afisa toate Cardsle existente in acest joc
	 */
	public void AfisarePachetComplet() { // adaugat de nicu
		for (int i = 0; i < ListaCuCards.size(); i++) {
			System.out.println(" Cartea nr " + i + " va da " + ListaCuCards.get(i).damage + " damage si va oferi "
					+ ListaCuCards.get(i).health + " viata");
		}
	}

	/*
	 * public static void main(String[] args) { Cards carte1 = new Cards();
	 * System.out.println(carte1); }
	 */

}
