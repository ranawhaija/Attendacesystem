package com.example.final_project;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Custum22 extends ArrayAdapter<String>{

	
	Context mcontext;
	List Sname1,IDs;
	int [] color;
	String day;
	int course;

	public Custum22(Context context, List name ,int [] imageIdd,String day,int courseID, List id) {
		super(context, R.layout.color,R.id.t1,name);
		// TODO Auto-generated constructor stub
		
		this.mcontext=context;
		this.Sname1=name;
		this.color=imageIdd;
		this.day=day;
		this.course=courseID;
		this.IDs=id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row =inflater.inflate(R.layout.color,parent,false);
		TextView txt=(TextView) row.findViewById(R.id.t1);

	    String fontPath="color/A_Nefel_Adeti.ttf";
	    Typeface type=Typeface.createFromAsset(mcontext.getAssets(), fontPath);
	    txt.setTypeface(type);
	    
	    
	    txt.setText(Html.fromHtml( (String) Sname1.get(position)));


				if(position % 2 ==0)
				txt.setBackgroundColor(color[0]);
				else
				txt.setBackgroundColor(color[1]);

					
			
		//txt.setText((CharSequence) Sname1.get(position));
		DBAdapter db = new DBAdapter(mcontext);
		db.open();
	int absentday=	db.getStudentDaysAbsentstotal(course, (String) IDs.get(position));
		db.close();
		
	
		if(day.equals("Su-Tu-Th")&&(absentday>=4 && absentday<=6))
		{
			// Toast.makeText(mcontext,  "fd", Toast.LENGTH_SHORT).show();
				txt.setBackgroundColor(color[2]);
		}
		else if (day.equals(day=="Mo-We") &&(absentday>=2 && absentday<=4))			

		{	
			txt.setBackgroundColor(color[2]);	
		}

		else if(day.equals(day=="All Days")&&(absentday>=3 && absentday<=5))
		{		
				txt.setBackgroundColor(color[2]);			
		}
		
		else if(day.equals("Su-Tu-Th")&&((absentday>6)))
		{	
				txt.setBackgroundColor(color[3]);	
		}
		
		else if (day.equals(day=="Mo-We")&&(absentday>4))
		{		
				txt.setBackgroundColor(color[3]);	
		}
	
		
		return row;
	}

}
	