package com.gdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
	MyAnimation animation;
	Animation<TextureRegion> playeridle;
	Texture idlesheet;
	SpriteBatch spriteBatch;
	float stateTime;
		
	
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
		idlesheet=new Texture(Gdx.files.internal("Without Outline//MiniDreadKnight.png"));
		TextureRegion[][] tmp = TextureRegion.split(idlesheet,idlesheet.getWidth()/8,idlesheet.getHeight()/9);

		//15 is the number of frames and colums we have
		TextureRegion[] Player_frames= new TextureRegion[2];
		int index=0;
		for(int i=0;i<2;++i) {
			Player_frames[index++]=tmp[3][i];
		}
		playeridle=new Animation<TextureRegion>(0.1f,Player_frames);
		
		randomMonsterTexture();
		
		
		
	}
	
	public void randomMonsterTexture() {
      
      for(int i=0;i<3;++i) {
          switch((int)Math.floor(Math.random() * (6 - 1 + 1) + 1)) {
          case 1:
              idlesheet=new Texture(Gdx.files.internal("Without Outline//MiniDreadKnight.png"));
              TextureRegion[][] tmp = TextureRegion.split(idlesheet,idlesheet.getWidth()/8,idlesheet.getHeight()/9);
              TextureRegion[] Player_frames= new TextureRegion[2];
              int index=0;
              for(int j=0;j<2;++j) {
                  Player_frames[index++]=tmp[3][j];
              }
              
              playeridle=new Animation<TextureRegion>(0.1f,Player_frames);
              animation=new MyAnimation(playeridle);
              break;
              
          case 2:
              idlesheet=new Texture(Gdx.files.internal("Without Outline//MiniGhost.png"));
              TextureRegion[][] tmp1 = TextureRegion.split(idlesheet,idlesheet.getWidth()/8,idlesheet.getHeight()/5);
              TextureRegion[] Player_frames1= new TextureRegion[4];
              index=0;
              for(int j=0;j<4;++j) {
                  Player_frames1[index++]=tmp1[0][j];
              }
              playeridle=new Animation<TextureRegion>(0.1f,Player_frames1);
              animation=new MyAnimation(playeridle);
              break;
          case 3:
              idlesheet=new Texture(Gdx.files.internal("Without Outline//MiniSkeleton.png"));
              TextureRegion[][] tmp2 = TextureRegion.split(idlesheet,idlesheet.getWidth()/6,idlesheet.getHeight()/6);
              TextureRegion[] Player_frames2= new TextureRegion[4];
              index=0;
              for(int j=0;j<4;++j) {
                  Player_frames2[index++]=tmp2[0][j];
              }
              playeridle=new Animation<TextureRegion>(0.1f,Player_frames2);
              animation=new MyAnimation(playeridle);
              break;
          case 4:
            idlesheet=new Texture(Gdx.files.internal("Without Outline//MiniLich.png"));
            TextureRegion[][] tmp3 = TextureRegion.split(idlesheet,idlesheet.getWidth()/8,idlesheet.getHeight()/7);
            TextureRegion[] Player_frames3= new TextureRegion[4];
            index=0;
            for(int j=0;j<4;++j) {
                Player_frames3[index++]=tmp3[0][j];
            }
            playeridle=new Animation<TextureRegion>(0.1f,Player_frames3);
            animation=new MyAnimation(playeridle);
            break;
          case 5:
            idlesheet=new Texture(Gdx.files.internal("Without Outline//MiniReaper.png"));
            TextureRegion[][] tmp4 = TextureRegion.split(idlesheet,idlesheet.getWidth()/8,idlesheet.getHeight()/5);
            TextureRegion[] Player_frames4= new TextureRegion[4];
            index=0;
            for(int j=0;j<4;++j) {
                Player_frames4[index++]=tmp4[0][j];
            }
            playeridle=new Animation<TextureRegion>(0.1f,Player_frames4);
            animation=new MyAnimation(playeridle);
            break;
          case 6:
            idlesheet=new Texture(Gdx.files.internal("Without Outline//MiniZombieButcher.png"));
            TextureRegion[][] tmp5 = TextureRegion.split(idlesheet,idlesheet.getWidth()/12,idlesheet.getHeight()/8);
            TextureRegion[] Player_frames5= new TextureRegion[4];
            index=0;
            for(int j=0;j<4;++j) {
                Player_frames5[index++]=tmp5[1][j];
            }
            playeridle=new Animation<TextureRegion>(0.1f,Player_frames5);
            animation=new MyAnimation(playeridle);
            
          
          }
      }
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

