package com.tdt4240.A6.junglearena.model.Weapons;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.model.entities.Entity;

public class Weapon extends Entity {
	private int damage;
	private String name;
	private String skin;
	private int areaOfEffect;
	private Vector2 centre;
	private Vector2 size;
	/**
	 * Variables for handling collision detection
	 * */
	private boolean isExploded;
	private boolean isCollided;
	private float timeBeforeExplosion;// seconds before the weapon explodes,
										// after the collision. Set to zero for
										// explosion right after the collision

	public Weapon() {
		this.isCollided = false;
		this.isExploded = false;
		this.timeBeforeExplosion = 3f;
	}

	public Weapon(int damage, String name, String skin, int areaOfEffect) {
		super();
		this.damage = damage;
		this.name = name;
		this.skin = skin;
		this.areaOfEffect = areaOfEffect;
		this.setCollided(false);
		this.setExploded(false);
		this.timeBeforeExplosion = 3f;
		this.size = new Vector2(10, 10);
		this.centre = new Vector2(this.getX() + this.getSize().x / 2f, this.getY() + this.getSize().y / 2f);
	}

	public Vector2 getCentre() {
		return centre;
	}

	public void setCentre(Vector2 centre) {
		this.centre = centre;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
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
	public void update(float dt) {
		if (this.isCollided) {
			this.timeBeforeExplosion -= dt;
		}
		if (timeBeforeExplosion < 0) {
			this.isExploded = true;
		}
		if (this.getBody() != null) {
			this.centre.x = this.getBody().getPosition().x;
			this.centre.y = this.getBody().getPosition().y;
			this.setPosition(this.centre.x - this.size.x / 2f, this.centre.y - this.size.y / 2f);
		}
	}

	@Override
	public void collisionHappened() {
		this.setCollided(true);
	}

}
