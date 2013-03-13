package com.tdt4240.A6.junglearena.model;

public class Weapon {
	private int damage;
	private String name;
	private String skin;
	private int areaOfEffect;

	public Weapon(int damage, String name, String skin, int areaOfEffect) {
		super();
		this.damage = damage;
		this.name = name;
		this.skin = skin;
		this.areaOfEffect = areaOfEffect;
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
}
