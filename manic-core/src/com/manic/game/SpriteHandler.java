
package com.manic.game;




import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class SpriteHandler {

	
	
	//Maps orders to sprites
	private SortedMap < Integer , Sprite > sprites;
	
	

	
	public void render()
	{
			
		
		//Use a better system
		SpriteBatch batch = new SpriteBatch ( sprites.size() );
		
		
		//keySet() returns the keys in ascending order, which is weird, but the doc says so
		Set<Integer> orders = sprites.keySet();
		
		
		batch.begin();
		
		//We need order here so we could do sprites at the correct "depth"
		for ( Iterator<Integer> i_order = orders.iterator() ; i_order.hasNext() ; )
		{
		
			
			Integer cur_order = (Integer) i_order.next();
			
			
			Sprite spr = sprites.get( cur_order );
			
			
			batch.draw(spr, spr.getX(), spr.getY() );
			
			
			
			
			
		}
		
		
		batch.end();
		
		
		
		
	}
	
	public void addSprite ( int order , Sprite spr )
	{
		
		sprites.put( order , spr );
		
	}
	
	public void deleteSprite ( int order )
	{
		
		sprites.remove ( order );
		
	}
	
	
	public void deleteAll ()
	{
		
		//Todo
		
	}
	
	
}
