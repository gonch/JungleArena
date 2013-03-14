package com.tdt4240.A6.junglearena.model;

import com.badlogic.gdx.math.Vector2;

public class Character {
	private int health;
	private String name;
	private Vector2 pos;
	private String skin;

	public Character(int health, String name, Vector2 pos, String skin) {
		super();
		this.health = health;
		this.name = name;
		this.pos = pos;
		this.skin = skin;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector2 getPos() {
		return pos;
	}

	public void setPos(Vector2 pos) {
		this.pos = pos;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

}
