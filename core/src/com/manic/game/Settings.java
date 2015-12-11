
/**
 * @author Stephen Lorenz
 * 
 * @brief Essentially a struct that store all program data that is required statically throughout several classes.
 * 
 */

package com.manic.game;

public class Settings {
	//box2D variables
	//pixels per meter
	public static final float PPM = 100;
	public static final float SCALE_PPM = PPM * Manic.SCALE;
	
	//box2D categories 
	//default category is 1
	public static final short BIT_PLATFORM = 2;
	public static final short BIT_PLAYER = 4;
	public static final short BIT_BALL = 8;
	
	public static final short BIT_HITBOX_CHARACTER = 16;
	public static final short BIT_HITBOX_DAMAGING = 32;
	
	
	//load()
	
	//save()
	
	//toggleSound()
	
	//.. etc

}
