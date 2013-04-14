package com.tdt4240.A6.junglearena.model.gameControls;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class TargetAccessor implements TweenAccessor<GameButton> {

	public static final int POSITION_X = 1;
	public static final int POSITION_Y = 2;
	public static final int POSITION_XY = 3;

	@Override
	public int getValues(GameButton target, int tweenType, float[] returnValues) {
		// TODO Auto-generated method stub
		switch (tweenType) {

		case POSITION_X:
			returnValues[0] = target.getPosition().x;
			return 1;
		case POSITION_Y:
			returnValues[0] = target.getPosition().y;
			return 1;
		case POSITION_XY:
			returnValues[0] = target.getPosition().x;
			returnValues[1] = target.getPosition().y;
			return 2;
		default:
			assert false;
			return -1;
		}

	}

	@Override
	public void setValues(GameButton target, int tweenType, float[] newValues) {
		float xx = target.getPosition().x;
		float yy = target.getPosition().y;
		switch (tweenType) {
		case POSITION_X:
			target.setPosition(new Vector2(newValues[0], yy));
			break;
		case POSITION_Y:
			target.setPosition(new Vector2(xx, newValues[0]));
			break;
		case POSITION_XY:
			target.setPosition(new Vector2(newValues[0], newValues[1]));
			break;
		default:
			assert false;
			break;
		}

	}

}
