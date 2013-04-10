/**
 * 
 */
package com.tdt4240.A6.junglearena.model;

public class Context {
	private boolean single;
	private String difficulty = "";
	private String nameChar1;
	private String nameChar2;
	private String namePlayer1;
	private String namePlayer2;
	
	private String environment;
	public Context(boolean single)
	{
		this.single = single;
	}
	public Context(boolean single, String difficulty, String nameChar1,
			String nameChar2, String environment) {
		
		this.single = single;
		this.difficulty = difficulty;
		this.nameChar1 = nameChar1;
		this.nameChar2 = nameChar2;
		this.environment = environment;
	}
	public boolean isSingle() {
		return single;
	}
	public void setSingle(boolean single) {
		this.single = single;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getNameChar1() {
		return nameChar1;
	}
	public void setNameChar1(String nameChar1) {
		this.nameChar1 = nameChar1;
	}
	public String getNameChar2() {
		return nameChar2;
	}
	public void setNameChar2(String nameChar2) {
		this.nameChar2 = nameChar2;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	/**
	 * @return the namePlayer1
	 */
	public String getNamePlayer1() {
		return namePlayer1;
	}
	/**
	 * @param namePlayer1 the namePlayer1 to set
	 */
	public void setNamePlayer1(String namePlayer1) {
		this.namePlayer1 = namePlayer1;
	}
	/**
	 * @return the namePlayer2
	 */
	public String getNamePlayer2() {
		return namePlayer2;
	}
	/**
	 * @param namePlayer2 the namePlayer2 to set
	 */
	public void setNamePlayer2(String namePlayer2) {
		this.namePlayer2 = namePlayer2;
	}
	
	

}
