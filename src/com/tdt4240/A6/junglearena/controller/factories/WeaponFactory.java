package com.tdt4240.A6.junglearena.controller.factories;

import com.tdt4240.A6.junglearena.model.Weapons.*;

public class WeaponFactory {

	public Weapon createWeapon(String type, int damage, String name, String skin, int areaOfEffect){
		switch(type){
		case "bomb": return new Bomb(damage, name, skin, areaOfEffect);
		case "gun":  return new Gun(damage, name, skin, areaOfEffect);
		case "bazuka": return new Bazuka(damage, name, skin, areaOfEffect);
		// Add more Weapons here, if needed.
		default: System.err.print("invalid Weapon. Can not create \""+type+"\"!Created default instead.");
			return new Bomb(damage, name, skin, areaOfEffect);
		}
	}
}
