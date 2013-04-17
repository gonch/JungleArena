package com.tdt4240.A6.junglearena.model.Weapons;

public class Gun extends Weapon {
	
	public Gun(){
		super();
		super.setName("Gun");
		super.setSkin("Gun");
		super.setDamage(10);
		super.setTimeBeforeExplosion(0.1f);
	}
	public Gun(int damage, String name, String skin, int areaOfEffect) {
		super(damage, name, skin, areaOfEffect);
		// TODO add special abilities/attributes for the Gun
	}
	
	@Override
	public void update(float dt) {
		if (this.isCollided()) {
			float time = this.getTimeBeforeExplosion();
			this.setTimeBeforeExplosion(time -= dt);
		}
		if (this.getTimeBeforeExplosion() < 0) {
			this.setExploded(true);
		}
		if (this.getBody() != null) {
			this.getCentre().x = this.getBody().getPosition().x;
			this.getCentre().y = this.getBody().getPosition().y;
			this.setPosition(this.getCentre().x - this.getSize().x / 2f, this.getCentre().y - this.getSize().y / 2f);
		}
	}
}
