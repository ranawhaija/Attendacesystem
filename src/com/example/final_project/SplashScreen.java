package com.example.final_project;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		
		ActionBar actionbar =getActionBar();
		actionbar.hide();
			
		

	    Thread t=new Thread()
	    {

	        public void run()
	        {   

	            try {

	                sleep(2000);
	                finish();
	                Intent cv=new Intent(SplashScreen.this,Home.class);
	                startActivity(cv);
	            } 

	            catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    };
	    t.start();
			
		
	}
	
	
	
	public boolean onTouchEvent(MotionEvent event) 
	{

	    if (event.getAction() == MotionEvent.ACTION_UP) {
	       // ontouch = true;
	    	 Intent cv1=new Intent(SplashScreen.this,Home.class);
             startActivity(cv1);
             finish();
	        return true;
	    }
	    return false;

	};

}
