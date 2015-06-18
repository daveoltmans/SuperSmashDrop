package com.epicfalldown.objects;

import com.example.epicfalldown.GameObject.PowerType;
import com.example.epicfalldown.GameObject.Type;

public class Random extends PowerUp {
	public static final String QUESTION_IMAGE = "questionblock";
	@Override
	public Type getObjectType() {
		// TODO Auto-generated method stub
		return super.getObjectType();
	}

	@Override
	public String getImageId() {
		// TODO Auto-generated method stub
		return QUESTION_IMAGE;
	}

	@Override
	public PowerType getPowerType() {
		// TODO Auto-generated method stub
		return PowerType.RandomMode;
	}
}
