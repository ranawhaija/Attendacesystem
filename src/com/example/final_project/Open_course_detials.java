package com.example.final_project;

import java.util.List;


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
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class Open_course_detials  extends ListActivity {
	
	String[]arr;
	String checked;
    String[] CourseSection;
	String[] CourseSymbol;
	int CourseIndex;
	int row_id1;
    int row_id2;
    Integer section;
    String coursename;
    ListView list;
    String [] names={"Delete Course", "Take Absence", "Absent Students Information", "cancel "};
	int [] pic={R.drawable.delet,R.drawable.take,R.drawable.studentinfo,R.drawable.cancel};

	protected void onCreate(Bundle savedInstanceState)
	{
		   super.onCreate(savedInstanceState);
		   setContentView(R.layout.activity_open_course_detials);
	
		   DBAdapter db = new DBAdapter(this);
	       db.open();
	       List values = db.getAllCourses();
	       db.close();
	       

	    list=getListView();
	       list.setChoiceMode(1);        
	       list.setTextFilterEnabled(true);

	       arr=(String[]) values.toArray(new String [values.size()]);
	        
		     int[] c={Color.parseColor("#E6E6E5"), Color.parseColor("#EEEEEE")};
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
		        
			       
			  if (CourseSection[1].length()==2)
			  {
			        row_id1= Character.getNumericValue(CourseSection[1].charAt(1));
			        section = Integer.valueOf(String.valueOf(row_id1));
		
			  }
			  else if (CourseSection[1].length()==3)
			  {
			        row_id1= Character.getNumericValue(CourseSection[1].charAt(1));
				    row_id2= Character.getNumericValue(CourseSection[1].charAt(2));
			        section = Integer.valueOf(String.valueOf(row_id1) + String.valueOf(row_id2));
		
			  }
			  coursename= CourseSymbol[0];
		      Toast.makeText(this, section + CourseSection[1]+ CourseSymbol[0] , Toast.LENGTH_SHORT).show();
		       
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
    	                      	  openAlert2(view,index,positopn);

    	                            break;
    	                        case 1:
    	                        	Intent i2 =new Intent(Open_course_detials.this,Takeabsence.class);
    	            				 i2.putExtra("coursrid", index);
    	            				// Toast.makeText(getBaseContext(), index, Toast.LENGTH_SHORT).show();
    	            				 startActivity(i2);
    	           	    	         break;
    	                        case 2:
    	                        	

    	                        	Intent i3 =new Intent(Open_course_detials.this,InformationOfAbsent.class);
    	            				 i3.putExtra("coursrid", index);
    	            				 startActivity(i3);
    	            				 break;
    	                        case 3:
    	           		         isItemChecked(positopn);

    	                      break;
    	                    }
    	                }
    	            });
    	    builder.create().show();
    	
    }
    


	//////////////////////////

	private void openAlert2(final View view,final int index,final int position) { 

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Open_course_detials.this);
        
        alertDialogBuilder.setTitle("Delete the course"); 
        alertDialogBuilder.setMessage(Html.fromHtml("<b>Are you Sure you want To delete this course?  </b><br></br>  when you Delete it , all the data for students will delete and you can't restore it"));
        alertDialogBuilder.setNegativeButton("Yes I want ",new DialogInterface.OnClickListener() { 
              public void onClick(DialogInterface dialog,int id)
              
              { 
            		DBAdapter db1 = new DBAdapter(getBaseContext());
					 db1.open();
					 db1.deleteStudent(index);
					 db1.deletecourse(index);
					 db1.deletestudentinformation(index);
					 db1.close();
					 
					 Intent i=new Intent(Open_course_detials.this,MainActivity.class);
					 startActivity(i);

					 
              } 

            }); 


      alertDialogBuilder.setNeutralButton("No I don't want",new DialogInterface.OnClickListener() { 
                public void onClick(DialogInterface dialog,int id) {
      			
      		         isItemChecked(position);

                } 

            });   
      
         AlertDialog alertDialog = alertDialogBuilder.create(); 
         
         alertDialog.show(); 
    } 
	
	
	
	
	public void isItemChecked (int position)
	{
		 
		  
	if (list.isItemChecked(position)) 
		
		list.setItemChecked(position, false);
	else
		list.setItemChecked(position, true);

	}

	

}
