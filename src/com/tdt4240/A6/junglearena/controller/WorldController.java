package com.tdt4240.A6.junglearena.controller;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tdt4240.A6.junglearena.model.Character;
import com.tdt4240.A6.junglearena.model.JungleWorld;

public class WorldController {
	private JungleWorld jungleWorld;

	public WorldController(JungleWorld world) {
		this.jungleWorld = world;
	}

	public void screenTouched(float x, float y) {
		Character tank1 = this.jungleWorld.getPlayer1().getCharacter();
//		tank1.setPosition(new Vector2(x, y));
		setCharacterStartingPositions();
	}

	/**
	 * The first character will be positioned on the first 1/3, the second in
	 * the last 1/3
	 * */
	public void setCharacterStartingPositions() {
		//TODO: center the images
		Character leftChar = this.jungleWorld.getPlayer1().getCharacter();
		Character rigthChar = this.jungleWorld.getPlayer2().getCharacter();
		Random random = new Random();
		int randomX = random.nextInt(Gdx.graphics.getWidth() / 3);
		float y = this.jungleWorld.getMap().getMapY()[randomX];
		leftChar.setPosition(new Vector2(randomX, y));
		randomX = random.nextInt(Gdx.graphics.getWidth() / 3) + Gdx.graphics.getWidth() * 2 / 3 ;
		y = this.jungleWorld.getMap().getMapY()[randomX];
		rigthChar.setPosition(new Vector2(randomX, y));
	}

	public void screenWithFling(float velocityX, float velocityY) {
		
	}
	
	public World generateBox2DWorld(){
		World world = new World(new Vector2(0,-10), true);
		

		// Create a body from the defintion and add it to the world
		
		List<PolygonShape> polygons = this.jungleWorld.getMap().getPolygons();
		for(PolygonShape groundBox : polygons){
			// Create our body definition
			BodyDef groundBodyDef =new BodyDef();  
			// Set its world position
			groundBodyDef.position.set(new Vector2(0, 0));  //TODO hardcoded
		// Create a polygon shape
		Body groundBody = world.createBody(groundBodyDef);  
		// Create a fixture from our polygon shape and add it to our ground body  
		groundBody.createFixture(groundBox, 0.0f); 
		}
		// Clean up after ourselves
//		groundBox.dispose();
		
//		BodyDef groundBodyDef =new BodyDef();  
//        groundBodyDef.position.set(new Vector2(0, 100));  
//        Body groundBody = world.createBody(groundBodyDef);  
//        PolygonShape groundBox = new PolygonShape();  
//        groundBox.setAsBox((480) , 10.0f);  
//        groundBody.createFixture(groundBox, 0.0f);  
		
		this.jungleWorld.setWorld(world);
		return world;
	}
}
