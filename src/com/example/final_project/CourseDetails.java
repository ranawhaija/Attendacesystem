package com.example.final_project;



import java.util.ArrayList;
import java.util.Calendar;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CourseDetails extends Activity {
	
	////////////intitalizing some gobal variable////////
		
	final Context context = this;
	//message msg1 =new message();
	getarrayindex i1=new getarrayindex();
	getarrayindex i2=new getarrayindex();
	
	int po1;
	int po2;
	
	String[] semesterArr;
	String[] dayArr;
	String[] courseSymbol ;
	String[] courseName1;

	TextView startDate;
	TextView endDate;
	
	String finalCourseSympol ;
	String finalCorseName ;
	
	//Dialog Form Dtart Date and End DAte
	DatePicker datePicker;
	
	String S_Full_Date;
	String E_Full_date;

	//initializing Stare Date and End Date to current Date
	int Syr, Smonth, Sday;
	int Eyr,Emonth,Eday;
	
	static final int S_DATE_DIALOG_ID1 = 0;
	static final int E_DATE_DIALOG_ID = 1;


	
	//////////////////////////////////////////////////
	/////////////////Start the Activity //////////////
	//////////////////////////////////////////////////

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_course_details);
		
		
		Intent intent = getIntent();

        final ArrayList<String> person = (ArrayList) intent.getSerializableExtra("name");
        final ArrayList<String> sn = (ArrayList) intent.getSerializableExtra("sn");
        final ArrayList<String> IDD = (ArrayList) intent.getSerializableExtra("iD");

     //   Toast.makeText(this, IDD.get(1), Toast.LENGTH_SHORT).show();
        
       
	///////////////////////////////////////////////////////
    ///////////Link Xml file with .Java file//////////////
   //////////////////////////////////////////////////////
	final EditText year= (EditText)findViewById(R.id.YeartxtID);
	final EditText section= (EditText)findViewById(R.id.sectiontxtID);
	final TextView errortxt = (TextView) findViewById(R.id.errortxt);
	Button nextbt = (Button) findViewById(R.id.finishbt);
	

    final Calendar cal = Calendar.getInstance();
    Syr=Eyr = cal.get(Calendar.YEAR);
    Smonth=Emonth = cal.get(Calendar.MONTH);
    Sday=Eday = cal.get(Calendar.DAY_OF_MONTH);
    String fyr= Integer.toString(Syr);
    year.setText(fyr);
	
		startDate= (TextView)findViewById(R.id.bStartDate);
		endDate= (TextView)findViewById(R.id.bendDate);
     //////////////////////////////////////////////////////////////////
	 ///////////////////Define Spener for Course symbol and course Name
     /////////////////////////////////////////////////////////////////// 
    courseName1=getResources().getStringArray(R.array.course_Name);
    	ArrayAdapter<String> adapter1 =new ArrayAdapter<String>(this,
			android.R.layout.simple_dropdown_item_1line,courseName1);
	final AutoCompleteTextView courseNameView =(AutoCompleteTextView)findViewById(R.id.courseNameDetail);
	courseNameView.setThreshold(1);
	courseNameView.setAdapter(adapter1);
	
	courseSymbol=getResources().getStringArray(R.array.course_sympol);	
	ArrayAdapter<String> adapter2 =new ArrayAdapter<String>(this,
			android.R.layout.simple_dropdown_item_1line,courseSymbol);
	final AutoCompleteTextView courseSympolView =(AutoCompleteTextView)findViewById(R.id.courseSympol);
	 courseSympolView.setThreshold(1);
	courseSympolView.setAdapter(adapter2);
	
	

    //////////////////////////////////////////////////////////////////
	 ///////////////////On Click Action///////////////////////////////
    /////////////////////////////////////////////////////////////////// 
	
	   courseNameView.setOnItemClickListener(new OnItemClickListener() {

	        @Override
	        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	                long arg3) {
	            String s1 = ((TextView)arg1).getText().toString();
	       po1= i1.getArrayIndex(courseName1, s1);
	       courseSympolView.setText(courseSymbol[po1]);
	       
	        }
	    });
	  
	   
	   courseSympolView.setOnItemClickListener(new OnItemClickListener() {

	        @Override
	        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	                long arg3) 
	        {
	            String s2 = ((TextView)arg1).getText().toString();
	       po2= i2.getArrayIndex(courseSymbol, s2);
	       courseNameView.setText(courseName1[po2]);
	        }
	    });
	
	   
	   
		semesterArr=getResources().getStringArray(R.array.semesterarray);
		final Spinner semesterSpinner =(Spinner) findViewById(R.id.semesSpinner);
		ArrayAdapter<String> adapter3 =new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, semesterArr);
		semesterSpinner.setAdapter(adapter3);
		
		dayArr=getResources().getStringArray(R.array.Daysarray);
		final Spinner daysSpinner =(Spinner) findViewById(R.id.daysSpinner);
		ArrayAdapter<String> adapter4 =new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,dayArr);
		daysSpinner.setAdapter(adapter4);
	
	
		
		////////////// show Calender ///////////////////
		 startDate.setOnClickListener(new View.OnClickListener() {
				
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(S_DATE_DIALOG_ID1);
				
				
			}
		});

		
		 
		 endDate.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					showDialog(E_DATE_DIALOG_ID);   
				}
			}); 
	
	nextbt.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			 finalCourseSympol = courseSympolView.getText().toString();
			finalCorseName = courseNameView.getText().toString();
			if(courseNameView.getText().toString().isEmpty() || courseSympolView.getText().toString().isEmpty() ||semesterSpinner.getSelectedItem().toString().isEmpty()||
			 section.getText().toString().isEmpty() || S_Full_Date.isEmpty() ||E_Full_date.isEmpty()||daysSpinner.getSelectedItem().toString().isEmpty())
			{

				//errortxt.setText("All fields are required\n");
		        AlertDialog alertDialog = new AlertDialog.Builder(
                        context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle("Incomplete information !!");
 
        // Setting Dialog Message
        alertDialog.setMessage("All fields are required !!");
 
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.warning);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                }
        });
 
        // Showing Alert Message
        alertDialog.show();
			
			}
			else
			{

				if( Eyr<Syr  )
				{
					showDialog();
					return;
				}
				else if(Eyr==Syr)
				{
					if(Emonth<Smonth)
						{
						showDialog();
						return;
						}
				
				    else if(Smonth==Emonth)
				{
					if(Eday<Sday)
						{
						showDialog();
						return;
						}
				}
				}

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
	 
				// set title
				alertDialogBuilder.setTitle("Course Details Confirmation");
	 
				// set dialog message
				alertDialogBuilder
				.setMessage(Html.fromHtml("<b>Your course details are:</b><br><br><b>Course Name:</b> "+courseNameView.getText()+
						"<br><b>Course Sympol:</b> "+courseSympolView.getText()+"<br><b>Semester:</b> "+semesterSpinner.getSelectedItem()
						+"<br><b>Section:</b> "+section.getText()+"<br><b>Start Date:</b> "+
						startDate.getText()+"<br><b>End Date:</b> "+endDate.getText()+"<br><b>Days:</b> "+daysSpinner.getSelectedItem()
						+"<br><br><b>Are you sure?</b><br><br>"))
					.setCancelable(false)
					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, close
							// current activity
							CourseDetails.this.finish();
							 int sec=Integer.parseInt(section.getText().toString());
							 ///////////////////////////////////////////////////////////
							 ///////////////////////////////////////////////////////////
							 /////////////////////////////////////////////////////////
							 ///////////////////////////////////////////////////
							 ////////////////////////////////////////////////////////
						        String id1,name,sn1;

							DBAdapter db = new DBAdapter(getBaseContext());
					        //------insert in to data base
					        db.open();
					        long id2 = db.insertContact(S_Full_Date
					        		,E_Full_date,daysSpinner.getSelectedItem().toString()
					        		,courseNameView.getText().toString(),courseSympolView.getText().toString(),
					        		Syr,sec,semesterSpinner.getSelectedItem().toString());
					        
					     int row=   db.lastRowInserted();
				    	//	Toast.makeText(getBaseContext(), row,Toast.LENGTH_LONG).show();	        	
  
					        for(int rows=1;rows<IDD.size();rows++)
					        {
					        	id1=IDD.get(rows);
					        	id1=id1.substring(1, id1.length()-1);
					        	
					        	name=person.get(rows);
					        	name=name.substring(1, name.length()-1);
					        	
					        	sn1=sn.get(rows);
					        	sn1=sn1.substring(1, sn1.length()-1);

					        	long id21=db.inset_student(sn1,id1, name, row);

					       }
					        		
					        if(id2==-1)
					        {
					    		Toast.makeText(getBaseContext(), "bad",Toast.LENGTH_LONG).show();	        	
					        }
					         db.close();

							
							
							Intent i=new Intent(CourseDetails.this,Mange_course.class);
							startActivity(i);
						}  })
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								dialog.cancel();
							}
						});
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
						
					}
				
			}
				
			
		});
		
	}
	




@Override
protected Dialog onCreateDialog(int id)
{
	switch (id) {
	
	
	case S_DATE_DIALOG_ID1:
		return new DatePickerDialog(
				this, mDateSetListener, Syr, Smonth, Sday);
	
	case E_DATE_DIALOG_ID:
		return new DatePickerDialog(
				this, EDateSetListener, Syr, Smonth, Sday);
		

	}
	return null;
}

private void showDialog()
{
    AlertDialog alertDialog = new AlertDialog.Builder(
            context).create();

// Setting Dialog Title
alertDialog.setTitle("Invalied Date");

// Setting Dialog Message
alertDialog.setMessage("End date is before Start date ! \nPlease correct it");

// Setting Icon to Dialog
alertDialog.setIcon(R.drawable.warning);

// Setting OK Button
alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
    public void onClick(DialogInterface dialog, int which) {
    // Write your code here to execute after dialog closed
    }
});

// Showing Alert Message
alertDialog.show();
}


private DatePickerDialog.OnDateSetListener mDateSetListener =
		new DatePickerDialog.OnDateSetListener()
{
	public void onDateSet(
			DatePicker view, int year, int monthOfYear, int dayOfMonth)
	{
		Syr = year;
		Smonth = monthOfYear+1;
		Sday = dayOfMonth;
		
		 S_Full_Date=Sday+"/"+Smonth+"/"+Syr;
		//Toast.makeText(getBaseContext(),
				//S_Full_Date	,
				//Toast.LENGTH_SHORT).show();
		 startDate.setText(S_Full_Date);
				
	}
};





private DatePickerDialog.OnDateSetListener EDateSetListener =
		new DatePickerDialog.OnDateSetListener()
{
	public void onDateSet(
			DatePicker view, int year, int monthOfYear, int dayOfMonth)
	{
		Eyr = year;
		Emonth = monthOfYear+1;
		Eday = dayOfMonth;
		
		E_Full_date=Eday+"/"+Emonth+"/"+Eyr;
		//Toast.makeText(getBaseContext(),S_Full_Date	,Toast.LENGTH_SHORT).show();
		endDate.setText(E_Full_date);
	}
};
}
