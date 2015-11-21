package com.manic.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.manic.game.Manic;

public class MainMenu extends GameState{
	
	private static final float buttonHeight = 50f;
	private static final float buttonWidth = 200f;
	private static final float buttonBuffer = 10f;
	
	public MainMenu(GameStateManager gsm){
		super (gsm);
		
		//Title Screen Title
/*		Skin skin = new Skin();
		Label titleLabel = new Label ("Manic!", skin);
		titleLabel.setX((Manic.V_WIDTH - buttonWidth)/2);
		titleLabel.setY((Manic.V_HEIGHT - buttonHeight)/2);
*/	}

	@Override
	public void handleInput() {
		
	}

	@Override
	public void update(float dt) {
		
	}

	@Override
	public void render() {
		
	}

	@Override
	public void dispose() {
		
	}
}
