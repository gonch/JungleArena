package com.tdt4240.A6.junglearena.model.entities;

import com.tdt4240.A6.junglearena.model.GameMap;
import com.tdt4240.A6.junglearena.model.Weapons.Weapon;

public class WeaponGameMap implements CoupleOfEntities {

	private Weapon weapon;
	private GameMap map;

	public WeaponGameMap(Weapon weapon, GameMap map) {
		super();
		this.weapon = weapon;
		this.map = map;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

	/**
	 * At the moment no special interaction between weapon and map
	 * */
	@Override
	public void collisionHappened() {
		this.weapon.collisionHappened();
		this.map.collisionHappened();
	}

}
