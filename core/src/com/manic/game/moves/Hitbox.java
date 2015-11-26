package com.manic.game.moves;

import com.badlogic.gdx.physics.box2d.Fixture;

public class Hitbox {

	private Fixture hbox;
	
	private float damage;
	
	private float hitstun;
	
	
	//Constructor
	public Hitbox ( Fixture hbox , float damage , float hitstun ){
		
		this.hbox = hbox;
		
		this.damage = damage;
		
		this.hitstun = hitstun;
		
	}
	
	
	//Getters
	public Fixture getHitbox (){
		return hbox;
	}
	
	public float getDamage (){
		return damage;
	}
	
	public float getHitstun(){
		return hitstun;
	}
	
	
}
