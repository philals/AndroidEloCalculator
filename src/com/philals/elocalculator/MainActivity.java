package com.philals.elocalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

	Button AWin_button;
	Button BWin_button;

	EditText A_scoreText;
	EditText B_scoreText;
	EditText K_factorText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		A_scoreText = (EditText)findViewById(R.id.A_textBox);
		B_scoreText = (EditText)findViewById(R.id.B_textBox);
		K_factorText = (EditText)findViewById(R.id.kFactor_textBox);

		AWin_button = (Button) findViewById(R.id.AWin_button);
		AWin_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {  
				Calc("A");
			}
		});

		BWin_button = (Button) findViewById(R.id.BWin_button);
		BWin_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {  
				Calc("B");
			}
		});
	}


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

	public void Calc(String winner){
		
		int K_Value = Integer.parseInt(K_factorText.getText().toString());
		
		int A_Rating = Integer.parseInt(A_scoreText.getText().toString());
		int B_Rating = Integer.parseInt(B_scoreText.getText().toString());

		EloRatingSystem elo = new EloRatingSystem("s", K_Value);
		
		int new_A_rating;
		int new_B_rating;
		
		if(winner == "A"){
			new_A_rating = elo.getNewRating(A_Rating, B_Rating, 1);
			new_B_rating = elo.getNewRating(B_Rating, A_Rating, 2);
		}
		else{
			new_A_rating = elo.getNewRating(A_Rating, B_Rating, 2);
			new_B_rating = elo.getNewRating(B_Rating, A_Rating, 1);
		}
		
		String asd = Integer.toString(new_A_rating);
		
		A_scoreText.setText(asd);
		B_scoreText.setText(Integer.toString(new_B_rating));
	}
}
