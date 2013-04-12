package com.tdt4240.A6.junglearena.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MathPhysicsUtils {

	//arcsin
	public static Vector3 calculateXCircleInterpolationGivenY(float originX,
			float originY, float screenX, float pointY, float radius) {
		// pointX = originX + radius * cos alpha
		// alpha = asin((PointY - originY)/radius)
		double arg = (pointY - originY) / radius;
		double alpha = 0d;
		float pointX = 0f;
		if (arg == Double.NaN) {
			if (originY < pointY) {
				pointY = originY + radius;
				pointX = originX;
			} else {
				pointY = originY - radius;
			}
		} else {
			
			if (originX < screenX) {
				alpha = Math.asin(arg);
			} else {
				if (originY < pointY) {
					alpha = Math.PI / 2d + Math.asin(arg);
				} else {
					alpha = Math.asin(arg) - Math.PI / 2d;
				}
			}
			// if(originX>screenX){
			// alpha *= -1;
			// }
			pointX = originX + radius * (float) Math.cos(alpha);
		}
		// if(screenX <= originX){
		// pointX *= -1;
		// }
		return new Vector3(pointX, pointY, (float) Math.toDegrees((float)alpha));
	}

	private static double distance(Vector2 positionMate, Vector2 position2) {

		return Math.sqrt(Math.pow(positionMate.x - position2.x, 2)
				+ Math.pow(positionMate.y - position2.y, 2));
	}
	
	//atg without angle
	public static Vector2 calculateXCircleInterpolation(float originX,
			float originY, float screenX, float pointY, float radius) {
		// pointX = originX + radius * cos alpha
		// alpha = asin((PointY - originY)/radius)
		double distance = distance(new Vector2(originX,originY),new Vector2(screenX,pointY));
		
		double beta = Math.atan((distance(new Vector2(0,originY),new Vector2(0,pointY))/(distance(new Vector2(originX,0),new Vector2(screenX,0)))));
		if(screenX < originX){
			beta += Math.PI;
		}
		float pointX = originX + radius * (float) Math.cos(beta); 
		pointY = originY + radius * (float) Math.sin(beta);
		return new Vector2(pointX, pointY);
	}
	
	//atg with angle
	public static Vector3 calculateXCircleInterpolationWithAngle(float originX,
			float originY, float screenX, float pointY, float radius) {
		// pointX = originX + radius * cos alpha
		// alpha = asin((PointY - originY)/radius)
		double distance = distance(new Vector2(originX,originY),new Vector2(screenX,pointY));
		
		double beta = Math.atan((distance(new Vector2(0,originY),new Vector2(0,pointY))/(distance(new Vector2(originX,0),new Vector2(screenX,0)))));
		if(screenX < originX){
			beta += Math.PI;
		}
		float pointX = originX + radius * (float) Math.cos(beta); 
		pointY = originY + radius * (float) Math.sin(beta);
		return new Vector3(pointX, pointY,(float) Math.toDegrees((float)beta));
	}
}
