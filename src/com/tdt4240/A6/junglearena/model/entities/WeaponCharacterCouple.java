package com.tdt4240.A6.junglearena.model.entities;

import com.badlogic.gdx.Gdx;
import com.tdt4240.A6.junglearena.model.Weapons.Weapon;
import com.tdt4240.A6.junglearena.model.characters.GameCharacter;

public class WeaponCharacterCouple implements CoupleOfEntities {
	private Weapon weapon;
	private GameCharacter character;

	public WeaponCharacterCouple(Weapon weapon, GameCharacter character) {
		super();
		this.weapon = weapon;
		this.character = character;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public GameCharacter getCharacter() {
		return character;
	}

	public void setCharacter(GameCharacter character) {
		this.character = character;
	}

	@Override
	public void collisionHappened() {
		float previousHealth = this.character.getHealth();
		float damage = this.weapon.getDamage();
		this.character.setHealth(previousHealth-damage);
		Gdx.input.vibrate(1000);
		this.character.collisionHappened();
		this.weapon.collisionHappened();
	}

}
