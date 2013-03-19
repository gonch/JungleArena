package com.tdt4240.A6.junglearena.controller;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.model.Character;
import com.tdt4240.A6.junglearena.model.World;

public class WorldController {
	private World world;
	
	public WorldController(World world){
		this.world = world;
	}
	
	public void screenTouched(float x, float y){
		Character tank1 = this.world.getPlayer1().getCharacter();
		tank1.setPosition(new Vector2(x,y));
	}
}
