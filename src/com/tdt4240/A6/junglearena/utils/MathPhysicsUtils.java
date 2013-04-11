package com.tdt4240.A6.junglearena.utils;

public class MathPhysicsUtils {

	public static float calculateXCircleInterpolationGivenY(float originX,float originY, float pointY, float radius){
		// pointX = originX + radius * cos alpha
		//alpha = asin((PointY - originY)/radius)
		double alpha = Math.asin((pointY-originY)/radius);
		float pointX = originX + radius * (float)Math.cos(alpha);
		if(pointX <= originX){
			pointX *= -1;
		}
		return pointX;
	}
}
