package com.manic.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Manic implements ApplicationListener
{
	Texture img;
	Player playerOne;
	public static final String TITLE = "Manic";
	public static final int V_WIDTH = 320;
	public static final int V_HEIGHT = 240;
	public static final int SCALE = 2;
	public static final float STEP = 1 / 60f;
	private float accum;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private OrthographicCamera hudCamera;
	private GameStateManager gsm;
	
	public SpriteBatch getSpriteBatch()
	{
		return batch;
	}
	
	public OrthographicCamera getCamera()
	{
		return camera;
	}
	
	public OrthographicCamera getHUDCamera()
	{
		return hudCamera;
	}
	
	public void pause() {}
	
	
	@Override
	public void create ()
	{
		Gdx.input.setInputProcessor(new InputProcessor());
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudCamera = new OrthographicCamera();
		hudCamera.setToOrtho(false, V_WIDTH, V_HEIGHT);
		
		gsm = new GameStateManager(this);
	}

	@Override
	public void render ()
	{
		accum += Gdx.graphics.getDeltaTime();
		while (accum >= STEP) {
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
			InputHandler.update();
		}
	}
	
	public void resume()
	{
		
	}
	
	public void resize(int height, int width)
	{
		
	}
	
	public void dispose()
	{
		
	}
}
