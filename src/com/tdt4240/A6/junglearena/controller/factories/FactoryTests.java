package com.tdt4240.A6.junglearena.controller.factories;

import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.A6.junglearena.model.Weapons.Weapon;
import com.tdt4240.A6.junglearena.model.characters.Character;

public class FactoryTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void WeaponFactory() {
		WeaponFactory wf = new WeaponFactory();
		String[]ss = wf.getWeapons();
		for (String s: ss){
			System.out.println(s);
		}
		
		Weapon c = wf.createWeapon("Gun", 10, "Colt 45", "coltskin", 2);
		System.out.print(c.toString());
		
	}
	
	@Test
	public void CharacterTest(){
			// test driver
			CharacterFactory cf = new CharacterFactory();
			String[]ss = cf.getCharacters();
			for (String s: ss){
				System.out.println(s);
			}
			Character c = cf.createCharacter("Elephant", 50, "Dumbo", new Vector2(0,0), "skin");
			System.out.print(c.toString());
	}

}
