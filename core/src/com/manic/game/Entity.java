package com.manic.game;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.CircleShape;

import static com.manic.game.Settings.PPM;

/**
 * @brief Basic structure of anything that exists within the world.
 *
 * This classes packages all the necessary Box2D data members necessary
 * to create anything within the world.
 *
 * @note Children classes will make assumptions about some of these parameters.
 *
 * @author Stephen Lorenz
 *
 * @version 0.1
 *
 * @date 11/4/15
 */

//TODO Add sprite functionality
public class Entity {
    private float x = 0;
    private float y = 0;
    
    private float height = 0;
    private float width = 0;
    private float radius = 0;
    
    protected BodyDef bodyDef;
    protected FixtureDef fixtureDef;
    
    /**
     * @brief Create a square Entity.
     * 
     * @param bt Determine whether the Entity is static, dynamic, or kinematic.
     * @param polygon
     * @param height
     * @param width
     * @param x
     * @param y
     */
    public Entity(BodyType bt, PolygonShape polygon, int height, int width, float x, float y)
    {       
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
       
        setX(x);
        setY(y);
        
        setHeight(height);
        setWidth(width);
       
        bodyDef.position.set(x/PPM, y/PPM);
        bodyDef.type = bt;
        polygon.setAsBox(height/PPM, width/PPM);
        fixtureDef.shape = polygon;
    }
    
    /**
     * @brief Create a circle Entity.
     * 
     * @param bt Determine whether the Entity is static, dynamic, or kinematic.
     * @param circle
     * @param radius
     * @param x
     * @param y
     */
    public Entity(BodyType bt, CircleShape circle, int radius, float x, float y)
    {       
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
     
        setX(x);
        setY(y);
        
        setRadius(radius);
      
        bodyDef.position.set(x/PPM, y/PPM);
        bodyDef.type = bt;
        circle.setRadius(radius/PPM);
        fixtureDef.shape = circle;
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
    
    public float getRadius()
    {
        return radius;
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
    /**
     * @brief Set the Entity's horizontal coordinate.
     * 
     * If the parameter passed is less than 0, x is set to 0.
     * 
     * @param x
     */
    protected void setX(float x)
    {
        if (y > 0) {
            this.x = x;
        }
        else {
            this.x = 0;
        }
    }

    /**
     * @brief Set the Entity's vertical coordinate.
     * 
     * If y is passed as less than 0, y is set to 0.
     * 
     * @param y
     */
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
    /**
     * @brief Set the Entity's height.
     * 
     * Only called if Entity is passed a PolygonShape object.
     * 
     * If h is passed as not greater than 0, height is set to 1.
     * 
     * @param r
     */
    protected void setHeight(int h)
    {
        if (h > 0) {
            height = h;
        }
        else {
        	height = 1;
        }
    }

    /**
     * @brief Set the Entity's width.
     * 
     * Only called if Entity is passed a PolygonShape object.
     * 
     * If w is passed as not greater than 0, width is set to 1.
     * 
     * @param r
     */
    protected void setWidth(int w)
    {
        if (w > 0) {
            width = w;
        }
        else {
        	width = 1;
        }
    }
    
    /**
     * @brief Set the Entity's radius.
     * 
     * Only called if Entity is passed a CircleShape object.
     * 
     * If r is passed as not greater than 0, radius is set to 1.
     * 
     * @param r
     */
    protected void setRadius(int r)
    {
        if (r > 0) {
            radius = r;
        }
        else {
        	radius = 1;
        }
    }

    public String toString() {
        return "Root class of all visible objects";
    }
}