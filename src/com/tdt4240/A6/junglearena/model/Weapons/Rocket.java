package com.tdt4240.A6.junglearena.model.Weapons;

public class Rocket extends Weapon {

	public Rocket() {
		super();
		super.setName("Rocket");
		super.setSkin("Rocket");
		super.setDamage(15);
		super.setTimeBeforeExplosion(0.1f);
	}

	public Rocket(int damage, String name, String skin, int areaOfEffect) {
		super(damage, name, skin, areaOfEffect);
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
