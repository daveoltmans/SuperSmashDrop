package com.example.epicfalldown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class StartMenu extends Activity {
	
	private Button startknop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startmenu);
		
		//maakt startknop en startknop listener aan moet in de onCreate staan
		startknop = (Button) findViewById(R.id.button1);
		startknop.setOnClickListener(new startknopListener());
		modes.add("Easy");
		modes.add("Medium");
		modes.add("Hard");
		mode = (Spinner) findViewById(R.id.spinnerMode);
		mode.setAdapter(new ModeSpinnerAdapter(this, R.layout.mode_item, modes));
	}
	
	/**
	 * dit is een button listener van de knop startknop
	 * @author Niek
	 *
	 */
	private class startknopListener implements View.OnClickListener {
		/**
		 * wanneer op de knop word gedrukt zal een ander scherm worden gestart
		 * View v is de view waarop geklikt was
		 */
		@Override
		public void onClick(View v) {
			//start spel door het spel scherm te openen
			
			//vergeet niet op Alt + Enter(option + return op een Mac) te drukken voor de imports
			//MainActivity.this moet worden vervangen door de naam van de mainactivity gevolgd door .this
			//De DisplayMessageActivity.class moet worden veranderd in de naam van de activity die je wilt opstarten gevolgd door .class bijv. naam.class
			//DisplayMessageActivity is eigenlijk de naam van de activity die je wilt optarten
			//de naam van de activity moet een overeenkomende naam met de bijbehorende layout hebben
			Intent intent = new Intent(StartMenu.this, MainActivity.class);
			startActivity(intent);
			}
	}
	
	//standaard aangemaakt geen aanpassingen aan verricht
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public static String getSelectedMode(){
		return selectedMode;
	}
}
