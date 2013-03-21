package com.tdt4240.A6.junglearena.controller;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.tdt4240.A6.junglearena.model.GameMap;

/**
 * Java implementation of Perlin Noise algorithm taken by
 * http://freespace.virgin.net/hugo.elias/models/m_perlin.htm
 * */
public class MapController {
	private String background;
	private float[] mapY;
	private int screenWidth, screenHeight;
	private GameMap map;

	// TODO REMEMBER TO refactor names!!!

	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

	/**
	 * amplitude for each frequency
	 */
	static float persistence = 0.03f;
	// static float persistence = (float) (1f/Math.sqrt(new Double(2)));
	/**
	 * number of noise functions that perlin adds together
	 */
	static int numberOfOctaves = 6;
	/**
	 * horizontal scale
	 */
	static float hScale = 0.012f;
	/**
	 * vertical scale
	 */
	static float vScale = 0.35f;

	public MapController(String background) {
		this.background = background;
		this.screenWidth = Gdx.graphics.getWidth();
		this.screenHeight = Gdx.graphics.getHeight();
		this.mapY = new float[screenWidth];
		this.map = new GameMap(background, mapY);
	}

	private static float noise(float x, float y) {
		int n = (int) ((int) x + y * 57);
		n = (n << 13) ^ n;
		return (1.0f - ((n * (n * n * 15731 + 789221) + 1376312589) & 0x7fffffff) / 1073741824f);
	}

	private static float smoothNoise(float x, float y) {
		float corners = (noise(x - 1, y - 1) + noise(x + 1, y - 1) + noise(x - 1, y + 1) + noise(x + 1, y + 1)) / 16;
		float sides = (noise(x - 1, y) + noise(x + 1, y) + noise(x, y - 1) + noise(x, y + 1)) / 8;
		float center = noise(x, y) / 4;
		return corners + center + sides;
	}

	private static float interpolatedNoise(float x, float y) {
		int integer_X = (int) x;
		float fractional_X = x - integer_X;

		int integer_Y = (int) y;
		float fractional_Y = y - integer_Y;

		float v1 = smoothNoise(integer_X, integer_Y);
		float v2 = smoothNoise(integer_X + 1, integer_Y);
		float v3 = smoothNoise(integer_X, integer_Y + 1);
		float v4 = smoothNoise(integer_X + 1, integer_Y + 1);

		float i1 = cosInterpolate(v1, v2, fractional_X);
		float i2 = cosInterpolate(v3, v4, fractional_X);

		return cosInterpolate(i1, i2, fractional_Y);
	}

	private static float cosInterpolate(float a, float b, float x) {
		float ft = x * 3.1415927f;
		float f = (float) ((1 - Math.cos(ft)) * 0.5f);
		return a * (1 - f) + b * f;
	}

	/**
	 * The starting function of the Perline noise 2D algorithm
	 * */
	private static float perlinNoise_2D(float x, float z) {
		float height = 0;
		float frequency;
		float amplitude;

		x *= hScale;
		z *= hScale;

		float p = persistence;
		int n = numberOfOctaves - 1;

		for (int i = 0; i < n; i++) {
			frequency = (float) Math.pow(2, i);
			amplitude = (float) Math.pow(p, i);
			height = height + interpolatedNoise(x * frequency, z * frequency) * amplitude;
		}
		return height * vScale;
	}

	/**
	 * Creates the map according to the perline noise algorithm
	 * */
	public void generateMap() {
		float r = (float) (Math.random() * screenWidth);
		System.out.println(screenWidth);
		System.out.println(r);
		for (int i = 0; i < screenWidth; i++) {
			float realSize = screenHeight - perlinNoise_2D(i, r) * screenHeight - screenHeight / 2;
			float scaledSize = realSize - 100;
			mapY[i] = scaledSize;
		}
		this.map.setMapY(mapY);
		convertMapIntoListPolygon(3);// TODO: hardcoded
	}

	/**
	 * The map is converted into Box2D polygon. We must use a list of polygon since box2d
	 * allows a max of 8 vertices and only convex polygon.
	 * The map is split into parts and each part is a trapezoid.
	 * **/
	public void convertMapIntoListPolygon(int numberOfPoints) {
		// numberOfPoints skipped to build the rectangle
		List<PolygonShape> polygons = new ArrayList<PolygonShape>();
		Vector2 vertices[] = new Vector2[4];
		int temp;
		for (int j = 0; j < mapY.length; j += numberOfPoints) {
			if (j + numberOfPoints >= mapY.length) {
				temp = mapY.length - 1; // for the last step, avoid out of
										// bounds
			} else {
				temp = j + numberOfPoints;
			}
			vertices[0] = new Vector2(j, 0);
			vertices[1] = new Vector2(j, mapY[j]);
			vertices[2] = new Vector2(temp, mapY[temp]);
			vertices[3] = new Vector2(temp, 0);
			PolygonShape polygon = new PolygonShape();
			polygon.set(vertices);
			polygon.setAsBox((vertices[3].x-vertices[0].x), (vertices[2].y-vertices[3].y));//for collision detection
			
			polygons.add(polygon);

		}

		this.map.setPolygons(polygons);
	}
}