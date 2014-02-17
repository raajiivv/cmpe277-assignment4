package com.example.assignment3;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SaveActivity extends Activity {
	private DBOperations dBOperations;
	
	private String firstName;
	private String lastName;
	private String address;
	private String creditCard;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		firstName = getIntent().getExtras().getString("firstName");
		lastName = getIntent().getExtras().getString("lastName");
		address = getIntent().getExtras().getString("address");
		creditCard = getIntent().getExtras().getString("creditCard");
		//System.out.println(firstName + lastName + address + creditCard);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save);
		
		Button sqliteButton = (Button) findViewById(R.id.sqliteButton);
		Button preferencesButton = (Button) findViewById(R.id.preferencesButton);
		
		dBOperations = new DBOperations(this);
		dBOperations.open();

		sqliteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addUserinSQLite();
			}
		});
		
		preferencesButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addUserinSharedPreferences();
			}
		});
	}
	
	
	public void addUserinSQLite () {
		dBOperations.addPerson(firstName, lastName,	address, creditCard);
	}
	
	public void addUserinSharedPreferences() {
		SharedPreferences userData = getSharedPreferences("userData", 0);
		SharedPreferences.Editor editor = userData.edit();
		editor.putString("firstName", firstName);
		editor.putString("lastName", lastName);
		editor.putString("address", address);
		editor.putString("creditCard", creditCard);
		editor.commit();
	}
}
