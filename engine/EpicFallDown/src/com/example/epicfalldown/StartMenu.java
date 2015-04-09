package com.example.epicfalldown;

import java.util.ArrayList;

import com.epicfalldown.View.ModeSpinnerAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.app.AlertDialog.Builder;

public class StartMenu extends Activity {

	private Button startknop;

	private ArrayList<String> modes = new ArrayList<String>();

	private static String selectedMode;
	private Spinner mode;

	private ImageButton uitleg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startmenu);

		// maakt startknop en startknop listener aan moet in de onCreate staan
		startknop = (Button) findViewById(R.id.button1);
		startknop.setOnClickListener(new startknopListener());
		
		// add 3 modes aan het lijstje modes
		modes.add("Easy");
		modes.add("Medium");
		modes.add("Hard");

		Color color = new Color();
		
		//maakt een Spinner aan en zet de adapter
		mode = (Spinner) findViewById(R.id.spinnerMode);
		mode.setBackgroundColor(color.WHITE);
		mode.setAdapter(new ModeSpinnerAdapter(this, R.layout.mode_item, modes));

		uitleg = (ImageButton) findViewById(R.id.imageButton1);
		uitleg.setOnClickListener(new uitlegListener());
	}

	/**
	 * dit is een button listener van de knop startknop
	 * 
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
			selectedMode = "" + mode.getSelectedItem().toString();
			// start spel door het spel scherm te openen

			// vergeet niet op Alt + Enter(option + return op een Mac) te
			// drukken voor de imports
			// MainActivity.this moet worden vervangen door de naam van de
			// mainactivity gevolgd door .this
			// De DisplayMessageActivity.class moet worden veranderd in de naam
			// van de activity die je wilt opstarten gevolgd door .class bijv.
			// naam.class
			// DisplayMessageActivity is eigenlijk de naam van de activity die
			// je wilt optarten
			// de naam van de activity moet een overeenkomende naam met de
			// bijbehorende layout hebben
			Intent intent = new Intent(StartMenu.this, MainActivity.class);
			startActivity(intent);
		}
	}

	/**
	 * dit is een listener van uitleg imagebutton
	 * 
	 * @author Niek
	 */
	private class uitlegListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			Builder builder = new AlertDialog.Builder(StartMenu.this);
			AlertDialog dialog = builder.create();
			dialog.setTitle("Spel Uitleg");
			dialog.setMessage("Het doel van het spel is om zo ver mogelijjk naar beneden te gaan. "
					+ "Dat doe je door tussen de Balken door te gaan en geen Spikes te raken. "
					+ "In je val kom je allerlei Powerups tegen. "
					+ "Je gaat opzij door naar links en rechts te swipen. "
					+ "Wanneer je over de zijkant van het scherm gaat kom je aan de andere kant terecht. "
					+ "Door naar beneden te swipen kun je naar beneden gaan."
					+ " Succes");
			dialog.setCancelable(true);
			dialog.show();
		}
	}

	// standaard aangemaakt geen aanpassingen aan verricht
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

	public static String getSelectedMode() {
		return selectedMode;
	}
}
