package com.gdx.game;

public class Moves {
	
	private int dmg;
	private int heal;
	private int armour;
	
	public Moves() {
		// TODO Auto-generated constructor stub
		dmg=0;
		heal=0;
		armour=0;
	}
	
	public void setDmg(int dmg)
	{
		this.dmg=dmg;
	}
	
	public void setHeal(int heal)
	{
		this.heal=heal;
	}
	
	public void setArmour(int armour)
	{
		this.armour=armour;
	}
	
	public int getDmg(int dmg)
	{
		return this.dmg;
	}
	
	public int getHeal(int heal)
	{
		return this.heal;
	}
	
	public int getArmour(int armour)
	{
		return this.armour;
	}

}
