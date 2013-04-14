package com.tdt4240.A6.junglearena.controller.factories;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.model.characters.*;
import com.tdt4240.A6.junglearena.model.characters.GameCharacter;

public class CharacterFactory {
	private static List<String> characters;
	private final String pkgPath = "com.tdt4240.A6.junglearena.model.characters";

	public CharacterFactory() {
		characters = new ArrayList<String>();
		characters.add("Elephant");
		characters.add("Monkey");
		characters.add("Turtle");
	}

	public String[] getCharacters() {
		String[] copyOfCharacters = new String[characters.size()];
		for (int i = 0; i < characters.size(); i++) {
			copyOfCharacters[i] = new String(characters.get(i));
		}
		return copyOfCharacters;
	}

	public GameCharacter createCharacter(String type, int health, String name,
			Vector2 pos, String skin) {
		String className = pkgPath + "." + type;
		for (String s : characters) {
			if (s.equals(type)) {
				try {
					@SuppressWarnings("unchecked")
					Class<GameCharacter> weapon = (Class<GameCharacter>) Class
							.forName(className);
					GameCharacter newInstanceOfCharacter = weapon
							.getConstructor(Integer.TYPE, String.class,
									Vector2.class, String.class).newInstance(
									health, name, pos, skin);
					return newInstanceOfCharacter;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.err.print("Invalid Character! Can not create \"" + className
				+ "\"! Created default instead.");
		return new Monkey(health, name, pos, skin);
	}

}
