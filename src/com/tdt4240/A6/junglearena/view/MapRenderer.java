package com.tdt4240.A6.junglearena.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.tdt4240.A6.junglearena.model.Map;

public class MapRenderer {

	private Map map;
	private SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;

	public MapRenderer(Map map, SpriteBatch spriteBatch) {
		this.map = map;
		this.spriteBatch = spriteBatch;
		this.shapeRenderer = new ShapeRenderer();
	}

	public void render() {
		String backgroundString = this.map.getBackground();
		Texture backgroundTexture = new Texture(Gdx.files.internal(backgroundString + ".jpeg"));
		spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		shapeRenderer.begin(ShapeType.Line);
		float[] mapY = map.getMapY();
		for(int i = 0; i < mapY.length; i++){
			shapeRenderer.setColor(1, 1, 0, 1);
			shapeRenderer.line(i, 0, i, mapY[i]);
//			shapeRenderer.rect(x, y, width, height);
//			shapeRenderer.circle(x, y, radius);
		}
		shapeRenderer.end();
	}
}
