package com.example.assignment3;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ProcessActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new CountDownTimer(5000, 1000) {
			Toast toast;

			@Override
			public void onTick(long millisUntilFinished) {

				toast = Toast.makeText(getApplicationContext(),
						"Processing Credit Card..", Toast.LENGTH_SHORT);
				// toast.setGravity(Gravity.CENTER_HORIZONTAL, 0,0);
				toast.show();
			}

			@Override
			public void onFinish() {
				toast.cancel();
				setContentView(R.layout.activity_process);
				completeProcess();
			}
		}.start();
	}

	private void completeProcess() {
		final Button completeProcessButton = (Button) findViewById(R.id.completeProcess);
		completeProcessButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

}
