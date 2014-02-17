package com.example.assignment3;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ReportActivity extends Activity {
	private DBOperations dBOperations;
	private TextView sqliteText;
	private TextView preferencesText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_data);
		
		sqliteText = (TextView) findViewById(R.id.sqliteTextView);
		preferencesText = (TextView) findViewById(R.id.preferencesTextView);
		
		showSQLiteData();
		showPreferencesData();
		
		
	}

	public void showSQLiteData(){
		dBOperations = new DBOperations(this);
		dBOperations.open();

		String userList = "";
		List<Person> values = dBOperations.getAllPersons();
		for (Person person : values) {
			userList = userList+person.firstName.toString()+"\n"+person.lastName.toString()+"\n";
			userList = userList+person.address.toString()+"\n"+person.creditCard.toString()+"\n";
			userList = userList+ "==========================\n";
		}
		
		sqliteText.setText(userList);
		sqliteText.setMovementMethod(new ScrollingMovementMethod());
	}
	
	public void showPreferencesData() {
		SharedPreferences preferences = this.getSharedPreferences("userData", Context.MODE_PRIVATE);
		
		String firstName = preferences.getString("firstName", "");
		String lastName = preferences.getString("lastName", "");
		String address = preferences.getString("address", "");
		String creditCard = preferences.getString("creditCard", "");
		
		//System.out.println(firstName+lastName+address+creditCard);
		
		String userData = firstName+"\n"+lastName+"\n"+address+"\n"+creditCard;
		
		preferencesText.setText(userData);
		preferencesText.setMovementMethod(new ScrollingMovementMethod());
	}
	
}
