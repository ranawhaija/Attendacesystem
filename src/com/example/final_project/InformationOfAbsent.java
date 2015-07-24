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
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class InformationOfAbsent extends ListActivity {

	final Context context = this;

	String[]arr;
	String[] IDDs;
	String full_date;
	String checked;
	String StudentName;
	String SN;
	String StudentID;
    int count;
	int Global_counter=0;
	int numberifdialog;
	int year;
	int month;
	int day;
	int course_ID;	
	char row_id1;
    int count1;
    TextView textView;
    String CourseName;
    String semesterName;
	DBAdapter db = new DBAdapter(this);
	ListView list;

	String [] names={"Edit student name", "Edit number of Absent days", "Delete", "Back "};
	int [] pic={R.drawable.delet,R.drawable.take,R.drawable.studentinfo,R.drawable.cancel};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information_of_absent);
		
		  ImageView Home=(ImageView)findViewById(R.id.imageView2);
		   ImageView Back=(ImageView)findViewById(R.id.imageView3);
		   

			ActionBar actionbar =getActionBar();
			actionbar.hide();
			
		   Home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent I=new Intent(InformationOfAbsent.this,Home.class);
				startActivity(I);
				
			}
		});
		   
		   Back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent I=new Intent(InformationOfAbsent.this,Mange_course.class);
					startActivity(I);
					
				}
			});
		   

		 Intent i3=getIntent();
		 course_ID=i3.getIntExtra("coursrid", 0);
		// Toast.makeText(this, course_ID+ "fgfd", Toast.LENGTH_SHORT).show();

	
		
		 
	     db.open();
	     List values = db.getStudents(course_ID);
	     List ids= db.getStudents__ID(course_ID);

	        CourseName= db.getCourseName(course_ID);
	    	
	    	semesterName=db.getSemesterName(course_ID);

	    	String d=db.getcourseday(course_ID);
	     
	     count= db.getstudentnumbers(course_ID);
	     db.close();
	     
	     textView=(TextView)findViewById(R.id.textView1);
	     textView.setTextColor(Color.WHITE);
		 textView.setText(CourseName +" Semester "+semesterName);
		 //textView.setMovementMethod(new ScrollingMovementMethod());

	      list=getListView();
	     list.setChoiceMode(1);        
	     list.setTextFilterEnabled(true); 
	    
	     int[] c={Color.parseColor("#799679"), Color.parseColor("#85AC85"), Color.parseColor("#8FB224") ,Color.parseColor("#8F0000")};
	     
		 Toast.makeText(this, d+ "", Toast.LENGTH_SHORT).show();

	     
	     
	     arr=(String[]) values.toArray(new String [values.size()]);
	     IDDs=(String[]) ids.toArray(new String [ids.size()]);
	     list.setAdapter(new Custum22(this,values,c,d,course_ID,ids));

//	     ArrayAdapter adapter = new ArrayAdapter(this,
	//     android.R.layout.simple_list_item_checked, values);
	   //  setListAdapter(adapter);
	//   
		       
	       
	}

public void onListItemClick(
	    ListView parent, View v, int position, long id)
	    {
	       
	        checked=arr[position];
	        String[] nameSn= checked.split(" ");
		    String []nameSN= checked.split("  ");
		    String []idd= checked.split("   ");

		  //   String newId = idd[2].substring(4,13);

				 //   Toast.makeText(this,  newId.substring(0,1), Toast.LENGTH_SHORT).show();

	        SN= nameSn[0];
	        StudentName=nameSN[2];
	        String testId;
	        
	        
	        
	    String newId = idd[2].substring(4,13);
        char bit0 = newId.charAt(0);
        char symbol='>';
        		  

				  
	        if(bit0==symbol)
	        {
				//  Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();

	        	
	        	idd[2]=	idd[2].substring(5,15);
	        	
	        }
	        else
	        {
	        	idd[2]=idd[2].substring(4,14);
	        	
	        }	

		//    Toast.makeText(this,  idd[2], Toast.LENGTH_SHORT).show();


	     //  db.open();
//	       Toast.makeText(this, StudentName, Toast.LENGTH_SHORT).show();

	     //  StudentID= db.getStudentNameById(StudentName.trim());
	      // StudentID= db.getStudentNameById2(StudentName);

	   //   count1 =db.getStudentDaysAbsents(course_ID,StudentID.trim());
	     //  db.close();
	    //   Toast.makeText(this, StudentID, Toast.LENGTH_SHORT).show();


	        
			openAlert(v,position,SN,course_ID,StudentName,idd[2]);					
		 
	    }


/////////////////////////////////////////////////////////////

private void openAlert(final View view,final int pos,
		final String sn,final int Cindex,final String sname,final String StudentID) { 

	   AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setTitle("More information");
	    builder.setAdapter(new Custum(getBaseContext(), names,pic), 
	            new DialogInterface.OnClickListener() {
	    	
	                public void onClick(DialogInterface dialog, int which) {
	                    // The 'which' argument contains the index position
	                    // of the selected item
	                    switch (which) {
	                        case 0:
	                        	
	                        	Intent i4 =new Intent(InformationOfAbsent.this,Edit_name.class);
	                  			 i4.putExtra("coursrindex", Cindex);
	                  			 i4.putExtra("SN", sn);	                  			 
	                  			 i4.putExtra("Sname", sname);
	                  			 
	                  			 startActivity(i4);
	                        	                        	
	                            break;
	                            
	                        case 1:

	                  	     // Toast.makeText(getBaseContext(), Cindex+"  "+StudentID+"  "+sname, Toast.LENGTH_SHORT).show();


	                        	Intent i1 =new Intent(InformationOfAbsent.this,SelectDate.class);
	                  		 i1.putExtra("coursrindex", Cindex);
	                  		i1.putExtra("ID", StudentID);	                  			 
	                  		i1.putExtra("Sname", sname);
	                  		 
	                  		 startActivity(i1);
	                        	                        
	                        	
	           	    	         break;
	           	    	         
	                        case 2:
	                        	
	                        	openAlert2(Cindex,StudentID ); 

	                        

	                        	


	                        	
	            				 break;
	                        case 3:

	                      break;
	                    }
	                }
	            });
	    builder.create().show();
	
}


/////////////////////////////////////////////

			
	
	public void isItemChecked (int position)
	{
		 
		  
	if (list.isItemChecked(position)) 
		
		list.setItemChecked(position, false);
	else
		list.setItemChecked(position, true);
	
	}
	
	
	
//////////////////////////

		private void openAlert2(final int Cindex,final String StudentID ) { 
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(InformationOfAbsent.this);
		
		alertDialogBuilder.setTitle("Delete Student "); 
        alertDialogBuilder.setMessage(Html.fromHtml("<b>Are you Sure you want To delete this Student?  </b><br></br>  when you Delete it , all the data of the student will delete and you can't restore it"));
		alertDialogBuilder.setNegativeButton("Confirm ",new DialogInterface.OnClickListener() { 
		public void onClick(DialogInterface dialog,int id)
		
		{
			db.open();
        	db.deleteStudent1(Cindex,StudentID);
        	db.close();
        	Intent i =new Intent(InformationOfAbsent.this,InformationOfAbsent.class);
  			 i.putExtra("coursrid", Cindex);
  			 startActivity(i);

        		
			
		} 
		
		}); 
		
		
		alertDialogBuilder.setNeutralButton("Cancel",new DialogInterface.OnClickListener() { 
		public void onClick(DialogInterface dialog,int id) {
		
		
		} 
		
		});   
		
		AlertDialog alertDialog = alertDialogBuilder.create(); 
		
		alertDialog.show(); 
		} 

			
			
			
		}
	


