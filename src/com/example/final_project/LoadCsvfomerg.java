package com.example.final_project;



import java.util.ArrayList;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoadCsvfomerg extends Activity {
	static final int CSV_RESULT = 1;
	TextView filename ;
	Button okbt;
	ArrayList<String> FName;
	ArrayList<String> FID;
	ArrayList<String> FSN;
	Context con;
	ReadCVS obj;
	   DBAdapter db = new DBAdapter(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_load_csvfomerg);
		
		Intent i=getIntent();
	final int courseID=i.getIntExtra("coursrid", 0);
		
		filename=(TextView) findViewById(R.id.file_name);
		Button load =(Button) findViewById(R.id.loadCSVbt);
		okbt =(Button) findViewById(R.id.okbt);
		
		
		   ImageView Home=(ImageView)findViewById(R.id.imageView2);
		   ImageView Back=(ImageView)findViewById(R.id.imageView3);
		   
		   
		   Home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent I=new Intent(LoadCsvfomerg.this,Home.class);
				startActivity(I);
				
			}
		});
		   
		   Back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent I=new Intent(LoadCsvfomerg.this,Loadmoodformarge.class);
					startActivity(I);
					
				}
			});
		
		FName = new ArrayList<String>();
		FSN = new ArrayList<String>();
		FID = new ArrayList<String>();
		
		okbt.setEnabled(false);
	
				load.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						/*Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
						intent.setType("file/*");
						startActivityForResult(intent, RESULT_OK);*/
						 Intent intent = new Intent();
	                        intent.setType("text/comma-separated-values");
	                        intent.setAction(Intent.ACTION_GET_CONTENT);
	                        
							startActivityForResult(Intent.createChooser(intent,
	                                "Select CSV File"), CSV_RESULT);
						
				          		}
				});
				
					
				
				okbt.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						db.open();
					db.merg(courseID,FName,FID,FSN);
						db.close();
						
						 Intent intent1 = new Intent(LoadCsvfomerg.this,Mange_course.class);
	                        
				//Toast.makeText(getBaseContext(), FName.get(5), Toast.LENGTH_SHORT).show();	
						
						startActivity(intent1);
				
				
						//id1=id1.substring(1, id1.length()-1);

						/*
				        // 1. create an intent  
				        Intent intent = new Intent(LoadCsvfomerg.this,CourseDetails.class);
					 
				        // pass array 
				        intent.putStringArrayListExtra("name", FName);
				        intent.putStringArrayListExtra("iD", FID);
				        intent.putStringArrayListExtra("sn", FSN);

				        // 4. start the activity
				        startActivity(intent);
						*/
						//startActivity(new Intent (LoadCSV.this,CourseDetails.class));	
					}
				});	
   	}
	

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	 // TODO Auto-generated method stub
	 switch(requestCode){
	 case CSV_RESULT:
	  if(resultCode==RESULT_OK)
	  {
	   
	  String FilePath = data.getData().getPath();
	  
	   String FileName = data.getData().getLastPathSegment();
	   //int lastPos = FilePath.length() - FileName.length();
	   //String Folder = FilePath.substring(0, lastPos);
	   
	 
		   okbt.setEnabled(true);
		   filename.setText("File Name: \n" + FileName + "\n");
		   obj = new ReadCVS();
		   obj.run(FilePath,getBaseContext());
		   //filename.setText(obj..toString());
		   Toast.makeText(this,FilePath,Toast.LENGTH_SHORT ).show();
		  FName= obj.retFName();
		  FSN=obj.retSN();
		  FID=obj.retFID();
		  //Toast.makeText(this,FName.get(1),Toast.LENGTH_SHORT ).show();//test
	  }
	       
	   
	   }
	   

		}
	  
	 }  
	 
		
	  
		
	    

   	 


	


