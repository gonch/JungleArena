package com.tdt4240.A6.junglearena.model;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Each model class that is subject to collision must extend this class
 * **/
public class Entity {

	private Body body;
	
	public Entity(){
		this.setBody(null);
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}	
}
