package com.example.final_project;

import java.util.Calendar;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;



public class Takeabsence  extends ListActivity
{
	
	final Context context = this;
	Button start;
	String[]arr;
	String[] IDDs;
	String full_date;
	String checked;
	String StudentName;
	String SN;
	String StudentID;
    int count;
	int year;
	int month;
	int day;
	int course_ID;	
	char row_id1;
	DBAdapter db = new DBAdapter(this);

    ListView list;

	    
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_takeabsence);
			
		

		ActionBar actionbar =getActionBar();
		actionbar.hide();
		
	   ImageView Home=(ImageView)findViewById(R.id.imageView2);
	   ImageView Back=(ImageView)findViewById(R.id.imageView3);
	 
	   Home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent I=new Intent(Takeabsence.this,Home.class);
				startActivity(I);
				
			}
		});
		   
		   Back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent I=new Intent(Takeabsence.this,TakeAbsenceee.class);
					startActivity(I);
					
				}
			});
		
		 Intent i2=getIntent();
		 course_ID=i2.getIntExtra("coursrid", 0);

		
		 Calendar cal = Calendar.getInstance();
		 year = cal.get(Calendar.YEAR);
		 month=cal.get(Calendar.MONTH)+1;
		 day = cal.get(Calendar.DAY_OF_MONTH);
		 full_date=year+"/"+month+"/"+day ;
		
		 
		 DBAdapter db = new DBAdapter(this);
	     db.open();
	     List values = db.getStudents(course_ID);
	     List ids= db.getStudents__ID(course_ID);
	     count= db.getstudentnumbers(course_ID);
	     db.close();
	     
	     
	      list=getListView();

	     
	   //  list.setTextFilterEnabled(true); 
	    
	     arr=(String[]) values.toArray(new String [values.size()]);
	     IDDs=(String[]) ids.toArray(new String [ids.size()]);
	        
	 //    ArrayAdapter adapter = new ArrayAdapter(this,
	 //    android.R.layout.simple_list_item_1, values);
	 //    setListAdapter(adapter);
	     int[] c={Color.parseColor("#799679"), Color.parseColor("#85AC85")};
	     list.setAdapter(new Custum2(this,values,c));
	     
	   
	 
	     
		 start=(Button)findViewById(R.id.button1);
		 start.setOnClickListener(new View.OnClickListener() {
				@Override
			public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(Takeabsence.this,NamesofstudentAbsece.class);
					i.putExtra("IDDs", IDDs);
					i.putExtra("Names", arr);
					i.putExtra("full_date", full_date);
					i.putExtra("course_ID", course_ID);
					startActivity(i);				
					}
			});
	
	}
	


public View getView(int position, View convertView, ViewGroup parent)
{
	if(position%2==0)
	{
		convertView.setBackgroundColor(2);
	}
	else
	{
		convertView.setBackgroundColor(3);
	}
	
	 return convertView;
 

}


}






	
