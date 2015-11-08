package com.manic.game;

import static com.manic.game.Settings.PPM;

//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//protected SpriteBatch spriteBatchHandle;
//protected Texture texture;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * @brief Basic structure of anything that exists within the world.
 *
 * This classes packages all the necessary Box2D data members necessary
 *  to create anything within the world.
 *
 * @note Children classes will make assumptions about some of these parameters.
 *
 * @author Stephen Lorenz
 *
 * @version 0.1
 *
 * @date 11/4/15
 */

public class Entity {
    protected float x = 0;
    protected float y = 0;
    protected float height = 0;
    protected float width = 0;
   
    protected BodyDef bodyDef;
    protected FixtureDef fixtureDef;
    
    

    public Entity(BodyType bt, PolygonShape ps, int height, int width, float x, float y)
    {       
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
       
        setHeight(height);
        setWidth(width);
        setX(x);
        setY(y);
       
        //PPM = 100
        bodyDef.position.set(x/PPM, y/PPM);
        bodyDef.type = bt;
        ps.setAsBox(height/PPM, width/PPM);
        fixtureDef.shape = ps;
    }


    //Getters
    //Coordinates
    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    //Dimensions
    public float getHeight()
    {
        return height;
    }

    public float getWidth()
    {
        return width;
    }
   
    //Bodies
    public BodyDef getBody()
    {
        return bodyDef;
    }
   
    //Fixtures
    public FixtureDef getFixture()
    {
        return fixtureDef;
    }

    //Setters
    //Coordinates
    protected void setX(float x)
    {
        if (y > 0) {
            this.x = x;
        }
        else {
            this.x = 0;
        }
    }

    protected void setY(float y)
    {
        if (y > 0) {
            this.y = y;
        }
        else {
            this.y = 0;
        }
    }

    //Dimensions
    protected void setHeight(int h)
    {
        if (h > 0) {
            height = h;
        }
        else {
            height = 1;
        }
    }

    protected void setWidth(int w)
    {
        if (w > 0) {
            width = w;
        }
        else {
            width = 1;
        }
    }

    public String toString() {
        return "Root class of all visible objects. Has a sprite, dimension, and location";
    }
}