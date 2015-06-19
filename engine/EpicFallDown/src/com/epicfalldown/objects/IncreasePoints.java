package com.epicfalldown.objects;


public class IncreasePoints extends PowerUp{
	public static final String INCREASE_IMAGE = "mushroom";
	@Override
	public Type getObjectType() {
		// TODO Auto-generated method stub
		return super.getObjectType();
	}

	@Override
	public String getImageId() {
		// TODO Auto-generated method stub
		return INCREASE_IMAGE;
	}

	@Override
	public PowerType getPowerType() {
		// TODO Auto-generated method stub
		return PowerType.Increase;
	}
}
