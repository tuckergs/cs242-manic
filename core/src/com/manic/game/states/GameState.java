package com.manic.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.manic.game.Manic;

public abstract class GameState {
	protected GameStateManager gsm;
	protected Manic manic;
	protected SpriteBatch sb;
	protected OrthographicCamera camera;
	protected OrthographicCamera hudCamera;

	@SuppressWarnings("static-access")
	protected GameState(GameStateManager gsm) 
	{
		this.gsm = gsm;
		manic = gsm.getManic();
		sb = manic.getSpriteBatch();
		camera = manic.getCamera();
		hudCamera = manic.getHUDCamera();
	}

	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
}
