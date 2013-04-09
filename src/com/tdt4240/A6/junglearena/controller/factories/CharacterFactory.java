package com.tdt4240.A6.junglearena.controller.factories;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.model.characters.*;
import com.tdt4240.A6.junglearena.model.characters.Character;

public class CharacterFactory {

	public Character createCharacter(String type, int health, String name, Vector2 pos, String skin) {

		if (type != null && type.equals("elephant")) {
			return new Elephant(health, name, pos, skin);
		} else if (type != null && type.equals("monkey")) {
			return new Monkey(health, name, pos, skin);
		} else if (type != null && type.equals("turtle")) {
			return new Elephant(health, name, pos, skin);
		}
		System.err.print("invalid Character. Can not create \"" + type + "\"!Created default instead.");
		return null;
	}
}
