package com.example.assignment3;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText firstName;
	EditText lastName;
	EditText address;
	EditText creditCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readData();
        processData();
        saveData();
        reportData();    
        closeActivity();
    }

    private void readData() {
    	firstName = (EditText) findViewById(R.id.firstNameValue);
    	lastName = (EditText) findViewById(R.id.lastNameValue);
    	address = (EditText) findViewById(R.id.addressValue);
    	creditCard = (EditText) findViewById(R.id.creditCardValue);
    	
    }
    
    private void processData(){
    	final Context context = this.getApplicationContext();
    	final Button processButton = (Button) findViewById(R.id.processData);
    	processButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent processIntent =  new Intent(context, ProcessActivity.class);
				startActivity(processIntent);
			}
		});
    } 
    
    private void saveData(){
    	final Context context = this.getApplicationContext();
    	final Button saveButton = (Button) findViewById(R.id.saveData);
    	
    	saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent saveIntent = new Intent(context, SaveActivity.class);
				Bundle extras = new Bundle();
				extras.putString("firstName", firstName.getText().toString());
				extras.putString("lastName", lastName.getText().toString());
				extras.putString("address", address.getText().toString());
				extras.putString("creditCard", creditCard.getText().toString());
				saveIntent.putExtras(extras);
				startActivity(saveIntent);
			}
		});
    }
    
    private void reportData(){
    	final Context context = this.getApplicationContext();
    	final Button reportButton = (Button) findViewById(R.id.reportData);
    	
    	reportButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent reportIntent  = new Intent(context, ReportActivity.class);
				
				DBOperations dBOperations = new DBOperations(context);
				dBOperations.open();
				List<Person> values = dBOperations.getAllPersons();
				
				SharedPreferences preferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
				
				if(values.isEmpty()&&preferences==null){
					Toast toast =Toast.makeText(getApplicationContext(), "Nothing to Display", Toast.LENGTH_LONG);				
			        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			        toast.show();
		        }
				else{
		        	startActivity(reportIntent);
		        }  
			}
		});
    }
    
    private void closeActivity() {
    	final Button closeButton = (Button) findViewById(R.id.closeButton);
    	closeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
