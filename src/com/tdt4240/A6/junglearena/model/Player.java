package com.tdt4240.A6.junglearena.model;

public class Player {
	private String name;
	private int id;
	private Character character;

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

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public Player(String name, int id, Character character) {
		super();
		this.name = name;
		this.id = id;
		this.character = character;
	}
}
