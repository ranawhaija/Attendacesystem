package com.example.final_project;

import java.util.Calendar;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Sumary extends ListActivity {
	final Context context = this;
	String checked;
int row_id1;
	int count;
	int course_ID;
    ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sumary);

		   ImageView Home=(ImageView)findViewById(R.id.imageView2);
		   ImageView Back=(ImageView)findViewById(R.id.imageView3);

		   Home.setOnClickListener(new View.OnClickListener() {
			   ImageView Back=(ImageView)findViewById(R.id.imageView3);

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent I=new Intent(Sumary.this,Home.class);
				startActivity(I);
				
			}
		});
		   
		   Home.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent I=new Intent(Sumary.this,Home.class);
					startActivity(I);
					
				}
			});
			   
		   Back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(Sumary.this,TakeAbsenceee.class);
			    	startActivity(i);
					
				}
			});

		   
		   //System.exit(0);

		ActionBar actionbar =getActionBar();
		actionbar.hide();
		
		
		 TextView t=(TextView)findViewById(R.id.textView1);
		 Intent i2=getIntent();
		 course_ID=i2.getIntExtra("coursrid", 0);
		 String dateee=i2.getStringExtra("date");
		
	       ListView list=getListView();
	       list.setChoiceMode(1);        
	       list.setTextFilterEnabled(true);
	    
		 DBAdapter db = new DBAdapter(this);
	       db.open();
	       List values = db.getallabsentstudent(course_ID,dateee);
	  int count4=    db. getnumberabsentstudent(course_ID,dateee);;
	  	 
	  	 db.close();
	  	 t.setText("The Number of absent Students Today is : "+count4);
	  	 
	  	 
	        ArrayAdapter adapter = new ArrayAdapter(this,
	                android.R.layout.simple_list_item_checked, values);
	        setListAdapter(adapter);
	        
	        
	        list=getListView();
		       list.setChoiceMode(1);        
		       list.setTextFilterEnabled(true);

			     int[] c={Color.parseColor("#799679"), Color.parseColor("#85AC85")};

		     list.setAdapter(new Custum33(this,values,c));

	        
	    	//Toast.makeText(this, count, Toast.LENGTH_SHORT).show();
	      /*  summary.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

			        ListView list1=getListView();
			        count= list1.getCount();
			//	Toast.makeText(getBaseContext(), count, Toast.LENGTH_SHORT).show();
			        showDialog();
				}
			});
		
	
	}

private void showDialog()
{
    AlertDialog alertDialog = new AlertDialog.Builder(
            context).create();

// Setting Dialog Title
alertDialog.setTitle("ÚSummary for today");

// Setting Dialog Message
alertDialog.setMessage("The number of absent students is  : "+count);


// Setting OK Button
alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
    public void onClick(DialogInterface dialog, int which) {
    // Write your code here to execute after dialog closed
    	 Intent i=new Intent(Sumary.this,MainActivity.class);

		  startActivity(i);
    }
});

// Showing Alert Message
alertDialog.show();
}


*/



	}
    @Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
    	Intent i=new Intent(Sumary.this,TakeAbsenceee.class);
    	startActivity(i);
    	
	}

}
