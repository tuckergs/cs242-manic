package com.manic.game;

import java.util.HashMap;

import com.manic.game.moves.Hitbox;
import com.manic.game.entities.Character;

public class CharacterHitboxFixtureUserData {

	private String charID;
	
	private boolean is_attack;
	
	private String hboxID;
	
	
	public CharacterHitboxFixtureUserData ( String cID , boolean is_attack , String hID ){
		
		setCharID (cID);
		
		setHboxID (hID);
		
	}
	
	
	public void setCharID ( String bID )
	{
		charID = bID;
	}
	
	public void setIsAttack ( boolean is_att )
	{
		is_attack = is_att;
	}
	
	public void setHboxID ( String hID )
	{
		hboxID = hID;
	}
	

	//This takes the "global" hashmap of all characters and uses it to get the hitbox
	public Hitbox getHitbox( HashMap < String , Character> hsh )
	{
		
		Character ch = hsh.get(charID);
		
		Hitbox h;
		
		if ( is_attack )
		{
			
			h = ch.getDamagingHitboxes().get(hboxID);
			
		}
		else
		{
			
			h = ch.getCharacterHitboxes().get(hboxID);
			
		}
		
		return h;
		
	}
	
	
	
}
