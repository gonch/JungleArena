package com.tdt4240.A6.junglearena.controller.factories;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import com.tdt4240.A6.junglearena.model.Weapons.*;

public class WeaponFactory {
	private static List<String> weapons;
	private final String pkgPath = "com.tdt4240.A6.junglearena.model.Weapons";

	public WeaponFactory() {
		weapons = new ArrayList<String>();
		// weapons.add("Bomb");
		// weapons.add("Gun");
		// weapons.add("Bazuka");
		loadWeapons();

	}

	private void loadWeapons() {
		Class[] classes = ReadClassesFromPackage(pkgPath);
		for (Class<?> c : classes) {
			String[] fullClassName = c.getName().split("\\.");
			String className = fullClassName[fullClassName.length - 1];
			if (!className.equals("Weapon")) {
				weapons.add(className); 
			}
		}
	}

	public String[] getWeapons() { // return deep copy
		String[] copyOfWeapons = new String[weapons.size()];
		for (int i = 0; i < weapons.size(); i++) {
			copyOfWeapons[i] = new String(weapons.get(i));
		}
		return copyOfWeapons;
	}

	public Weapon createWeapon(String type, int damage, String name, String skin, int areaOfEffect) {
		String className = pkgPath + "." + type;
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

	Class[] ReadClassesFromPackage(String packageName) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources;
		try {
			resources = classLoader.getResources(path);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.print("Could not load class from " + path);
			return new Class[1];
		}
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			try {
				classes.addAll(findClasses(directory, packageName));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return classes.toArray(new Class[classes.size()]);

	}

	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}
}
