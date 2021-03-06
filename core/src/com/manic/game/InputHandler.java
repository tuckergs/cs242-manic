package com.manic.game;

/**
 * 
 * @author Stephen Lorenz
 * 
 * @brief Easy user input recognition through integers.
 * 
 * Keys are represented by integers. This class communicates
 * with the InputProcesser that constantly sets the two boolean
 * arrays keys and pressedKeys. Through these arrays, other classes
 * are able to check whether or not the key was pressed per frame.
 * 
 */

public class InputHandler
{
	public static boolean down;
	public static boolean pressedDown;
	public static boolean[] keys;
	public static boolean[] pressedKeys;
	private static final int NUM_KEYS = 13;
	public static final int KEY_SPACE = 0;
	public static final int KEY_A = 1;
	public static final int KEY_D = 2;
	public static final int KEY_S = 3;
	public static final int KEY_UP = 4;
	public static final int KEY_LEFT = 5;
	public static final int KEY_RIGHT = 6;
	public static final int KEY_DOWN = 7;
	public static final int KEY_P = 8;
	public static final int KEY_Q = 9;
	public static final int KEY_E = 10;
	public static final int KEY_U = 11;
	public static final int KEY_O = 12;
	
	static{
		keys = new boolean[NUM_KEYS];
		pressedKeys = new boolean[NUM_KEYS];
	}
	
	public static void update(){
		pressedDown = down;
		for (int i = 0; i < NUM_KEYS; i++) {
			pressedKeys[i] = keys[i];
		}
	}
	
	public static boolean isDown(){
		return down;
	}
	
	public static boolean isPressed(){
		return down && !pressedDown;
	}
	
	public static boolean isReleased(){
		return !down && pressedDown;
	}
	
	public static void setKey(int i, boolean b){
		keys[i] = b;
	}
	
	public static boolean isDown(int i){
		return keys[i];
	}
	
	public static boolean isPressed(int i){
		return keys[i] && !pressedKeys[i];
	}
}
