package com.epicfalldown.objects;

public class Clear extends PowerUp{
	

	public static final String CLEAR_IMAGE = "fireball";
	
	@Override
	public String getImageId() {
		// TODO Auto-generated method stub
		return CLEAR_IMAGE;
	}

	@Override
	public PowerType getPowerType() {
		// TODO Auto-generated method stub
		return PowerType.TotalAnihilation;
	}
}
