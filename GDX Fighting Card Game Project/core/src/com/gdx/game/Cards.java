package com.gdx.game;

import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Cards {

	int damage;
	int health; // am schimbat in public ca sa le pot accesa din player
	String PozaCarte;

	ArrayList<Cards> ListaCuCards = new ArrayList<>();


	/*
	 * Prin intermediul acestui constructor se va crea un nou pachet de Cards.
	 */

	Cards() { // e ca un fel de " creare pachet"

		// damage = 5 + (int)(Math.random() * ((10 - 5) + 1));
		// health = 5 +(int)(Math.random() * ((10 - 5) + 1));
		// System.out.println("Damage card = "+ damage);
		// System.out.println("Health card = " + health);

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
