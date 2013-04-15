package com.tdt4240.A6.junglearena.listeners;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.tdt4240.A6.junglearena.controller.factories.CoupleOfEntitiesFactory;
import com.tdt4240.A6.junglearena.model.entities.CoupleOfEntities;
import com.tdt4240.A6.junglearena.model.entities.Entity;

public class CollisionListener implements ContactListener {

	/**
	 * This is called when two fixtures begin to overlap. This is called for
	 * sensors and non-sensors. This event can only occur inside the time step.
	 * *
	 * **/
	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		Body bodyA = fixA.getBody();
		Body bodyB = fixB.getBody();
		Entity entityA = null;
		Entity entityB = null;
		
		//checks if the userdata is a class that extends Entity. Is not really needed, is just for lazy programmers :)
		if (bodyA.getUserData() != null && Entity.class.isAssignableFrom(bodyA.getUserData().getClass()) && bodyB.getUserData() != null && Entity.class.isAssignableFrom(bodyB.getUserData().getClass())) { 
			entityA = (Entity) bodyA.getUserData();
			entityB = (Entity) bodyB.getUserData();
			CoupleOfEntities entities = CoupleOfEntitiesFactory.getInstance().createCoupleOfEntities(entityA, entityB);
			if(entities!=null){
				entities.collisionHappened();
			}
		}

	}

	/**
	 * This is called when two fixtures cease to overlap. This is called for
	 * sensors and non-sensors. This may be called when a body is destroyed, so
	 * this event can occur outside the time step.
	 * */
	@Override
	public void endContact(Contact contact) {
	}

	/**
	 * This is called after collision detection, but before collision
	 * resolution. This gives you a chance to disable the contact based on the
	 * current configuration.
	 * 
	 * */
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	/**
	 * The post solve event is where you can gather collision impulse results.
	 **/
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}

}
