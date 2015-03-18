package com.epicfalldown.objects;

import com.example.epicfalldown.GameBoard;
import com.example.epicfalldown.GameObject;

public class Spike extends GameObject{
	
	public static final String ROCK_IMAGE = "Rock";
	
	public Spike() {
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

}
