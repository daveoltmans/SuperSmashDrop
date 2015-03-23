package com.epicfalldown.objects;

import com.example.epicfalldown.GameBoard;
import com.example.epicfalldown.GameObject;

public class Balk extends GameObject{
	
	public static final String ROCK_IMAGE = "Rock";
	
	public Balk() {
		super();
	}

	@Override
	public String getImageId() {
		return ROCK_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		gameBoard.updateView();
		
	}
	
	/**
	 * Gets the ObjectType for the Spike object
	 */
	@Override
	public Type getObjectType() {
		return Type.Obstacle;
	}

}
