package com.tdt4240.A6.junglearena.controller.factories;

import java.util.ArrayList;
import java.util.List;

import com.tdt4240.A6.junglearena.model.Weapons.*;

/**
 * @author hengsti
 */
public class WeaponFactory {
	private static List<String> weaponNames;
	private final static String pkgPath = "com.tdt4240.A6.junglearena.model.Weapons";
	private static final WeaponFactory instance = new WeaponFactory();

	private WeaponFactory() {

	}

	/**
	 * @return the instance of this Class (Singelton)
	 */
	public static WeaponFactory getInstance() {
		weaponNames = new ArrayList<String>();
		weaponNames.add("Bomb");
		weaponNames.add("Rocket");
		weaponNames.add("Gun");	
//		loadWeapons(pkgPath);
		return instance;
	}

	/**
	 * Load all weapons from the according package.
	 * 
	 * @param pkgPath
	 */
	private static void loadWeapons(String pkgPath) {
		Class<?>[] classes = ClassReader.readFromPackage(pkgPath);
		for (Class<?> c : classes) {
			String[] fullClassName = c.getName().split("\\.");
			String className = fullClassName[fullClassName.length - 1];
			if (!className.equals("Weapon")) {
				weaponNames.add(className);
			}
		}
	}

	/**
	 * @return a deep copied list of the weapons available
	 */
	public String[] getWeaponsArray() {
		String[] copyOfWeapons = new String[weaponNames.size()];
		for (int i = 0; i < weaponNames.size(); i++) {
			copyOfWeapons[i] = new String(weaponNames.get(i));
		}
		return copyOfWeapons;
	}
	
	public static List<String> getWeaponNames(){
		return weaponNames;
	}

	/**
	 * @param type
	 *            of weapon; Use getWeapons() and chose one.
	 * @param damage
	 * @param name
	 * @param skin
	 * @param areaOfEffect
	 * @return a new instance of the specified weapon
	 */
	public Weapon createWeapon(String type, int damage, String name, String skin, int areaOfEffect) {
		String className = pkgPath + "." + type;
		for (String s : weaponNames) {
			if (s.equals(type)) {
				try {
					@SuppressWarnings("unchecked")
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

	/**
	 * @param type
	 *            of weapon; Use getWeapons() and chose one.
	 * 
	 * @return a new instance of the specified weapon
	 */
	public Weapon createWeapon(String type) {
		String className = pkgPath + "." + type;
		for (String s : weaponNames) {
			if (s.equals(type)) {
				try {
					@SuppressWarnings("unchecked")
					Class<Weapon> weapon = (Class<Weapon>) Class.forName(className);
					Weapon newInstanceOfWeapon = weapon.getConstructor().newInstance();
					return newInstanceOfWeapon;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.err.print("Invalid Weapon! Can not create \"" + className + "\"! Created default instead.");
		return new Bomb();
	}
}
