/* Defines what a character is, what they can do, and how they do it */
// every player has a character

package com.manic.game;

public class Character {
	private String characterName;
	private float movementSpeed;
	private float health;
	
	public Character(String name, float speed)
	{
		movementSpeed = speed;
	}
	
	public float getMovementSpeed() {
		return movementSpeed;
	}

}
