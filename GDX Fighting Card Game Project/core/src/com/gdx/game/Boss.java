package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Boss {

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
	
	/**
	 * Constructorul care asigneaza:
	 * Viata monstrului la creare intre [80 120]
	 * Daunele care aceste le poate provoca intre [4 10]
	 * Armura incepe de la 0
	 */
	Boss(){
		health=300;
		System.out.println(health);
		damage=15;
		System.out.println(damage);
		armour=0;
		alive=true;
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

