package com.tdt4240.A6.junglearena.model;

import com.badlogic.gdx.math.Vector3;

public class Physics {
	private double degree;
	private double power;
	private double gravity;
	private Vector3 wind;
	public Physics(double degree, double power, double gravity, Vector3 wind) {
		super();
		this.degree = degree;
		this.power = power;
		this.gravity = gravity;
		this.wind = wind;
	}
	public double getDegree() {
		return degree;
	}
	public void setDegree(double degree) {
		this.degree = degree;
	}
	public double getPower() {
		return power;
	}
	public void setPower(double power) {
		this.power = power;
	}
	public double getGravity() {
		return gravity;
	}
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	public Vector3 getWind() {
		return wind;
	}
	public void setWind(Vector3 wind) {
		this.wind = wind;
	}
}
