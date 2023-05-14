package com.gdx.game;

import java.util.ArrayList;

public class Monster {

	/**
	 * damage = Daunele pe care monstrul le poate provoca
	 * health = Viata monstrului
	 * armour= Armura actuala a monstrului
	 */
	private int damage;
	private int health;
	private int maxHealth;
	private int armour;
	boolean alive;
	private ArrayList<Moves> moves= new ArrayList<>();
	
	/**
	 * Constructorul care asigneaza:
	 * Viata monstrului la creare intre [80 120]
	 * Daunele care aceste le poate provoca intre [4 10]
	 * Armura incepe de la 0
	 */
	Monster(){
		maxHealth=(int)Math.floor(Math.random() * (120 - 80 + 1) + 80);
		health=maxHealth;
		System.out.println(health);
		damage=(int)Math.floor(Math.random() * (10 - 4 + 1) + 4);
		System.out.println(damage);
		armour=0;
		alive=true;
		moves.add(new Moves(damage*2,0,0));
		moves.add(new Moves(0,damage,0));
		moves.add(new Moves(0,0,damage*2));
	}
	
	/**
	 * 
	 * @return Viata monstrului.
	 */
	public int getHealth() {
		return health;
		
	}
	
	/**
	 * 
	 * @return Daunele care monstrul le poate provoca jucatorului.
	 */
	public int getDamage() {
		return damage;
	}
	
	public Moves getMove()
	{
		int i=(int)Math.floor(Math.random() * (2 - 0 + 1) + 0);
		
		return moves.get(i);
		
	}
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
	
	public void moves(int dmg,int heal,int armour)
	{
		
	}
	
	public void setArmour(int armour)
	{
		this.armour=armour;
	}
	/**
	 * 
	 * @param damageReceived - Daunele provocate de jucator monstrului.
	 *  	 aux - se foloseste pentru a calcula daca daunele sunt mai mari decat armura actuala a monstrului si parte din daune merg direct la viata lui sau dacasunt blocate de armura
	 *
	 */
	public void SetHealth(int damageReceived) {
			int aux=armour-damageReceived;
			if(aux>0)
			{
				this.armour=aux;
				return;
			}
			health=health+aux;
			if (health<=0)
			{
				System.out.println("mort");
				alive=false;
			}
	}
}

