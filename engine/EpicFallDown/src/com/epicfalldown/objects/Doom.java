package com.epicfalldown.objects;

public class Doom extends PowerUp {


	public static final String GHOST_IMAGE = "ghost";
	@Override
	public Type getObjectType() {
		// TODO Auto-generated method stub
		return super.getObjectType();
	}

	@Override
	public String getImageId() {
		// TODO Auto-generated method stub
		return GHOST_IMAGE;
	}

	@Override
	public PowerType getPowerType() {
		// TODO Auto-generated method stub
		return PowerType.DoomMode;
	}

}
