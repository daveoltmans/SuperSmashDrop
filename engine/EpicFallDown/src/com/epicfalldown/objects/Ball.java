package com.epicfalldown.objects;

import com.example.epicfalldown.GameBoard;
import com.example.epicfalldown.GameObject;

public class Ball extends GameObject {
	
	public static final String BALL_IMAGE = "Ball";

	public Ball() {
		super();
	}
	

	@Override
	public String getImageId() {
		return BALL_IMAGE;
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

	/**
	 * Gets the powerType for the Ball object
	 */
	@Override
	public PowerType getPowerType() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
