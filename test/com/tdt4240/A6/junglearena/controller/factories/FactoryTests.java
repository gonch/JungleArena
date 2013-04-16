package com.tdt4240.A6.junglearena.controller.factories;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.model.Weapons.Weapon;
import com.tdt4240.A6.junglearena.model.characters.GameCharacter;

public class FactoryTests {

	@Test
	public void WeaponFactory() {
		WeaponFactory wf = WeaponFactory.getInstance();
		String[] ss = wf.getWeapons();
		for (String s : ss) {
			System.out.println("Weapon: " + s);
		}

		Weapon c = wf.createWeapon("Gun", 10, "Colt 45", "coltskin", 2);
		System.out.println("created: " + c.toString());

	}

	@Test
	public void ReadClasses() {
		Class<?>[] classes = ClassReader.readFromPackage("com.tdt4240.A6.junglearena.controller.factories");
		for (Class<?> c : classes) {
			System.out.println(c.getName());
		}
	}

	@Test
	public void CharacterTest() {
		// test driver
		CharacterFactory cf = CharacterFactory.getInstance();
//		CharacterFactory cf = new CharacterFactory();
		String[] ss = cf.getCharacters();
		for (String s : ss) {
			System.out.println(s);
		}
		GameCharacter c = cf.createCharacter("Elephant", 50, "Dumbo", new Vector2(0, 0), "skin");
		System.out.print(c.toString());

	}

}
