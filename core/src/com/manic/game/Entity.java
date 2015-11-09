package com.manic.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
	
public class Entity {
	protected Vector2 coordinates;
	protected SpriteBatch batch;
	protected Texture sprite;
	protected String spritePath;
	
	public Entity(Vector2 v)
    {
		coordinates = new Vector2(v);
    }
    
    public Entity(Vector2 v, SpriteBatch batch, String spritePath)
    {
        this(v);
        this.batch = batch;
        this.spritePath = spritePath;
    }
    
    protected void create() {
        sprite = new Texture(Gdx.files.internal(spritePath));
    }

    protected void render() {
        batch.begin();
        batch.draw(sprite, 100, 100);
        batch.end();
    }
    
    protected Vector2 getCoordinates()
    {
    	return coordinates;
    }
 

    public String toString() {
        return "Basic foundation of all rendered objects";
    }
}