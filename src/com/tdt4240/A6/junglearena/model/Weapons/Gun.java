package com.tdt4240.A6.junglearena.model.Weapons;

public class Gun extends Weapon {
	
	public Gun(){
		super();
		super.setName("Gun");
		super.setSkin("Gun");
		super.setDamage(75);
		super.setTimeBeforeExplosion(0f);
	}
	public Gun(int damage, String name, String skin, int areaOfEffect) {
		super(damage, name, skin, areaOfEffect);
		// TODO add special abilities/attributes for the Gun
	}
	
	@Override
	public void update(float dt){
		super.update(dt);
	}

}
