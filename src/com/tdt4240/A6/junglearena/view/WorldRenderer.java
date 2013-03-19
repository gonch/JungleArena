package com.tdt4240.A6.junglearena.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.tdt4240.A6.junglearena.model.Character;
import com.tdt4240.A6.junglearena.model.Map;
import com.tdt4240.A6.junglearena.model.Player;
import com.tdt4240.A6.junglearena.model.World;

public class WorldRenderer {

	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;

	private World world;
	private OrthographicCamera cam;

	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();
	private ShapeRenderer shapeRenderer;

	/** Textures **/
	private Texture tankTexture;

	private SpriteBatch spriteBatch;
	private int width;
	private int height;
	private float ppuX; // pixels per unit on the X axis
	private float ppuY; // pixels per unit on the Y axis
	
	public WorldRenderer(World world) {
		this.world = world;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.cam.update();
		this.ppuX = 10/ CAMERA_WIDTH;
		this.ppuY = 10/ CAMERA_WIDTH;
		spriteBatch = new SpriteBatch();
		this.shapeRenderer = new ShapeRenderer();
		loadTextures();
	}

	/*
	 * Converts from world coordinates to screen coordinates
	 * **/
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = width / CAMERA_WIDTH;
		ppuY = height / CAMERA_HEIGHT;
	}

	private void loadTextures() {
		tankTexture = new Texture(Gdx.files.internal("tank.png"));	
	}

	public void render() {
		spriteBatch.begin();
		drawTank();
		drawMap();
		spriteBatch.end();
	}

	private void drawTank() {
		Player player1 = world.getPlayer1();
		Player player2 = world.getPlayer2();
		Character ch1 = player1.getCharacter();
		Character ch2 = player2.getCharacter();
//		spriteBatch.draw(tankTexture, ch1.getPosition().x * ppuX, ch1.getPosition().y * ppuY, ppuX,
//				ppuY);
//		spriteBatch.draw(tankTexture, 100, 100, CAMERA_WIDTH, CAMERA_HEIGHT); //scale the img to fit the width and height
		spriteBatch.draw(tankTexture,  ch1.getPosition().x,  ch1.getPosition().y);	
		spriteBatch.draw(tankTexture,  ch2.getPosition().x,  ch2.getPosition().y);	
	}
	
	private void drawMap(){
		Map map = this.world.getMap();
		String backgroundString = map.getBackground();
		Texture backgroundTexture = new Texture(Gdx.files.internal(backgroundString + ".jpeg"));
		spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		spriteBatch.draw(backgroundTexture, 0, 0);

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
