package com.epicfalldown.objects;

import android.util.Log;

import com.epicfalldown.FallDownGame.GameFallDown;
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

	private boolean left;
	private boolean right;
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
	public void setLeft(boolean b) {
		left = b;
	}
	public void setRight(boolean b) {
		right = b;
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

	public boolean getLeft() {
		return left;
	}

	public boolean getRight() {
		return right;
	}

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
	public void onTouched(GameBoard gameBoard) {

		Log.d(GameFallDown.TAG, "Touched wombat");
		
		// Wombats always move a square to the right
		int newPosX = getPositionX() + 1;
		int newPosY = getPositionY();

		// If new position is over the edge of the board, do nothing
		if (newPosX >= 5) {
			newPosX = 0;
		}
		if (newPosX < 0){
			newPosX = gameBoard.getWidth();
		}

		// Check if there is a object on the new position
		GameObject objectAtNewPos = gameBoard.getObject(newPosX, newPosY);
		if (objectAtNewPos != null) {

			// Wombats can't move through rocks
			if (objectAtNewPos.getObjectType() == Type.Obstacle) {
				gameBoard.updateView();
				return;
			}

			// Caught a leaf? Score!
			if (objectAtNewPos.getObjectType() == Type.PowerUp) {
				gameBoard.removeObject(objectAtNewPos);
				((GameFallDown) gameBoard.getGame()).increaseScore(1);
			}
			
			if (objectAtNewPos.getObjectType() == Type.KillingObstacle){
				gameBoard.removeObject(gameBoard.getObject(getPositionX(), getPositionY()));
			}
		}

		// Move wombat to the new position and redraw the app
		gameBoard.moveObject(this, newPosX, newPosY);
		gameBoard.updateView();
	}
	
	/**
	 * Gets the ObjectType for the Spike object
	 */
	@Override
	public Type getObjectType() {
		return Type.Ball;
	}

}
