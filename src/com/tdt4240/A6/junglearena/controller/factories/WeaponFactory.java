package com.tdt4240.A6.junglearena.controller.factories;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import com.tdt4240.A6.junglearena.model.Weapons.*;

public class WeaponFactory {
	private List<String> weapons;
	private final String pkgPath = "com.tdt4240.A6.junglearena.model.weapons";

	public WeaponFactory() {
		weapons = new LinkedList<String>();
		weapons.add("bomb");
		weapons.add("gun");
		weapons.add("bazuka");
		//TODO introduce Poperties file to read these names from
	}

	public String[] getWeapons() {
		return (String[]) weapons.toArray();
	}

	public Weapon createWeapon(String type, int damage, String name, String skin, int areaOfEffect) {
		String className = pkgPath + type;
		for (String s : weapons) {
			if (s.equals(type.toLowerCase())) {
				try {
					Class<Weapon> weapon = (Class<Weapon>) Class.forName(className);
					Weapon newInstanceOfWeapon = weapon.getConstructor(Integer.TYPE,String.class,String.class,Integer.TYPE).newInstance(damage, name, skin,areaOfEffect);
					return newInstanceOfWeapon;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.err.print("Invalid Weapon! Can not create \""+className+"\"!Created default instead.");
		return new Bomb(damage, name, skin, areaOfEffect);

	}
}
