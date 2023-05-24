package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ArmourVisual {
Table table;
private int armour;
Label l1;

  public ArmourVisual(GdxFightingGame game) {
    // TODO Auto-generated constructor stub
    armour=game.p1.getArmour();
    this.table=new Table();
    Texture im=new Texture(Gdx.files.internal("cardselement//metal.png"));
    Texture idlesheet=new Texture(Gdx.files.internal("cardselement//metal.png"));
    TextureRegion[][] tmp = TextureRegion.split(idlesheet,idlesheet.getWidth(),idlesheet.getHeight());
    this.table.setBackground(new TextureRegionDrawable(tmp[0][0]));
    Skin gameSkin = new Skin(Gdx.files.internal("arcade//skin//arcade-ui.json"));
     l1=new Label(""+this.armour , gameSkin);
    table.add(l1);
  }
  
  void setArmour(int armour)
  {
    this.armour=armour;
    l1.setText(""+this.armour);
  }
  
}
