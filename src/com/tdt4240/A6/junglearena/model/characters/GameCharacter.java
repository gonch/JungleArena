package com.tdt4240.A6.junglearena.model.characters;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.model.entities.Entity;
import com.tdt4240.A6.junglearena.utils.Constants;

public class GameCharacter extends Entity{
	private float health;
	private String name;
	private Vector2 position;
	private String skin;
	private Vector2 size;
	private Vector2 centre;
	private boolean collided;

	public GameCharacter(float health, String name, Vector2 pos, String skin) {
		super();
		this.health = health;
		this.name = name;
		this.position = pos;
		this.skin = skin;
		this.setSize(new Vector2(50f,50f));
		this.centre = new Vector2(position.x + size.x/2f,position.y + size.y/2);
		this.collided = false;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
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
	
	public void setCentre(Vector2 centre) {
		this.centre = centre;
	}

	public boolean isCollided() {
		return collided;
	}

	public void setCollided(boolean collided) {
		this.collided = collided;
	}

	@Override
	public void collisionHappened() {
		this.setCollided(true);
	}
	
	@Override
	public void update(float dt){
		if(this.getBody()!=null){
			this.centre.x = this.getBody().getPosition().x ;
			this.centre.y = this.getBody().getPosition().y;
			this.position.x = this.centre.x - this.size.x/2f;
			this.position.y = this.centre.y - this.size.y/2f;
}
	}
}
