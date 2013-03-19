package com.tdt4240.A6.junglearena.model;

public class Map {
	private String background;
	private float[] mapY;

	public Map(String background,float[] mapY) {
		this.setBackground(background);
		this.setMapY(mapY);
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

}
