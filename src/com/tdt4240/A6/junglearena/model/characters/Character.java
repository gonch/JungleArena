package com.tdt4240.A6.junglearena.model.characters;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.model.Entity;
import com.tdt4240.A6.junglearena.utils.Constants;

public class Character extends Entity{
	private int health;
	private String name;
	private Vector2 position;
	private String skin;
	private Vector2 size;
	private Vector2 centre;

	public Character(int health, String name, Vector2 pos, String skin) {
		super();
		this.health = health;
		this.name = name;
		this.position = pos;
		this.skin = skin;
		this.setSize(new Vector2(50f,50f));
		this.centre = new Vector2(position.x + size.x/2f,position.y + size.y/2);
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
	
	public Vector2 getCentre(){
		return new Vector2(position.x + size.x/2f,position.y + size.y/2);
	}

	@Override
	public void collisionHappened() {
		
	}
	
	@Override
	public void update(float dt){
		if(this.getBody()!=null){
			// in box2d the position is the given by the coordinate of the centre of the shape
			this.centre.x = this.getBody().getPosition().x;
			this.centre.y = this.getBody().getPosition().y;
			this.position.x = this.centre.x - this.size.x/2f;
			this.position.y = this.centre.y - this.size.y/2f;
		}
	}
}
