package com.epicfalldown.objects;

import com.example.epicfalldown.GameBoard;
import com.example.epicfalldown.GameObject;

public class Balk extends GameObject {

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
	 * Gets the ObjectType for the Balk  object
	 */
	@Override
	public Type getObjectType() {
		return Type.Obstacle;
	}

	/**
	 * Gets the powerType for the Balk object
	 */
	@Override
	public PowerType getPowerType() {
		// TODO Auto-generated method stub
		return null;
	}

}
