package com.manic.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.manic.game.Manic;

/**
 * @class GameState
 *
 * @author Stephen Lorenz
 * 
 * @brief The abstract outline of all states. 
 *
 * Has a reference to the GameStateManager, a spriteBatch, 
 * and two cameras. A state is the definition of what is 
 * occuring at a particular time.
 * 
 * @version 1.0
 * 
 * @contact lorenzsj@clarkson.edu
 *
 */

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

        /**
	 * @brief Prepares what should be rendered in the next frame.
	 *
	 * @param dt Represents the change in time between frames.
	 *
	 */
        public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
}
