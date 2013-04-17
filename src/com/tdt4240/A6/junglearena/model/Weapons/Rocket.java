package com.tdt4240.A6.junglearena.model.Weapons;

public class Rocket extends Weapon {

	public Rocket() {
		super();
		super.setName("Rocket");
		super.setSkin("Rocket");
		super.setDamage(75);
		super.setTimeBeforeExplosion(0f);
	}

	public Rocket(int damage, String name, String skin, int areaOfEffect) {
		super(damage, name, skin, areaOfEffect);
	}
	
	@Override
	public void update(float dt){
		super.update(dt);
	}

}
