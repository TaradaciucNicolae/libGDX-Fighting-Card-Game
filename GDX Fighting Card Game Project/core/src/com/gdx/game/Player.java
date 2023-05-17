package com.gdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.btree.decorator.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

public class Player {
	// asta o sa fie stearsa
	// ArrayList<Integer> ListaCuCards=new ArrayList<>(); // asta se va afla in
	// clasa Cards si in loc de "Integer" va fi "carte"
	// acest Integer trebuie modificat si in ListaCards si la IDCarte si la
	// FolosesteCartea

	public Texture idlesheet;
	private int armour;
	public boolean alive=true;
	/**
	 * health = Viata curenta a jucatorului (Initializata cu 100)
	 */
	private int health = 100;

	/**
	 * nrCards = Numarul de Cards pe care jucatorul le poate utiliza
	 */
	private int nrCards;

	/**
	 * ListaCardsInMana = Toate Cardsle detinute de jucator in momentul respectiv
	 */
	ArrayList<Cards> ListaCardsInMana = new ArrayList<>();
	ArrayList<Cards> ListaCardsTotal = new ArrayList<>();
	ArrayList<Cards> ListaDiscarded= new ArrayList<>();

	/**
	 * Constructorul Player() va da jucatorului un numar random de Cards de joc. (
	 * Intre 5 si 13 Cards din ListaCuCards)
	 */
	Player() {
		// ListaCuCards.add(1);
		// ListaCuCards.add(2);
		// ListaCuCards.add(3); // astea 3 vor fi sterse, acuma le folosim pe post de Cards
		 
		/*
		Cards c = new Cards();

		nrCards = new Random().nextInt(8) + 5;

		for (int i = 0; i <= nrCards; i++) {
			Cards carte = c.ListaCuCards.get(new Random().nextInt(c.ListaCuCards.size()));
			ListaCardsInMana.add(carte);
		}
		*/
		idlesheet=new Texture(Gdx.files.internal("Player//AnimationSheet_Character.png"));
	}

	/**
	 * Functie de creare a pachetului initial al jucatorului
	 * (Intre 5 si 13 Cards din ListaCuCards)
	 */

	//asta nu merge 
	
	
	  public void CrearePachet()
	  
	  { Cards c = new Cards(); // aici se creaza un pachet de 10 carti random, dar noi vol prestabili cele 10 carti
	  	int min = 10;
	  	int max = 10;
	  
	  	nrCards = 10; 
			  // (int) (Math.random()*(max-min+1)+min); // AICI TREBUIE RAFACUTA INEXAREA
	  
	  for (int i = 0; i < nrCards; i++) { 
		  Cards carte = c.ListaCuCards.get((int) (Math.random()*(c.ListaCuCards.size()-1-0+1)+0));
		  ListaCardsTotal.add(carte);
		  System.out.println("nr de carti este"+nrCards);
		  }
	  
	  // 5 carti random vor fi atribuite jucatorului in mana si restul 5 vor ramane in pachet
	  	for (int i = ListaCardsTotal.size()/2; i >0; i--) { // AICI TREBUIE RAFACUTA INEXAREA
	  		
	  		ListaCardsInMana.add(ListaCardsTotal.get(i));
	  		ListaCardsTotal.remove(ListaCardsTotal.get(i));
	  		
	  	}
	  	//ListaCardsTotal.removeAll(ListaCardsInMana);
	  	
	  	/*
	  	System.out.println("carti in mana");
		for (int i = 0; i < this.ListaCardsInMana.size(); i++) {
			System.out.println(" Cartea nr " + i + " va da " + this.ListaCardsInMana.get(i).damage + " damage si va oferi "
					+ this.ListaCardsInMana.get(i).health + " viata");
		}
	  	System.out.println("carti in pachet");
		for (int i = 0; i < this.ListaCardsTotal.size(); i++) {
			System.out.println(" Cartea nr " + i + " va da " + this.ListaCardsTotal.get(i).damage + " damage si va oferi "
					+ this.ListaCardsTotal.get(i).health + " viata");
		}*/
	  
	  }
	 
	/**
	 * 
	 * @return Returneaza viata actuala a jucatorului
	 */
	public int getHealth() {
		return health;

	}
	/**
	 * 
	 * @param dmg -dmg-ul luat de player
	 */
	public void setHealth(int dmg)
	{
		
		this.health=this.health-dmg;
		if(this.health<=0)
			this.alive=false;
	}
	
	public int getArmour()
	{
		return this.armour;
	}
	
	public void setArmour(int dmg)
	{
		this.armour=this.armour-dmg;
	}
	/**
	 * 
	 * @return Returneaza numarul de Cards pe care jucatorul le pote utiliza
	 */
	public int getNrCards() {
		return nrCards;
	}

	/**
	 * 
	 * @param damage = Daunele provocate jucatorului
	 */
	public void ReceivesDamages(int damage) {
		if (health - damage < 0) {
			health = 0;
			System.out.println("Ai murit");
			return;

		}
		health = health - damage;
	}
	
	public void ReceivesHealth(int health) {
			if (this.health+health >=100)
			{
				this.health=100;
			
			}
			else
			{
				this.health +=health;
			}
	}

	/**
	 * 
	 * @param c este cartea care a fost folosita in timpul luptei.
	 */
	public void FolosesteCarte(Cards c) {
		ListaCardsInMana.remove(c);

	}

	/**
	 * Functie de afisare a Cardslor ce se afla in posesia jucatorului
	 */
	public void AfisarePachetPlayer() {
		for (int i = 0; i < ListaCardsInMana.size(); i++) {
			System.out.println(" Cartea nr " + i + " va da " + ListaCardsInMana.get(i).damage + " damage si va oferi "
					+ ListaCardsInMana.get(i).health + " viata");

		}
	}
	public void AfisareCartiPeEcran(final Player p,final Table TableForCards,final ArrayList<ImageButton> ListaButoaneCarti,final Stage stage) {
		TableForCards.clear();
		ListaButoaneCarti.clear();

		for (int i = 0; i < p.ListaCardsInMana.size(); i++) {
	
			final ImageButton ButonAdaugareCarte;
		

			Texture tex = new Texture(Gdx.files.internal(p.ListaCardsInMana.get(i).PozaCarte));
			TextureRegion TexReg = new TextureRegion(tex);
			TextureRegionDrawable TexRegDraw = new TextureRegionDrawable(TexReg);


			ButonAdaugareCarte = new ImageButton(TexRegDraw);

			ListaButoaneCarti.add(ButonAdaugareCarte);
			stage.addActor(ButonAdaugareCarte); //


			ButonAdaugareCarte.addListener(new InputListener() {
				public void enter(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer,
						Actor actor) {
					super.enter(event, x, y, pointer, actor);

					ButonAdaugareCarte.setSize(90, 150);

					stage.draw();
				}

				public void exit(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer,
						Actor actor) {
					super.exit(event, x, y, pointer, actor);

					ButonAdaugareCarte.setSize(80, 150);

				}

			});

			TableForCards.add(ButonAdaugareCarte).uniform().pad(10).size(80, 150);

			ButonAdaugareCarte.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {

					System.out.println("carte apasata");
					
					for (int j = 0; j < ListaButoaneCarti.size(); j++) { 
					
						if (event.getTarget() == ListaButoaneCarti.get(j)) {
							
							p.ListaDiscarded.add(p.ListaCardsInMana.get(j));
				
							ButonAdaugareCarte.remove();
	
							ListaButoaneCarti.remove(ButonAdaugareCarte);
							p.FolosesteCarte(p.ListaCardsInMana.get(j)); 
																			
							p.AfisarePachetPlayer();
						}

					}

					TableForCards.clearChildren(); // remove all buttons from the table

					// add the remaining buttons back to the table
					for (int j = 0; j < ListaButoaneCarti.size(); j++) {
						TableForCards.add(ListaButoaneCarti.get(j)).uniform().pad(10).size(80, 150);
					//	TableForCards.add(ListaButoaneCarti.get(j));
					
					}

				}
			});

			Gdx.input.setInputProcessor(stage);
		}

	
		Gdx.input.setInputProcessor(stage);

	}

}
