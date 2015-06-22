package com.epicfalldown.objects;


public class DecreasePoints extends PowerUp{
	public static final String DECREASE_IMAGE = "mushroom";
	@Override
	public Type getObjectType() {
		// TODO Auto-generated method stub
		return super.getObjectType();
	}

	@Override
	public String getImageId() {
		// TODO Auto-generated method stub
		return DECREASE_IMAGE;
	}

	@Override
	public PowerType getPowerType() {
		// TODO Auto-generated method stub
		return PowerType.Decrease;
	}
}
