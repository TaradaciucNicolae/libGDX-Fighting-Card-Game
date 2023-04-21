package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GdxFightingGame extends ApplicationAdapter {

	private Skin skin;
	private Skin skin2;
	private Skin skin3;
	private Skin skin4;
	private TextButton button;
	private TextButton button2;
	private TextButton button3;
	private Stage stage;
	private Label nameLabel;
	private TextField nameText;
	private Label addressLabel;
	private TextField addressText;
	private Table table;

	@Override
	public void create() {
		// make a stage for your button to go on
		stage = new Stage();

		// load a skin(a collection of styles for objects)
		// skin is from gdx-skins (https://github.com/czyzby/gdx-skins)
		skin = new Skin(Gdx.files.internal("glassy//skin//glassy-ui.json"));
		skin2 = new Skin(Gdx.files.internal("neon//skin//neon-ui.json"));
		skin3 = new Skin(Gdx.files.internal("terra-mother//skin//terra-mother-ui.json"));
		skin4 = new Skin(Gdx.files.internal("rainbow//skin//rainbow-ui.json"));
		// create your button
		button = new TextButton("play", skin4);
		button3 = new TextButton("settings", skin);
		button2 = new TextButton("exit", skin2);
		Label nameLabel = new Label("Aventura unui babuin", skin4);
		TextField nameText = new TextField(" ", skin);
		Label addressLabel = new Label("Address:", skin);
		TextField addressText = new TextField("", skin);

		// add it to your stage

		table = new Table(skin);

		button3.getLabel().setFontScale(0.5f);
		nameLabel.setFontScale(2);
		table.add(nameLabel).height(200).fill();
		table.row().height(50);
		table.add();
		table.row().height(100);// Row 0, column 0.
		table.add(button).fill();
		table.row().height(50);
		table.add();
		table.row().height(100);
		// Move to next row.
		table.add(button3).fill();
		table.row().height(50);
		table.add();
		table.row().height(100);
		table.add(button2).fill();
		// Row 1, column 0.

		table.center().top();
		table.setFillParent(true);
		// adaugare linii ajutatoare pentru a vedea layout-ul
		table.debug();

		stage.addActor(table);

		// add a listener to your buttons so it does something when clicked
		button3.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("I was clicked");
			}
		});

		button2.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("babuin");

			}
		});
		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("babuin se joaca");

			}
		});

		// set the sgae as the input processor so it will respond to clicks etc
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void render() {
		// clear the screen
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// tell stage to do action and then draw itself
		stage.draw();
		stage.act();
	}
}