package com.example.final_project;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;




public class MainActivity extends Activity 
{
	

	//Declaration for public variable.
	Button openCourse;
	Button addCourse;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
 ///Delet all record from database

/*
        DBAdapter db = new DBAdapter(this);
        
        db.open();   
        db.del3();
    db.del2();
    db.del();
        db.close();

*/
        
        //link xml file with java file.
        openCourse = (Button) findViewById(R.id.OPENbtn);
		addCourse = (Button) findViewById(R.id.ADDbtn);
	
		
		//on click method.
		addCourse.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent i =new Intent(MainActivity.this,Load_csv.class);
				startActivity(i);
			}
		});
		

		openCourse.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v)
			{
			       Intent i2 =new Intent(MainActivity.this,Open_course_detials.class);
					startActivity(i2);
			}	
		});
		
		
		
		
		
	  
		
		

	       
    }
}
