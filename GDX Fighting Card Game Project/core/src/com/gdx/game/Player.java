package com.gdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.btree.decorator.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.SortedIntList.Iterator;

public class Player {

	/**
	 * @param idlesheet - 
	 * @param armour - Armura curenta a jucatorului
	 * @param alive - Acest parametru este conditia principala in a determina daca jocutorul a murit sau nu
	 * @param health - Viata curenta a jucatorului (Initializata cu 100)
	 * @param nrCards - Numarul de Cards pe care jucatorul le poate utiliza
	 *
	 */
	public Texture idlesheet;
	private int armour;
	public boolean alive=true;
	private int maxHealth = 100;
	private int health;
	private int nrCards=-1;

	
	
	/**
	 * @param ListaCardsInMana - Toate Cartile pe care jucatorul de tine in mana in momentul respectiv
	 * @param ListaCardsTotal - Toate Cartile detinute de jucator in momentul respectiv
	 * @param ListaCardsInMana - Toate Cartile ce au fost utilizate in combat
	 */
	ArrayList<Cards> ListaCardsInMana = new ArrayList<>();
	ArrayList<Cards> ListaCardsTotal = new ArrayList<>();
	ArrayList<Cards> ListaDiscarded= new ArrayList<>();
	
	

	/**
	 * Constructorul Player() va crea Jucatorul ca obiect.
	 */
	Player() {
		idlesheet=new Texture(Gdx.files.internal("Player//AnimationSheet_Character.png"));
		health=maxHealth;
		CrearePachet();
	}
	
	/**
	 *  Extragerea cartilor din pachet  DE RESCRIS
	 */
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
			ListaDiscarded.forEach((n) -> ListaCardsTotal.add(n));
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
	
	
	/**
	 * Functie de creare a pachetului initial al jucatorului
	 * (Intre 5 si 13 Cards din ListaCuCards)
	 */
	  public void CrearePachet()
	  
	  { ListaCardsInMana.clear();
	  ListaCardsTotal.clear();
	  ListaDiscarded.clear();
	  nrCards=-1;
		ArrayList<Cards> c =new ArrayList<>();// aici se creaza un pachet de 10 carti random, dar noi vol prestabili cele 10 carti
	  	int min = 10;
	  	int max = 10;
	  
	  	ListaCardsTotal.add(new MetalCard(5,10));
	  	ListaCardsTotal.add(new WaterCard(5));
	  	ListaCardsTotal.add(new FireCard(20));
	  	ListaCardsTotal.add(new WoodCard(15,7));
	  	ListaCardsTotal.add(new EarthCard(20));
	  	ListaCardsTotal.add(new FireCard(20));

	  	
	  // 5 carti random vor fi atribuite jucatorului in mana si restul 5 vor ramane in pachet
	  	for (int i = ListaCardsTotal.size()/2; i >0; i--) { // AICI TREBUIE RAFACUTA INEXAREA
	  		nrCards++;
	  		ListaCardsInMana.add(ListaCardsTotal.get(i));
	  		ListaCardsTotal.remove(ListaCardsTotal.get(i));
	  		
	  	}
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
	 * @param dmg -Damageul primit de player, in cadrul unui atac
	 */
	public void setHealth(int dmg)
	{
		
		this.health=this.health-dmg;
		if(this.health<=0)
			this.alive=false;
	}
	
	/**
	 * 
	 * @return Returneaza nivelul de armura al jucatorului
	 */
	public int getArmour()
	{
		return this.armour;
	}
	
	/**
	 * @param dmg - Damageul primit de jucator.
	 * @return Seteaza nou nivel de armura al jucatoruli in functie de damageul primit
	 */
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
	 * @param c - este cartea care a fost folosita in timpul luptei.
	 */
	public void FolosesteCarte(Cards c) {
		ListaDiscarded.add(c);
		ListaCardsInMana.remove(c);
		nrCards=nrCards-1;
		System.out.println("nr carti"+nrCards);

	}


	/**
	 * Functie de afisare a Cartilor ce se afla in posesia jucatorului
	 */
	public void AfisarePachetPlayer() {
		for (int i = 0; i < ListaCardsInMana.size(); i++) {
			System.out.println(" Cartea nr " + i + " va da " + ListaCardsInMana.get(i).damage + " damage si va oferi "
					+ ListaCardsInMana.get(i).health + " viata");

		}
	}

}
