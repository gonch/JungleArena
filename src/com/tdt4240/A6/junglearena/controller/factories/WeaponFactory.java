package com.tdt4240.A6.junglearena.controller.factories;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.model.Weapons.*;
import com.tdt4240.A6.junglearena.model.characters.Character;

public class WeaponFactory {
	private static List<String> weapons;
	private final String pkgPath = "com.tdt4240.A6.junglearena.model.Weapons";

	public WeaponFactory() {
		weapons = new LinkedList<String>();
		weapons.add("Bomb");
		weapons.add("Gun");
		weapons.add("Bazuka");
	}

	public String[] getWeapons() { //deep copy
		String[] copyOfWeapons = new String[weapons.size()];
		for(int i=0; i<weapons.size();i++){
			copyOfWeapons[i]= new String (weapons.get(i));
		}
		return copyOfWeapons;
	}

	public Weapon createWeapon(String type, int damage, String name, String skin, int areaOfEffect) {
		String className = pkgPath +"."+ type;
		for (String s : weapons) {
			if (s.equals(type)) {
				try {
					Class<Weapon> weapon = (Class<Weapon>) Class.forName(className);
					Weapon newInstanceOfWeapon = weapon.getConstructor(Integer.TYPE, String.class, String.class,
							Integer.TYPE).newInstance(damage, name, skin, areaOfEffect);
					return newInstanceOfWeapon;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.err.print("Invalid Weapon! Can not create \"" + className + "\"! Created default instead.");
		return new Bomb(damage, name, skin, areaOfEffect);
	}

	public static void main(String[] args) {
		// test driver
		WeaponFactory wf = new WeaponFactory();
		String[]ss = wf.getWeapons();
		for (String s: ss){
			System.out.println(s);
		}
		Weapon c = wf.createWeapon("Gun", 10, "Colt 45", "coltskin", 2);
		System.out.print(c.toString());

	}
}
