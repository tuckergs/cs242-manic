package com.manic.game;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.manic.game.entities.Player;
import com.manic.game.exceptions.InvalidXMLException;
import com.manic.game.helper.FileStuff;
import com.manic.game.states.GameStateManager;
import com.manic.game.xml.AnimationDataParser;

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
	
	//This is for testing purposes
	private ObjectTimeline<TextureRegion> sagatstand;
	
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
		try {
			
			Gdx.input.setInputProcessor(new InputProcessor());
			
			batch = new SpriteBatch();
			camera = new OrthographicCamera();
			camera.setToOrtho(false, V_WIDTH, V_HEIGHT);
			hudCamera = new OrthographicCamera();
			hudCamera.setToOrtho(false, V_WIDTH, V_HEIGHT);
			
			gsm = new GameStateManager(this);
			
			
			//Test anim_data_parser
			AnimationDataParser p = new AnimationDataParser();
			
			String xml = FileStuff.fileToString("..\\sprites\\sagatstand.xml");
			
			
			p.parse(xml);
			
			
			HashMap < Integer , TextureRegion > hsh = p.get_data();
			
			int length = p.get_length();
			
			
			
			sagatstand = new ObjectTimeline <TextureRegion>( hsh , length , STEP );
			
			
			
		} catch (IOException e) {
			
			System.out.println( e.getMessage() );
	
		} catch (InvalidXMLException e) {
			
			System.out.println( e.getMessage() );
			
		}
		
		
		
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
			
			sagatstand.update( STEP );
			
			batch.begin();
			
			batch.draw ( sagatstand.getCurrentObj() , 30 , 30 );
			
			batch.end();
			
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
