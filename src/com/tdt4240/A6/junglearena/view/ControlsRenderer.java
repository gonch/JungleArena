package com.tdt4240.A6.junglearena.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.tdt4240.A6.junglearena.model.gameControls.ControlsLayer;
import com.tdt4240.A6.junglearena.model.gameControls.GameButton;

public class ControlsRenderer {

	private static final float CAMERA_WIDTH = Gdx.graphics.getWidth();
	private static final float CAMERA_HEIGHT = Gdx.graphics.getHeight();

	private ControlsLayer controls;
	private OrthographicCamera cam;

	private ShapeRenderer shapeRenderer;
	private Box2DDebugRenderer debugRenderer;	

	/** Textures **/
	
	private Texture targetTexture, powerBarTexture, fireButtonTexture;
	
	private SpriteBatch spriteBatch;
	private int width;
	private int height;
	private float ppuX; // pixels per unit on the X axis
	private float ppuY; // pixels per unit on the Y axis

	public ControlsRenderer(ControlsLayer controls) {
		this.controls = controls;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.cam.update();
		this.ppuX = 10 / CAMERA_WIDTH;
		this.ppuY = 10 / CAMERA_WIDTH;
		spriteBatch = new SpriteBatch();
		this.shapeRenderer = new ShapeRenderer();
		loadTextures();
		this.debugRenderer = new Box2DDebugRenderer();
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
		this.targetTexture = new Texture(Gdx.files.internal("crosshair.png"));
		this.powerBarTexture = new Texture(Gdx.files.internal("crosshair.png"));
		this.fireButtonTexture = new Texture(Gdx.files.internal("crosshair.png"));

	}

	public void render() {
		spriteBatch.begin();
		drawControls();
		spriteBatch.end();
	}
	
	private void drawControls(){
		GameButton target = this.controls.getTarget();
		GameButton powerBar = this.controls.getPowerBar();
		GameButton fireButton = this.controls.getFireButton();
		spriteBatch.draw(targetTexture, target.getPosition().x,target.getPosition().y,target.getSize().x,target.getSize().y);
		spriteBatch.draw(targetTexture, powerBar.getPosition().x,powerBar.getPosition().y,powerBar.getScaleX()*powerBar.getSize().x/100f,powerBar.getSize().y);
		spriteBatch.draw(fireButtonTexture,fireButton.getPosition().x,fireButton.getPosition().y,fireButton.getSize().x,fireButton.getSize().y);
	}

}
