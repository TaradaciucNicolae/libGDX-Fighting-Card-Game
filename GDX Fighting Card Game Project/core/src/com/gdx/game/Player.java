package com.gdx.game;

import java.util.ArrayList;
import java.util.ListIterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.btree.decorator.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.SortedIntList.Iterator;

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
	private int maxHealth = 100;
	private int health;

	/**
	 * nrCards = Numarul de Cards pe care jucatorul le poate utiliza
	 */
	private int nrCards=-1;

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
		health=maxHealth;
		CrearePachet();
	}
	
	void draw()
	{
		if(!this.ListaCardsTotal.isEmpty())
		{
		Cards c= ListaCardsTotal.get(0);
		this.nrCards=this.nrCards+1;
		ListaCardsTotal.remove(0);
		ListaCardsInMana.add(c);
		}
		else
		{
			for (ListIterator<Cards> iter = ListaDiscarded.listIterator(); iter.hasNext(); ) {
			    Cards element = iter.next();
			    ListaCardsTotal.add(element);
			}
			ListaDiscarded.clear();
			if(!this.ListaCardsTotal.isEmpty()) {
			Cards c= ListaCardsTotal.get(0);
			this.nrCards=this.nrCards+1;
			ListaCardsTotal.remove(0);
			ListaCardsInMana.add(c);
			}
		}
	}
	
	
	/**
	 * Functie de creare a pachetului initial al jucatorului
	 * (Intre 5 si 13 Cards din ListaCuCards)
	 */

	//asta nu merge 
	
	public void heal(int heal)
	{
		if(this.maxHealth>=this.health+heal)
		{
		this.health=this.health+heal;
		}
		else
		{
			this.health=this.maxHealth;
		}
	}
	  public void CrearePachet()
	  
	  { ListaCardsInMana.clear();
	  ListaCardsTotal.clear();
	  ListaDiscarded.clear();
	  nrCards=-1;
		ArrayList<Cards> c =new ArrayList<>();// aici se creaza un pachet de 10 carti random, dar noi vol prestabili cele 10 carti
	  	int min = 10;
	  	int max = 10;
	  
	  	
	  	
	  	/*for(int i=0;i<=5;++i)
	  	{
	  		ListaCardsTotal.add(new Cards());
	  	}
	  	*/
	  	ListaCardsTotal.add(new MetalCard(5,10));
	  	ListaCardsTotal.add(new WaterCard(5));
	  	ListaCardsTotal.add(new FireCard(300));
	  	ListaCardsTotal.add(new WoodCard(15,7));
	  	ListaCardsTotal.add(new EarthCard(20));
	  	ListaCardsTotal.add(new FireCard(300));
			  // (int) (Math.random()*(max-min+1)+min); // AICI TREBUIE RAFACUTA INEXAREA
	  
	 /* for (int i = 0; i < nrCards; i++) { 
		  Cards carte = c.ListaCuCards.get((int) (Math.random()*(c.ListaCuCards.size()-1-0+1)+0));
		  ListaCardsTotal.add(carte);
		  System.out.println("nr de carti este"+nrCards);
		  }
	  */
	  	
	  // 5 carti random vor fi atribuite jucatorului in mana si restul 5 vor ramane in pachet
	  	for (int i = ListaCardsTotal.size()/2; i >0; i--) { // AICI TREBUIE RAFACUTA INEXAREA
	  		nrCards++;
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

	/**
	 * 
	 * @param c este cartea care a fost folosita in timpul luptei.
	 */
	public void FolosesteCarte(Cards c) {
		ListaDiscarded.add(c);
		ListaCardsInMana.remove(c);
		nrCards=nrCards-1;
		System.out.println("nr carti"+nrCards);

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

}
