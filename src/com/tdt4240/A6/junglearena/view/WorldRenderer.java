package com.tdt4240.A6.junglearena.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tdt4240.A6.junglearena.model.Character;
import com.tdt4240.A6.junglearena.model.Player;
import com.tdt4240.A6.junglearena.model.World;

public class WorldRenderer {

	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;

	private World world;
	private OrthographicCamera cam;

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
		this.ppuX = 10 / CAMERA_WIDTH;
		this.ppuY = 10 / CAMERA_WIDTH;
		spriteBatch = new SpriteBatch();
		this.shapeRenderer = new ShapeRenderer();
		loadTextures();
	}

	/*
	 * Converts from world coordinates to screen coordinates *
	 */
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
		drawTanks();
		spriteBatch.end();
	}

	private void drawTanks() {
		Player player1 = world.getPlayer1();
		Player player2 = world.getPlayer2();
		Character ch1 = player1.getCharacter();
		Character ch2 = player2.getCharacter();

		Sprite leftSprite = new Sprite(tankTexture); 
		leftSprite.flip(true, false);//now the second tank faces to the left
		spriteBatch.draw(tankTexture, ch1.getPosition().x, ch1.getPosition().y);
		spriteBatch.draw(leftSprite, ch2.getPosition().x, ch2.getPosition().y);
	}	

}