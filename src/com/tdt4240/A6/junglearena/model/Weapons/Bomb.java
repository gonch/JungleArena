package com.tdt4240.A6.junglearena.model.Weapons;

public class Bomb extends Weapon {

	public Bomb(){
		super();
		super.setName("Bomb");
		super.setSkin("Bomb");
		super.setDamage(50);
		super.setTimeBeforeExplosion(3f);
	}
	
	public Bomb(int damage, String name, String skin, int areaOfEffect) {
		super(damage, name, skin, areaOfEffect);
		// TODO add special abilities/attributes for the Bomb
	}
	
	@Override
	public void update(float dt){
		super.update(dt);
	}

}
