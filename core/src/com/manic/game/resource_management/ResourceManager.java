package com.manic.game.resource_management;

import java.io.IOException;

import com.manic.game.exceptions.InvalidXMLException;

/**
 * 
 * @author gabe
 *
 * This a lot like Block Bunny's Content class,
 * but it loads up animations, not textures.
 * Also, it will load up other things, like hitboxes
 *
 */

public abstract class ResourceManager {

	
	public abstract void load ( String file , String name )
							throws IOException , InvalidXMLException;
	
	public abstract Object get ( String key );
	
	
}
