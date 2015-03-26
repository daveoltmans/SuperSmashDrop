package com.epicfalldown.objects;

import com.example.epicfalldown.GameBoard;
import com.example.epicfalldown.GameObject;

public class Ball extends GameObject {

	/**
	 * 
	 * private int x positie x van de ball private int y positie y van de ball
	 * 
	 * private boolean left checkt of de ball naar links private boolean right
	 * checkt of de ball naar rechts gaat private boolean up checkt of de ball
	 * naar boven gaat private boolean down checkt of de ball naar beneden gaat
	 * 
	 */
	private int x;
	private int y;

	private boolean up;
	private boolean down;
	private boolean isHit;
	
	public static final String WOMBAT_IMAGE = "Wombat";

	/**
	 * Public bal() constructor Ball class
	 * 
	 */

	public Ball() {
		isHit = false;
	}
	
	//Setters 
	
	/**
	 * getx()
	 * gety()
	 * @return returned de x,y positie
	 */
	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}
	public void setUp(boolean b) {
		up = b;
	}
	public void setDown(boolean b) {
		down = b;
	}
	public void setHit(boolean b){
		isHit = b;
	}
	
	//Getters

	public boolean getUp() {
		return up;
	}

	public boolean getDown() {
		return down;
	}
	
	public boolean isHit(){
		return isHit;
	}
	
	/** 
	 * Update()
	 * 
	 * Update Methode die booleans checkt
	 * 
	 */

	@Override
	public String getImageId() {
		return WOMBAT_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {}
	
	/**
	 * Gets the ObjectType for the Ball object
	 */
	@Override
	public Type getObjectType() {
		return Type.Ball;
	}

}
