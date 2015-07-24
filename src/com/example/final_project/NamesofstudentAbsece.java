package com.example.final_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NamesofstudentAbsece extends Activity {

	int count1;
    int Global_counter=0;
	 int numberifdialog;
	 TextView Studentname;
		Button Attend;
		Button absent;
		String []IDs;
		String []name;
		String date;
		int pressed=0;
		DBAdapter db = new DBAdapter(this);
		 int course_ID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_namesofstudent_absece);
		
		
		 this.setFinishOnTouchOutside(false);		
		Intent i=getIntent();
		final String []IDs=i.getStringArrayExtra("IDDs");
		final String []name=i.getStringArrayExtra("Names");
		final String date=i.getStringExtra("full_date");
		 course_ID=i.getIntExtra("course_ID", 0);
		
		   ImageView undo=(ImageView)findViewById(R.id.imageView1);
		   
		   
		   
		   

		 Studentname=(TextView)findViewById(R.id.textView1);
		 absent=(Button)findViewById(R.id.button1);
		 Attend=(Button)findViewById(R.id.button2);

			Studentname.setText(Html.fromHtml(name[Global_counter]));
		
		Attend.setOnClickListener(new View.OnClickListener() {
		@Override					
			public void onClick(View v) {
				// TODO Auto-generated method stub
			if(pressed==0)
			{
            	Global_counter=Global_counter+1;
            	 if(Global_counter==name.length)
           	  {
            		 Intent i=new Intent(NamesofstudentAbsece.this,Sumary.class);
     				 i.putExtra("coursrid", course_ID);
     				 i.putExtra("date", date);               	
     				 startActivity(i);
           	  }
            	 else
         			Studentname.setText(Html.fromHtml(name[Global_counter]));
            } 
			else
			{
     			pressed=0;

				db.open();
				db.deleteParticularDate(date,course_ID,IDs[Global_counter]);
				db.close();
				Global_counter++;
     			Studentname.setText(Html.fromHtml(name[Global_counter]));
				
			}

			
		}
		{}
			
		});
		
		
		absent.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(pressed==0)
				{
					
				  Global_counter=Global_counter+1;
	          	  if(Global_counter==name.length)
	          	  {
	             	 db.open();  
	                   long iddd=db.insert_studentinfo(IDs[Global_counter-1], date, course_ID);
	                   db.close();
	                   if(iddd==-1)
	                   Toast.makeText(getBaseContext(), "false", Toast.LENGTH_SHORT).show();
	              
	          		  Intent i=new Intent(NamesofstudentAbsece.this,Sumary.class);
	   				 i.putExtra("coursrid", course_ID);
	   				 i.putExtra("date", "2015/7/3");
	          		  startActivity(i);				
				}

            	  else
            	  {
           			Studentname.setText(Html.fromHtml(name[Global_counter]));

            	 db.open();  
                  long iddd=db.insert_studentinfo(IDs[Global_counter-1], date, course_ID);
              	 Toast.makeText(getBaseContext(), date, Toast.LENGTH_SHORT).show();

                  db.close();
                  if(iddd==-1)
                  Toast.makeText(getBaseContext(), "folse", Toast.LENGTH_SHORT).show();
                
            	  }
				}
				else
				{
         			pressed=0;

	            	 db.open();  
	                  long iddd=db.insert_studentinfo(IDs[Global_counter], date, course_ID);
	              	 Toast.makeText(getBaseContext(), date, Toast.LENGTH_SHORT).show();

	                  db.close();
					  Global_counter=Global_counter+1;
	           			Studentname.setText(Html.fromHtml(name[Global_counter]));

					  
					
				}
	          	  
	          	  
			}
		
		});
	
		
		undo.setOnClickListener(new View.OnClickListener() {
			@Override					
				public void onClick(View v) {
					// TODO Auto-generated method stub
	            	Global_counter=Global_counter-1;
	    			Studentname.setText(Html.fromHtml(name[Global_counter]));
	    			pressed=1;
	            } 
				
			});
		
	
	}

	
    @Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
        moveTaskToBack(false);
		
	}
}
