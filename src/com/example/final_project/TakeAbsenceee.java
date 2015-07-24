package com.example.final_project;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class TakeAbsenceee extends ListActivity {
	
	String[]arr;
	String checked;
    String[] CourseSection;
    String []courseSestion;
	String[] CourseSymbol;
	int CourseIndex;
	int row_id1;
    int row_id2;
    Integer section;
    String coursename;
    ListView list;
    String [] names={"Take Absencce","Back"};
	int [] pic={R.drawable.edit,R.drawable.cancel};

	protected void onCreate(Bundle savedInstanceState)
	{
		
		
		
		   super.onCreate(savedInstanceState);
		   setContentView(R.layout.activity_take_absenceee);
	
		   
			ActionBar actionbar =getActionBar();
			actionbar.hide();
			
		   DBAdapter db = new DBAdapter(this);
	       db.open();
	       List values = db.getAllCourses();
	       db.close();
	       
	       
	       
	       ImageView Home=(ImageView)findViewById(R.id.imageView2);
		   ImageView Back=(ImageView)findViewById(R.id.imageView3);
		   
	
		   Home.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent I=new Intent(TakeAbsenceee.this,Home.class);
					startActivity(I);
					
				}
			});
			   
			   Back.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent I=new Intent(TakeAbsenceee.this,Home.class);
						startActivity(I);
						
					}
				});
	  

	    list=getListView();
	       list.setChoiceMode(1);        
	       list.setTextFilterEnabled(true);

	       arr=(String[]) values.toArray(new String [values.size()]);
	        
		     int[] c={Color.parseColor("#799679"), Color.parseColor("#85AC85")};
		     list.setAdapter(new Custum3(this,values,c));

		     
		     
	      //  ArrayAdapter adapter = new ArrayAdapter(this,
	   //     android.R.layout.simple_list_item_checked, values);
	    //    setListAdapter(adapter);
	        
	       
	}
	

	public void onListItemClick(
		    ListView parent, View v, int position, long id)
		    {		 
				        
	         checked=arr[position];
	         
	         CourseSection= checked.split(":");
	         CourseSymbol= checked.split("  ");
		        
	         CourseSection= CourseSection[1].split(" ");

	         
	         
	        // Toast.makeText(getBaseContext(), CourseSymbol[0], Toast.LENGTH_SHORT).show();
			    
			  if (CourseSection[1].length()==2)
			  {
			        row_id1= Character.getNumericValue(CourseSection[1].charAt(1));
			        section = Integer.valueOf(String.valueOf(row_id1));
			        section=  Integer.valueOf(CourseSection[1]);

			        
		
			  }
			  else if (CourseSection[1].length()==3)
			  {
			        row_id1= Character.getNumericValue(CourseSection[1].charAt(1));
				    row_id2= Character.getNumericValue(CourseSection[1].charAt(2));
			        section = Integer.valueOf(String.valueOf(row_id1) + String.valueOf(row_id2));

			  }
		        section=  Integer.valueOf(CourseSection[1]);
			  
			  
			  
			  coursename= CourseSymbol[0];
		      Toast.makeText(this,section+ CourseSymbol[0] , Toast.LENGTH_SHORT).show();
		     
		      DBAdapter db11 = new DBAdapter(this);
			  db11.open();
			  CourseIndex= db11.getcourseID(section, coursename);
			  db11.close();
		  openAlert(v,CourseIndex,position);

		    }
		
	

    private void openAlert(final View view,final int index, final int positopn) { 
    	   AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	    builder.setTitle("What do you want to do? ");
    	    builder.setAdapter(new Custum(getBaseContext(), names,pic), 
    	            new DialogInterface.OnClickListener() {
    	    	
    	                public void onClick(DialogInterface dialog, int which) {
    	                    // The 'which' argument contains the index position
    	                    // of the selected item
    	                    switch (which) {
    	                        case 0:

    	                        	Intent i3 =new Intent(TakeAbsenceee.this,Takeabsence.class);
    	            				 i3.putExtra("coursrid", index);
    	            				 startActivity(i3);
    	                            break;
    	                            
    	                        case 1:    	           		        
    	                        	isItemChecked(positopn);
    	                        
    	           	    	         break;
    	                    }
    	                }
    	            });
    	    builder.create().show();
    	
    }
    


	//////////////////////////

	private void openAlert2(final View view,final int index,final int position) {} 
	
	
	
	
	public void isItemChecked (int position)
	{
		 
		  
	if (list.isItemChecked(position)) 
		
		list.setItemChecked(position, false);
	else
		list.setItemChecked(position, true);

	}

	

}
