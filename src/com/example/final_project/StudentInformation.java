package com.example.final_project;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class StudentInformation extends ListActivity {
	String checked;
	String[]arr;
    String[] date;
	String iid;
	List items;
	int CourseIndex;
	String sn;
	DBAdapter db = new DBAdapter(this);
	ListView list;
	TextView name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_information);
		

		 Intent i4=getIntent();
		 CourseIndex=i4.getIntExtra("coursrindex", 0);
		 sn=i4.getStringExtra("SN");
		 
	
		 db.open();
		 iid = db.getStudentID(sn,CourseIndex);
         items =db.dateOfAbsent(iid,CourseIndex);
         String s= db.getStudentName(iid);
  //     Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        	      db.close(); 
        	      
        	      
       	       arr=(String[]) items.toArray(new String [items.size()]);

       
       TextView et=new TextView(this);
       et.setInputType(InputType.TYPE_CLASS_TEXT); 
       et.setText(s);
       
     

 	      list=getListView();
 	     list.setChoiceMode(1);        
 	     list.setTextFilterEnabled(true); 
 	  list.addHeaderView(et);

 	        
 	     ArrayAdapter adapter = new ArrayAdapter(this,
 	     android.R.layout.simple_list_item_checked, items);
 	     setListAdapter(adapter);
	
		
	}
	
	public void onListItemClick(
		    ListView parent, View v, int position, long id)
		    {		 
		  String day;
		       day= (String) list.getItemAtPosition(position);
		      Toast.makeText(this,  (CharSequence) list.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
		      
			  openAlert(v,day,position);

	
		     

		    }
	

    private void openAlert(final View view,final String day,final int position) { 
    	   AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	    builder.setTitle("Delete this day");
    	    
    	    builder.setItems(new CharSequence[]
    	            {"Delete Day","cancel "},
    	            new DialogInterface.OnClickListener() {
    	    	
    	                public void onClick(DialogInterface dialog, int which) {
    	                    // The 'which' argument contains the index position
    	                    // of the selected item
    	                    switch (which) {
    	                        case 0:
    	                  		  db.open();
    	                  		db.deleteParticularDate(day, CourseIndex, iid);
    	                  		db.close();
    	                  		
    	                  		items.remove(position-1);
    	                  		isItemChecked (position);

    	                            break;
    	                        case 1:
        	                  		isItemChecked (position);

    	           	    	         break;


    	                    }
    	                }
    	            });
    	    builder.create().show();
    	
    }
    

	public void isItemChecked (int position)
	{
		 
		  
	if (list.isItemChecked(position)) 
		
		list.setItemChecked(position, false);
	else
		list.setItemChecked(position, true);

	}

	

}
