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

public class Custum33 extends ArrayAdapter<String>{

	Context mcontext;
	List Sname1;
	int [] color;
	public Custum33(Context context, List name ,int [] imageIdd) {
		super(context, R.layout.activity_custum33,R.id.rowtxt1,name);
		// TODO Auto-generated constructor stub
		
		this.mcontext=context;
		this.Sname1=name;
		this.color=imageIdd;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row =inflater.inflate(R.layout.activity_custum33,parent,false);
		TextView txt=(TextView) row.findViewById(R.id.rowtxt1);

		txt.setText((CharSequence) Sname1.get(position));

	
		
		
		if(position % 2==0)
		txt.setBackgroundColor(color[0]);
		else
			txt.setBackgroundColor(color[1]);

		
		
		return row;
	}

}
	