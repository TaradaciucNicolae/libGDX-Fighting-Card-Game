package com.gdx.game;

public class Monster {

	/**
	 * damage = Daunele pe care monstrul le poate provoca
	 * health = Viata monstrului
	 */
	private int damage;
	private int health;
	boolean alive;
	
	/**
	 * Constructorul care asigneaza:
	 * Viata monstrului la creare intre [80 120]
	 * Daunele care aceste le poate provoca intre [4 10]
	 */
	Monster(){
		health=(int)Math.floor(Math.random() * (120 - 80 + 1) + 80);
		System.out.println(health);
		damage=(int)Math.floor(Math.random() * (10 - 4 + 1) + 4);
		System.out.println(damage);
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
	
	/**
	 * 
	 * @param damageReceived - Daunele provocate de jucator monstrului.
	 *
	 */
	public void SetHealth(int damageReceived) {
	
			health=health-damageReceived;
			if (health<=0)
			{
				System.out.println("mort");
				alive=false;
			}
	}
}

