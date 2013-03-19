package com.tdt4240.A6.junglearena.controller;

import com.badlogic.gdx.Gdx;
import com.tdt4240.A6.junglearena.model.Map;

/**
 * Java implementation of Perlin Noise algorithm taken by
 * http://freespace.virgin.net/hugo.elias/models/m_perlin.htm
 * */
public class MapController {
	private String background;
	private float[] mapY;
	private int screenWidth, screenHeight;
	private Map map;

	// TODO REMEMBER TO refactor names!!!

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * persistance is the amplitude for each frequency
	 */
	static float persistence = 0.03f;
	/**
	 * numberOfOctaves - number of noise functions added together
	 */
	static int numberOfOctaves = 6;
	/**
	 * hScale - custom defined horizontal scale
	 */
	static float hScale = 0.012f;
	/**
	 * vScale - custom defined vertical scale
	 */
	static float vScale = 0.35f;

	public MapController(String background) {
		this.background = background;
		this.screenWidth = Gdx.graphics.getWidth();
		this.screenHeight = Gdx.graphics.getHeight();
		this.mapY = new float[screenWidth];
		this.map = new Map(background, mapY);
	}

	/**
	 * Initial noise function creates a seemingly random number(noise) that can
	 * be repeated
	 */
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

	/**
	 * noise interpolation method used to generate a smoother output of the
	 * noise
	 */
	private static float interpolatedNoise(float x, float y) {
		int integer_X = (int) x;
		float fractional_X = x - integer_X;

		int integer_Y = (int) y;
		float fractional_Y = y - integer_Y;

		float v1 = smoothNoise(integer_X, integer_Y);
		float v2 = smoothNoise(integer_X + 1, integer_Y);
		float v3 = smoothNoise(integer_X, integer_Y + 1);
		float v4 = smoothNoise(integer_X + 1, integer_Y + 1);

		float i1 = interpolate(v1, v2, fractional_X);
		float i2 = interpolate(v3, v4, fractional_X);

		return interpolate(i1, i2, fractional_Y);
	}

	/**
	 * Cosine interpolation method used to smooth out the output curve
	 */
	private static float interpolate(float a, float b, float x) {
		// cosine
		float ft = x * 3.1415927f;
		float f = (float) ((1 - Math.cos(ft)) * 0.5f);
		return a * (1 - f) + b * f;

	}

	public static float perlinNoise_2D(float x, float z) {
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
	}
}
