package com.tdt4240.A6.junglearena.controller.factories;

import com.tdt4240.A6.junglearena.model.Weapons.*;

public class WeaponFactory {

	public Weapon createWeapon(String type, int damage, String name, String skin, int areaOfEffect){
		
		if (type != null && type.equals("bomb")) {
			return new Bomb(damage, name, skin, areaOfEffect);
		} 
		if (type != null && type.equals("gun")) {
			return new Gun(damage, name, skin, areaOfEffect);
		} 
		if (type != null && type.equals("bazuka")) {
			return new Bazuka(damage, name, skin, areaOfEffect);
		} 
		 System.err.print("invalid Weapon. Can not create \""+type+"\"!Created default instead.");
		return null;
		
	}
}
