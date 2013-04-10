package com.tdt4240.A6.junglearena.controller.factories;

import java.util.LinkedList;
import java.util.List;

import com.tdt4240.A6.junglearena.model.Weapons.*;

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
	
	// private List<String> ReadWeaponsFromPackage(String packageName){
	// ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	// assert classLoader !=null;
	// String path = packageName.replace('.','/');
	// Enumeration<URL> resources = classLoader.getResources(path);
	// List<String> weaponClassNames= new ArrayList<String>();
	// while(resources.hasMoreElements()){
	// URL resoucre = resources.nextElement();
	// weaponClassNames.add(resoucre.)
	// }
	//
	// return list;
	// }
	
}