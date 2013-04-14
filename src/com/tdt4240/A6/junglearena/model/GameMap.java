package com.tdt4240.A6.junglearena.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.tdt4240.A6.junglearena.model.entities.Entity;

public class GameMap extends Entity{
	private String background;
	private float[] mapY;
	private List<PolygonShape> polygons;
	private ChainShape chainShape;

	public GameMap(String background,float[] mapY) {
		this.setBackground(background);
		this.setMapY(mapY);
		this.setPolygons(new ArrayList<PolygonShape>());
		this.chainShape = new ChainShape();		
	}

	public float[] getMapY() {
		return mapY;
	}

	public void setMapY(float[] mapY) {
		this.mapY = mapY;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public List<PolygonShape> getPolygons() {
		return polygons;
	}

	public void setPolygons(List<PolygonShape> polygons) {
		this.polygons = polygons;
	}

	public ChainShape getChainShape() {
		return chainShape;
	}

	public void setChainShape(ChainShape chainShape) {
		this.chainShape = chainShape;
	}

	@Override
	public void collisionHappened() {
		/*at the moment nothing happens to the ground if something collides on it.* 
		 * */
	}

}
