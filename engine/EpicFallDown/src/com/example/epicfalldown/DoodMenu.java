package com.example.epicfalldown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoodMenu extends Activity{
	private Button restart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doodmenu);
		
		restart = (Button) findViewById(R.id.restartButton);
		restart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DoodMenu.this, MainActivity.class);
				startActivity(intent);				
			}
		});
		
		
		
	}
}
