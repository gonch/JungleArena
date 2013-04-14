package com.tdt4240.A6.junglearena.model;

import com.tdt4240.A6.junglearena.model.characters.GameCharacter;

public class Player {
	private String name;
	private int id;
	private GameCharacter character;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GameCharacter getCharacter() {
		return character;
	}

	public void setCharacter(GameCharacter character) {
		this.character = character;
	}

	public Player(String name, int id, GameCharacter lion) {
		super();
		this.name = name;
		this.id = id;
		this.character = lion;
	}
}
