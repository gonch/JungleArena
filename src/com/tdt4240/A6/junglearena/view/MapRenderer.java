package com.tdt4240.A6.junglearena.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.tdt4240.A6.junglearena.model.GameMap;

public class MapRenderer {

	private GameMap map;
	private SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;
	private Texture backgroundTexture;

	public MapRenderer(GameMap map) {
		this.map = map;
		this.spriteBatch = new SpriteBatch();
		this.shapeRenderer = new ShapeRenderer();
		String backgroundString = map.getBackground();	
		this.backgroundTexture = new Texture(Gdx.files.internal(backgroundString + ".jpeg"));
	}

	public void render(){
		this.spriteBatch.begin();

		spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		spriteBatch.end();
		shapeRenderer.begin(ShapeType.FilledRectangle);
		float[] mapY = map.getMapY();
		for(int i = 0; i < mapY.length; i+=3){
			shapeRenderer.setColor(1, 0, 0, 1);
//			shapeRenderer.line(i, 0, i, mapY[i]);
			shapeRenderer.filledRect(i, 0, 3, mapY[i]);
		}
		shapeRenderer.end();
//		 Gdx.graphics.setContinuousRendering(false); //for non continuous rendering

	}
}
