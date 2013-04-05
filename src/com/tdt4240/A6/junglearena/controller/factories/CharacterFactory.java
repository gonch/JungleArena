package com.tdt4240.A6.junglearena.controller.factories;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.model.characters.*;
import com.tdt4240.A6.junglearena.model.characters.Character;

public class CharacterFactory {

	public Character createCharacter(String type,int health, String name, Vector2 pos, String skin){
		switch(type){
		case "elephant": return new Elephant(health, name, pos, skin);
		case "monkey" : return new Monkey(health, name, pos, skin);
		case "turtle" : return new Turtle(health, name, pos, skin);
		// Add more Characters here, if needed
		default: System.err.print("invalid Character. Can not create \""+type+"\"!Created default instead.");
					return new Monkey(health, name, pos, skin);
		}
	}
}
