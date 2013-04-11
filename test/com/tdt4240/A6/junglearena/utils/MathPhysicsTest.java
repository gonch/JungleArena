package com.tdt4240.A6.junglearena.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MathPhysicsTest {

	@Test
	public void testCalculateXCircleInterpolation() {
		Vector3 result = MathPhysicsUtils.calculateXCircleInterpolationGivenY(0, 0, 100, 100, 1);
		System.out.println(result);
		result = MathPhysicsUtils.calculateXCircleInterpolationGivenY(0, 0, 100, -100, 1);
		System.out.println(result);
		result = MathPhysicsUtils.calculateXCircleInterpolationGivenY(0, 0, -100, 100, 1);
		System.out.println(result);
		result = MathPhysicsUtils.calculateXCircleInterpolationGivenY(0, 0, -100, -100, 1);
		System.out.println(result);
	}

}
