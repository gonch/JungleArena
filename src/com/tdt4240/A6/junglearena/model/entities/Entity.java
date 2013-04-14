package com.tdt4240.A6.junglearena.model.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Each model class that is subject to collision must extend this class
 * **/
public abstract class Entity extends Sprite{

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
	
	/**
	 * This method will be overrided from all the class that extend entity.
	 * It contains the code that will be triggered when the first collision happens and 
	 * modifies something in the entity. If the collision doesn't make any effect on the 
	 * entity, this method can be kept empty.
	 * **/
	public abstract void collisionHappened();
	
	
	public void update(float dt){
		
	}
}
