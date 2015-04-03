package com.epicfalldown.View;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.epicfalldown.R;

public class ModeSpinnerAdapter extends ArrayAdapter<String> {
	private List<String> modes;
	
	public ModeSpinnerAdapter(Context context, int resource,
			List<String> objects) {
		super(context, resource, objects);
		modes = objects;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		
		return getCustomView(position, convertView, parent);
	}
	
	public View getCustomView(int position, View convertView, ViewGroup parent) { 
		LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		View mySpinner = inflater.inflate(R.layout.mode_item, parent, false); 
		TextView mode = (TextView) mySpinner.findViewById(R.id.modeTextView); 
		mode.setText("Difficulty: "+modes.get(position)); 
		mode.setTextSize(20);
		
		return mySpinner;
	}

	

	
}
