package com.manic.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObject
{
	public float x = 0;
	public float y = 0;
	private SpriteBatch spriteBatchHandle;
	
	private Texture texture;
	
	public GameObject(String texture_path, SpriteBatch batch, float xCoord, float yCoord)
	{
		spriteBatchHandle = batch;
		texture = new Texture(texture_path);
		
		x = xCoord;
		y = yCoord;
	}
	
	public void updatePosition()
	{
		int speed = 5;
		
		if ( Gdx.input.isKeyPressed(Input.Keys.A) ) 
		{
			if (x > 0)
			{
				x -= speed;
			}
		}
		
		else if ( Gdx.input.isKeyPressed(Input.Keys.D) ) 
		{
			if (x < 500)
			{
				x += speed;
			}
		}
		
		else if ( Gdx.input.isKeyPressed(Input.Keys.W) ) 
		{
			if (y < 230)
			{
				y += speed;
			}
			System.out.print(y + '\n');
		}
		
		else if ( Gdx.input.isKeyPressed(Input.Keys.S) ) 
		{
			if (y > 0)
			{
				y -= speed;
			}
		}
	}
	
	public void draw()
	{
		spriteBatchHandle.draw(texture, x, y);
	}
}
