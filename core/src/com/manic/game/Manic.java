package com.manic.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Manic extends ApplicationAdapter
{
	SpriteBatch batch;
	Texture img;
	Player playerOne;
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		img = new Texture("stage-demo.jpg");
		
		Character shaq = new Character("Shaq", 5);
		playerOne = new Player(shaq, "shaq.png", batch, 0, 0, 16, 16);
	}

	@Override
	public void render ()
	{
		Gdx.graphics.setDisplayMode(1080, 824, false);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, -75, -30);
		playerOne.updatePosition();
		playerOne.draw();
		
		batch.end();
	}
	
}
