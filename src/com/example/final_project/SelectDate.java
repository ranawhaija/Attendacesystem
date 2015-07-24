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
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
	
	public class SelectDate extends ListActivity {
		final Context context = this;
		String checked;
	   int row_id1;
		int count;
        String[] arr;
        ListView list;

		int coursrindex;
		String StudentID;
		String sname;
		String [] names={"Change Status of the Day ", "Delete the Date", "Back "};
		int [] pic={R.drawable.edit,R.drawable.delet,R.drawable.cancel};
	    DBAdapter db11 = new DBAdapter(this);

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_select_date);
	

			ActionBar actionbar =getActionBar();
			actionbar.hide();
			
			  list=getListView();
		       list.setChoiceMode(1);        
		       list.setTextFilterEnabled(true);
			
		  	 Intent i3=getIntent();
			 coursrindex=i3.getIntExtra("coursrindex", 0);
			 StudentID=i3.getStringExtra("ID");
			 sname=i3.getStringExtra("Sname");
			 
		       TextView tname=(TextView)findViewById(R.id.textView1);
		       

			   ImageView Home=(ImageView)findViewById(R.id.imageView2);
			   ImageView Back=(ImageView)findViewById(R.id.imageView3);

			   Home.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent I=new Intent(SelectDate.this,Home.class);
						startActivity(I);
						
					}
				});
			   
			   Back.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						Intent I =new Intent(SelectDate.this,InformationOfAbsent.class);
       				 I.putExtra("coursrid", coursrindex);
       				 startActivity(I);
       				 
					
						
					}
				});
		       
		
			 
			 tname.setText(sname);
		    
			 DBAdapter db = new DBAdapter(this);
		       db.open();
		       List values = db.missingDays(coursrindex,StudentID);
			//   db.insert_studentinfo2("2009873064","21/5/2015",3);

		  	 db.close();  
		  	 
		  	 
		  	 arr=(String[]) values.toArray(new String [values.size()]);
		  	 
			 int[] c={Color.parseColor("#799679"), Color.parseColor("#85AC85")};
			 list.setAdapter(new Custum4(this,values,c,coursrindex,StudentID));

		      
			 
		      
		}
	
		

		public void onListItemClick(
			    ListView parent, View v, int position, long id)
			    {		 
					        
		         checked=arr[position];

//db11.open();
	//		       if( db11.wether(coursrindex,(String) checked,StudentID))
		//	    	   Toast.makeText(this,"permission" , Toast.LENGTH_SHORT).show();
			    	   
//db11.close();




			      openAlert(coursrindex,checked,StudentID);
			      
             	/*Intent i1 =new Intent(SelectDate.this,EditNumberOfDay.class);
       		 i1.putExtra("coursrindex", coursrindex);
       		i1.putExtra("ID", StudentID);	                  			 
       		i1.putExtra("Sname", sname);
       		i1.putExtra("date", checked);
       		
       		 
       		 startActivity(i1);
         */
			      //Toast.makeText(this,checked , Toast.LENGTH_SHORT).show();
			       
			    //  DBAdapter db11 = new DBAdapter(this);
				 // db11.open();
				  //CourseIndex= db11.getcourseID(section, coursename);
				  //db11.close();
				  //openAlert(v,CourseIndex,position);

			    }
		
		
		
		

	    private void openAlert(final int index,final String date,final String ID) { 
	    	   AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	    builder.setTitle("What do you want to do? ");
	    	    builder.setAdapter(new Custum(getBaseContext(), names,pic), 
	    	            new DialogInterface.OnClickListener() {
	    	    	
	    	                public void onClick(DialogInterface dialog, int which) {
	    	                    // The 'which' argument contains the index position
	    	                    // of the selected item
	    	                    switch (which) {
	    	                        case 0:
	    	                        	
	    	                 
	    	                        	 openAlert2(index,ID,sname,date);
	    	                                break;
	    	                            
	    	                        case 1:
	    	                        	
	    	                        	 openAlert3(index,ID,sname,date);

	    	                        	

	    	           	    	         break;
	    	                        case 2:    	           		        
	    	                        
	    	           	    	         break;
	    	                    }
	    	                }
	    	            });
	    	    builder.create().show();
	    	
	    }
	    

	

			
		//////////////////////////////////


private void openAlert2(final int index,final String ID,final String Sname,final String date) { 

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SelectDate.this);
		
		alertDialogBuilder.setTitle("Update Statuse of the date"); 
		alertDialogBuilder.setMessage(Html.fromHtml("<b>Are you Sure you want To Update this Date?  </b>"));
		alertDialogBuilder.setNegativeButton("Confirm ",new DialogInterface.OnClickListener() { 
		public void onClick(DialogInterface dialog,int id)
		
		{ 
			

        	db11.open();
        if(db11.wether (index,date,ID))
        {
        	db11.updateAbsentDay2(date, ID, index);
		     //Toast.makeText(getBaseContext(),"permission" +date+ " "+ID , Toast.LENGTH_SHORT).show();

        }
        else
        {
        	db11.updateAbsentDay(date, ID, index);
		   //  Toast.makeText(getBaseContext(),"no permission" +date+ " "+ID , Toast.LENGTH_SHORT).show();

        }
        	db11.close();
        	
     	Intent i =new Intent(SelectDate.this,SelectDate.class);
			 i.putExtra("coursrindex", index);
			 i.putExtra("Sname", Sname);
			 i.putExtra("ID", ID);
		
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



		

/////////////////////////////////////////


private void openAlert3(final int index,final String ID,final String Sname,final String date) { 

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SelectDate.this);
		
		alertDialogBuilder.setTitle("Delete the Date"); 
        alertDialogBuilder.setMessage(Html.fromHtml("<b>Are you Sure you want To delete this Date?  </b><br></br>  when you Delete the date you can't restore it"));
		alertDialogBuilder.setNegativeButton("Confirm ",new DialogInterface.OnClickListener() { 
		public void onClick(DialogInterface dialog,int id)
		
		{ 
			db11.open();
        	db11.deleteParticularDate(date,index,ID);
        	db11.close();

     
     	Intent i =new Intent(SelectDate.this,SelectDate.class);
			 i.putExtra("coursrindex", index);
			 i.putExtra("Sname", Sname);
			 i.putExtra("ID", ID);
		
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
