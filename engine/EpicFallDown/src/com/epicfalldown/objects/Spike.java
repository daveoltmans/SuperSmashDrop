package com.epicfalldown.objects;

import com.example.epicfalldown.GameBoard;
import com.example.epicfalldown.GameObject;

public class Spike extends GameObject {
	public static final String SPIKE_IMAGE = "Leaf";
	
	/**
	 * Returns the ImageID for this object.
	 */
	@Override
	public String getImageId() {
		return SPIKE_IMAGE;
	}

	/**
	 * This (mandatory) method is not applicable for this type of GameObject and thus does nothing.
	 */
	@Override
	public void onTouched(GameBoard gameBoard) {}

	/**
	 * Gets the ObjectType for the Spike object
	 */
	@Override
	public Type getObjectType() {
		return Type.KillingObstacle;
	}

}

