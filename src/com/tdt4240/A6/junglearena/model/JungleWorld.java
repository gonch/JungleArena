package com.tdt4240.A6.junglearena.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public class JungleWorld{
	private Player player1, player2;
	private List<Player> players;
	private GameMap map;
	private World world;
	
	public JungleWorld() {
		Character lion = new Character(100, "lion", new Vector2(10, 10), "lion");
		Character lion2 = new Character(100, "lion", new Vector2(300, 300), "lion");
		this.player1 = new Player("alessio", 1, lion);
		this.player2 = new Player("gonzalo", 2, lion2);
		this.players = new ArrayList<Player>();
		this.players.add(player1);
		this.players.add(player2);
		this.map = null;
		this.setWorld(new World(new Vector2(0,-10), true));		
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public GameMap getMap() {
		return this.map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

	/**
	 * This method is used to swap players. In fact the player who is going to
	 * play the round is the one at position 0
	 * */
	public List<Player> swapPlayers() {
		Player tmp = this.players.get(0);
		this.players.remove(0);
		this.players.add(tmp);
		return this.players;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
//	public void setWorldParameters(){
//		// First we create a body definition
//		BodyDef bodyDef = new BodyDef();
//		// We set our body to dynamic, for something like ground which doesnt move we would set it to StaticBody
//		bodyDef.type = BodyType.DynamicBody;
//		// Set our body's starting position in the world
//		bodyDef.position.set(100, 300);
//
//		// Create our body in the world using our body definition
//		Body body = world.createBody(bodyDef);
//
//		// Create a circle shape and set its radius to 6
//		CircleShape circle = new CircleShape();
//		circle.setRadius(6f);
//
//		// Create a fixture definition to apply our shape to
//		FixtureDef fixtureDef = new FixtureDef();
//		fixtureDef.shape = circle;
//		fixtureDef.density = 0.5f; 
//		fixtureDef.friction = 0.4f;
//		fixtureDef.restitution = 0.6f; // Make it bounce a little bit
//
//		// Create our fixture and attach it to the body
//		Fixture fixture = body.createFixture(fixtureDef);
//
//		// Remember to dispose of any shapes after you're done with them!
//		// BodyDef and FixtureDef don't need disposing, but shapes do.
//		circle.dispose();
//		
//		// Create our body definition
//		BodyDef groundBodyDef =new BodyDef();  
//		// Set its world position
//		groundBodyDef.position.set(new Vector2(0, 10));  
//
//		// Create a body from the defintion and add it to the world
//		Body groundBody = world.createBody(groundBodyDef);  
//		
//		// Create a polygon shape
//		PolygonShape groundBox = new PolygonShape();  
//		// Set the polygon shape as a box which is twice the size of our view port and 20 high
//		// (setAsBox takes half-width and half-height as arguments)
////		groundBox.setAsBox(camera.viewportWidth, 10.0f);
//		// Create a fixture from our polygon shape and add it to our ground body  
//		groundBody.createFixture(groundBox, 0.0f); 
//		// Clean up after ourselves
//		groundBox.dispose();
//	}

}
