package com.epicfalldown.objects;

import com.epicfalldown.FallDownGame.GameFallDown;
import com.example.epicfalldown.GameBoard;
import com.example.epicfalldown.GameObject;

import android.util.Log;
/**
 * A leaf object. This is the object that wombats should try to eat. Leafs move
 * up a square when touched, unless a rock is in their path.
 * 
 * @author Paul de Groot
 * @author Jan Stroet
 */
public class Leaf extends GameObject {
	public static final String LEAF_IMAGE = "";

	/** Returns the ImageId of the image to show. */
	@Override
	public String getImageId() {
		return LEAF_IMAGE;
	}

	/** Called when the user touched this leaf. */
	@Override
	public void onTouched(GameBoard gameBoard) {
		Log.d(GameFallDown.TAG, "Touched leaf");

		// Leafs always move a square up
		int newPosX = getPositionX();
		int newPosY = getPositionY() - 1;

		// If new position is over the edge of the board, do nothing
		if (newPosY < 0) {
			return;
		}

		// Check if there is a object on the new position
		GameObject objectAtNewPos = gameBoard.getObject(newPosX, newPosY);
		if (objectAtNewPos != null) {

			// Leafs can't move through rocks or wombats
			if ((objectAtNewPos instanceof Balk) || 
			    (objectAtNewPos instanceof Ball)){
				return;
			}
		}

		// Move leaf to the new position and redraw the app
		gameBoard.moveObject(this, newPosX, newPosY);
		gameBoard.updateView();
	}
	
	/**
	 * Gets the ObjectType for the Spike object
	 */
	@Override
	public Type getObjectType() {
		return Type.PowerUp;
	}
}
