package com.epicfalldown.objects;

import com.example.epicfalldown.GameBoard;
import com.example.epicfalldown.GameObject;

public class Balk extends GameObject{
	
	public static final String BALK_IMAGE = "balk";
	
	public Balk() {
		super();
	}

	@Override
	public String getImageId() {
		return BALK_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		gameBoard.updateView();
		
	}
	
	/**
	 * Gets the ObjectType for the Balk (bar) object
	 */
	@Override
	public Type getObjectType() {
		return Type.Obstacle;
	}

}
