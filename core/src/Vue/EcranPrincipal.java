package Vue;

import Tower.TowerDefense;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class EcranPrincipal implements Screen{

	//VARIABLES
	Stage stage;
	TowerDefense game;
	Texture background;
	Texture btnUp,btnDown,btnChecked;

	//CONSTRUCTEUR
	public EcranPrincipal (TowerDefense game){this.game = game;}

    //RENDER FLOAT OBLIGATOIRE (REMPLACE RENDER())
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.getBatch().begin();
		stage.getBatch().draw(background, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.getBatch().end();        
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();        
	}

	//SHOW OBLIGATOIR DANS UN SCREEN (REMPLACE CREATE)
	@Override
	public void show() {
		background = new Texture(Gdx.files.internal("FondEcran.jpeg"));        

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);//stage re�oit les input


		//SKIN
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));    

		//LABEL COPYRIGHT  
		Label copyRight = new Label(" Copyright Herve/Jeremy/Luc/Yohan/Charlie/Juliana", skin);
		copyRight.setPosition(10, 10);
		copyRight.setFontScale(0.8f);

		//LABEL TITRE 
		Label titre = new Label(" --- Menu du jeu --- ", skin);
		titre.setPosition(Gdx.graphics.getWidth()/2-titre.getWidth()/2, Gdx.graphics.getHeight()-50);


		//BTN QUITTER
		TextButton quitBouton = new TextButton("  Quitter  ",skin);
		int xPos = (int)(Gdx.graphics.getWidth()-quitBouton.getWidth())/2;
		quitBouton.setPosition(xPos, Gdx.graphics.getHeight()*2/3 - 150);
		quitBouton.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				Gdx.app.exit();
				return false;
			}
		});


		//BTN JOUER
		TextButton playBouton = new TextButton("   Jouer   ",skin);
		xPos = (int)(Gdx.graphics.getWidth()-playBouton.getWidth())/2;
		playBouton.setPosition(xPos, Gdx.graphics.getHeight()*2/3 - 50);
		playBouton.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				game.setScreen(new EcranJeu(game));
				return false;    
			}
		});
		
		//BTN OPTION
		TextButton optionBouton = new TextButton("   Option  ",skin);
		xPos = (int)(Gdx.graphics.getWidth()-optionBouton.getWidth())/2;
		optionBouton.setPosition(xPos, Gdx.graphics.getHeight()*2/3 - 100);
		optionBouton.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				game.setScreen(new EcranOption(game));
				return false;    
			}
		});


		//AJOUT DES OBJETS
		stage.addActor(titre);
		stage.addActor(copyRight);
		stage.addActor(quitBouton);
		stage.addActor(optionBouton);
		stage.addActor(playBouton);
		

	}
	@Override
	public void dispose() {
		stage.dispose();
		background.dispose();
		btnUp.dispose();
		btnDown.dispose();
		btnChecked.dispose();
	}


	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

}