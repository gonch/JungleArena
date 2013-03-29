package com.tdt4240.A6.junglearena.model;

import com.badlogic.gdx.physics.box2d.Body;

public class Weapon {
	private int damage;
	private String name;
	private String skin;
	private int areaOfEffect;

	/**
	 * Variables for handling collision detection
	 * */
	private boolean isExploded;
	private boolean isCollided;
	private Body body;
	private float timeBeforeExplosion;//seconds before the weapon explodes, after the collision. Set to zero for explosion right after the collision

	public Weapon(int damage, String name, String skin, int areaOfEffect) {
		super();
		this.damage = damage;
		this.name = name;
		this.skin = skin;
		this.areaOfEffect = areaOfEffect;
		this.setCollided(false);
		this.setExploded(false);
		this.body = null;
		this.timeBeforeExplosion = 0f;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public int getAreaOfEffect() {
		return areaOfEffect;
	}

	public void setAreaOfEffect(int areaOfEffect) {
		this.areaOfEffect = areaOfEffect;
	}

	public boolean isExploded() {
		return isExploded;
	}

	public void setExploded(boolean isExploded) {
		this.isExploded = isExploded;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public boolean isCollided() {
		return isCollided;
	}

	public void setCollided(boolean isCollided) {
		this.isCollided = isCollided;
	}
	
	
	public float getTimeBeforeExplosion() {
		return timeBeforeExplosion;
	}

	public void setTimeBeforeExplosion(float timeBeforeExplosion) {
		this.timeBeforeExplosion = timeBeforeExplosion;
	}
	
	/**
	 * For explosions
	 * **/
	public void update(float dt){
		if(this.isCollided){
			this.timeBeforeExplosion -= dt;
		}
		System.out.println(timeBeforeExplosion);
		if(timeBeforeExplosion < 0){
			this.isExploded = true;
		}
	}
}
