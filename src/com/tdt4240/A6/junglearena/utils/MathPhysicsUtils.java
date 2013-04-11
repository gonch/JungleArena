package com.tdt4240.A6.junglearena.utils;

public class MathPhysicsUtils {

	public static float calculateXCircleInterpolationGivenY(float originX,float originY, float pointY, float radius, float screenX){
		// pointX = originX + radius * cos alpha
		//alpha = asin((PointY - originY)/radius)
		double arg = (pointY-originY)/radius;
//		if(arg = N)
		double alpha = Math.asin(arg);
//		if(originX>screenX){
//			alpha *= -1;
//		}
		float pointX = originX + radius * (float)Math.cos(alpha);
		
//		if(screenX <= originX){
//			pointX *= -1;
//		}
		return pointX;
	}
}
