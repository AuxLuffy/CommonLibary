package com.lenovo.service.basicpubliclibrary.stikyscrollview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;

public class StikyScrollviewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stiky_scrollview);
		
		
		
		Button mButton = (Button) findViewById(R.id.button);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(StikyScrollviewActivity.this, "clicked !", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		
	}


}
