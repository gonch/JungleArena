package com.tdt4240.A6.junglearena.controller.factories;

import com.tdt4240.A6.junglearena.model.GameMap;
import com.tdt4240.A6.junglearena.model.Weapons.Weapon;
import com.tdt4240.A6.junglearena.model.characters.GameCharacter;
import com.tdt4240.A6.junglearena.model.entities.CharacterGameMap;
import com.tdt4240.A6.junglearena.model.entities.CoupleOfEntities;
import com.tdt4240.A6.junglearena.model.entities.Entity;
import com.tdt4240.A6.junglearena.model.entities.WeaponCharacterCouple;
import com.tdt4240.A6.junglearena.model.entities.WeaponGameMap;

public class CoupleOfEntitiesFactory {

	// Private constructor prevents instantiation from other classes
	private CoupleOfEntitiesFactory() {
	}

	/**
	 * SingletonHolder is loaded on the first execution of
	 * Singleton.getInstance() or the first access to SingletonHolder.INSTANCE,
	 * not before.
	 */
	private static class SingletonHolder {
		public static final CoupleOfEntitiesFactory INSTANCE = new CoupleOfEntitiesFactory();
	}

	public static CoupleOfEntitiesFactory getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public CoupleOfEntities createCoupleOfEntities(Entity e1, Entity e2) {
		Class<? extends Entity> e1Clazz = e1.getClass();
		Class<? extends Entity> e2Clazz = e2.getClass();
		System.out.println(e1.toString() + "," + e2.toString());
		CoupleOfEntities entities = null;
		if (Weapon.class.isAssignableFrom(e1Clazz)) {
			if (GameCharacter.class.isAssignableFrom(e2Clazz)) {
				entities = new WeaponCharacterCouple((Weapon) e1,
						(GameCharacter) e2);
			} else if (GameMap.class.isAssignableFrom(e2Clazz)) {
				entities = new WeaponGameMap((Weapon) e1, (GameMap) e2);
			}
		} else if (Weapon.class.isAssignableFrom(e2Clazz)) {
			if (GameCharacter.class.isAssignableFrom(e1Clazz)) {
				entities = new WeaponCharacterCouple((Weapon) e2,
						(GameCharacter) e1);
			} else if (GameMap.class.isAssignableFrom(e1Clazz)) {
				entities = new WeaponGameMap((Weapon) e2, (GameMap) e1);
			}
		} else if (GameMap.class.isAssignableFrom(e2Clazz)
				&& GameCharacter.class.isAssignableFrom(e1Clazz)) {
			entities = new CharacterGameMap((GameCharacter) e1, (GameMap) e2);
		} else if (GameMap.class.isAssignableFrom(e1Clazz)
				&& GameCharacter.class.isAssignableFrom(e2Clazz)) {
			entities = new CharacterGameMap((GameCharacter) e2, (GameMap) e1);
		}
		return entities;
	}
}
