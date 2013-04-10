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
	}

	public String[] getWeapons() {
		return (String[]) weapons.toArray();
	}

	public Weapon createWeapon(String type, int damage, String name, String skin, int areaOfEffect) {
		String className = pkgPath + type;
		for (String s : weapons) {
			if (s.equals(type.toLowerCase())) {

				try {
					Class weapon = Class.forName(className);
					Object newInstanceOfWeapon = weapon.getConstructor(Integer.TYPE,String.class,String.class,Integer.TYPE).newInstance(damage, name, skin,areaOfEffect);
					return (Weapon)newInstanceOfWeapon;
				} catch (IllegalArgumentException | SecurityException | InstantiationException | IllegalAccessException
						| InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
					e.printStackTrace();
				}

				// ClassLoader WeaponClassLoader =
				// ClassLoader.getSystemClassLoader();
				// String className = pkgPath + type;
				// Class<?> w = WeaponClassLoader.loadClass(className);
				//
				// Object WeaponInstance = w.

				// return new class (type) with damage, name, skin,
				// areaOfEffect)
			}
		}
		System.err.print("Invalid Weapon! Can not create \""+className+"\"!Created default instead.");
		return new Bomb(damage, name, skin, areaOfEffect);

		// switch(type){
		// case "bomb": return new Bomb(damage, name, skin, areaOfEffect);
		// case "gun": return new Gun(damage, name, skin, areaOfEffect);
		// case "bazuka": return new Bazuka(damage, name, skin, areaOfEffect);
		// // Add more Weapons here, if needed.
		// default:
		// System.err.print("invalid Weapon. Can not create \""+type+"\"!Created default instead.");
		// return new Bomb(damage, name, skin, areaOfEffect);
		// }
	}
}
