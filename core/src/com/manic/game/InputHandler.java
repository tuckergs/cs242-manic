package com.manic.game;

public class InputHandler
{
	public static boolean down;
	public static boolean pressedDown;
	public static boolean[] keys;
	public static boolean[] pressedKeys;
	private static final int NUM_KEYS = 2;
	public static final int BUTTON1 = 0;
	public static final int BUTTON2 = 1;
	static
	{
		keys = new boolean[NUM_KEYS];
		pressedKeys = new boolean[NUM_KEYS];
	}
	
	public static void update()
	{
		pressedDown = down;
		
		for (int i = 0; i < NUM_KEYS; i++) {
			pressedKeys[i] = keys[i];
		}
	}
	
	public static boolean isDown()
	{
		return down;
	}
	
	public static boolean isPressed()
	{
		return down && !pressedDown;
	}
	
	public static boolean isReleased()
	{
		return !down && pressedDown;
	}
	
	public static void setKey(int i, boolean b)
	{
		keys[i] = b;
	}
	
	public static boolean isDown(int i)
	{
		return keys[i];
	}
	
	public static boolean isPressed(int i)
	{
		return keys[i] && !pressedKeys[i];
	}
}
