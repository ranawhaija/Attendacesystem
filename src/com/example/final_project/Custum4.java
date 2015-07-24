package com.example.final_project;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Custum4 extends ArrayAdapter<String>{

	Context mcontext;
	DBAdapter db;
	List Sname1;
	int [] color;
	int CourseID;
	String StudentID;
	
	public Custum4(Context context, List name ,int [] imageIdd,int cousrid,String sid) {
		super(context, R.layout.activity_custum4,R.id.rowtxt,name);
		// TODO Auto-generated constructor stub
		
		this.mcontext=context;
		this.Sname1=name;
		this.color=imageIdd;
		this.CourseID=cousrid;
		this.StudentID=sid;
		db = new DBAdapter(mcontext);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row =inflater.inflate(R.layout.activity_custum4,parent,false);
		
		TextView txt=(TextView) row.findViewById(R.id.rowtxt);
		ImageView img=(ImageView) row.findViewById(R.id.imageView1);

		txt.setText((CharSequence) Sname1.get(position));
		

			
		db.open();
		
    
        	
		        if(db.wether(CourseID,(String) Sname1.get(position),StudentID))
		        {
		    		img.setImageResource(R.drawable.yespermissio);
			    	   Toast.makeText(mcontext,(String) Sname1.get(position) , Toast.LENGTH_SHORT).show();

		        }
		        else
		        {
		    		img.setImageResource(R.drawable.nopermission);
		
		        }

        
		   db.close();
		
	
	
		
		
		if(position % 2==0)
		txt.setBackgroundColor(color[0]);
		else
			txt.setBackgroundColor(color[1]);

		
		
		return row;
	}

}
	