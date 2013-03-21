package com.tdt4240.A6.junglearena.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.physics.box2d.PolygonShape;

public class GameMap {
	private String background;
	private float[] mapY;
	private PolygonShape polygonShape;
	private List<PolygonShape> polygons;
	public PolygonShape getPolygonShape() {
		return polygonShape;
	}

	public GameMap(String background,float[] mapY) {
		this.setBackground(background);
		this.setMapY(mapY);
		this.polygonShape = new PolygonShape();
		this.setPolygons(new ArrayList<PolygonShape>());
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

	public void setPolygonShape(PolygonShape polygonShape) {
		this.polygonShape = polygonShape;
	}

	public List<PolygonShape> getPolygons() {
		return polygons;
	}

	public void setPolygons(List<PolygonShape> polygons) {
		this.polygons = polygons;
	}

}
