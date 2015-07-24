package com.example.final_project;

import java.io.File;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Mange_course extends ListActivity {
	
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
    String [] names={"Edit Students Information","Export","Marge","Delete Course","send Mail","Back"};
	int [] pic={R.drawable.edit,R.drawable.explor,R.drawable.take,R.drawable.delet,R.drawable.send,R.drawable.cancel};
	   DBAdapter db = new DBAdapter(this);

	protected void onCreate(Bundle savedInstanceState)
	{
		
		
		
		   super.onCreate(savedInstanceState);
		   setContentView(R.layout.activity_mange_course);
	
			ActionBar actionbar =getActionBar();
			actionbar.hide();
			
		   ImageView Home=(ImageView)findViewById(R.id.imageView2);
		   ImageView Back=(ImageView)findViewById(R.id.imageView3);
		   
		   
		   Home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent I=new Intent(Mange_course.this,Home.class);
				startActivity(I);
				
			}
		});
		   
		   Back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent I=new Intent(Mange_course.this,Home.class);
					startActivity(I);
					
				}
			});
		   
	       db.open();
	       List values = db.getAllCourses();
	       db.close();
	       
	       Button addbtn=(Button)findViewById(R.id.btn);
	       
	       addbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				   Intent i=new Intent(Mange_course.this,Loadmode.class);
	                startActivity(i);
				
				
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
		  //    Toast.makeText(this,section+ CourseSymbol[0] , Toast.LENGTH_SHORT).show();
		     
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

    	                        	Intent i3 =new Intent(Mange_course.this,InformationOfAbsent.class);
    	            				 i3.putExtra("coursrid", index);
    	            				 startActivity(i3);
    	                            break;
    	                            
    	                        case 1:    	           		        
    	                        	isItemChecked(positopn);
    	                        	Export test = new Export();
    	                         //	Toast.makeText(getBaseContext(), index+"", Toast.LENGTH_SHORT).show();

    	                        	test.ExportFun(getBaseContext(),index);
    	           	    	   
    	           	    	         break;
    	                        case 2:
    	                        	
    	                        	Intent i =new Intent(Mange_course.this,Loadmoodformarge.class);
   	            				 i.putExtra("coursrid", index);
   	            				 startActivity(i);
    	            				 break;

    	                        case 3:
        	                      	  openAlert2(view,index,positopn);

    	                      break;
    	                        case 4 :
    	                        	 
    	                            String FILE = Environment.getExternalStorageDirectory() + File.separator
    	                          	        + "Foldername";
    	                          	   Intent sendIntent = new Intent(android.content.Intent.ACTION_SEND);
    	                          	   sendIntent.setType("application/csv");
    	                          	   sendIntent.putExtra(android.content.Intent.EXTRA_EMAIL, "");
    	                          	   sendIntent.putExtra(Intent.EXTRA_SUBJECT, "");
    	                          	   sendIntent.putExtra(Intent.EXTRA_TEXT, "");
    	                         	  
    	                          	 db.open();
    	                        	      String FILENAME = db.getCourseNS(index)+".csv";
    	                        	     db.close();
    	                          	   
    	                                 File F = new File(Environment.getExternalStorageDirectory(), "Students Information/"+FILENAME);
    	                          	    Uri U = Uri.fromFile(F);
    	                          	    sendIntent.putExtra(Intent.EXTRA_STREAM, U);
    	                          	    startActivity(Intent.createChooser(sendIntent, "Send Mail"));
    	                     
    	                        	break;
    	                        	
    	                        case 5:
    	           		         isItemChecked(positopn);

    	                      break;
    	                    }
    	                }
    	            });
    	    builder.create().show();
    	
    }
    


	//////////////////////////

	private void openAlert2(final View view,final int index,final int position) { 

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Mange_course.this);
        
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
					 
					 Intent i=new Intent(Mange_course.this,Mange_course.class);
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
