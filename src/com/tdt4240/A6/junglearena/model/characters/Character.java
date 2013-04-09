package com.tdt4240.A6.junglearena.model.characters;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.model.Entity;

public class Character extends Entity{
	private int health;
	private String name;
	private Vector2 position;
	private String skin;
	private Vector2 size;

	public Character(int health, String name, Vector2 pos, String skin) {
		super();
		this.health = health;
		this.name = name;
		this.position = pos;
		this.skin = skin;
		this.setSize(new Vector2(50,50));
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

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 pos) {
		this.position = pos;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	@Override
	public void collisionHappened() {
		
	}

}
