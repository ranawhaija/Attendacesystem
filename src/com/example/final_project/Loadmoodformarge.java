package com.example.final_project;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class Loadmoodformarge extends Activity {
	Button login;
	Button LoadCSVfile;
	int  course_ID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loadmoodformarge);
		login = (Button) findViewById(R.id.lgnbtn);
		LoadCSVfile = (Button) findViewById(R.id.ldbtn);
		ActionBar actionbar =getActionBar();
		actionbar.hide();
		
	   ImageView Home=(ImageView)findViewById(R.id.imageView2);
	   ImageView Back=(ImageView)findViewById(R.id.imageView3);
	   
	   
	   Home.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent I=new Intent(Loadmoodformarge.this,Home.class);
			startActivity(I);
			
		}
	});
	   
	   Back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent I=new Intent(Loadmoodformarge.this,Mange_course.class);
				startActivity(I);
				
			}
		});
	   
		 Intent i3=getIntent();
		  course_ID=i3.getIntExtra("coursrid", 0);
		 
		LoadCSVfile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Loadmoodformarge.this,LoadCsvfomerg.class);
				i.putExtra("coursrid", course_ID);
				startActivity(i);
				
			}
		});
		
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i2 = new Intent(Loadmoodformarge.this,Loginformarge.class);
				startActivity(i2);
				
			}
		});
	}


}
